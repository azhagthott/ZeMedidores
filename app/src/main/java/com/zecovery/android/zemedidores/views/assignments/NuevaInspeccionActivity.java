package com.zecovery.android.zemedidores.views.assignments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.models.Residente;
import com.zecovery.android.zemedidores.views.assignments.fragments.forms.FormularioResidencialCallback;
import com.zecovery.android.zemedidores.views.assignments.fragments.forms.GuardaDatosResidente;

import static com.zecovery.android.zemedidores.data.Constant.TIPO_INSPECCION;
import static com.zecovery.android.zemedidores.data.Constant.ID_INSPECCION;
import static com.zecovery.android.zemedidores.data.Constant.RESIDENCIAL;

public class NuevaInspeccionActivity extends AppCompatActivity implements View.OnClickListener, FormularioResidencialCallback {

    private FloatingActionButton nuevaInspeccionFab;
    private EditText nuevaInspeccionDireccion;
    private EditText nuevoResidenteNombre;
    private EditText nuevoResidenteRut;
    private EditText nuevoResidenteTelefono;
    private EditText nuevoResidenteEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_inspeccion);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViews();

        nuevaInspeccionFab.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void findViews() {
        nuevaInspeccionDireccion = findViewById(R.id.nuevaInspeccionDireccion);
        nuevoResidenteNombre = findViewById(R.id.nuevoResidenteNombre);
        nuevoResidenteRut = findViewById(R.id.nuevoResidenteRut);
        nuevoResidenteTelefono = findViewById(R.id.nuevoResidenteTelefono);
        nuevoResidenteEmail = findViewById(R.id.nuevoResidenteEmail);
        nuevaInspeccionFab = findViewById(R.id.nuevaInspeccionFab);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.nuevaInspeccionFab) {
            LocalDatabase db = new LocalDatabase(this);

            Residente residente = new Residente();
            residente.setNombreResidente(nuevoResidenteNombre.getText().toString());
            residente.setRutResidente(nuevoResidenteRut.getText().toString());
            residente.setTelefonoResidente(nuevoResidenteTelefono.getText().toString());
            residente.setEmailResidente(nuevoResidenteEmail.getText().toString());
            residente.setDireccionResidente(nuevaInspeccionDireccion.getText().toString());
            residente.setFechaResidente(nuevaInspeccionDireccion.getText().toString());

            db.guardaDatosResidente(residente,10000000);


            new GuardaDatosResidente(this, this).guardaDatos(residente, 10000000);

            //db.getDatosResidente(10000000);

        }
    }

    @Override
    public void guardaDatos() {

        Intent intent = new Intent(this, ContentActivity.class);

        intent.putExtra(TIPO_INSPECCION, RESIDENCIAL);
        intent.putExtra(ID_INSPECCION, 10000000);

        startActivity(intent);

    }

    @Override
    public void errorGuardaDatos() {

    }
}
