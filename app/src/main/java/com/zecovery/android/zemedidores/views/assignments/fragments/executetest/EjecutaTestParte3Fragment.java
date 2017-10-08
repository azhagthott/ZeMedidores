package com.zecovery.android.zemedidores.views.assignments.fragments.executetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.models.TestParte3;
import com.zecovery.android.zemedidores.network.EnviaInspeccion;
import com.zecovery.android.zemedidores.network.EnviaInspeccionCallback;
import com.zecovery.android.zemedidores.views.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static com.zecovery.android.zemedidores.data.Constant.ID_INSPECCION_EJECUTA_TEST_3;
import static com.zecovery.android.zemedidores.data.Constant.NO_ENVIA_RESPUESTA;
import static com.zecovery.android.zemedidores.data.Constant.RESPONDE_NO;
import static com.zecovery.android.zemedidores.data.Constant.RESPONDE_SI;
import static com.zecovery.android.zemedidores.data.Constant.SELECT_OPTION;
import static com.zecovery.android.zemedidores.data.Constant.TAG;
import static com.zecovery.android.zemedidores.data.Constant.TYPE_1;
import static com.zecovery.android.zemedidores.data.Constant.TYPE_2;

public class EjecutaTestParte3Fragment extends Fragment implements View.OnClickListener, EnviaInspeccionCallback {

    private Button saveButton;

    private RadioButton newBuildingYesRadioButton;
    private RadioButton newBuildingNoRadioButton;
    private RadioButton autoAbastecimientoSi;
    private RadioButton autoAbastecimientoNo;
    private RadioButton activoSi;
    private RadioButton activoNo;

    private Spinner propertyTypeSpinner;

    private EditText occupantEditText;
    private EditText bathRoomsEditText;
    private EditText surfaceEditText;
    private EditText surfaceGardenEditText;
    private EditText accessEditText;
    private EditText totalSurfaceEditText;
    private EditText test3Obs1EditText;
    private EditText sourceTypeEditText;
    private EditText useEditText;
    private EditText capacityEditText;
    private EditText dgaEditText;
    private EditText caudalEditText;
    private EditText test3Obs2EditText;

    private LinearLayout abastecimientoLayout;

    private int idInspeccion;

    private LocalDatabase db;

    public EjecutaTestParte3Fragment() {
    }

    public EjecutaTestParte3Fragment newInstance(int idInspeccion) {
        EjecutaTestParte3Fragment executeTestFragment = new EjecutaTestParte3Fragment();
        Bundle args = new Bundle();
        args.putInt(ID_INSPECCION_EJECUTA_TEST_3, idInspeccion);
        executeTestFragment.setArguments(args);
        return executeTestFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ejecuta_test_parte3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        idInspeccion = getArguments().getInt(ID_INSPECCION_EJECUTA_TEST_3);

        db = new LocalDatabase(getContext());

        findViews(view);

        Log.d(TAG, "EjecutaTestParte3Fragment");
        Log.d(TAG, "token: " + idInspeccion);

        List<String> propertyType = new ArrayList<>();

        propertyType.add(SELECT_OPTION);
        propertyType.add(TYPE_1);
        propertyType.add(TYPE_2);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, propertyType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        propertyTypeSpinner.setAdapter(adapter);

        saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
    }

    private void findViews(View view) {

        newBuildingYesRadioButton = view.findViewById(R.id.newBuildingYesRadioButton);
        newBuildingNoRadioButton = view.findViewById(R.id.newBuildingNoRadioButton);
        autoAbastecimientoSi = view.findViewById(R.id.autoAbastecimientoSi);
        autoAbastecimientoNo = view.findViewById(R.id.autoAbastecimientoNo);
        activoSi = view.findViewById(R.id.activoSi);
        activoNo = view.findViewById(R.id.activoNo);

        propertyTypeSpinner = view.findViewById(R.id.propertyTypeSpinner); //FIXME: falta definir

        occupantEditText = view.findViewById(R.id.occupantEditText);
        bathRoomsEditText = view.findViewById(R.id.bathRoomsEditText);
        surfaceEditText = view.findViewById(R.id.surfaceEditText);
        surfaceGardenEditText = view.findViewById(R.id.surfaceGardenEditText);
        accessEditText = view.findViewById(R.id.accessEditText);
        totalSurfaceEditText = view.findViewById(R.id.totalSurfaceEditText);

        sourceTypeEditText = view.findViewById(R.id.sourceTypeEditText);
        useEditText = view.findViewById(R.id.useEditText);
        capacityEditText = view.findViewById(R.id.capacityEditText);
        dgaEditText = view.findViewById(R.id.dgaEditText);
        caudalEditText = view.findViewById(R.id.caudalEditText);

        test3Obs1EditText = view.findViewById(R.id.test3Obs1EditText);
        test3Obs2EditText = view.findViewById(R.id.test3Obs2EditText);

        abastecimientoLayout = view.findViewById(R.id.abastecimientoLayout);

        autoAbastecimientoNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    abastecimientoLayout.setVisibility(View.GONE);
                } else {
                    abastecimientoLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        autoAbastecimientoSi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    abastecimientoLayout.setVisibility(View.VISIBLE);
                } else {
                    abastecimientoLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

        TestParte3 test = new TestParte3();

        if (newBuildingYesRadioButton.isChecked()) {
            test.setConstruccionNueva(RESPONDE_SI);
        } else if (newBuildingNoRadioButton.isChecked()) {
            test.setConstruccionNueva(RESPONDE_NO);
        } else {
            test.setConstruccionNueva(NO_ENVIA_RESPUESTA);
        }

        String habitantes = occupantEditText.getText().toString();
        test.setHabitantes(habitantes);

        String banos = bathRoomsEditText.getText().toString();
        test.setBanos(banos);

        String sup = surfaceEditText.getText().toString();
        test.setSuperficieEdificada(sup);

        String supJardin = surfaceGardenEditText.getText().toString();
        test.setSuperficieJardin(supJardin);

        String acceso = accessEditText.getText().toString();
        test.setAcceso(acceso);

        String supTerreno = totalSurfaceEditText.getText().toString();
        test.setSuperficieTerreno(supTerreno);

        String obs1 = test3Obs1EditText.getText().toString();
        test.setObservaciones1(obs1);

        if (autoAbastecimientoSi.isChecked()) {
            test.setAutoAbastecimiento(RESPONDE_SI);
        } else if (autoAbastecimientoNo.isChecked()) {
            test.setAutoAbastecimiento(RESPONDE_NO);
        } else {
            test.setAutoAbastecimiento(NO_ENVIA_RESPUESTA);
        }

        if (activoSi.isChecked()) {
            test.setActivo(RESPONDE_SI);
        } else if (activoNo.isChecked()) {
            test.setActivo(RESPONDE_NO);
        } else {
            test.setActivo(NO_ENVIA_RESPUESTA);
        }

        String fuentePropia = sourceTypeEditText.getText().toString();
        test.setTipoFuente(fuentePropia);

        String uso = useEditText.getText().toString();
        test.setUso(uso);

        String capacidad = capacityEditText.getText().toString();
        test.setCapacidadBomba(capacidad);

        String dga = dgaEditText.getText().toString();
        test.setResolucion(dga);

        String caudal = caudalEditText.getText().toString();
        test.setCaudal(caudal);

        String obs2 = test3Obs2EditText.getText().toString();
        test.setObservaciones2(obs2);

        db.guardaDatosTestParte3(test, idInspeccion);
        new EnviaInspeccion(this).enviaInpeccion(getContext(), idInspeccion);

    }

    @Override
    public void enviaInspeccionOk(int code) {
        startActivity(new Intent(getContext(), MainActivity.class));
    }

    @Override
    public void enviarInspeccionError(int code) {
        Toast.makeText(getContext(), "No se ha podido enviar inspeccion", Toast.LENGTH_SHORT).show();
    }
}
