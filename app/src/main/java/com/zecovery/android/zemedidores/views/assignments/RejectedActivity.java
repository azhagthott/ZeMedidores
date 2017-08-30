package com.zecovery.android.zemedidores.views.assignments;

import android.content.Intent;
import android.os.Bundle;
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
import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT;
import static com.zecovery.android.zemedidores.data.Constant.SELECT_OPTION;
import static com.zecovery.android.zemedidores.data.Constant.UNWELCOME;
import static com.zecovery.android.zemedidores.data.Constant.WRONG_DIRECTION;

public class RejectedActivity extends AppCompatActivity implements RejectionCallback, View.OnClickListener {

    private Spinner reasonsSpinner;
    private Button saveButton;
    private EditText anotherReasonEditText;
    private TextView hintTextView;
    private ImageButton rejectionPhoto;

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

    @Override
    public void onClick(View v) {
        int token = getIntent().getIntExtra(ID_ASSIGNMENT, 0);
        String reason = reasonsSpinner.getSelectedItem().toString();

        if (reasonsSpinner.getSelectedItem().equals(ANOTHER_REASON)) {
            String reasonText = anotherReasonEditText.getText().toString();

            JSONArray array = new JSONArray();
            JSONObject values = new JSONObject();
            try {
                values.put("razon", reasonText);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            PostResult postResult = new PostResultInterceptor().post();
            Call<JSONArray> call = postResult.post(array);
            call.enqueue(new Callback<JSONArray>() {
                @Override
                public void onResponse(Call<JSONArray> call, Response<JSONArray> response) {
                    Toast.makeText(RejectedActivity.this, "asdf", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<JSONArray> call, Throwable t) {

                }
            });


        } else {

        }
    }
}