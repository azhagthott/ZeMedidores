package com.zecovery.android.zemedidores.views.assignments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.zecovery.android.zemedidores.R;

import static com.zecovery.android.zemedidores.data.Constant.ADDRESS;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_TYPE;
import static com.zecovery.android.zemedidores.data.Constant.BUSINESS;
import static com.zecovery.android.zemedidores.data.Constant.COMMERCIAL;
import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT;
import static com.zecovery.android.zemedidores.data.Constant.LATITUDE;
import static com.zecovery.android.zemedidores.data.Constant.LONGITUDE;
import static com.zecovery.android.zemedidores.data.Constant.RESIDENTIAL;
import static com.zecovery.android.zemedidores.data.Constant.TAG;

public class AskActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    private static final int LOCATION_REQUEST_CODE = 999;
    private GoogleMap map;

    private TextView addressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button affirmative = findViewById(R.id.affirmative);
        Button negative = findViewById(R.id.negative);

        affirmative.setOnClickListener(this);
        negative.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        addressTextView = findViewById(R.id.addressTextView);
        if (getIntent().getExtras() != null) {
            addressTextView.setText(getIntent().getStringExtra(ADDRESS));
            Log.d(TAG, "onResume: " + getIntent().getStringExtra(ID_ASSIGNMENT));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.affirmative:
                assignmentType();
                break;
            case R.id.negative:
                Intent intent = new Intent(AskActivity.this, RejectedActivity.class);
                intent.putExtra(ID_ASSIGNMENT, getIntent().getStringExtra(ID_ASSIGNMENT));
                startActivity(intent);
                break;
        }
    }

    private void assignmentType() {

        if (getIntent().getExtras() != null) {

            Intent intent = new Intent(AskActivity.this, AssignmentActivity.class);

            switch (getIntent().getStringExtra(ASSIGNMENT_TYPE)) {

                case RESIDENTIAL:
                    intent.putExtra(ASSIGNMENT_TYPE, RESIDENTIAL);
                    intent.putExtra(ID_ASSIGNMENT, getIntent().getStringExtra(ID_ASSIGNMENT));
                    startActivity(intent);
                    break;
                case COMMERCIAL:
                    intent.putExtra(ASSIGNMENT_TYPE, COMMERCIAL);
                    intent.putExtra(ID_ASSIGNMENT, getIntent().getStringExtra(ID_ASSIGNMENT));
                    startActivity(intent);
                    break;
                case BUSINESS:
                    intent.putExtra(ASSIGNMENT_TYPE, BUSINESS);
                    intent.putExtra(ID_ASSIGNMENT, getIntent().getStringExtra(ID_ASSIGNMENT));
                    startActivity(intent);
                    break;
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;

        map.setMinZoomPreference(18.0f);
        map.getUiSettings().setZoomControlsEnabled(true);

        if (getIntent().getExtras() != null) {

            LatLng assignmentLocation = new LatLng(
                    getIntent().getDoubleExtra(LATITUDE, 0),
                    getIntent().getDoubleExtra(LONGITUDE, 0));

            map.addMarker(
                    new MarkerOptions().position(assignmentLocation));
            map.moveCamera(CameraUpdateFactory.newLatLng(assignmentLocation));
        }
    }
}