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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.CurrentUser;
import com.zecovery.android.zemedidores.data.Nodes;
import com.zecovery.android.zemedidores.views.assignments.fragments.TokenListener;

import java.util.ArrayList;
import java.util.List;

import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT_EXECUTE_TEST_3;
import static com.zecovery.android.zemedidores.data.Constant.SELECT_OPTION;
import static com.zecovery.android.zemedidores.data.Constant.TAG;
import static com.zecovery.android.zemedidores.data.Constant.TYPE_1;
import static com.zecovery.android.zemedidores.data.Constant.TYPE_2;

public class ExecuteTestPart3Fragment extends Fragment implements TestResultsCallback, View.OnClickListener {

    private Button saveButton;

    private RadioButton newBuildingYesRadioButton;
    private RadioButton newBuildingNoRadioButton;

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

    private String token;

    private TokenListener callback;

    public ExecuteTestPart3Fragment() {
    }

    public ExecuteTestPart3Fragment newInstance(String pushKey) {
        ExecuteTestPart3Fragment executeTestFragment = new ExecuteTestPart3Fragment();
        Bundle args = new Bundle();
        args.putString(ID_ASSIGNMENT_EXECUTE_TEST_3, pushKey);
        executeTestFragment.setArguments(args);
        return executeTestFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_execute_test_part3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        token = getArguments().getString(ID_ASSIGNMENT_EXECUTE_TEST_3);

        findViews(view);

        Log.d(TAG, "ExecuteTestPart3Fragment");
        Log.d(TAG, "pushKey: " + token);

        List<String> propertyType = new ArrayList<>();

        propertyType.add(SELECT_OPTION);
        propertyType.add(TYPE_1);
        propertyType.add(TYPE_2);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, propertyType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        propertyTypeSpinner.setAdapter(adapter);

        saveButton = (Button) view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
    }

    private void findViews(View view) {

        newBuildingYesRadioButton = (RadioButton) view.findViewById(R.id.newBuildingYesRadioButton);
        newBuildingNoRadioButton = (RadioButton) view.findViewById(R.id.newBuildingNoRadioButton);

        propertyTypeSpinner = (Spinner) view.findViewById(R.id.propertyTypeSpinner); //FIXME: falta definir

        occupantEditText = (EditText) view.findViewById(R.id.occupantEditText);
        bathRoomsEditText = (EditText) view.findViewById(R.id.bathRoomsEditText);
        surfaceEditText = (EditText) view.findViewById(R.id.surfaceEditText);
        surfaceGardenEditText = (EditText) view.findViewById(R.id.surfaceGardenEditText);
        accessEditText = (EditText) view.findViewById(R.id.accessEditText);
        totalSurfaceEditText = (EditText) view.findViewById(R.id.totalSurfaceEditText);

        sourceTypeEditText = (EditText) view.findViewById(R.id.sourceTypeEditText);
        useEditText = (EditText) view.findViewById(R.id.useEditText);
        capacityEditText = (EditText) view.findViewById(R.id.capacityEditText);
        dgaEditText = (EditText) view.findViewById(R.id.dgaEditText);
        caudalEditText = (EditText) view.findViewById(R.id.caudalEditText);

        test3Obs1EditText = (EditText) view.findViewById(R.id.test3Obs1EditText);
        test3Obs2EditText = (EditText) view.findViewById(R.id.test3Obs2EditText);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {

            try {
                Activity activity = (Activity) context;
                callback = (TokenListener) activity;
            } catch (Exception e) {
                Log.d("TAG", e.toString());
                throw new ClassCastException(context.toString()
                        + " must implement OnHeadlineSelectedListener");
            }
        }
    }

    @Override
    public void onClick(View v) {

        if (newBuildingYesRadioButton.isChecked()) {
            new Nodes().testResults(new CurrentUser().uid()).child(token).child("construccion_nueva").setValue("SI");
        } else {
            new Nodes().testResults(new CurrentUser().uid()).child(token).child("construccion_nueva").setValue("NO");
        }
        new Nodes().testResults(new CurrentUser().uid()).child(token).child("habitantes").setValue(occupantEditText.getText().toString());
        new Nodes().testResults(new CurrentUser().uid()).child(token).child("banos").setValue(bathRoomsEditText.getText().toString());
        new Nodes().testResults(new CurrentUser().uid()).child(token).child("superficie_construida").setValue(surfaceEditText.getText().toString());
        new Nodes().testResults(new CurrentUser().uid()).child(token).child("superficie_jardin").setValue(surfaceGardenEditText.getText().toString());
        new Nodes().testResults(new CurrentUser().uid()).child(token).child("accesos").setValue(accessEditText.getText().toString());
        new Nodes().testResults(new CurrentUser().uid()).child(token).child("superficie_terreno").setValue(totalSurfaceEditText.getText().toString());
        new Nodes().testResults(new CurrentUser().uid()).child(token).child("observaciones_3_1").setValue(test3Obs1EditText.getText().toString());

        new Nodes().testResults(new CurrentUser().uid()).child(token).child("tipo_fuente_propia").setValue(sourceTypeEditText.getText().toString());
        new Nodes().testResults(new CurrentUser().uid()).child(token).child("uso").setValue(useEditText.getText().toString());
        new Nodes().testResults(new CurrentUser().uid()).child(token).child("capacidad").setValue(capacityEditText.getText().toString());
        new Nodes().testResults(new CurrentUser().uid()).child(token).child("dga").setValue(dgaEditText.getText().toString());
        new Nodes().testResults(new CurrentUser().uid()).child(token).child("caudal").setValue(caudalEditText.getText().toString());
        new Nodes().testResults(new CurrentUser().uid()).child(token).child("observaciones_3_2").setValue(test3Obs2EditText.getText().toString());

        new SendTestResults(this).save(token);
    }

    @Override
    public void resultsSaved() {
        callback.tokenToNegotiation(token);
    }

    @Override
    public void resultsNotSaved() {
        Toast.makeText(getContext(), R.string.test_results_save_error, Toast.LENGTH_SHORT).show();
    }
}
