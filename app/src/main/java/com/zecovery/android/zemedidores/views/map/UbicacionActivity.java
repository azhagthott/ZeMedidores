package com.zecovery.android.zemedidores.views.map;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.zecovery.android.zemedidores.views.assignments.ContentActivity;
import com.zecovery.android.zemedidores.views.assignments.InspeccionRechazadaActivity;

import static com.zecovery.android.zemedidores.data.Constant.COMERCIAL;
import static com.zecovery.android.zemedidores.data.Constant.DIRECCION;
import static com.zecovery.android.zemedidores.data.Constant.EMPRESA;
import static com.zecovery.android.zemedidores.data.Constant.ID_INSPECCION;
import static com.zecovery.android.zemedidores.data.Constant.LATITUD;
import static com.zecovery.android.zemedidores.data.Constant.LONGITUD;
import static com.zecovery.android.zemedidores.data.Constant.NUEVA;
import static com.zecovery.android.zemedidores.data.Constant.RESIDENCIAL;
import static com.zecovery.android.zemedidores.data.Constant.TIPO_INSPECCION;

public class UbicacionActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback, LocationListener {

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
        getCurrentLocation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView addressTextView = findViewById(R.id.addressTextView);
        if (getIntent().getExtras() != null && getIntent().getIntExtra(ID_INSPECCION, 0) > 0) {
            addressTextView.setText(getIntent().getStringExtra(DIRECCION));
        }
    }

    @SuppressWarnings({"MissingPermission"})
    private void getCurrentLocation() {
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
                Intent intent = new Intent(UbicacionActivity.this, InspeccionRechazadaActivity.class);
                intent.putExtra(ID_INSPECCION, getIntent().getIntExtra(ID_INSPECCION, 0));
                startActivity(intent);
                break;
        }
    }

    private void assignmentType() {

        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String empresaInspeccion = mSharedPreferences.getString(getString(R.string.sp_key_empresa), "VACIO");

        if (getIntent().getExtras() != null) {

            int idInspeccion = getIntent().getIntExtra(ID_INSPECCION, 0);
            Intent intent = new Intent(UbicacionActivity.this, ContentActivity.class);

            switch (getIntent().getStringExtra(TIPO_INSPECCION)) {

                case NUEVA:
                    intent.putExtra(TIPO_INSPECCION, NUEVA);
                    intent.putExtra(ID_INSPECCION, getIntent().getIntExtra(ID_INSPECCION, 0));
                    db.creaInspeccion(idInspeccion, empresaInspeccion);
                    startActivity(intent);
                    break;
                case RESIDENCIAL:
                    intent.putExtra(TIPO_INSPECCION, RESIDENCIAL);
                    intent.putExtra(ID_INSPECCION, getIntent().getIntExtra(ID_INSPECCION, 0));
                    db.creaInspeccion(idInspeccion, empresaInspeccion);
                    startActivity(intent);
                    break;
                case COMERCIAL:
                    intent.putExtra(TIPO_INSPECCION, RESIDENCIAL); //FIXME: lleva siempre a residencial
                    intent.putExtra(ID_INSPECCION, getIntent().getIntExtra(ID_INSPECCION, 0));
                    db.creaInspeccion(idInspeccion, empresaInspeccion);
                    startActivity(intent);
                    break;
                case EMPRESA:
                    intent.putExtra(TIPO_INSPECCION, RESIDENCIAL);//FIXME: lleva siempre a residencial
                    intent.putExtra(ID_INSPECCION, getIntent().getIntExtra(ID_INSPECCION, 0));
                    db.creaInspeccion(idInspeccion, empresaInspeccion);
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

            Log.d("TAG", "LATITUD: " + getIntent().getDoubleExtra(LATITUD, 0));
            Log.d("TAG", "LONGITUD: " + getIntent().getDoubleExtra(LONGITUD, 0));

            double lat = getIntent().getDoubleExtra(LATITUD, 0);
            double lng = getIntent().getDoubleExtra(LONGITUD, 0);

            if (lat == 0 || lng == 0) {
                assignmentLocation = new LatLng(0, 0);
            } else {
                assignmentLocation = new LatLng(lng, lat); //FIXME: la coordenadas están al reves
            }
            map.addMarker(new MarkerOptions().position(assignmentLocation));
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