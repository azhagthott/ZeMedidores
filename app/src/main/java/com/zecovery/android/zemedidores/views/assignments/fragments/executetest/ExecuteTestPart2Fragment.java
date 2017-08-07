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
import android.widget.Toast;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.CurrentUser;
import com.zecovery.android.zemedidores.data.Nodes;
import com.zecovery.android.zemedidores.views.assignments.fragments.PushKeyListener;

import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT_EXECUTE_TEST_2;
import static com.zecovery.android.zemedidores.data.Constant.TAG;

public class ExecuteTestPart2Fragment extends Fragment implements TestResultsCallback, View.OnClickListener {

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

    private String token;

    private PushKeyListener callback;

    public ExecuteTestPart2Fragment() {
    }

    public ExecuteTestPart2Fragment newInstance(String pushKey) {
        ExecuteTestPart2Fragment executeTestFragment = new ExecuteTestPart2Fragment();
        Bundle args = new Bundle();
        args.putString(ID_ASSIGNMENT_EXECUTE_TEST_2, pushKey);
        executeTestFragment.setArguments(args);
        return executeTestFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_execute_test_part2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        token = getArguments().getString(ID_ASSIGNMENT_EXECUTE_TEST_2);
        findViews(view);
        Log.d(TAG, "ExecuteTestPart2Fragment");
        Log.d(TAG, "token: " + token);
        saveButton.setOnClickListener(this);
    }

    private void findViews(View view) {
        saveButton = (Button) view.findViewById(R.id.saveButton);
        classARadioButton = (RadioButton) view.findViewById(R.id.classARadioButton);
        classBRadioButton = (RadioButton) view.findViewById(R.id.classBRadioButton);
        classCRadioButton = (RadioButton) view.findViewById(R.id.classCRadioButton);

        registerType1RadioButton = (RadioButton) view.findViewById(R.id.registerType1RadioButton);
        registerType2RadioButton = (RadioButton) view.findViewById(R.id.registerType2RadioButton);

        position1RadioButton = (RadioButton) view.findViewById(R.id.position1RadioButton);
        position2RadioButton = (RadioButton) view.findViewById(R.id.position2RadioButton);

        beforeYesRadioButton = (RadioButton) view.findViewById(R.id.beforeYesRadioButton);
        beforeNoRadioButton = (RadioButton) view.findViewById(R.id.beforeNoRadioButton);
        afterYesRadioButton = (RadioButton) view.findViewById(R.id.afterYesRadioButton);
        afterNoRadioButton = (RadioButton) view.findViewById(R.id.afterNoRadioButton);

        alternativeYesRadioButton = (RadioButton) view.findViewById(R.id.alternativeYesRadioButton);
        alternativeNoRadioButton = (RadioButton) view.findViewById(R.id.alternativeNoRadioButton);

        typeYesRadioButton = (RadioButton) view.findViewById(R.id.typeYesRadioButton);
        typeNoRadioButton = (RadioButton) view.findViewById(R.id.typeNoRadioButton);

        yearEditText = (EditText) view.findViewById(R.id.yearEditText);
        modelEditText = (EditText) view.findViewById(R.id.modelEditText);
        obs1EditText = (EditText) view.findViewById(R.id.obs1EditText);
        obs2EditText = (EditText) view.findViewById(R.id.obs2EditText);
        verticalStatusEditText = (EditText) view.findViewById(R.id.verticalStatusEditText);
        cutStatusEditText = (EditText) view.findViewById(R.id.cutStatusEditText);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {

            try {
                Activity activity = (Activity) context;
                callback = (PushKeyListener) activity;
            } catch (Exception e) {
                Log.d("TAG", e.toString());
                throw new ClassCastException(context.toString()
                        + " must implement OnHeadlineSelectedListener");
            }
        }
    }

    @Override
    public void onClick(View v) {

        if (classARadioButton.isChecked()) {
            new Nodes().testResults(new CurrentUser().uid()).child(token).child("clase").setValue("A");
        } else if (classBRadioButton.isChecked()) {
            new Nodes().testResults(new CurrentUser().uid()).child(token).child("clase").setValue("B");
        } else {
            new Nodes().testResults(new CurrentUser().uid()).child(token).child("clase").setValue("C");
        }

        String year = yearEditText.getText().toString();
        String model = modelEditText.getText().toString();

        new Nodes().testResults(new CurrentUser().uid()).child(token).child("ano_medidor").setValue(year);
        new Nodes().testResults(new CurrentUser().uid()).child(token).child("modelo_medidor").setValue(model);

        if (registerType1RadioButton.isChecked()) {
            new Nodes().testResults(new CurrentUser().uid()).child(token).child("registrador").setValue("CuVi");
        } else {
            new Nodes().testResults(new CurrentUser().uid()).child(token).child("registrador").setValue("Plastico");
        }

        if (position1RadioButton.isChecked()) {
            new Nodes().testResults(new CurrentUser().uid()).child(token).child("posicion_instalación").setValue("aerea");
        } else {
            new Nodes().testResults(new CurrentUser().uid()).child(token).child("posicion_instalación").setValue("subterranea");
        }

        if (beforeYesRadioButton.isChecked()) {
            new Nodes().testResults(new CurrentUser().uid()).child(token).child("tramo_recto_antes").setValue("SI");
        } else {
            new Nodes().testResults(new CurrentUser().uid()).child(token).child("tramo_recto_antes").setValue("NO");
        }

        new Nodes().testResults(new CurrentUser().uid()).child(token).child("estado_verticales").setValue(verticalStatusEditText.getText().toString());
        new Nodes().testResults(new CurrentUser().uid()).child(token).child("estado_verticales").setValue(cutStatusEditText.getText().toString());

        if (afterYesRadioButton.isChecked()) {
            new Nodes().testResults(new CurrentUser().uid()).child(token).child("tramo_recto_despues").setValue("SI");
        } else {
            new Nodes().testResults(new CurrentUser().uid()).child(token).child("tramo_recto_despues").setValue("NO");
        }

        if (alternativeYesRadioButton.isChecked()) {
            new Nodes().testResults(new CurrentUser().uid()).child(token).child("suministro_alternativo").setValue("SI");
        } else {
            new Nodes().testResults(new CurrentUser().uid()).child(token).child("suministro_alternativo").setValue("NO");
        }

        if (typeYesRadioButton.isChecked()) {
            new Nodes().testResults(new CurrentUser().uid()).child(token).child("cumple_tipo_plano").setValue("SI");
        } else {
            new Nodes().testResults(new CurrentUser().uid()).child(token).child("cumple_tipo_plano").setValue("NO");
        }

        callback.pushKeyToExecuteTestPart3(token);
        //new SendTestResults(this).save(token);
    }

    @Override
    public void resultsSaved() {
        callback.pushKeyToExecuteTestPart3(token);
    }

    @Override
    public void resultsNotSaved() {
        Toast.makeText(getContext(), R.string.test_results_save_error, Toast.LENGTH_SHORT).show();
    }
}
