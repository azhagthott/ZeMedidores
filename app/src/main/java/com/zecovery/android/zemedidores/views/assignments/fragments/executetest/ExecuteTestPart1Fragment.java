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
import android.widget.RadioButton;
import android.widget.Toast;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.views.assignments.fragments.TokenListener;

import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT_EXECUTE_TEST_1;
import static com.zecovery.android.zemedidores.data.Constant.TAG;

public class ExecuteTestPart1Fragment extends Fragment implements TestResultsCallback, View.OnClickListener {

    private int token;

    private RadioButton test1YesRadioButton;
    private RadioButton test1NoRadioButton;
    private RadioButton test2YesRadioButton;
    private RadioButton test2NoRadioButton;
    private RadioButton test3YesRadioButton;
    private RadioButton test3NoRadioButton;

    private RadioButton case1PositiveRadioButton;
    private RadioButton case1NegativeRadioButton;
    private RadioButton case2PositiveRadioButton;
    private RadioButton case2NegativeRadioButton;
    private RadioButton case3PositiveRadioButton;
    private RadioButton case3NegativeRadioButton;
    private RadioButton case4PositiveRadioButton;
    private RadioButton case4NegativeRadioButton;
    private RadioButton case5PositiveRadioButton;
    private RadioButton case5NegativeRadioButton;
    private RadioButton case6PositiveRadioButton;
    private RadioButton case6NegativeRadioButton;
    private RadioButton case7PositiveRadioButton;
    private RadioButton case7NegativeRadioButton;
    private RadioButton case8PositiveRadioButton;
    private RadioButton case8NegativeRadioButton;
    private RadioButton case9PositiveRadioButton;
    private RadioButton case9NegativeRadioButton;
    private RadioButton case10PositiveRadioButton;
    private RadioButton case10NegativeRadioButton;

    private Button saveButton;

    private TokenListener callback;

    public ExecuteTestPart1Fragment() {
    }

    public ExecuteTestPart1Fragment newInstance(int token) {
        ExecuteTestPart1Fragment executeTestFragment = new ExecuteTestPart1Fragment();
        Bundle args = new Bundle();
        args.putInt(ID_ASSIGNMENT_EXECUTE_TEST_1, token);
        executeTestFragment.setArguments(args);
        return executeTestFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_execute_test_part1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);

        token = getArguments().getInt(ID_ASSIGNMENT_EXECUTE_TEST_1);

        Log.d(TAG, "ExecuteTestPart1Fragment");
        Log.d(TAG, "token: " + token);

        saveButton.setOnClickListener(this);
    }

    private void findViews(View view) {

        test1YesRadioButton = view.findViewById(R.id.test1YesRadioButton);
        test1NoRadioButton = view.findViewById(R.id.test1NoRadioButton);

        test2YesRadioButton = view.findViewById(R.id.test2YesRadioButton);
        test2NoRadioButton = view.findViewById(R.id.test2NoRadioButton);

        test3YesRadioButton = view.findViewById(R.id.test3YesRadioButton);
        test3NoRadioButton = view.findViewById(R.id.test3NoRadioButton);

        case1PositiveRadioButton = view.findViewById(R.id.case1PositiveRadioButton);
        case1NegativeRadioButton = view.findViewById(R.id.case1NegativeRadioButton);

        case2PositiveRadioButton = view.findViewById(R.id.case2PositiveRadioButton);
        case2NegativeRadioButton = view.findViewById(R.id.case2NegativeRadioButton);

        case3PositiveRadioButton = view.findViewById(R.id.case3PositiveRadioButton);
        case3NegativeRadioButton = view.findViewById(R.id.case3NegativeRadioButton);

        case4PositiveRadioButton = view.findViewById(R.id.case4PositiveRadioButton);
        case4NegativeRadioButton = view.findViewById(R.id.case4NegativeRadioButton);

        case5PositiveRadioButton = view.findViewById(R.id.case5PositiveRadioButton);
        case5NegativeRadioButton = view.findViewById(R.id.case5NegativeRadioButton);

        case6PositiveRadioButton = view.findViewById(R.id.case6PositiveRadioButton);
        case6NegativeRadioButton = view.findViewById(R.id.case6NegativeRadioButton);

        case7PositiveRadioButton = view.findViewById(R.id.case7PositiveRadioButton);
        case7NegativeRadioButton = view.findViewById(R.id.case7NegativeRadioButton);

        case8PositiveRadioButton = view.findViewById(R.id.case8PositiveRadioButton);
        case8NegativeRadioButton = view.findViewById(R.id.case8NegativeRadioButton);

        case9PositiveRadioButton = view.findViewById(R.id.case9PositiveRadioButton);
        case9NegativeRadioButton = view.findViewById(R.id.case9NegativeRadioButton);

        case10PositiveRadioButton = view.findViewById(R.id.case10PositiveRadioButton);
        case10NegativeRadioButton = view.findViewById(R.id.case10NegativeRadioButton);

        saveButton = view.findViewById(R.id.saveButton);
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
    public void resultsSaved() {
        Toast.makeText(getContext(), R.string.test_results_saved, Toast.LENGTH_SHORT).show();
        callback.tokenToExecuteTestPart2(token);
    }

    @Override
    public void resultsNotSaved() {
        Toast.makeText(getContext(), R.string.test_results_save_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.saveButton) {

            if (test1YesRadioButton.isChecked()) {

            } else {

            }


            if (test2YesRadioButton.isChecked()) {

            } else {

            }

            if (test3YesRadioButton.isChecked()) {

            } else {


            }


            if (case1PositiveRadioButton.isChecked()) {
            } else {

            }

            if (case2PositiveRadioButton.isChecked()) {

            } else {

            }

            if (case3PositiveRadioButton.isChecked()) {

            } else {

            }

            if (case4PositiveRadioButton.isChecked()) {

            } else {

            }

            if (case5PositiveRadioButton.isChecked()) {

            } else {

            }

            if (case6PositiveRadioButton.isChecked()) {

            } else {

            }

            if (case7PositiveRadioButton.isChecked()) {

            } else {

            }

            if (case8PositiveRadioButton.isChecked()) {

            } else {

            }

            if (case9PositiveRadioButton.isChecked()) {

            } else {

            }

            if (case10PositiveRadioButton.isChecked()) {

            } else {

            }

            callback.tokenToExecuteTestPart2(token);
        }
    }
}
