package com.zecovery.android.zemedidores.views.assignments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.crash.FirebaseCrash;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.LocalDatabase;

import static com.zecovery.android.zemedidores.data.Constant.ADDRESS;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_TYPE;
import static com.zecovery.android.zemedidores.data.Constant.BUSINESS;
import static com.zecovery.android.zemedidores.data.Constant.COMMERCIAL;
import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT;
import static com.zecovery.android.zemedidores.data.Constant.LATITUDE;
import static com.zecovery.android.zemedidores.data.Constant.LONGITUDE;
import static com.zecovery.android.zemedidores.data.Constant.RESIDENTIAL;

public class MapActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback, LocationListener {

    private static final String[] LOCATION = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};

    private GoogleMap map;

    private LatLng mLatLng;
    private LocationManager mLocationManager;

    private LocalDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button affirmative = findViewById(R.id.affirmative);
        Button negative = findViewById(R.id.negative);

        affirmative.setOnClickListener(this);
        negative.setOnClickListener(this);

        gerCurrentLocation();

        db = new LocalDatabase(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView addressTextView = findViewById(R.id.addressTextView);

        if (getIntent().getExtras() != null) {
            addressTextView.setText(getIntent().getStringExtra(ADDRESS));
            Log.d("MapActivity", "onResume: " + getIntent().getIntExtra(ID_ASSIGNMENT, 0));
        }
    }

    @SuppressWarnings({"MissingPermission"})
    private void gerCurrentLocation() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 5, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.affirmative:
                assignmentType();
                break;
            case R.id.negative:
                Intent intent = new Intent(MapActivity.this, RejectedActivity.class);
                intent.putExtra(ID_ASSIGNMENT, getIntent().getIntExtra(ID_ASSIGNMENT, 0));
                startActivity(intent);
                break;
        }
    }

    private void assignmentType() {

        if (getIntent().getExtras() != null) {

            Intent intent = new Intent(MapActivity.this, AssignmentActivity.class);

            switch (getIntent().getStringExtra(ASSIGNMENT_TYPE)) {

                case RESIDENTIAL:
                    intent.putExtra(ASSIGNMENT_TYPE, RESIDENTIAL);
                    intent.putExtra(ID_ASSIGNMENT, getIntent().getIntExtra(ID_ASSIGNMENT, 0));
                    startActivity(intent);
                    break;
                case COMMERCIAL:
                    intent.putExtra(ASSIGNMENT_TYPE, RESIDENTIAL); //FIXME: lleva siempre a residencial
                    intent.putExtra(ID_ASSIGNMENT, getIntent().getIntExtra(ID_ASSIGNMENT, 0));
                    startActivity(intent);
                    break;
                case BUSINESS:
                    intent.putExtra(ASSIGNMENT_TYPE, RESIDENTIAL);//FIXME: lleva siempre a residencial
                    intent.putExtra(ID_ASSIGNMENT, getIntent().getIntExtra(ID_ASSIGNMENT, 0));
                    startActivity(intent);
                    break;
            }
        }
    }

    @Override
    @SuppressWarnings({"MissingPermission"})
    public void onMapReady(GoogleMap map) {
        this.map = map;

        map.setMyLocationEnabled(true);
        map.setMinZoomPreference(17.0f);
        map.getUiSettings().setZoomControlsEnabled(true);

        if (getIntent().getExtras() != null) {

            LatLng assignmentLocation;

            if (getIntent().getDoubleExtra(LATITUDE, 0) == 0 && getIntent().getDoubleExtra(LONGITUDE, 0) == 0) {
                assignmentLocation = new LatLng(0, 0);
            } else {
                assignmentLocation = mLatLng;
            }
            map.addMarker(
                    new MarkerOptions().position(assignmentLocation));
            map.moveCamera(CameraUpdateFactory.newLatLng(assignmentLocation));
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            db.guardaUbicacionActual(location.getLatitude(), location.getLongitude());
            if (location.getLatitude() != 0 && location.getLongitude() != 0) {
                mLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            }
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(this, R.string.permissions_location, Toast.LENGTH_SHORT).show();
        FirebaseCrash.log("onProviderDisabled: " + s);
    }
}