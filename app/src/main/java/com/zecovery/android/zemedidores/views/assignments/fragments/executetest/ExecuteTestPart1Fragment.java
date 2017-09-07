package com.zecovery.android.zemedidores.views.assignments.fragments.executetest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.google.firebase.crash.FirebaseCrash;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.models.Foto;
import com.zecovery.android.zemedidores.models.TestParte1;
import com.zecovery.android.zemedidores.views.assignments.fragments.TokenListener;

import static com.zecovery.android.zemedidores.data.Constant.FOLDER_ZE_MEDIDORES;
import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT_EXECUTE_TEST_1;
import static com.zecovery.android.zemedidores.data.Constant.RESIZE_PHOTO_PIXELS_PERCENTAGE;
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

    private ImageButton intervensionRedFoto;
    private ImageButton bypassFoto;
    private ImageButton otroFoto;
    private Button saveButton;

    private LocalDatabase db;

    private MagicalCamera magicalCamera;
    private String photoName;
    private String localPath;

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
        intervensionRedFoto.setOnClickListener(this);
        bypassFoto.setOnClickListener(this);
        otroFoto.setOnClickListener(this);

        intervensionRedFoto.setVisibility(View.INVISIBLE);
        bypassFoto.setVisibility(View.INVISIBLE);
        otroFoto.setVisibility(View.INVISIBLE);

        case8PositiveRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    intervensionRedFoto.setVisibility(View.VISIBLE);
                } else {
                    intervensionRedFoto.setVisibility(View.INVISIBLE);
                }
            }
        });

        case9PositiveRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bypassFoto.setVisibility(View.VISIBLE);
                } else {
                    bypassFoto.setVisibility(View.INVISIBLE);
                }
            }
        });

        case10PositiveRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    otroFoto.setVisibility(View.VISIBLE);
                } else {
                    otroFoto.setVisibility(View.INVISIBLE);
                }
            }
        });

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
        intervensionRedFoto = view.findViewById(R.id.intervensionRedFoto);
        bypassFoto = view.findViewById(R.id.bypassFoto);
        otroFoto = view.findViewById(R.id.otroFoto);
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

        int id = v.getId();

        if (id == R.id.intervensionRedFoto) {
            callCamera("intervension_red");
        }

        if (id == R.id.bypassFoto) {
            callCamera("bypass");
        }

        if (id == R.id.otroFoto) {
            callCamera("otro");
        }

        if (id == R.id.saveButton) {

            TestParte1 test = new TestParte1();

            if (test1YesRadioButton.isChecked()) {
                test.setTest1("SI");
            } else if (test1NoRadioButton.isChecked()) {
                test.setTest1("NO");
            } else {
                test.setTest1("No enviar respuesta");
            }

            if (test2YesRadioButton.isChecked()) {
                test.setTest2("SI");
            } else if (test2NoRadioButton.isChecked()) {
                test.setTest2("NO");
            } else {
                test.setTest2("No enviar respuesta");
            }

            if (test3YesRadioButton.isChecked()) {
                test.setTest3("SI");
            } else if (test3NoRadioButton.isChecked()) {
                test.setTest3("NO");
            } else {
                test.setTest2("No enviar respuesta");
            }

            if (case1PositiveRadioButton.isChecked()) {
                test.setUsoImanes("SI");
            } else if (case1NegativeRadioButton.isChecked()) {
                test.setUsoImanes("NO");
            } else {
                test.setUsoImanes("No enviar respuesta");
            }

            if (case2PositiveRadioButton.isChecked()) {
                test.setInvertirTomas("SI");
            } else if (case2NegativeRadioButton.isChecked()) {
                test.setInvertirTomas("NO");
            } else {
                test.setInvertirTomas("No enviar respuesta");
            }

            if (case3PositiveRadioButton.isChecked()) {
                test.setPerforaCupula("SI");
            } else if (case3NegativeRadioButton.isChecked()) {
                test.setPerforaCupula("NO");
            } else {
                test.setPerforaCupula("No enviar respuesta");
            }

            if (case4PositiveRadioButton.isChecked()) {
                test.setCortaEngranaje("SI");
            } else if (case4NegativeRadioButton.isChecked()) {
                test.setCortaEngranaje("NO");
            } else {
                test.setCortaEngranaje("No enviar respuesta");
            }

            if (case5PositiveRadioButton.isChecked()) {
                test.setUsoAlambres("SI");
            } else if (case5NegativeRadioButton.isChecked()) {
                test.setUsoAlambres("NO");
            } else {
                test.setUsoAlambres("No enviar respuestaO");
            }

            if (case6PositiveRadioButton.isChecked()) {
                test.setPrensado("SI");
            } else if (case6NegativeRadioButton.isChecked()) {
                test.setPrensado("NO");
            } else {
                test.setPrensado("No enviar respuesta");
            }

            if (case7PositiveRadioButton.isChecked()) {
                test.setOtro("SI");
            } else if (case7NegativeRadioButton.isChecked()) {
                test.setOtro("NO");
            } else {
                test.setOtro("No enviar respuesta");
            }

            if (case8PositiveRadioButton.isChecked()) {
                test.setInstalacionParalela("SI");
            } else if (case8NegativeRadioButton.isChecked()) {
                test.setInstalacionParalela("NO");
            } else {
                test.setInstalacionParalela("No enviar respuesta");
            }

            if (case9PositiveRadioButton.isChecked()) {
                test.setBypass("SI");
            } else if (case9NegativeRadioButton.isChecked()) {
                test.setBypass("NO");
            } else {
                test.setBypass("No enviar respuesta");
            }

            if (case10PositiveRadioButton.isChecked()) {
                test.setOtro2("SI");
            } else if (case10NegativeRadioButton.isChecked()) {
                test.setOtro2("NO");
            } else {
                test.setOtro2("No enviar respuesta");
            }

            db.guardaDatosTestParte1(test, token);

            callback.tokenToExecuteTestPart2(token);
        }
    }


    private Intent callCamera(String photoName) {

        this.photoName = photoName;

        String[] permissions = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        MagicalPermissions magicalPermissions = new MagicalPermissions(getActivity(), permissions);
        magicalCamera = new MagicalCamera(getActivity(), RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);
        magicalCamera.takeFragmentPhoto(this);
        return new Intent();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        magicalCamera.resultPhoto(requestCode, resultCode, data);
        Foto foto = new Foto();

        try {
            localPath = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(), photoName, token + "/" + FOLDER_ZE_MEDIDORES, MagicalCamera.PNG, true);

            switch (photoName) {
                case "intervension_red":
                    foto.setIntervencionRed(localPath);
                    db.guardaFoto(foto, token);
                    break;
                case "bypass":
                    foto.setBypass(localPath);
                    db.guardaFoto(foto, token);
                    break;
                case "otro":
                    foto.setOtro(localPath);
                    db.guardaFoto(foto, token);
                    break;
            }

        } catch (Exception e) {
            FirebaseCrash.log("Error: " + e);
        }


    }

}
