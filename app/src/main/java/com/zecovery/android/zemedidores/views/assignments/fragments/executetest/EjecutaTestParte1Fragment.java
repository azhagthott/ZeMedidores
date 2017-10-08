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
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.google.firebase.crash.FirebaseCrash;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.models.Foto;
import com.zecovery.android.zemedidores.models.TestParte1;
import com.zecovery.android.zemedidores.views.assignments.fragments.IdInspeccionListener;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.zecovery.android.zemedidores.data.Constant.BYPASS_1;
import static com.zecovery.android.zemedidores.data.Constant.BYPASS_2;
import static com.zecovery.android.zemedidores.data.Constant.BYPASS_3;
import static com.zecovery.android.zemedidores.data.Constant.CORTA_ENGRANAJE_1;
import static com.zecovery.android.zemedidores.data.Constant.CORTA_ENGRANAJE_2;
import static com.zecovery.android.zemedidores.data.Constant.CORTA_ENGRANAJE_3;
import static com.zecovery.android.zemedidores.data.Constant.FOLDER_ZE_MEDIDORES;
import static com.zecovery.android.zemedidores.data.Constant.ID_INSPECCION_EJECUTA_TEST_1;
import static com.zecovery.android.zemedidores.data.Constant.INSTALACION_PARALELA_1;
import static com.zecovery.android.zemedidores.data.Constant.INSTALACION_PARALELA_2;
import static com.zecovery.android.zemedidores.data.Constant.INSTALACION_PARALELA_3;
import static com.zecovery.android.zemedidores.data.Constant.INVERTIR_TOMAS_1;
import static com.zecovery.android.zemedidores.data.Constant.INVERTIR_TOMAS_2;
import static com.zecovery.android.zemedidores.data.Constant.INVERTIR_TOMAS_3;
import static com.zecovery.android.zemedidores.data.Constant.NO_RESPONDE;
import static com.zecovery.android.zemedidores.data.Constant.OTROS_1_1;
import static com.zecovery.android.zemedidores.data.Constant.OTROS_1_2;
import static com.zecovery.android.zemedidores.data.Constant.OTROS_1_3;
import static com.zecovery.android.zemedidores.data.Constant.OTROS_2_1;
import static com.zecovery.android.zemedidores.data.Constant.OTROS_2_2;
import static com.zecovery.android.zemedidores.data.Constant.OTROS_2_3;
import static com.zecovery.android.zemedidores.data.Constant.PERFORA_CUPULA_1;
import static com.zecovery.android.zemedidores.data.Constant.PERFORA_CUPULA_2;
import static com.zecovery.android.zemedidores.data.Constant.PERFORA_CUPULA_3;
import static com.zecovery.android.zemedidores.data.Constant.PRENSADO_1;
import static com.zecovery.android.zemedidores.data.Constant.PRENSADO_2;
import static com.zecovery.android.zemedidores.data.Constant.PRENSADO_3;
import static com.zecovery.android.zemedidores.data.Constant.RESIZE_PHOTO_PIXELS_PERCENTAGE;
import static com.zecovery.android.zemedidores.data.Constant.RESPONDE_NO;
import static com.zecovery.android.zemedidores.data.Constant.RESPONDE_SI;
import static com.zecovery.android.zemedidores.data.Constant.USO_ALAMBRE_1;
import static com.zecovery.android.zemedidores.data.Constant.USO_ALAMBRE_2;
import static com.zecovery.android.zemedidores.data.Constant.USO_ALAMBRE_3;
import static com.zecovery.android.zemedidores.data.Constant.USO_IMANES_1;
import static com.zecovery.android.zemedidores.data.Constant.USO_IMANES_2;
import static com.zecovery.android.zemedidores.data.Constant.USO_IMANES_3;

public class EjecutaTestParte1Fragment extends Fragment implements View.OnClickListener {

    /* Test 1 parte 1*/
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

    /* Test 1 parte 2*/
    private RadioButton usoImanesPositivoRb;
    private RadioButton usoImanesNegativoRb;
    private RadioButton invertirTomasPositivoRb;
    private RadioButton invertirTomasNegativoRb;
    private RadioButton perforaCupulaPositivoRb;
    private RadioButton perforaCupulaNegativoRb;
    private RadioButton cortaEngranajePositivoRb;
    private RadioButton cortaEngranajeNegativoRb;
    private RadioButton usoAlambrePositivoRb;
    private RadioButton usoAlambreNegativoRb;
    private RadioButton prensadoPositivoRb;
    private RadioButton prensadoNegativoRb;
    private RadioButton otro12PositivoRb;
    private RadioButton otro12NegativoRb;
    private EditText otro12EditText;

    /* Test 1 parte 3*/
    private RadioButton case8PositiveRadioButton;
    private RadioButton case8NegativeRadioButton;
    private RadioButton case9PositiveRadioButton;
    private RadioButton case9NegativeRadioButton;
    private RadioButton case10PositiveRadioButton;
    private RadioButton case10NegativeRadioButton;

    private LinearLayout ocultaFotosImanesLl;
    private LinearLayout ocultaFotosInvertirTomasLl;
    private LinearLayout ocultaFotosCupulaLl;
    private LinearLayout ocultaFotosEngranajeLl;
    private LinearLayout ocultaFotosAlambresLl;
    private LinearLayout ocultaFotosPrensadoLl;
    private LinearLayout ocultaFotosOtros12Ll;

    private ImageButton usoImanesFoto1;
    private ImageButton usoImanesFoto2;
    private ImageButton usoImanesFoto3;
    private ImageButton invertirTomasFoto1;
    private ImageButton invertirTomasFoto2;
    private ImageButton invertirTomasFoto3;
    private ImageButton perforaCupulaFoto1;
    private ImageButton perforaCupulaFoto2;
    private ImageButton perforaCupulaFoto3;
    private ImageButton engranajeFoto1;
    private ImageButton engranajeFoto2;
    private ImageButton engranajeFoto3;
    private ImageButton alambresFoto1;
    private ImageButton alambresFoto2;
    private ImageButton alambresFoto3;
    private ImageButton prensadoFoto1;
    private ImageButton prensadoFoto2;
    private ImageButton prensadoFoto3;
    private ImageButton otrosFoto11;
    private ImageButton otrosFoto12;
    private ImageButton otrosFoto13;
    private ImageButton instalacionParalelaFoto1;
    private ImageButton instalacionParalelaFoto2;
    private ImageButton instalacionParalelaFoto3;
    private ImageButton bypassFoto1;
    private ImageButton bypassFoto2;
    private ImageButton bypassFoto3;
    private ImageButton otroFoto21;
    private ImageButton otroFoto22;
    private ImageButton otroFoto23;


    private CircleImageView usoImanesPreview1;
    private CircleImageView usoImanesPreview2;
    private CircleImageView usoImanesPreview3;

    private CircleImageView engranajePreview1;
    private CircleImageView engranajePreview2;
    private CircleImageView engranajePreview3;

    private CircleImageView alambresPreview1;
    private CircleImageView alambresPreview2;
    private CircleImageView alambresPreview3;

    private CircleImageView prensadoPreview1;
    private CircleImageView prensadoPreview2;
    private CircleImageView prensadoPreview3;

    private CircleImageView otros12Preview1;
    private CircleImageView otros12Preview2;
    private CircleImageView otros12Preview3;

    private CircleImageView intervensionRedPreview1;
    private CircleImageView intervensionRedPreview2;
    private CircleImageView intervensionRedPreview3;

    private CircleImageView bypassPreview1;
    private CircleImageView bypassPreview2;
    private CircleImageView bypassPreview3;

    private CircleImageView otroPreview1;
    private CircleImageView otroPreview2;
    private CircleImageView otroPreview3;

    private LinearLayout ocultaFotosInstParalelaLl;
    private LinearLayout ocultaFotosBypassLl;
    private LinearLayout ocultaFotosOtros13Ll;

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
        db = new LocalDatabase(getContext());

        ocultaFotosImanesLl.setVisibility(View.GONE);
        ocultaFotosInvertirTomasLl.setVisibility(View.GONE);
        ocultaFotosCupulaLl.setVisibility(View.GONE);
        ocultaFotosEngranajeLl.setVisibility(View.GONE);
        ocultaFotosAlambresLl.setVisibility(View.GONE);
        ocultaFotosPrensadoLl.setVisibility(View.GONE);
        ocultaFotosOtros12Ll.setVisibility(View.GONE);

        ocultaFotosInstParalelaLl.setVisibility(View.GONE);
        ocultaFotosBypassLl.setVisibility(View.GONE);
        ocultaFotosOtros13Ll.setVisibility(View.GONE);

        saveButton.setOnClickListener(this);

        usoImanesFoto1.setOnClickListener(this);
        usoImanesFoto2.setOnClickListener(this);
        usoImanesFoto3.setOnClickListener(this);
        invertirTomasFoto1.setOnClickListener(this);
        invertirTomasFoto2.setOnClickListener(this);
        invertirTomasFoto3.setOnClickListener(this);
        perforaCupulaFoto1.setOnClickListener(this);
        perforaCupulaFoto2.setOnClickListener(this);
        perforaCupulaFoto3.setOnClickListener(this);
        engranajeFoto1.setOnClickListener(this);
        engranajeFoto2.setOnClickListener(this);
        engranajeFoto3.setOnClickListener(this);
        alambresFoto1.setOnClickListener(this);
        alambresFoto2.setOnClickListener(this);
        alambresFoto3.setOnClickListener(this);
        prensadoFoto1.setOnClickListener(this);
        prensadoFoto2.setOnClickListener(this);
        prensadoFoto3.setOnClickListener(this);
        otrosFoto11.setOnClickListener(this);
        otrosFoto12.setOnClickListener(this);
        otrosFoto13.setOnClickListener(this);

        instalacionParalelaFoto1.setOnClickListener(this);
        instalacionParalelaFoto2.setOnClickListener(this);
        instalacionParalelaFoto3.setOnClickListener(this);

        bypassFoto1.setOnClickListener(this);
        bypassFoto2.setOnClickListener(this);
        bypassFoto3.setOnClickListener(this);

        otroFoto21.setOnClickListener(this);
        otroFoto22.setOnClickListener(this);
        otroFoto23.setOnClickListener(this);

        otro12EditText.setVisibility(View.GONE);
        otroFotoEditText.setVisibility(View.GONE);

        if (db.getDatosTestParte1(idInspeccion).getTest1() != null) {
            if (db.getDatosTestParte1(idInspeccion).getTest1().equals(RESPONDE_SI)) {
                test11YesRadioButton.setChecked(true);
            } else if (db.getDatosTestParte1(idInspeccion).getTest1().equals(RESPONDE_NO)) {
                test11NoRadioButton.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getTest2() != null) {
            if (db.getDatosTestParte1(idInspeccion).getTest2().equals(RESPONDE_SI)) {
                test12YesRadioButton.setChecked(true);
            } else if (db.getDatosTestParte1(idInspeccion).getTest2().equals(RESPONDE_NO)) {
                test12NoRadioButton.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getTest3() != null) {
            if (db.getDatosTestParte1(idInspeccion).getTest3().equals(RESPONDE_SI)) {
                test13YesRadioButton.setChecked(true);
            } else if (db.getDatosTestParte1(idInspeccion).getTest3().equals(RESPONDE_NO)) {
                test13NoRadioButton.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getTest4() != null) {
            if (db.getDatosTestParte1(idInspeccion).getTest4().equals(RESPONDE_SI)) {
                test14YesRadioButton.setChecked(true);
            } else if (db.getDatosTestParte1(idInspeccion).getTest4().equals(RESPONDE_NO)) {
                test14NoRadioButton.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getTest5() != null) {
            if (db.getDatosTestParte1(idInspeccion).getTest5().equals(RESPONDE_SI)) {
                test15YesRadioButton.setChecked(true);
            } else if (db.getDatosTestParte1(idInspeccion).getTest5().equals(RESPONDE_NO)) {
                test15NoRadioButton.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getUsoImanes() != null) {
            if (db.getDatosTestParte1(idInspeccion).getUsoImanes().equals(RESPONDE_SI)) {
                usoImanesPositivoRb.setChecked(true);
            } else if (db.getDatosTestParte1(idInspeccion).getUsoImanes().equals(RESPONDE_NO)) {
                usoImanesNegativoRb.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getInvertirTomas() != null) {
            if (db.getDatosTestParte1(idInspeccion).getInvertirTomas().equals(RESPONDE_SI)) {
                invertirTomasPositivoRb.setChecked(true);
            } else if (db.getDatosTestParte1(idInspeccion).getInvertirTomas().equals(RESPONDE_NO)) {
                invertirTomasNegativoRb.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getPerforaCupula() != null) {
            if (db.getDatosTestParte1(idInspeccion).getPerforaCupula().equals(RESPONDE_SI)) {
                perforaCupulaPositivoRb.setChecked(true);
            } else if (db.getDatosTestParte1(idInspeccion).getPerforaCupula().equals(RESPONDE_NO)) {
                perforaCupulaNegativoRb.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getCortaEngranaje() != null) {
            if (db.getDatosTestParte1(idInspeccion).getCortaEngranaje().equals(RESPONDE_SI)) {
                cortaEngranajePositivoRb.setChecked(true);
            } else if (db.getDatosTestParte1(idInspeccion).getCortaEngranaje().equals(RESPONDE_NO)) {
                cortaEngranajeNegativoRb.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getUsoAlambres() != null) {
            if (db.getDatosTestParte1(idInspeccion).getUsoAlambres().equals(RESPONDE_SI)) {
                usoAlambrePositivoRb.setChecked(true);
            } else if (db.getDatosTestParte1(idInspeccion).getUsoAlambres().equals(RESPONDE_NO)) {
                usoAlambreNegativoRb.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getPrensado() != null) {
            if (db.getDatosTestParte1(idInspeccion).getPrensado().equals(RESPONDE_SI)) {
                prensadoPositivoRb.setChecked(true);
            } else if (db.getDatosTestParte1(idInspeccion).getPrensado().equals(RESPONDE_NO)) {
                prensadoNegativoRb.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getOtro() != null) {
            if (db.getDatosTestParte1(idInspeccion).getOtro().equals(RESPONDE_SI)) {
                otro12PositivoRb.setChecked(true);
            } else if (db.getDatosTestParte1(idInspeccion).getOtro().equals(RESPONDE_NO)) {
                otro12NegativoRb.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getOtroText() != null) {
            otro12EditText.setText(db.getDatosTestParte1(idInspeccion).getOtroText());
        }

        ///////////////////////////////////////////////////////////////////////////////////////////

        usoImanesPositivoRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    ocultaFotosImanesLl.setVisibility(View.VISIBLE);
                    Log.d("case 1", "VISIBLE");
                } else {
                    ocultaFotosImanesLl.setVisibility(View.GONE);
                    Log.d("case 1", "GONE");
                }
            }
        });

        invertirTomasPositivoRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    ocultaFotosInvertirTomasLl.setVisibility(View.VISIBLE);
                    Log.d("case 2", "VISIBLE");
                } else {
                    ocultaFotosInvertirTomasLl.setVisibility(View.GONE);
                    Log.d("case 2", "GONE");
                }
            }
        });

        perforaCupulaPositivoRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    ocultaFotosCupulaLl.setVisibility(View.VISIBLE);
                    Log.d("case 2", "VISIBLE");
                } else {
                    ocultaFotosCupulaLl.setVisibility(View.GONE);
                    Log.d("case 2", "GONE");
                }
            }
        });

        cortaEngranajePositivoRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    ocultaFotosEngranajeLl.setVisibility(View.VISIBLE);
                    Log.d("case 2", "VISIBLE");
                } else {
                    ocultaFotosEngranajeLl.setVisibility(View.GONE);
                    Log.d("case 2", "GONE");
                }
            }
        });

        usoAlambrePositivoRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    ocultaFotosAlambresLl.setVisibility(View.VISIBLE);
                    Log.d("case 2", "VISIBLE");
                } else {
                    ocultaFotosAlambresLl.setVisibility(View.GONE);
                    Log.d("case 2", "GONE");
                }
            }
        });

        prensadoPositivoRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    ocultaFotosPrensadoLl.setVisibility(View.VISIBLE);
                    Log.d("case 2", "VISIBLE");
                } else {
                    ocultaFotosPrensadoLl.setVisibility(View.GONE);
                    Log.d("case 2", "GONE");
                }
            }
        });

        otro12PositivoRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    ocultaFotosOtros12Ll.setVisibility(View.VISIBLE);
                    otro12EditText.setVisibility(View.VISIBLE);
                    Log.d("case 7", "VISIBLE");
                } else {
                    ocultaFotosOtros12Ll.setVisibility(View.GONE);
                    otro12EditText.setVisibility(View.GONE);
                    Log.d("case 7", "GONE");
                }
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////////

        case8PositiveRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    ocultaFotosInstParalelaLl.setVisibility(View.VISIBLE);
                    Log.d("case 8", "VISIBLE");
                } else {
                    ocultaFotosInstParalelaLl.setVisibility(View.GONE);
                    Log.d("case 8", "INVISIBLE");
                }
            }
        });

        case9PositiveRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    ocultaFotosBypassLl.setVisibility(View.VISIBLE);
                    Log.d("case 9", "VISIBLE");
                } else {
                    ocultaFotosBypassLl.setVisibility(View.GONE);
                    Log.d("case 9", "INVISIBLE");
                }
            }
        });

        case10PositiveRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    otroFotoEditText.setVisibility(View.VISIBLE);
                    ocultaFotosOtros13Ll.setVisibility(View.VISIBLE);
                    Log.d("case 10", "VISIBLE");
                } else {
                    otroFotoEditText.setVisibility(View.GONE);
                    ocultaFotosOtros13Ll.setVisibility(View.GONE);
                    Log.d("case 10", "INVISIBLE");
                }
            }
        });

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

        switch (id) {

            case R.id.usoImanesFoto1:
                callCamera(USO_IMANES_1);
                break;
            case R.id.usoImanesFoto2:
                callCamera(USO_IMANES_2);
                break;
            case R.id.usoImanesFoto3:
                callCamera(USO_IMANES_3);
                break;
            case R.id.invertirTomasFoto1:
                callCamera(INVERTIR_TOMAS_1);
                break;
            case R.id.invertirTomasFoto2:
                callCamera(INVERTIR_TOMAS_2);
                break;
            case R.id.invertirTomasFoto3:
                callCamera(INVERTIR_TOMAS_3);
                break;
            case R.id.perforaCupulaFoto1:
                callCamera(PERFORA_CUPULA_1);
                break;
            case R.id.perforaCupulaFoto2:
                callCamera(PERFORA_CUPULA_2);
                break;
            case R.id.perforaCupulaFoto3:
                callCamera(PERFORA_CUPULA_3);
                break;
            case R.id.engranajeFoto1:
                callCamera(CORTA_ENGRANAJE_1);
                break;
            case R.id.engranajeFoto2:
                callCamera(CORTA_ENGRANAJE_2);
                break;
            case R.id.engranajeFoto3:
                callCamera(CORTA_ENGRANAJE_3);
                break;
            case R.id.alambresFoto1:
                callCamera(USO_ALAMBRE_1);
                break;
            case R.id.alambresFoto2:
                callCamera(USO_ALAMBRE_2);
                break;
            case R.id.alambresFoto3:
                callCamera(USO_ALAMBRE_3);
                break;
            case R.id.prensadoFoto1:
                callCamera(PRENSADO_1);
                break;
            case R.id.prensadoFoto2:
                callCamera(PRENSADO_2);
                break;
            case R.id.prensadoFoto3:
                callCamera(PRENSADO_3);
                break;
            case R.id.otrosFoto11:
                callCamera(OTROS_1_1);
                break;
            case R.id.otrosFoto12:
                callCamera(OTROS_1_2);
                break;
            case R.id.otrosFoto13:
                callCamera(OTROS_1_3);
                break;
            case R.id.instalacionParalelaFoto1:
                callCamera(INSTALACION_PARALELA_1);
                break;
            case R.id.instalacionParalelaFoto2:
                callCamera(INSTALACION_PARALELA_2);
                break;
            case R.id.instalacionParalelaFoto3:
                callCamera(INSTALACION_PARALELA_3);
                break;
            case R.id.bypassFoto1:
                callCamera(BYPASS_1);
                break;
            case R.id.bypassFoto2:
                callCamera(BYPASS_2);
                break;
            case R.id.bypassFoto3:
                callCamera(BYPASS_3);
                break;
            case R.id.otroFoto21:
                callCamera(OTROS_2_1);
                break;
            case R.id.otroFoto22:
                callCamera(OTROS_2_2);
                break;
            case R.id.otroFoto23:
                callCamera(OTROS_2_3);
                break;
        }

        if (id == R.id.saveButton) {
            TestParte1 test = new TestParte1();

            /*
            Test 1 Parte 1
            */
            if (test11YesRadioButton.isChecked()) {
                test.setTest1(RESPONDE_SI);
            } else if (test11NoRadioButton.isChecked()) {
                test.setTest1(RESPONDE_NO);
            } else {
                test.setTest1(NO_RESPONDE);
            }

            if (test12YesRadioButton.isChecked()) {
                test.setTest2(RESPONDE_SI);
            } else if (test12NoRadioButton.isChecked()) {
                test.setTest2(RESPONDE_NO);
            } else {
                test.setTest2(NO_RESPONDE);
            }

            if (test13YesRadioButton.isChecked()) {
                test.setTest3(RESPONDE_SI);
            } else if (test13NoRadioButton.isChecked()) {
                test.setTest3(RESPONDE_NO);
            } else {
                test.setTest3(NO_RESPONDE);
            }

            if (test14YesRadioButton.isChecked()) {
                test.setTest4(RESPONDE_SI);
            } else if (test14NoRadioButton.isChecked()) {
                test.setTest4(RESPONDE_NO);
            } else {
                test.setTest4(NO_RESPONDE);
            }

            if (test15YesRadioButton.isChecked()) {
                test.setTest5(RESPONDE_SI);
            } else if (test15NoRadioButton.isChecked()) {
                test.setTest5(RESPONDE_NO);
            } else {
                test.setTest5(NO_RESPONDE);
            }

            /*
            Test 1 Parte 2
            */
            if (usoImanesPositivoRb.isChecked()) {
                test.setUsoImanes(RESPONDE_SI);
            } else if (usoImanesNegativoRb.isChecked()) {
                test.setUsoImanes(RESPONDE_NO);
            } else {
                test.setUsoImanes(NO_RESPONDE);
            }

            if (invertirTomasPositivoRb.isChecked()) {
                test.setInvertirTomas(RESPONDE_SI);
            } else if (invertirTomasNegativoRb.isChecked()) {
                test.setInvertirTomas(RESPONDE_NO);
            } else {
                test.setInvertirTomas(NO_RESPONDE);
            }

            if (perforaCupulaPositivoRb.isChecked()) {
                test.setPerforaCupula(RESPONDE_SI);
            } else if (perforaCupulaNegativoRb.isChecked()) {
                test.setPerforaCupula(RESPONDE_NO);
            } else {
                test.setPerforaCupula(NO_RESPONDE);
            }

            if (cortaEngranajePositivoRb.isChecked()) {
                test.setCortaEngranaje(RESPONDE_SI);
            } else if (cortaEngranajeNegativoRb.isChecked()) {
                test.setCortaEngranaje(RESPONDE_NO);
            } else {
                test.setCortaEngranaje(NO_RESPONDE);
            }

            if (usoAlambrePositivoRb.isChecked()) {
                test.setUsoAlambres(RESPONDE_SI);
            } else if (usoAlambreNegativoRb.isChecked()) {
                test.setUsoAlambres(RESPONDE_NO);
            } else {
                test.setUsoAlambres(NO_RESPONDE);
            }

            if (prensadoPositivoRb.isChecked()) {
                test.setPrensado(RESPONDE_SI);
            } else if (prensadoNegativoRb.isChecked()) {
                test.setPrensado(RESPONDE_NO);
            } else {
                test.setPrensado(NO_RESPONDE);
            }

            if (otro12PositivoRb.isChecked()) {
                test.setOtro(RESPONDE_SI);
                test.setOtroText(otro12EditText.getText().toString());
            } else if (otro12NegativoRb.isChecked()) {
                test.setOtro(RESPONDE_NO);
            } else {
                test.setOtro(NO_RESPONDE);
            }

            /*
            Test 1 Parte 3
            */
            if (case8PositiveRadioButton.isChecked()) {
                test.setInstalacionParalela(RESPONDE_SI);
            } else if (case8NegativeRadioButton.isChecked()) {
                Log.d("TAG", "onClick: SI");
                test.setInstalacionParalela(RESPONDE_NO);
                Log.d("TAG", "onClick: NO");
            } else {
                test.setInstalacionParalela(NO_RESPONDE);
            }

            if (case9PositiveRadioButton.isChecked()) {
                test.setBypass(RESPONDE_SI);
            } else if (case9NegativeRadioButton.isChecked()) {
                test.setBypass(RESPONDE_NO);
            } else {
                test.setBypass(NO_RESPONDE);
            }

            if (case10PositiveRadioButton.isChecked()) {
                test.setOtro2(RESPONDE_SI);
                test.setOtroText2(otroFotoEditText.getText().toString());
            } else if (case10NegativeRadioButton.isChecked()) {
                test.setOtro2(RESPONDE_NO);
            } else {
                test.setOtro2(NO_RESPONDE);
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

                case USO_IMANES_1:
                    foto.setUsoImanes1(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case USO_IMANES_2:
                    foto.setUsoImanes2(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case USO_IMANES_3:
                    foto.setUsoImanes3(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case INVERTIR_TOMAS_1:
                    foto.setInvertirTomas1(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case INVERTIR_TOMAS_2:
                    foto.setInvertirTomas2(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case INVERTIR_TOMAS_3:
                    foto.setInvertirTomas3(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case PERFORA_CUPULA_1:
                    foto.setPerforaCupula1(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case PERFORA_CUPULA_2:
                    foto.setPerforaCupula2(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case PERFORA_CUPULA_3:
                    foto.setPerforaCupula3(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case CORTA_ENGRANAJE_1:
                    foto.setCortaEngrnaje1(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case CORTA_ENGRANAJE_2:
                    foto.setCortaEngrnaje2(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case CORTA_ENGRANAJE_3:
                    foto.setCortaEngrnaje3(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case USO_ALAMBRE_1:
                    foto.setUsoAlambre1(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case USO_ALAMBRE_2:
                    foto.setUsoAlambre2(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case USO_ALAMBRE_3:
                    foto.setUsoAlambre3(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case PRENSADO_1:
                    foto.setPrensado1(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case PRENSADO_2:
                    foto.setPrensado2(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case PRENSADO_3:
                    foto.setPrensado3(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case OTROS_1_1:
                    foto.setOtro11(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case OTROS_1_2:
                    foto.setOtro12(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case OTROS_1_3:
                    foto.setOtro13(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case INSTALACION_PARALELA_1:
                    foto.setInstalacionParalela1(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case INSTALACION_PARALELA_2:
                    foto.setInstalacionParalela2(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case INSTALACION_PARALELA_3:
                    foto.setInstalacionParalela3(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case BYPASS_1:
                    foto.setBypass1(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case BYPASS_2:
                    foto.setBypass2(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case BYPASS_3:
                    foto.setBypass3(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case OTROS_2_1:
                    foto.setOtro21(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case OTROS_2_2:
                    foto.setOtro22(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
                case OTROS_2_3:
                    foto.setOtro23(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    break;
            }

        } catch (Exception e) {
            FirebaseCrash.log("Error: " + e);
        }
    }

    private void findViews(View view) {

        /* Test 1 parte 1 */
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

        /* Test 1 parte 2 */
        usoImanesPositivoRb = view.findViewById(R.id.usoImanesPositivoRb);
        usoImanesNegativoRb = view.findViewById(R.id.usoImanesNegativoRb);
        invertirTomasPositivoRb = view.findViewById(R.id.invertirTomasPositivoRb);
        invertirTomasNegativoRb = view.findViewById(R.id.invertirTomasNegativoRb);
        perforaCupulaPositivoRb = view.findViewById(R.id.perforaCupulaPositivoRb);
        perforaCupulaNegativoRb = view.findViewById(R.id.perforaCupulaNegativoRb);
        cortaEngranajePositivoRb = view.findViewById(R.id.cortaEngranajePositivoRb);
        cortaEngranajeNegativoRb = view.findViewById(R.id.cortaEngranajeNegativoRb);
        usoAlambrePositivoRb = view.findViewById(R.id.usoAlambrePositivoRb);
        usoAlambreNegativoRb = view.findViewById(R.id.usoAlambreNegativoRb);
        prensadoPositivoRb = view.findViewById(R.id.prensadoPositivoRb);
        prensadoNegativoRb = view.findViewById(R.id.prensadoNegativoRb);
        otro12PositivoRb = view.findViewById(R.id.otro12PositivoRb);
        otro12NegativoRb = view.findViewById(R.id.otro12NegativoRb);
        otro12EditText = view.findViewById(R.id.otro12EditText);

        /* Test 1 parte 3 */
        case8PositiveRadioButton = view.findViewById(R.id.case8PositiveRadioButton);
        case8NegativeRadioButton = view.findViewById(R.id.case8NegativeRadioButton);
        case9PositiveRadioButton = view.findViewById(R.id.case9PositiveRadioButton);
        case9NegativeRadioButton = view.findViewById(R.id.case9NegativeRadioButton);
        case10PositiveRadioButton = view.findViewById(R.id.case10PositiveRadioButton);
        case10NegativeRadioButton = view.findViewById(R.id.case10NegativeRadioButton);

        ocultaFotosImanesLl = view.findViewById(R.id.ocultaFotosImanesLl);
        ocultaFotosInvertirTomasLl = view.findViewById(R.id.ocultaFotosInvertirTomasLl);
        ocultaFotosCupulaLl = view.findViewById(R.id.ocultaFotosCupulaLl);
        ocultaFotosEngranajeLl = view.findViewById(R.id.ocultaFotosEngranajeLl);
        ocultaFotosAlambresLl = view.findViewById(R.id.ocultaFotosAlambresLl);
        ocultaFotosPrensadoLl = view.findViewById(R.id.ocultaFotosPrensadoLl);
        ocultaFotosOtros12Ll = view.findViewById(R.id.ocultaFotosOtros12Ll);

        usoImanesPreview1 = view.findViewById(R.id.usoImanesPreview1);
        usoImanesPreview2 = view.findViewById(R.id.usoImanesPreview2);
        usoImanesPreview3 = view.findViewById(R.id.usoImanesPreview3);
        engranajePreview1 = view.findViewById(R.id.engranajePreview1);
        engranajePreview2 = view.findViewById(R.id.engranajePreview2);
        engranajePreview3 = view.findViewById(R.id.engranajePreview3);
        alambresPreview1 = view.findViewById(R.id.alambresPreview1);
        alambresPreview2 = view.findViewById(R.id.alambresPreview2);
        alambresPreview3 = view.findViewById(R.id.alambresPreview3);
        prensadoPreview1 = view.findViewById(R.id.prensadoPreview1);
        prensadoPreview2 = view.findViewById(R.id.prensadoPreview2);
        prensadoPreview3 = view.findViewById(R.id.prensadoPreview3);
        otros12Preview1 = view.findViewById(R.id.otros12Preview1);
        otros12Preview2 = view.findViewById(R.id.otros12Preview2);
        otros12Preview3 = view.findViewById(R.id.otros12Preview3);

        usoImanesFoto1 = view.findViewById(R.id.usoImanesFoto1);
        usoImanesFoto2 = view.findViewById(R.id.usoImanesFoto2);
        usoImanesFoto3 = view.findViewById(R.id.usoImanesFoto3);
        invertirTomasFoto1 = view.findViewById(R.id.invertirTomasFoto1);
        invertirTomasFoto2 = view.findViewById(R.id.invertirTomasFoto2);
        invertirTomasFoto3 = view.findViewById(R.id.invertirTomasFoto3);
        perforaCupulaFoto1 = view.findViewById(R.id.perforaCupulaFoto1);
        perforaCupulaFoto2 = view.findViewById(R.id.perforaCupulaFoto2);
        perforaCupulaFoto3 = view.findViewById(R.id.perforaCupulaFoto3);
        engranajeFoto1 = view.findViewById(R.id.engranajeFoto1);
        engranajeFoto2 = view.findViewById(R.id.engranajeFoto2);
        engranajeFoto3 = view.findViewById(R.id.engranajeFoto3);
        alambresFoto1 = view.findViewById(R.id.alambresFoto1);
        alambresFoto2 = view.findViewById(R.id.alambresFoto2);
        alambresFoto3 = view.findViewById(R.id.alambresFoto3);
        prensadoFoto1 = view.findViewById(R.id.prensadoFoto1);
        prensadoFoto2 = view.findViewById(R.id.prensadoFoto2);
        prensadoFoto3 = view.findViewById(R.id.prensadoFoto3);
        otrosFoto11 = view.findViewById(R.id.otrosFoto11);
        otrosFoto12 = view.findViewById(R.id.otrosFoto12);
        otrosFoto13 = view.findViewById(R.id.otrosFoto13);

        instalacionParalelaFoto1 = view.findViewById(R.id.instalacionParalelaFoto1);
        instalacionParalelaFoto2 = view.findViewById(R.id.instalacionParalelaFoto2);
        instalacionParalelaFoto3 = view.findViewById(R.id.instalacionParalelaFoto3);

        bypassFoto1 = view.findViewById(R.id.bypassFoto1);
        bypassFoto2 = view.findViewById(R.id.bypassFoto2);
        bypassFoto3 = view.findViewById(R.id.bypassFoto3);

        otroFoto21 = view.findViewById(R.id.otroFoto21);
        otroFoto22 = view.findViewById(R.id.otroFoto22);
        otroFoto23 = view.findViewById(R.id.otroFoto23);

        ocultaFotosInstParalelaLl = view.findViewById(R.id.ocultaFotosInstParalelaLl);
        ocultaFotosBypassLl = view.findViewById(R.id.ocultaFotosBypassLl);
        ocultaFotosOtros13Ll = view.findViewById(R.id.ocultaFotosOtros13Ll);

        intervensionRedPreview1 = view.findViewById(R.id.intervensionRedPreview1);
        intervensionRedPreview2 = view.findViewById(R.id.intervensionRedPreview2);
        intervensionRedPreview3 = view.findViewById(R.id.intervensionRedPreview3);

        bypassPreview1 = view.findViewById(R.id.bypassPreview1);
        bypassPreview2 = view.findViewById(R.id.bypassPreview2);
        bypassPreview3 = view.findViewById(R.id.bypassPreview3);

        otroPreview1 = view.findViewById(R.id.otroPreview1);
        otroPreview2 = view.findViewById(R.id.otroPreview2);
        otroPreview3 = view.findViewById(R.id.otroPreview3);

        otroFotoEditText = view.findViewById(R.id.otroFotoEditText);
        saveButton = view.findViewById(R.id.saveButton);
    }
}
