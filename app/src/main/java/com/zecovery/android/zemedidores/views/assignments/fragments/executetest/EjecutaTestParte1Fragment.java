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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.google.firebase.crash.FirebaseCrash;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.models.Foto;
import com.zecovery.android.zemedidores.models.TestParte1;
import com.zecovery.android.zemedidores.views.assignments.fragments.IdInspeccionListener;

import static com.zecovery.android.zemedidores.data.Constant.FOLDER_ZE_MEDIDORES;
import static com.zecovery.android.zemedidores.data.Constant.ID_INSPECCION_EJECUTA_TEST_1;
import static com.zecovery.android.zemedidores.data.Constant.RESIZE_PHOTO_PIXELS_PERCENTAGE;
import static com.zecovery.android.zemedidores.data.Constant.TAG;

public class EjecutaTestParte1Fragment extends Fragment implements View.OnClickListener {

    private RadioButton test11YesRadioButton;
    private RadioButton test11NoRadioButton;
    private RadioButton test12YesRadioButton;
    private RadioButton test12NoRadioButton;
    private RadioButton test13YesRadioButton;
    private RadioButton test13NoRadioButton;
    private RadioButton test14YesRadioButton;
    private RadioButton test14NoRadioButton;
    private RadioButton test15YesRadioButton;
    private RadioButton test15NoRadioButton;

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
    private EditText case7EditText;
    private RadioButton case8PositiveRadioButton;
    private RadioButton case8NegativeRadioButton;
    private RadioButton case9PositiveRadioButton;
    private RadioButton case9NegativeRadioButton;
    private RadioButton case10PositiveRadioButton;
    private RadioButton case10NegativeRadioButton;

    private ImageButton intervensionRedFoto;
    private ImageButton bypassFoto;
    private ImageButton otroFoto;
    private EditText otroFotoEditText;
    private Button saveButton;

    private LocalDatabase db;

    private MagicalCamera magicalCamera;
    private String photoName;
    private String localPath;

    private int idInspeccion;

    private IdInspeccionListener callback;

    public EjecutaTestParte1Fragment() {
    }

    public EjecutaTestParte1Fragment newInstance(int idInspeccion) {
        EjecutaTestParte1Fragment executeTestFragment = new EjecutaTestParte1Fragment();
        Bundle args = new Bundle();
        args.putInt(ID_INSPECCION_EJECUTA_TEST_1, idInspeccion);
        executeTestFragment.setArguments(args);
        return executeTestFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ejecuta_test_parte1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);

        idInspeccion = getArguments().getInt(ID_INSPECCION_EJECUTA_TEST_1);

        Log.d(TAG, "EjecutaTestParte1Fragment");
        Log.d(TAG, "idInspeccion: " + idInspeccion);

        db = new LocalDatabase(getContext());

        saveButton.setOnClickListener(this);
        intervensionRedFoto.setOnClickListener(this);
        bypassFoto.setOnClickListener(this);
        otroFoto.setOnClickListener(this);

        intervensionRedFoto.setVisibility(View.INVISIBLE);
        bypassFoto.setVisibility(View.INVISIBLE);
        otroFoto.setVisibility(View.INVISIBLE);

        case7EditText.setVisibility(View.GONE);
        otroFotoEditText.setVisibility(View.GONE);

        case7PositiveRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    case7EditText.setVisibility(View.VISIBLE);
                    Log.d("case 7", "VISIBLE");
                } else {
                    case7EditText.setVisibility(View.GONE);
                    Log.d("case 7", "GONE");
                }
            }
        });

        case8PositiveRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    intervensionRedFoto.setVisibility(View.VISIBLE);
                    Log.d("case 8", "VISIBLE");
                } else {
                    intervensionRedFoto.setVisibility(View.INVISIBLE);
                    Log.d("case 8", "INVISIBLE");
                }
            }
        });

        case9PositiveRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    bypassFoto.setVisibility(View.VISIBLE);
                    Log.d("case 9", "VISIBLE");
                } else {
                    bypassFoto.setVisibility(View.INVISIBLE);
                    Log.d("case 9", "INVISIBLE");
                }
            }
        });

        case10PositiveRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    otroFoto.setVisibility(View.VISIBLE);
                    otroFotoEditText.setVisibility(View.VISIBLE);
                    Log.d("case 10", "VISIBLE");
                } else {
                    otroFoto.setVisibility(View.INVISIBLE);
                    otroFotoEditText.setVisibility(View.GONE);
                    Log.d("case 10", "INVISIBLE");
                }
            }
        });

    }

    private void findViews(View view) {
        test11YesRadioButton = view.findViewById(R.id.test11YesRadioButton);
        test11NoRadioButton = view.findViewById(R.id.test11NoRadioButton);
        test12YesRadioButton = view.findViewById(R.id.test12YesRadioButton);
        test12NoRadioButton = view.findViewById(R.id.test12NoRadioButton);
        test13YesRadioButton = view.findViewById(R.id.test13YesRadioButton);
        test13NoRadioButton = view.findViewById(R.id.test13NoRadioButton);
        test14YesRadioButton = view.findViewById(R.id.test14YesRadioButton);
        test14NoRadioButton = view.findViewById(R.id.test14NoRadioButton);
        test15YesRadioButton = view.findViewById(R.id.test15YesRadioButton);
        test15NoRadioButton = view.findViewById(R.id.test15NoRadioButton);

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
        case7EditText = view.findViewById(R.id.case7EditText);
        case8PositiveRadioButton = view.findViewById(R.id.case8PositiveRadioButton);
        case8NegativeRadioButton = view.findViewById(R.id.case8NegativeRadioButton);
        case9PositiveRadioButton = view.findViewById(R.id.case9PositiveRadioButton);
        case9NegativeRadioButton = view.findViewById(R.id.case9NegativeRadioButton);
        case10PositiveRadioButton = view.findViewById(R.id.case10PositiveRadioButton);
        case10NegativeRadioButton = view.findViewById(R.id.case10NegativeRadioButton);
        intervensionRedFoto = view.findViewById(R.id.intervensionRedFoto);
        bypassFoto = view.findViewById(R.id.bypassFoto);
        otroFoto = view.findViewById(R.id.otroFoto);
        otroFotoEditText = view.findViewById(R.id.otroFotoEditText);
        saveButton = view.findViewById(R.id.saveButton);
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

            if (test11YesRadioButton.isChecked()) {
                test.setTest1("SI");
            } else if (test11NoRadioButton.isChecked()) {
                test.setTest1("NO");
            } else {
                test.setTest1("No envia respuesta");
            }

            if (test12YesRadioButton.isChecked()) {
                test.setTest2("SI");
            } else if (test12NoRadioButton.isChecked()) {
                test.setTest2("NO");
            } else {
                test.setTest2("No envia respuesta");
            }

            if (test13YesRadioButton.isChecked()) {
                test.setTest3("SI");
            } else if (test13NoRadioButton.isChecked()) {
                test.setTest3("NO");
            } else {
                test.setTest3("No envia respuesta");
            }

            if (test14YesRadioButton.isChecked()) {
                test.setTest4("SI");
            } else if (test14NoRadioButton.isChecked()) {
                test.setTest4("NO");
            } else {
                test.setTest4("No envia respuesta");
            }

            if (test15YesRadioButton.isChecked()) {
                test.setTest5("SI");
            } else if (test15NoRadioButton.isChecked()) {
                test.setTest5("NO");
            } else {
                test.setTest5("No envia respuesta");
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
                test.setUsoAlambres("No envia respuesta");
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
                test.setOtroText(case7EditText.getText().toString());
            } else if (case7NegativeRadioButton.isChecked()) {
                test.setOtro("NO");
            } else {
                test.setOtro("No envia respuesta");
            }

            if (case8PositiveRadioButton.isChecked()) {
                test.setInstalacionParalela("SI");
            } else if (case8NegativeRadioButton.isChecked()) {
                Log.d("TAG", "onClick: SI");
                test.setInstalacionParalela("NO");
                Log.d("TAG", "onClick: NO");
            } else {
                test.setInstalacionParalela("No envia respuesta");
            }

            if (case9PositiveRadioButton.isChecked()) {
                test.setBypass("SI");
                Log.d("TAG", "onClick: SI");
            } else if (case9NegativeRadioButton.isChecked()) {
                test.setBypass("NO");
                Log.d("TAG", "onClick: NO");
            } else {
                test.setBypass("No envia respuesta");
            }

            if (case10PositiveRadioButton.isChecked()) {
                test.setOtro2("SI");
                Log.d("TAG", "onClick: SI");
                test.setOtroText2(otroFotoEditText.getText().toString());
            } else if (case10NegativeRadioButton.isChecked()) {
                test.setOtro2("NO");
                Log.d("TAG", "onClick: NO");
            } else {
                test.setOtro2("No envia respuesta");
            }

            db.guardaDatosTestParte1(test, idInspeccion);

            callback.IdInspeccionEjecutaTestParte2(idInspeccion);
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
            localPath = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(), photoName, idInspeccion + "/" + FOLDER_ZE_MEDIDORES, MagicalCamera.PNG, true);

            switch (photoName) {
                case "intervension_red":
                    foto.setIntervencionRed(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case "bypass":
                    foto.setBypass(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case "otro":
                    foto.setOtro(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
            }

        } catch (Exception e) {
            FirebaseCrash.log("Error: " + e);
        }
    }
}
