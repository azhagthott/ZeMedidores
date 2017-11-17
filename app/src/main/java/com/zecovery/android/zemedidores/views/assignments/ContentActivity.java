package com.zecovery.android.zemedidores.views.assignments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.views.assignments.fragments.IdInspeccionListener;
import com.zecovery.android.zemedidores.views.assignments.fragments.executetest.EjecutaTestParte1Fragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.executetest.EjecutaTestParte2Fragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.executetest.EjecutaTestParte3Fragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.executetest.FotosTestFragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.forms.FormularioComercialFragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.forms.FormularioEmpresaFragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.forms.FormularioInspeccionNuevaFragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.forms.FormularioNegociacionFragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.forms.FormularioResidencialFragment;

import static com.zecovery.android.zemedidores.data.Constant.COMERCIAL;
import static com.zecovery.android.zemedidores.data.Constant.DIRECCION;
import static com.zecovery.android.zemedidores.data.Constant.EMPRESA;
import static com.zecovery.android.zemedidores.data.Constant.ID_INSPECCION;
import static com.zecovery.android.zemedidores.data.Constant.NUEVA;
import static com.zecovery.android.zemedidores.data.Constant.RESIDENCIAL;
import static com.zecovery.android.zemedidores.data.Constant.TIPO_INSPECCION;

public class ContentActivity extends AppCompatActivity implements IdInspeccionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extra = getIntent().getExtras();

        if (extra != null) {

            int idInspeccion = getIntent().getIntExtra(ID_INSPECCION, 0);
            String direccion = getIntent().getStringExtra(DIRECCION);

            Log.d("TAG", "onCreate: " + direccion);


            Log.d("ContentActivity", "idInspeccion: " + idInspeccion);


            switch (getIntent().getStringExtra(TIPO_INSPECCION)) {

                case NUEVA:
                    callFragment(new FormularioInspeccionNuevaFragment().newInstance(idInspeccion));
                    break;
                case RESIDENCIAL:
                    callFragment(new FormularioResidencialFragment().newInstance(idInspeccion, direccion));
                    break;
                case COMERCIAL:
                    callFragment(new FormularioComercialFragment());
                    break;
                case EMPRESA:
                    callFragment(new FormularioEmpresaFragment());
                    break;
            }
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void callFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_assignment, fragment).commit();
    }

    @Override
    public void IdInspeccionFotoTest(int idInspeccion) {
        callFragment(new FotosTestFragment().newInstance(idInspeccion));
    }

    @Override
    public void IdInspeccionEjecutaTestParte1(int idInspeccion) {
        callFragment(new EjecutaTestParte1Fragment().newInstance(idInspeccion));
    }

    @Override
    public void IdInspeccionEjecutaTestParte2(int idInspeccion) {
        callFragment(new EjecutaTestParte2Fragment().newInstance(idInspeccion));
    }

    @Override
    public void IdInspeccionEjecutaTestParte3(int idInspeccion) {
        callFragment(new EjecutaTestParte3Fragment().newInstance(idInspeccion));
    }

    @Override
    public void idInspeccionFormNegociacion(int idInspeccion) {
        callFragment(new FormularioNegociacionFragment().newInstance(idInspeccion));
    }
}
