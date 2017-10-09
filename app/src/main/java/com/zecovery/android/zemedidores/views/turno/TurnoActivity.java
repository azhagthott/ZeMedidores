package com.zecovery.android.zemedidores.views.turno;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.crash.FirebaseCrash;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.views.MainActivity;
import com.zecovery.android.zemedidores.views.assignments.fragments.executetest.ProgressAlertDialogFragment;
import com.zecovery.android.zemedidores.views.login.LoginActivity;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class TurnoActivity extends AppCompatActivity implements IniciaTurnoCallback, EasyPermissions.PermissionCallbacks {

    private static final String[] LOCATION_AND_CAMERA = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};

    private static final int RC_LOCATION_AND_CAMERA = 124;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        locationAndContactsTask();

        new ProgressAlertDialogFragment();

        Button button = findViewById(R.id.initShiftButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IniciaTurno(TurnoActivity.this).init();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sign_out) {
            signOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void signOut() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(TurnoActivity.this, LoginActivity.class));
                        finish();
                    }
                });
    }

    @Override
    public void iniciaTurnoOk() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void iniciaTurnoError() {
        FirebaseCrash.report(new Exception(getResources().getString(R.string.validacion_falla_iniciar_turno)));
    }

    private boolean hasLocationAndCameraPermissions() {
        return EasyPermissions.hasPermissions(this, LOCATION_AND_CAMERA);
    }

    @AfterPermissionGranted(RC_LOCATION_AND_CAMERA)
    public void locationAndContactsTask() {

        Toast.makeText(this, R.string.permissions_check, Toast.LENGTH_LONG).show();

        if (hasLocationAndCameraPermissions()) {
            // Have permissions, do the thing!
            Toast.makeText(this, R.string.permissions_result, Toast.LENGTH_LONG).show();
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.permissions_location),
                    RC_LOCATION_AND_CAMERA,
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
        Log.d("TAG", "onPermissionsGranted:" + requestCode + ":" + perms.size());

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        FirebaseCrash.log("onPermissionsDenied: " + requestCode + ":" + perms.size());
        Log.d("TAG", "onPermissionsDenied:" + requestCode + ":" + perms.size());

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
        }
    }
}
