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
import com.zecovery.android.zemedidores.data.CurrentUser;
import com.zecovery.android.zemedidores.data.Nodes;
import com.zecovery.android.zemedidores.views.assignments.fragments.TokenListener;

import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT_EXECUTE_TEST_1;
import static com.zecovery.android.zemedidores.data.Constant.TAG;

public class ExecuteTestPart1Fragment extends Fragment implements TestResultsCallback, View.OnClickListener {

    private String token;

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

    public ExecuteTestPart1Fragment newInstance(String pushKey) {
        ExecuteTestPart1Fragment executeTestFragment = new ExecuteTestPart1Fragment();
        Bundle args = new Bundle();
        args.putString(ID_ASSIGNMENT_EXECUTE_TEST_1, pushKey);
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

        token = getArguments().getString(ID_ASSIGNMENT_EXECUTE_TEST_1);

        Log.d(TAG, "ExecuteTestPart1Fragment");
        Log.d(TAG, "pushKey: " + token);

        saveButton.setOnClickListener(this);
    }

    private void findViews(View view) {

        test1YesRadioButton = (RadioButton) view.findViewById(R.id.test1YesRadioButton);
        test1NoRadioButton = (RadioButton) view.findViewById(R.id.test1NoRadioButton);

        test2YesRadioButton = (RadioButton) view.findViewById(R.id.test2YesRadioButton);
        test2NoRadioButton = (RadioButton) view.findViewById(R.id.test2NoRadioButton);

        test3YesRadioButton = (RadioButton) view.findViewById(R.id.test3YesRadioButton);
        test3NoRadioButton = (RadioButton) view.findViewById(R.id.test3NoRadioButton);

        case1PositiveRadioButton = (RadioButton) view.findViewById(R.id.case1PositiveRadioButton);
        case1NegativeRadioButton = (RadioButton) view.findViewById(R.id.case1NegativeRadioButton);

        case2PositiveRadioButton = (RadioButton) view.findViewById(R.id.case2PositiveRadioButton);
        case2NegativeRadioButton = (RadioButton) view.findViewById(R.id.case2NegativeRadioButton);

        case3PositiveRadioButton = (RadioButton) view.findViewById(R.id.case3PositiveRadioButton);
        case3NegativeRadioButton = (RadioButton) view.findViewById(R.id.case3NegativeRadioButton);

        case4PositiveRadioButton = (RadioButton) view.findViewById(R.id.case4PositiveRadioButton);
        case4NegativeRadioButton = (RadioButton) view.findViewById(R.id.case4NegativeRadioButton);

        case5PositiveRadioButton = (RadioButton) view.findViewById(R.id.case5PositiveRadioButton);
        case5NegativeRadioButton = (RadioButton) view.findViewById(R.id.case5NegativeRadioButton);

        case6PositiveRadioButton = (RadioButton) view.findViewById(R.id.case6PositiveRadioButton);
        case6NegativeRadioButton = (RadioButton) view.findViewById(R.id.case6NegativeRadioButton);

        case7PositiveRadioButton = (RadioButton) view.findViewById(R.id.case7PositiveRadioButton);
        case7NegativeRadioButton = (RadioButton) view.findViewById(R.id.case7NegativeRadioButton);

        case8PositiveRadioButton = (RadioButton) view.findViewById(R.id.case8PositiveRadioButton);
        case8NegativeRadioButton = (RadioButton) view.findViewById(R.id.case8NegativeRadioButton);

        case9PositiveRadioButton = (RadioButton) view.findViewById(R.id.case9PositiveRadioButton);
        case9NegativeRadioButton = (RadioButton) view.findViewById(R.id.case9NegativeRadioButton);

        case10PositiveRadioButton = (RadioButton) view.findViewById(R.id.case10PositiveRadioButton);
        case10NegativeRadioButton = (RadioButton) view.findViewById(R.id.case10NegativeRadioButton);

        saveButton = (Button) view.findViewById(R.id.saveButton);
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
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("test_1").setValue("SI");
            } else if (test1NoRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("test_1").setValue("NO");
            } else {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("test_1").setValue("");
            }

            if (test2YesRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("test_2").setValue("SI");
            } else if (test2NoRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("test_2").setValue("NO");
            } else {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("test_2").setValue("");
            }

            if (test3YesRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("test_3").setValue("SI");
            } else if (test3NoRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("test_3").setValue("NO");
            } else {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("test_3").setValue("");
            }

            if (case1PositiveRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_1").setValue("SI");
            } else if (case1NegativeRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_1").setValue("NO");
            } else {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_1").setValue("");
            }

            if (case2PositiveRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_2").setValue("SI");
            } else if (case2NegativeRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_2").setValue("NO");
            } else {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_2").setValue("");
            }

            if (case3PositiveRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_3").setValue("SI");
            } else if (case3NegativeRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_3").setValue("NO");
            } else {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_3").setValue("");
            }

            if (case4PositiveRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_4").setValue("SI");
            } else if (case4NegativeRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_4").setValue("NO");
            } else {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_4").setValue("");
            }

            if (case5PositiveRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_5").setValue("SI");
            } else if (case5NegativeRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_5").setValue("NO");
            } else {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_5").setValue("");
            }

            if (case6PositiveRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_6").setValue("SI");
            } else if (case6NegativeRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_6").setValue("NO");
            } else {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_6").setValue("");
            }

            if (case7PositiveRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_7").setValue("SI");
            } else if (case7NegativeRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_7").setValue("NO");
            } else {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_7").setValue("");
            }

            if (case8PositiveRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_8").setValue("SI");
            } else if (case8NegativeRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_8").setValue("NO");
            } else {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_8").setValue("");
            }

            if (case9PositiveRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_9").setValue("SI");
            } else if (case9NegativeRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_9").setValue("NO");
            } else {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_9").setValue("");
            }

            if (case10PositiveRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_10").setValue("SI");
            } else if (case10NegativeRadioButton.isChecked()) {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_10").setValue("NO");
            } else {
                new Nodes().testResults(new CurrentUser().uid()).child(token).child("caso_10").setValue("");
            }

            callback.tokenToExecuteTestPart2(token);
        }
        //new SendTestResults(this).save(token);
    }
}
