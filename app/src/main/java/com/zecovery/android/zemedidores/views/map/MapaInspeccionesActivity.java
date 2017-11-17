package com.zecovery.android.zemedidores.views.map;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.crash.FirebaseCrash;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.LocalDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.zecovery.android.zemedidores.data.Constant.DIRECCION;
import static com.zecovery.android.zemedidores.data.Constant.ID_INSPECCION;
import static com.zecovery.android.zemedidores.data.Constant.LATITUD;
import static com.zecovery.android.zemedidores.data.Constant.LONGITUD;

/**
 * Created by moe on 19-10-17.
 */

public class MapaInspeccionesActivity extends AppCompatActivity implements OnMapReadyCallback,
        LocationListener, GoogleMap.OnInfoWindowClickListener {

    private static final String[] LOCATION = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};

    private LatLng mLatLng;
    private LocationManager mLocationManager;
    private LocalDatabase db;

    private List<LatLng> inspeccionesLatLng = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_inspecciones);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getCurrentLocation();

        db = new LocalDatabase(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @SuppressWarnings({"MissingPermission"})
    private void getCurrentLocation() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 5, this);
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

    @Override
    @SuppressWarnings({"MissingPermission"})
    public void onMapReady(GoogleMap map) {
        map.setMyLocationEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);

        Map<String, LatLng> list = db.getUbicacionInspecciones();


        for (Map.Entry<String, LatLng> e : list.entrySet()) {
            map.addMarker(
                    new MarkerOptions()
                            .position(e.getValue())
                            .title("ID INSPECCION:")
                            .snippet(e.getKey())
            );


        }

        map.setOnInfoWindowClickListener(this);

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        String idInspeccion = marker.getSnippet();
        List<String> datos = db.getDatosInspeccion(idInspeccion);

        Intent intent = new Intent(MapaInspeccionesActivity.this, UbicacionActivity.class);

        intent.putExtra(ID_INSPECCION, Integer.valueOf(idInspeccion));
        intent.putExtra(DIRECCION, datos.get(0));
        intent.putExtra(LONGITUD, Double.valueOf(datos.get(1)));
        intent.putExtra(LATITUD, Double.valueOf(datos.get(2)));

        startActivity(intent);
    }
}
