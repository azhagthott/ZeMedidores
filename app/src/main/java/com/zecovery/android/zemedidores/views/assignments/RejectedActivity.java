package com.zecovery.android.zemedidores.views.assignments;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.google.firebase.crash.FirebaseCrash;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.network.PostResult;
import com.zecovery.android.zemedidores.network.RechazoCallback;
import com.zecovery.android.zemedidores.views.MainActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.zecovery.android.zemedidores.data.Constant.ANOTHER_REASON;
import static com.zecovery.android.zemedidores.data.Constant.EMPTY_PLACE;
import static com.zecovery.android.zemedidores.data.Constant.FOLDER_ZE_MEDIDORES;
import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT;
import static com.zecovery.android.zemedidores.data.Constant.RESIZE_PHOTO_PIXELS_PERCENTAGE;
import static com.zecovery.android.zemedidores.data.Constant.SELECT_OPTION;
import static com.zecovery.android.zemedidores.data.Constant.UNWELCOME;
import static com.zecovery.android.zemedidores.data.Constant.URL_BASE_DESA;
import static com.zecovery.android.zemedidores.data.Constant.WRONG_DIRECTION;

public class RejectedActivity extends AppCompatActivity implements RechazoCallback, View.OnClickListener {

    private Button saveButton;
    private Spinner reasonsSpinner;
    private EditText anotherReasonEditText;
    private TextView hintTextView;
    private ImageButton rejectionPhoto;

    private MagicalCamera magicalCamera;

    private int token;
    private String photoName;
    private String localPath;

    private LocalDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejected);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        saveButton = findViewById(R.id.saveButton);
        reasonsSpinner = findViewById(R.id.reasonsSpinner);
        anotherReasonEditText = findViewById(R.id.anotherReasonEditText);
        hintTextView = findViewById(R.id.hintTextView);
        rejectionPhoto = findViewById(R.id.rejectionPhoto);

        saveButton.setOnClickListener(this);
        rejectionPhoto.setOnClickListener(this);

        db = new LocalDatabase(this);

        List<String> reasons = new ArrayList<>();
        reasons.add(SELECT_OPTION);
        reasons.add(EMPTY_PLACE);
        reasons.add(UNWELCOME);
        reasons.add(WRONG_DIRECTION);
        reasons.add(ANOTHER_REASON);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, reasons);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reasonsSpinner.setAdapter(adapter);
        reasonsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (reasonsSpinner.getSelectedItem().equals(SELECT_OPTION)) {
                    saveButton.setVisibility(View.GONE);
                    anotherReasonEditText.setVisibility(View.GONE);
                    hintTextView.setVisibility(View.GONE);
                    rejectionPhoto.setVisibility(View.GONE);
                } else {
                    rejectionPhoto.setVisibility(View.VISIBLE);
                    if (reasonsSpinner.getSelectedItem().equals(ANOTHER_REASON)) {
                        anotherReasonEditText.setVisibility(View.VISIBLE);
                        hintTextView.setVisibility(View.VISIBLE);
                        rejectionPhoto.setVisibility(View.VISIBLE);
                    } else {
                        anotherReasonEditText.setVisibility(View.GONE);
                        hintTextView.setVisibility(View.GONE);
                        rejectionPhoto.setVisibility(View.GONE);
                        rejectionPhoto.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        if (getIntent().getExtras() != null) {
            token = getIntent().getIntExtra(ID_ASSIGNMENT, 0);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void envia() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void error() {
        Toast.makeText(this, R.string.rejection_error, Toast.LENGTH_SHORT).show();
    }

    private Intent callCamera() {
        String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        MagicalPermissions magicalPermissions = new MagicalPermissions(RejectedActivity.this, permissions);
        magicalCamera = new MagicalCamera(RejectedActivity.this, RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);
        magicalCamera.takePhoto();
        return new Intent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        saveButton.setVisibility(View.VISIBLE);
        magicalCamera.resultPhoto(requestCode, resultCode, data);

        try {
            localPath = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(), "inspeccion_fallida", token + "/" + FOLDER_ZE_MEDIDORES, MagicalCamera.PNG, true);
            String localPathParts[] = localPath.split("/");
            photoName = localPathParts[7];
        } catch (Exception e) {
            FirebaseCrash.log("Exception: " + e);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rejectionPhoto:
                callCamera();
                break;
            case R.id.saveButton:
                String reason = reasonsSpinner.getSelectedItem().toString();
                if (reasonsSpinner.getSelectedItem().equals(ANOTHER_REASON)) {
                    String reasonText = anotherReasonEditText.getText().toString();
                    post(token, reason, reasonText, localPath);
                } else {
                    post(token, reason, null, localPath);
                }
                break;
        }
    }

    private void post(int token, String reason, @Nullable String reasonText, String localPath) {

        File file = new File(localPath);
        String status = "rechazo";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), status);

        RequestBody lat = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(db.getCurrentDbLocation().latitude));
        RequestBody lng = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(db.getCurrentDbLocation().longitude));

        Calendar rightNow = Calendar.getInstance();
        int fechaAcutal = (int) (rightNow.getTimeInMillis() / 1000);
        RequestBody fecha = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(fechaAcutal));


        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("foto_rechazo", photoName, requestFile);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_BASE_DESA).build();
        PostResult service = retrofit.create(PostResult.class);

        RequestBody idInspeccionBody = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(token));
        RequestBody reasonBody = RequestBody.create(MediaType.parse("multipart/form-data"), reason);

        if (reasonText == null) {

            Call<ResponseBody> call = service.postInspectionRejected(description, idInspeccionBody, reasonBody, null, lat, lng, fecha, body);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    envia();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    FirebaseCrash.log("Exception: " + t.getMessage());
                    error();
                }
            });

        } else {
            RequestBody reasonTextBody = RequestBody.create(MediaType.parse("multipart/form-data"), reasonText);

            Call<ResponseBody> call = service.postInspectionRejected(description, idInspeccionBody, reasonBody, reasonTextBody, lat, lng, fecha, body);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    envia();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    FirebaseCrash.log("Exception: " + t.getMessage());
                    error();
                }
            });
        }
    }
}