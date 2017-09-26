package com.zecovery.android.zemedidores.views.assignments.fragments.executetest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.models.TestParte2;
import com.zecovery.android.zemedidores.views.assignments.fragments.IdInspeccionListener;

import static com.zecovery.android.zemedidores.data.Constant.ID_INSPECCION_EJECUTA_TEST_2;
import static com.zecovery.android.zemedidores.data.Constant.TAG;

public class EjecutaTestParte2Fragment extends Fragment implements View.OnClickListener {

    private RadioButton classARadioButton;
    private RadioButton classBRadioButton;
    private RadioButton classCRadioButton;
    private RadioButton registerType1RadioButton;
    private RadioButton registerType2RadioButton;
    private RadioButton position1RadioButton;
    private RadioButton position2RadioButton;

    private RadioButton beforeYesRadioButton;
    private RadioButton beforeNoRadioButton;
    private RadioButton afterYesRadioButton;
    private RadioButton afterNoRadioButton;

    private RadioButton alternativeYesRadioButton;
    private RadioButton alternativeNoRadioButton;
    private RadioButton typeYesRadioButton;
    private RadioButton typeNoRadioButton;


    private EditText yearEditText;
    private EditText modelEditText;
    private EditText obs1EditText;
    private EditText obs2EditText;
    private EditText verticalStatusEditText;
    private EditText cutStatusEditText;

    private Button saveButton;
    private IdInspeccionListener callback;
    private LocalDatabase db;

    private int idInspeccion;

    public EjecutaTestParte2Fragment() {
    }

    public EjecutaTestParte2Fragment newInstance(int idInspeccion) {
        EjecutaTestParte2Fragment executeTestFragment = new EjecutaTestParte2Fragment();
        Bundle args = new Bundle();
        args.putInt(ID_INSPECCION_EJECUTA_TEST_2, idInspeccion);
        executeTestFragment.setArguments(args);
        return executeTestFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ejecuta_test_parte2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idInspeccion = getArguments().getInt(ID_INSPECCION_EJECUTA_TEST_2);
        findViews(view);
        Log.d(TAG, "EjecutaTestParte2Fragment");
        Log.d(TAG, "idInspeccion: " + idInspeccion);

        db = new LocalDatabase(getContext());

        saveButton.setOnClickListener(this);
    }

    private void findViews(View view) {
        saveButton = view.findViewById(R.id.saveButton);
        classARadioButton = view.findViewById(R.id.classARadioButton);
        classBRadioButton = view.findViewById(R.id.classBRadioButton);
        classCRadioButton = view.findViewById(R.id.classCRadioButton);

        registerType1RadioButton = view.findViewById(R.id.registerType1RadioButton);
        registerType2RadioButton = view.findViewById(R.id.registerType2RadioButton);

        position1RadioButton = view.findViewById(R.id.position1RadioButton);
        position2RadioButton = view.findViewById(R.id.position2RadioButton);

        beforeYesRadioButton = view.findViewById(R.id.beforeYesRadioButton);
        beforeNoRadioButton = view.findViewById(R.id.beforeNoRadioButton);
        afterYesRadioButton = view.findViewById(R.id.afterYesRadioButton);
        afterNoRadioButton = view.findViewById(R.id.afterNoRadioButton);

        alternativeYesRadioButton = view.findViewById(R.id.alternativeYesRadioButton);
        alternativeNoRadioButton = view.findViewById(R.id.alternativeNoRadioButton);

        typeYesRadioButton = view.findViewById(R.id.typeYesRadioButton);
        typeNoRadioButton = view.findViewById(R.id.typeNoRadioButton);

        yearEditText = view.findViewById(R.id.yearEditText);
        modelEditText = view.findViewById(R.id.modelEditText);
        obs1EditText = view.findViewById(R.id.obs1EditText);
        obs2EditText = view.findViewById(R.id.obs2EditText);
        verticalStatusEditText = view.findViewById(R.id.verticalStatusEditText);
        cutStatusEditText = view.findViewById(R.id.cutStatusEditText);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {

            try {
                Activity activity = (Activity) context;
                callback = (IdInspeccionListener) activity;
            } catch (Exception e) {
                Log.d("TAG", e.toString());
                throw new ClassCastException(context.toString()
                        + " must implement OnHeadlineSelectedListener");
            }
        }
    }

    @Override
    public void onClick(View v) {

        TestParte2 test = new TestParte2();

        if (classARadioButton.isChecked()) {
            test.setClaseMedidor("A");
        } else if (classBRadioButton.isChecked()) {
            test.setClaseMedidor("B");
        } else if (classCRadioButton.isChecked()) {
            test.setClaseMedidor("C");
        } else {
            test.setClaseMedidor("No envia respuesta");
        }

        String year = yearEditText.getText().toString();
        test.setAnoMedidor(year);

        String marca = modelEditText.getText().toString();
        test.setMarca(marca);

        if (registerType1RadioButton.isChecked()) {
            test.setRegistrador("CuVi");
        } else if (registerType2RadioButton.isChecked()) {
            test.setRegistrador("Plastico");
        } else {
            test.setRegistrador("No envia respuesta");
        }

        if (position1RadioButton.isChecked()) {
            test.setInstalacion("Aerea");
        } else if (position2RadioButton.isChecked()) {
            test.setInstalacion("Subterranea");
        } else {
            test.setInstalacion("No envia respuesta");
        }

        if (beforeYesRadioButton.isChecked()) {
            test.setTramoAntes("SI");
        } else if (beforeNoRadioButton.isChecked()) {
            test.setTramoAntes("NO");
        } else {
            test.setTramoAntes("No envia respuesta");
        }


        if (afterYesRadioButton.isChecked()) {
            test.setTramoDespues("SI");
        } else if (afterNoRadioButton.isChecked()) {
            test.setTramoDespues("NO");
        } else {
            test.setTramoDespues("No envia respuesta");
        }

        String obs1 = obs1EditText.getText().toString();
        test.setObservaciones(obs1);


        String verticales = verticalStatusEditText.getText().toString();
        test.setEstadoVerticales(verticales);

        String cortes = cutStatusEditText.getText().toString();
        test.setEstadoCortes(cortes);


        if (alternativeYesRadioButton.isChecked()) {
            test.setSuministroAlternativo("SI");
        } else if (alternativeNoRadioButton.isChecked()) {
            test.setSuministroAlternativo("NO");
        } else {
            test.setSuministroAlternativo("No envia respuesta");
        }

        if (typeYesRadioButton.isChecked()) {
            test.setCumplePlano("SI");
        } else if (typeNoRadioButton.isChecked()) {
            test.setCumplePlano("NO");
        } else {
            test.setCumplePlano("No envia respuesta");
        }

        String obs2 = obs2EditText.getText().toString();
        test.setObservaciones2(obs2);

        db.guardaDatosTestParte2(test, idInspeccion);

        callback.IdInspeccionEjecutaTestParte3(idInspeccion);
    }
}
