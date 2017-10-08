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

import com.bumptech.glide.Glide;
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
import static com.zecovery.android.zemedidores.data.Constant.NO_ENVIA_RESPUESTA;
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
    private CircleImageView invertirTomasPreview1;
    private CircleImageView invertirTomasPreview2;
    private CircleImageView invertirTomasPreview3;
    private CircleImageView perforaCupulaPreview1;
    private CircleImageView perforaCupulaPreview2;
    private CircleImageView perforaCupulaPreview3;
    private CircleImageView engranajePreview1;
    private CircleImageView engranajePreview2;
    private CircleImageView engranajePreview3;
    private CircleImageView alambresPreview1;
    private CircleImageView alambresPreview2;
    private CircleImageView alambresPreview3;
    private CircleImageView prensadoPreview1;
    private CircleImageView prensadoPreview2;
    private CircleImageView prensadoPreview3;
    private CircleImageView otros1Preview1;
    private CircleImageView otros1Preview2;
    private CircleImageView otros1Preview3;
    private CircleImageView instalacionParalelaPreview1;
    private CircleImageView instalacionParalelaPreview2;
    private CircleImageView instalacionParalelaPreview3;
    private CircleImageView bypassPreview1;
    private CircleImageView bypassPreview2;
    private CircleImageView bypassPreview3;
    private CircleImageView otros2Preview1;
    private CircleImageView otros2Preview2;
    private CircleImageView otros2Preview3;

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
                ocultaFotosImanesLl.setVisibility(View.VISIBLE);
            } else if (db.getDatosTestParte1(idInspeccion).getUsoImanes().equals(RESPONDE_NO)) {
                usoImanesNegativoRb.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getInvertirTomas() != null) {
            if (db.getDatosTestParte1(idInspeccion).getInvertirTomas().equals(RESPONDE_SI)) {
                invertirTomasPositivoRb.setChecked(true);
                ocultaFotosInvertirTomasLl.setVisibility(View.VISIBLE);
            } else if (db.getDatosTestParte1(idInspeccion).getInvertirTomas().equals(RESPONDE_NO)) {
                invertirTomasNegativoRb.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getPerforaCupula() != null) {
            if (db.getDatosTestParte1(idInspeccion).getPerforaCupula().equals(RESPONDE_SI)) {
                perforaCupulaPositivoRb.setChecked(true);
                ocultaFotosCupulaLl.setVisibility(View.VISIBLE);
            } else if (db.getDatosTestParte1(idInspeccion).getPerforaCupula().equals(RESPONDE_NO)) {
                perforaCupulaNegativoRb.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getCortaEngranaje() != null) {
            if (db.getDatosTestParte1(idInspeccion).getCortaEngranaje().equals(RESPONDE_SI)) {
                cortaEngranajePositivoRb.setChecked(true);
                ocultaFotosEngranajeLl.setVisibility(View.VISIBLE);
            } else if (db.getDatosTestParte1(idInspeccion).getCortaEngranaje().equals(RESPONDE_NO)) {
                cortaEngranajeNegativoRb.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getUsoAlambres() != null) {
            if (db.getDatosTestParte1(idInspeccion).getUsoAlambres().equals(RESPONDE_SI)) {
                usoAlambrePositivoRb.setChecked(true);
                ocultaFotosAlambresLl.setVisibility(View.VISIBLE);
            } else if (db.getDatosTestParte1(idInspeccion).getUsoAlambres().equals(RESPONDE_NO)) {
                usoAlambreNegativoRb.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getPrensado() != null) {
            if (db.getDatosTestParte1(idInspeccion).getPrensado().equals(RESPONDE_SI)) {
                prensadoPositivoRb.setChecked(true);
                ocultaFotosPrensadoLl.setVisibility(View.VISIBLE);
            } else if (db.getDatosTestParte1(idInspeccion).getPrensado().equals(RESPONDE_NO)) {
                prensadoNegativoRb.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getOtro() != null) {
            if (db.getDatosTestParte1(idInspeccion).getOtro().equals(RESPONDE_SI)) {
                otro12PositivoRb.setChecked(true);
                ocultaFotosOtros12Ll.setVisibility(View.VISIBLE);
                otro12EditText.setVisibility(View.VISIBLE);
            } else if (db.getDatosTestParte1(idInspeccion).getOtro().equals(RESPONDE_NO)) {
                otro12NegativoRb.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getOtroText() != null && !db.getDatosTestParte1(idInspeccion).getOtroText().equals("null")) {
            otro12EditText.setText(db.getDatosTestParte1(idInspeccion).getOtroText());
        } else {
            otro12EditText.setText("");
        }

        if (db.getDatosTestParte1(idInspeccion).getInstalacionParalela() != null) {
            if (db.getDatosTestParte1(idInspeccion).getInstalacionParalela().equals(RESPONDE_SI)) {
                case8PositiveRadioButton.setChecked(true);
                ocultaFotosInstParalelaLl.setVisibility(View.VISIBLE);
            } else if (db.getDatosTestParte1(idInspeccion).getInstalacionParalela().equals(RESPONDE_NO)) {
                case8NegativeRadioButton.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getBypass() != null) {
            if (db.getDatosTestParte1(idInspeccion).getBypass().equals(RESPONDE_SI)) {
                case9PositiveRadioButton.setChecked(true);
                ocultaFotosBypassLl.setVisibility(View.VISIBLE);
            } else if (db.getDatosTestParte1(idInspeccion).getBypass().equals(RESPONDE_NO)) {
                case9NegativeRadioButton.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getOtro2() != null) {
            if (db.getDatosTestParte1(idInspeccion).getOtro2().equals(RESPONDE_SI)) {
                case10PositiveRadioButton.setChecked(true);
                ocultaFotosOtros13Ll.setVisibility(View.VISIBLE);
                otroFotoEditText.setVisibility(View.VISIBLE);
            } else if (db.getDatosTestParte1(idInspeccion).getOtro2().equals(RESPONDE_NO)) {
                case10NegativeRadioButton.setChecked(true);
            }
        }

        if (db.getDatosTestParte1(idInspeccion).getOtroText2() != null && !db.getDatosTestParte1(idInspeccion).getOtroText2().equals("null")) {
            otroFotoEditText.setText(db.getDatosTestParte1(idInspeccion).getOtroText2());
        } else {
            otroFotoEditText.setText("");
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
                    ocultaFotosOtros13Ll.setVisibility(View.VISIBLE);
                    otroFotoEditText.setVisibility(View.VISIBLE);
                    Log.d("case 10", "VISIBLE");
                } else {
                    otroFotoEditText.setVisibility(View.GONE);
                    ocultaFotosOtros13Ll.setVisibility(View.GONE);
                    Log.d("case 10", "INVISIBLE");
                }
            }
        });

        /* Preview uso de imanes */
        if (db.getFotos(idInspeccion).getUsoImanes1() != null) {
            usoImanesPreview1.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getUsoImanes1()).into(usoImanesPreview1);
        }

        if (db.getFotos(idInspeccion).getUsoImanes2() != null) {
            usoImanesPreview2.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getUsoImanes2()).into(usoImanesPreview2);
        }

        if (db.getFotos(idInspeccion).getUsoImanes3() != null) {
            usoImanesPreview3.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getUsoImanes3()).into(usoImanesPreview3);
        }

        /* Preview invertir tomas */
        if (db.getFotos(idInspeccion).getInvertirTomas1() != null) {
            invertirTomasPreview1.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getInvertirTomas1()).into(invertirTomasPreview1);
        }

        if (db.getFotos(idInspeccion).getInvertirTomas2() != null) {
            invertirTomasPreview2.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getInvertirTomas2()).into(invertirTomasPreview2);
        }

        if (db.getFotos(idInspeccion).getInvertirTomas3() != null) {
            invertirTomasPreview3.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getInvertirTomas3()).into(invertirTomasPreview3);
        }

        /* Preview perfora cupula */
        if (db.getFotos(idInspeccion).getPerforaCupula1() != null) {
            perforaCupulaPreview1.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getPerforaCupula1()).into(perforaCupulaPreview1);
        }

        if (db.getFotos(idInspeccion).getPerforaCupula2() != null) {
            perforaCupulaPreview2.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getPerforaCupula2()).into(perforaCupulaPreview2);
        }

        if (db.getFotos(idInspeccion).getPerforaCupula3() != null) {
            perforaCupulaPreview3.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getPerforaCupula3()).into(perforaCupulaPreview3);
        }

        /* Preview corta engranajes */
        if (db.getFotos(idInspeccion).getCortaEngrnaje1() != null) {
            engranajePreview1.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getCortaEngrnaje1()).into(engranajePreview1);
        }

        if (db.getFotos(idInspeccion).getCortaEngrnaje2() != null) {
            engranajePreview2.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getCortaEngrnaje2()).into(engranajePreview2);
        }

        if (db.getFotos(idInspeccion).getCortaEngrnaje3() != null) {
            engranajePreview3.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getCortaEngrnaje3()).into(engranajePreview3);
        }

        /* Preview uso de alambes */
        if (db.getFotos(idInspeccion).getUsoAlambre1() != null) {
            alambresPreview1.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getUsoAlambre1()).into(alambresPreview1);
        }

        if (db.getFotos(idInspeccion).getUsoAlambre2() != null) {
            alambresPreview2.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getUsoAlambre2()).into(alambresPreview2);
        }

        if (db.getFotos(idInspeccion).getUsoAlambre3() != null) {
            alambresPreview3.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getUsoAlambre3()).into(alambresPreview3);
        }

        /* Preview prensado */
        if (db.getFotos(idInspeccion).getPrensado1() != null) {
            prensadoPreview1.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getPrensado1()).into(prensadoPreview1);
        }

        if (db.getFotos(idInspeccion).getPrensado2() != null) {
            prensadoPreview2.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getPrensado2()).into(prensadoPreview2);
        }

        if (db.getFotos(idInspeccion).getPrensado3() != null) {
            prensadoPreview3.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getPrensado3()).into(prensadoPreview3);
        }

        /* Preview otros 1 */
        if (db.getFotos(idInspeccion).getOtro11() != null) {
            otros1Preview1.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getOtro11()).into(otros1Preview1);
        }

        if (db.getFotos(idInspeccion).getOtro12() != null) {
            otros1Preview2.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getOtro12()).into(otros1Preview2);
        }

        if (db.getFotos(idInspeccion).getOtro13() != null) {
            otros1Preview3.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getOtro13()).into(otros1Preview3);
        }

        /* Preview instalacion paralela */
        if (db.getFotos(idInspeccion).getInstalacionParalela1() != null) {
            instalacionParalelaPreview1.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getInstalacionParalela1()).into(instalacionParalelaPreview1);
        }

        if (db.getFotos(idInspeccion).getInstalacionParalela2() != null) {
            instalacionParalelaPreview2.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getInstalacionParalela2()).into(instalacionParalelaPreview2);
        }

        if (db.getFotos(idInspeccion).getInstalacionParalela3() != null) {
            instalacionParalelaPreview3.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getInstalacionParalela3()).into(instalacionParalelaPreview3);
        }

        /* Preview bypass */
        if (db.getFotos(idInspeccion).getBypass1() != null) {
            bypassPreview1.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getBypass1()).into(bypassPreview1);
        }

        if (db.getFotos(idInspeccion).getBypass2() != null) {
            bypassPreview2.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getBypass2()).into(bypassPreview2);
        }

        if (db.getFotos(idInspeccion).getBypass3() != null) {
            bypassPreview3.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getBypass3()).into(bypassPreview3);
        }

        /* Preview otros 2 */
        if (db.getFotos(idInspeccion).getOtro21() != null) {
            otros2Preview1.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getOtro21()).into(otros2Preview1);
        }

        if (db.getFotos(idInspeccion).getOtro22() != null) {
            otros2Preview2.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getOtro22()).into(otros2Preview2);
        }

        if (db.getFotos(idInspeccion).getOtro23() != null) {
            otros2Preview3.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getOtro23()).into(otros2Preview3);
        }
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
                test.setTest1(NO_ENVIA_RESPUESTA);
            }

            if (test12YesRadioButton.isChecked()) {
                test.setTest2(RESPONDE_SI);
            } else if (test12NoRadioButton.isChecked()) {
                test.setTest2(RESPONDE_NO);
            } else {
                test.setTest2(NO_ENVIA_RESPUESTA);
            }

            if (test13YesRadioButton.isChecked()) {
                test.setTest3(RESPONDE_SI);
            } else if (test13NoRadioButton.isChecked()) {
                test.setTest3(RESPONDE_NO);
            } else {
                test.setTest3(NO_ENVIA_RESPUESTA);
            }

            if (test14YesRadioButton.isChecked()) {
                test.setTest4(RESPONDE_SI);
            } else if (test14NoRadioButton.isChecked()) {
                test.setTest4(RESPONDE_NO);
            } else {
                test.setTest4(NO_ENVIA_RESPUESTA);
            }

            if (test15YesRadioButton.isChecked()) {
                test.setTest5(RESPONDE_SI);
            } else if (test15NoRadioButton.isChecked()) {
                test.setTest5(RESPONDE_NO);
            } else {
                test.setTest5(NO_ENVIA_RESPUESTA);
            }

            /*
            Test 1 Parte 2
            */
            if (usoImanesPositivoRb.isChecked()) {
                test.setUsoImanes(RESPONDE_SI);
            } else if (usoImanesNegativoRb.isChecked()) {
                test.setUsoImanes(RESPONDE_NO);
            } else {
                test.setUsoImanes(NO_ENVIA_RESPUESTA);
            }

            if (invertirTomasPositivoRb.isChecked()) {
                test.setInvertirTomas(RESPONDE_SI);
            } else if (invertirTomasNegativoRb.isChecked()) {
                test.setInvertirTomas(RESPONDE_NO);
            } else {
                test.setInvertirTomas(NO_ENVIA_RESPUESTA);
            }

            if (perforaCupulaPositivoRb.isChecked()) {
                test.setPerforaCupula(RESPONDE_SI);
            } else if (perforaCupulaNegativoRb.isChecked()) {
                test.setPerforaCupula(RESPONDE_NO);
            } else {
                test.setPerforaCupula(NO_ENVIA_RESPUESTA);
            }

            if (cortaEngranajePositivoRb.isChecked()) {
                test.setCortaEngranaje(RESPONDE_SI);
            } else if (cortaEngranajeNegativoRb.isChecked()) {
                test.setCortaEngranaje(RESPONDE_NO);
            } else {
                test.setCortaEngranaje(NO_ENVIA_RESPUESTA);
            }

            if (usoAlambrePositivoRb.isChecked()) {
                test.setUsoAlambres(RESPONDE_SI);
            } else if (usoAlambreNegativoRb.isChecked()) {
                test.setUsoAlambres(RESPONDE_NO);
            } else {
                test.setUsoAlambres(NO_ENVIA_RESPUESTA);
            }

            if (prensadoPositivoRb.isChecked()) {
                test.setPrensado(RESPONDE_SI);
            } else if (prensadoNegativoRb.isChecked()) {
                test.setPrensado(RESPONDE_NO);
            } else {
                test.setPrensado(NO_ENVIA_RESPUESTA);
            }

            if (otro12PositivoRb.isChecked()) {
                test.setOtro(RESPONDE_SI);
                test.setOtroText(otro12EditText.getText().toString());
            } else if (otro12NegativoRb.isChecked()) {
                test.setOtro(RESPONDE_NO);
            } else {
                test.setOtro(NO_ENVIA_RESPUESTA);
            }

            /*
            Test 1 Parte 3
            */
            if (case8PositiveRadioButton.isChecked()) {
                test.setInstalacionParalela(RESPONDE_SI);
            } else if (case8NegativeRadioButton.isChecked()) {
                test.setInstalacionParalela(RESPONDE_NO);
            } else {
                test.setInstalacionParalela(NO_ENVIA_RESPUESTA);
            }

            if (case9PositiveRadioButton.isChecked()) {
                test.setBypass(RESPONDE_SI);
            } else if (case9NegativeRadioButton.isChecked()) {
                test.setBypass(RESPONDE_NO);
            } else {
                test.setBypass(NO_ENVIA_RESPUESTA);
            }

            if (case10PositiveRadioButton.isChecked()) {
                test.setOtro2(RESPONDE_SI);
                test.setOtroText2(otroFotoEditText.getText().toString());
            } else if (case10NegativeRadioButton.isChecked()) {
                test.setOtro2(RESPONDE_NO);
            } else {
                test.setOtro2(NO_ENVIA_RESPUESTA);
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
                    usoImanesPreview1.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getUsoImanes1()).into(usoImanesPreview1);
                    break;
                case USO_IMANES_2:
                    foto.setUsoImanes2(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    usoImanesPreview2.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getUsoImanes2()).into(usoImanesPreview2);
                    break;
                case USO_IMANES_3:
                    foto.setUsoImanes3(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    usoImanesPreview3.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getUsoImanes3()).into(usoImanesPreview3);
                    break;
                case INVERTIR_TOMAS_1:
                    foto.setInvertirTomas1(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    invertirTomasPreview1.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getInvertirTomas1()).into(invertirTomasPreview1);
                    break;
                case INVERTIR_TOMAS_2:
                    foto.setInvertirTomas2(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    invertirTomasPreview2.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getInvertirTomas2()).into(invertirTomasPreview2);
                    break;
                case INVERTIR_TOMAS_3:
                    foto.setInvertirTomas3(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    invertirTomasPreview3.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getInvertirTomas3()).into(invertirTomasPreview3);
                    break;
                case PERFORA_CUPULA_1:
                    foto.setPerforaCupula1(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    perforaCupulaPreview1.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getPerforaCupula1()).into(perforaCupulaPreview1);
                    break;
                case PERFORA_CUPULA_2:
                    foto.setPerforaCupula2(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    perforaCupulaPreview2.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getPerforaCupula2()).into(perforaCupulaPreview2);
                    break;
                case PERFORA_CUPULA_3:
                    foto.setPerforaCupula3(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    perforaCupulaPreview3.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getPerforaCupula3()).into(perforaCupulaPreview3);
                    break;
                case CORTA_ENGRANAJE_1:
                    foto.setCortaEngrnaje1(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    engranajePreview1.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getCortaEngrnaje1()).into(engranajePreview1);
                    break;
                case CORTA_ENGRANAJE_2:
                    foto.setCortaEngrnaje2(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    engranajePreview2.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getCortaEngrnaje2()).into(engranajePreview2);
                    break;
                case CORTA_ENGRANAJE_3:
                    foto.setCortaEngrnaje3(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    engranajePreview3.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getCortaEngrnaje3()).into(engranajePreview3);
                    break;
                case USO_ALAMBRE_1:
                    foto.setUsoAlambre1(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    alambresPreview1.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getUsoAlambre1()).into(alambresPreview1);
                    break;
                case USO_ALAMBRE_2:
                    foto.setUsoAlambre2(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    alambresPreview2.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getUsoAlambre2()).into(alambresPreview2);
                    break;
                case USO_ALAMBRE_3:
                    foto.setUsoAlambre3(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    alambresPreview3.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getUsoAlambre3()).into(alambresPreview3);
                    break;
                case PRENSADO_1:
                    foto.setPrensado1(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    prensadoPreview1.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getPrensado1()).into(prensadoPreview1);
                    break;
                case PRENSADO_2:
                    foto.setPrensado2(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    prensadoPreview2.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getPrensado2()).into(prensadoPreview2);
                    break;
                case PRENSADO_3:
                    foto.setPrensado3(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    prensadoPreview3.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getPrensado3()).into(prensadoPreview3);
                    break;
                case OTROS_1_1:
                    foto.setOtro11(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    otros1Preview1.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getOtro11()).into(otros1Preview1);
                    break;
                case OTROS_1_2:
                    foto.setOtro12(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    otros1Preview2.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getOtro12()).into(otros1Preview2);
                    break;
                case OTROS_1_3:
                    foto.setOtro13(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    otros1Preview3.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getOtro13()).into(otros1Preview3);
                    break;
                case INSTALACION_PARALELA_1:
                    foto.setInstalacionParalela1(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    instalacionParalelaPreview1.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getInstalacionParalela1()).into(instalacionParalelaPreview1);
                    break;
                case INSTALACION_PARALELA_2:
                    foto.setInstalacionParalela2(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    instalacionParalelaPreview2.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getInstalacionParalela2()).into(instalacionParalelaPreview2);
                    break;
                case INSTALACION_PARALELA_3:
                    foto.setInstalacionParalela3(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    instalacionParalelaPreview3.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getInstalacionParalela3()).into(instalacionParalelaPreview3);
                    break;
                case BYPASS_1:
                    foto.setBypass1(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    bypassPreview1.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getBypass1()).into(bypassPreview1);
                    break;
                case BYPASS_2:
                    foto.setBypass2(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    bypassPreview2.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getBypass2()).into(bypassPreview2);
                    break;
                case BYPASS_3:
                    foto.setBypass3(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    bypassPreview3.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getBypass3()).into(bypassPreview3);
                    break;
                case OTROS_2_1:
                    foto.setOtro21(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    otros2Preview1.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getOtro21()).into(otros2Preview1);
                    break;
                case OTROS_2_2:
                    foto.setOtro22(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    otros2Preview2.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getOtro22()).into(otros2Preview2);
                    break;
                case OTROS_2_3:
                    foto.setOtro23(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    otros2Preview3.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getOtro23()).into(otros2Preview3);
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
        ocultaFotosInstParalelaLl = view.findViewById(R.id.ocultaFotosInstParalelaLl);
        ocultaFotosBypassLl = view.findViewById(R.id.ocultaFotosBypassLl);
        ocultaFotosOtros13Ll = view.findViewById(R.id.ocultaFotosOtros13Ll);

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

        usoImanesPreview1 = view.findViewById(R.id.usoImanesPreview1);
        usoImanesPreview2 = view.findViewById(R.id.usoImanesPreview2);
        usoImanesPreview3 = view.findViewById(R.id.usoImanesPreview3);
        invertirTomasPreview1 = view.findViewById(R.id.invertirTomasPreview1);
        invertirTomasPreview2 = view.findViewById(R.id.invertirTomasPreview2);
        invertirTomasPreview3 = view.findViewById(R.id.invertirTomasPreview3);
        engranajePreview1 = view.findViewById(R.id.engranajePreview1);
        engranajePreview2 = view.findViewById(R.id.engranajePreview2);
        engranajePreview3 = view.findViewById(R.id.engranajePreview3);
        perforaCupulaPreview1 = view.findViewById(R.id.perforaCupulaPreview1);
        perforaCupulaPreview2 = view.findViewById(R.id.perforaCupulaPreview2);
        perforaCupulaPreview3 = view.findViewById(R.id.perforaCupulaPreview3);
        alambresPreview1 = view.findViewById(R.id.alambresPreview1);
        alambresPreview2 = view.findViewById(R.id.alambresPreview2);
        alambresPreview3 = view.findViewById(R.id.alambresPreview3);
        prensadoPreview1 = view.findViewById(R.id.prensadoPreview1);
        prensadoPreview2 = view.findViewById(R.id.prensadoPreview2);
        prensadoPreview3 = view.findViewById(R.id.prensadoPreview3);
        otros1Preview1 = view.findViewById(R.id.otros1Preview1);
        otros1Preview2 = view.findViewById(R.id.otros1Preview2);
        otros1Preview3 = view.findViewById(R.id.otros1Preview3);
        instalacionParalelaPreview1 = view.findViewById(R.id.instalacionParalelaPreview1);
        instalacionParalelaPreview2 = view.findViewById(R.id.instalacionParalelaPreview2);
        instalacionParalelaPreview3 = view.findViewById(R.id.instalacionParalelaPreview3);
        bypassPreview1 = view.findViewById(R.id.bypassPreview1);
        bypassPreview2 = view.findViewById(R.id.bypassPreview2);
        bypassPreview3 = view.findViewById(R.id.bypassPreview3);
        otros2Preview1 = view.findViewById(R.id.otros2Preview1);
        otros2Preview2 = view.findViewById(R.id.otros2Preview2);
        otros2Preview3 = view.findViewById(R.id.otros2Preview3);

        otroFotoEditText = view.findViewById(R.id.otroFotoEditText);
        saveButton = view.findViewById(R.id.saveButton);

        /* Vistas que se inician ocultas*/

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

        usoImanesPreview1.setVisibility(View.GONE);
        usoImanesPreview2.setVisibility(View.GONE);
        usoImanesPreview3.setVisibility(View.GONE);
        invertirTomasPreview1.setVisibility(View.GONE);
        invertirTomasPreview2.setVisibility(View.GONE);
        invertirTomasPreview3.setVisibility(View.GONE);
        perforaCupulaPreview1.setVisibility(View.GONE);
        perforaCupulaPreview2.setVisibility(View.GONE);
        perforaCupulaPreview3.setVisibility(View.GONE);
        engranajePreview1.setVisibility(View.GONE);
        engranajePreview2.setVisibility(View.GONE);
        engranajePreview3.setVisibility(View.GONE);
        alambresPreview1.setVisibility(View.GONE);
        alambresPreview2.setVisibility(View.GONE);
        alambresPreview3.setVisibility(View.GONE);
        prensadoPreview1.setVisibility(View.GONE);
        prensadoPreview2.setVisibility(View.GONE);
        prensadoPreview3.setVisibility(View.GONE);
        otros1Preview1.setVisibility(View.GONE);
        otros1Preview2.setVisibility(View.GONE);
        otros1Preview3.setVisibility(View.GONE);
        instalacionParalelaPreview1.setVisibility(View.GONE);
        instalacionParalelaPreview2.setVisibility(View.GONE);
        instalacionParalelaPreview3.setVisibility(View.GONE);
        bypassPreview1.setVisibility(View.GONE);
        bypassPreview2.setVisibility(View.GONE);
        bypassPreview3.setVisibility(View.GONE);
        otros2Preview1.setVisibility(View.GONE);
        otros2Preview2.setVisibility(View.GONE);
        otros2Preview3.setVisibility(View.GONE);

        otro12EditText.setVisibility(View.GONE);
        otroFotoEditText.setVisibility(View.GONE);
    }
}
