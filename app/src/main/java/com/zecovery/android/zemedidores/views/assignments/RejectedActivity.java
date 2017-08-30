package com.zecovery.android.zemedidores.views.assignments;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.network.PostResult;
import com.zecovery.android.zemedidores.network.PostResultInterceptor;
import com.zecovery.android.zemedidores.network.RejectionCallback;
import com.zecovery.android.zemedidores.views.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zecovery.android.zemedidores.data.Constant.ANOTHER_REASON;
import static com.zecovery.android.zemedidores.data.Constant.EMPTY_PLACE;
import static com.zecovery.android.zemedidores.data.Constant.FOLDER_ZE_MEDIDORES;
import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT;
import static com.zecovery.android.zemedidores.data.Constant.RESIZE_PHOTO_PIXELS_PERCENTAGE;
import static com.zecovery.android.zemedidores.data.Constant.SELECT_OPTION;
import static com.zecovery.android.zemedidores.data.Constant.UNWELCOME;
import static com.zecovery.android.zemedidores.data.Constant.WRONG_DIRECTION;

public class RejectedActivity extends AppCompatActivity implements RejectionCallback, View.OnClickListener {


    private Button saveButton;
    private Spinner reasonsSpinner;
    private EditText anotherReasonEditText;
    private TextView hintTextView;
    private ImageButton rejectionPhoto;

    private MagicalCamera magicalCamera;
    private MagicalPermissions magicalPermissions;

    private int token;
    private String localPhotoName;

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
                    saveButton.setVisibility(View.VISIBLE);
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
    public void send() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void error() {
        Toast.makeText(this, R.string.rejection_error, Toast.LENGTH_SHORT).show();
    }

    private Intent callCamera() {
        String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        magicalPermissions = new MagicalPermissions(RejectedActivity.this, permissions);
        magicalCamera = new MagicalCamera(RejectedActivity.this, RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);
        magicalCamera.takePhoto();
        return new Intent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        magicalCamera.resultPhoto(requestCode, resultCode, data);

        try {
            String localPath = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(), "inspeccion_fallida", token + "/" + FOLDER_ZE_MEDIDORES, MagicalCamera.PNG, true);
            String localPathParts[] = localPath.split("/");
            localPhotoName = localPathParts[7];
            Log.d("TAG", "localPath: " + localPath);
            Log.d("TAG", "localPhotoName: " + localPhotoName);

        } catch (Exception e) {
            Log.d("TAG", "onActivityResult: " + e);
        }
    }

    private void post(int token, String reason, @Nullable String reasonText) {

        JSONArray array = new JSONArray();
        JSONObject values = new JSONObject();

        if (reasonText == null) {

        } else {

            try {
                values.put("id_inspeccion", token);
                values.put("razon", reason);
                values.put("razon_texto", reasonText);
                array.put(values);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.d("RejectedActivity", "post: " + array);

            PostResult postResult = new PostResultInterceptor().post();
            Call<JSONArray> call = postResult.post(array);
            call.enqueue(new Callback<JSONArray>() {
                @Override
                public void onResponse(Call<JSONArray> call, Response<JSONArray> response) {
                    Toast.makeText(RejectedActivity.this, "asdf", Toast.LENGTH_SHORT).show();
                    Log.d("RejectedActivity", "call: " + call);
                    Log.d("RejectedActivity", "response: " + response);
                    Log.d("RejectedActivity", "code: " + response.code());
                }
                @Override
                public void onFailure(Call<JSONArray> call, Throwable t) {
                    Log.d("RejectedActivity", "call: " + call);
                    Log.d("RejectedActivity", "Throwable: " + t);
                }
            });
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rejectionPhoto:
                callCamera();
                break;
            case R.id.saveButton:
                //token = getIntent().getIntExtra(ID_ASSIGNMENT, 0);
                String reason = reasonsSpinner.getSelectedItem().toString();
                if (reasonsSpinner.getSelectedItem().equals(ANOTHER_REASON)) {
                    String reasonText = anotherReasonEditText.getText().toString();
                    post(token, reason, reasonText);
                } else {
                    post(token, reason, null);
                }
                break;
        }
    }
}