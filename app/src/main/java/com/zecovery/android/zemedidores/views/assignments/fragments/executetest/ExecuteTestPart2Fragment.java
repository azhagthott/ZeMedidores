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
import com.zecovery.android.zemedidores.views.assignments.fragments.TokenListener;

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

    private int token;

    private TokenListener callback;

    public ExecuteTestPart2Fragment() {
    }

    public ExecuteTestPart2Fragment newInstance(int token) {
        ExecuteTestPart2Fragment executeTestFragment = new ExecuteTestPart2Fragment();
        Bundle args = new Bundle();
        args.putInt(ID_ASSIGNMENT_EXECUTE_TEST_2, token);
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
        token = getArguments().getInt(ID_ASSIGNMENT_EXECUTE_TEST_2);
        findViews(view);
        Log.d(TAG, "ExecuteTestPart2Fragment");
        Log.d(TAG, "token: " + token);
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

        if (classARadioButton.isChecked()) {

        } else if (classBRadioButton.isChecked()) {

        } else {

        }

        String year = yearEditText.getText().toString();
        String model = modelEditText.getText().toString();


        if (registerType1RadioButton.isChecked()) {

        } else {

        }

        if (position1RadioButton.isChecked()) {

        } else {

        }

        if (beforeYesRadioButton.isChecked()) {

        } else {

        }


        if (afterYesRadioButton.isChecked()) {

        } else {

        }

        if (alternativeYesRadioButton.isChecked()) {

        } else {

        }

        if (typeYesRadioButton.isChecked()) {

        } else {

        }

        callback.tokenToExecuteTestPart3(token);
        //new SendTestResults(this).save(token);
    }

    @Override
    public void resultsSaved() {
        callback.tokenToExecuteTestPart3(token);
    }

    @Override
    public void resultsNotSaved() {
        Toast.makeText(getContext(), R.string.test_results_save_error, Toast.LENGTH_SHORT).show();
    }
}
