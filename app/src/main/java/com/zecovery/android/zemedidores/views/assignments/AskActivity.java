package com.zecovery.android.zemedidores.views.assignments;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.crash.FirebaseCrash;
import com.zecovery.android.zemedidores.R;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static com.zecovery.android.zemedidores.data.Constant.ADDRESS;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_TYPE;
import static com.zecovery.android.zemedidores.data.Constant.BUSINESS;
import static com.zecovery.android.zemedidores.data.Constant.COMMERCIAL;
import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT;
import static com.zecovery.android.zemedidores.data.Constant.LATITUDE;
import static com.zecovery.android.zemedidores.data.Constant.LONGITUDE;
import static com.zecovery.android.zemedidores.data.Constant.RESIDENTIAL;

public class AskActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback,
        EasyPermissions.PermissionCallbacks {

    private static final int LOCATION_REQUEST_CODE = 999;

    private static final String[] LOCATION = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};

    private static final int RC_LOCATION = 124;

    private GoogleMap map;
    private FusedLocationProviderClient mFusedLocationClient;


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

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


    }

    private boolean hasLocationPermissions() {
        return EasyPermissions.hasPermissions(this, LOCATION);
    }

    @AfterPermissionGranted(RC_LOCATION)
    public void locationTask() {
        if (hasLocationPermissions()) {
            // Have permissions, do the thing!
            Toast.makeText(this, "TODO: Location and Contacts things", Toast.LENGTH_LONG).show();

            mFusedLocationClient.getLastLocation()

        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.permissions_location),
                    RC_LOCATION_PERM,
                    LOCATION_AND_CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        FirebaseCrash.log("onPermissionsDenied: " + requestCode + ":" + perms.size());
        Log.d("LOG", "onPermissionsDenied:" + requestCode + ":" + perms.size());
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            String yes = getString(R.string.permissions_yes);
            String no = getString(R.string.permissions_no);

            // Do something after user returned from app settings screen, like showing a Toast.
            Toast.makeText(this, "asdf", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        TextView addressTextView = findViewById(R.id.addressTextView);

        if (getIntent().getExtras() != null) {
            addressTextView.setText(getIntent().getStringExtra(ADDRESS));
            Log.d("AskActivity", "onResume: " + getIntent().getIntExtra(ID_ASSIGNMENT, 0));
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
                intent.putExtra(ID_ASSIGNMENT, getIntent().getIntExtra(ID_ASSIGNMENT, 0));
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
                    intent.putExtra(ID_ASSIGNMENT, getIntent().getIntExtra(ID_ASSIGNMENT, 0));
                    startActivity(intent);
                    break;
                case COMMERCIAL:
                    intent.putExtra(ASSIGNMENT_TYPE, COMMERCIAL);
                    intent.putExtra(ID_ASSIGNMENT, getIntent().getIntExtra(ID_ASSIGNMENT, 0));
                    startActivity(intent);
                    break;
                case BUSINESS:
                    intent.putExtra(ASSIGNMENT_TYPE, BUSINESS);
                    intent.putExtra(ID_ASSIGNMENT, getIntent().getIntExtra(ID_ASSIGNMENT, 0));
                    startActivity(intent);
                    break;
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;

        map.setMinZoomPreference(17.0f);
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