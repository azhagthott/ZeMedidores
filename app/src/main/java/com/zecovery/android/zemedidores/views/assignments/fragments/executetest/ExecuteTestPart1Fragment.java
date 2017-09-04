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

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.models.TestParte1;
import com.zecovery.android.zemedidores.views.assignments.fragments.TokenListener;

import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT_EXECUTE_TEST_1;
import static com.zecovery.android.zemedidores.data.Constant.TAG;

public class ExecuteTestPart1Fragment extends Fragment implements View.OnClickListener {

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

    private LocalDatabase db;

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

        db = new LocalDatabase(getContext());

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
    public void onClick(View v) {

        if (v.getId() == R.id.saveButton) {

            TestParte1 test = new TestParte1();

            if (test1YesRadioButton.isChecked()) {
                test.setTest1("SI");
            } else if (test1NoRadioButton.isChecked()) {
                test.setTest1("NO");
            } else {
                test.setTest1("No envia respuesta");
            }


            if (test2YesRadioButton.isChecked()) {
                test.setTest2("SI");
            } else if (test2NoRadioButton.isChecked()) {
                test.setTest2("NO");
            } else {
                test.setTest2("No envia respuesta");
            }


            if (test3YesRadioButton.isChecked()) {
                test.setTest3("SI");
            } else if (test3NoRadioButton.isChecked()) {
                test.setTest3("NO");
            } else {
                test.setTest2("No envia respuesta");
            }


            if (case1PositiveRadioButton.isChecked()) {
                test.setUsoImanes("SI");
            } else if (case1NegativeRadioButton.isChecked()) {
                test.setUsoImanes("NO");
            } else {
                test.setUsoImanes("No envia respuesta");
            }


            if (case2PositiveRadioButton.isChecked()) {
                test.setInvertirTomas("SI");
            } else if (case2NegativeRadioButton.isChecked()) {
                test.setInvertirTomas("NO");
            } else {
                test.setInvertirTomas("No envia respuesta");
            }


            if (case3PositiveRadioButton.isChecked()) {
                test.setPerforaCupula("SI");
            } else if (case3NegativeRadioButton.isChecked()) {
                test.setPerforaCupula("NO");
            } else {
                test.setPerforaCupula("No envia respuesta");
            }


            if (case4PositiveRadioButton.isChecked()) {
                test.setCortaEngranaje("SI");
            } else if (case4NegativeRadioButton.isChecked()) {
                test.setCortaEngranaje("NO");
            } else {
                test.setCortaEngranaje("No envia respuesta");
            }


            if (case5PositiveRadioButton.isChecked()) {
                test.setUsoAlambres("SI");
            } else if (case5NegativeRadioButton.isChecked()) {
                test.setUsoAlambres("NO");
            } else {
                test.setUsoAlambres("No envia respuestaO");
            }


            if (case6PositiveRadioButton.isChecked()) {
                test.setPrensado("SI");
            } else if (case6NegativeRadioButton.isChecked()) {
                test.setPrensado("NO");
            } else {
                test.setPrensado("No envia respuesta");
            }


            if (case7PositiveRadioButton.isChecked()) {
                test.setOtro("SI");
            } else if (case7NegativeRadioButton.isChecked()) {
                test.setOtro("NO");
            } else {
                test.setOtro("No envia respuesta");
            }

            if (case8PositiveRadioButton.isChecked()) {
                test.setInstalacionParalela("SI");
            } else if (case8NegativeRadioButton.isChecked()) {
                test.setInstalacionParalela("NO");
            } else {
                test.setInstalacionParalela("No envia respuesta");
            }

            if (case9PositiveRadioButton.isChecked()) {
                test.setBypass("SI");
            } else if (case9NegativeRadioButton.isChecked()) {
                test.setBypass("NO");
            } else {
                test.setBypass("No envia respuesta");
            }

            if (case10PositiveRadioButton.isChecked()) {
                test.setOtro2("SI");
            } else if (case10NegativeRadioButton.isChecked()) {
                test.setOtro2("NO");
            } else {
                test.setOtro2("No envia respuesta");
            }

            db.guardaDatosTestParte1(test, token);

            callback.tokenToExecuteTestPart2(token);
        }
    }
}
