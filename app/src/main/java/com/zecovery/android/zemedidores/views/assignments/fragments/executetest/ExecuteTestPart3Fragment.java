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

    private int token;

    private TokenListener callback;

    public ExecuteTestPart3Fragment() {
    }

    public ExecuteTestPart3Fragment newInstance(int token) {
        ExecuteTestPart3Fragment executeTestFragment = new ExecuteTestPart3Fragment();
        Bundle args = new Bundle();
        args.putInt(ID_ASSIGNMENT_EXECUTE_TEST_3, token);
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

        token = getArguments().getInt(ID_ASSIGNMENT_EXECUTE_TEST_3);

        findViews(view);

        Log.d(TAG, "ExecuteTestPart3Fragment");
        Log.d(TAG, "token: " + token);

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

        } else {

        }

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
