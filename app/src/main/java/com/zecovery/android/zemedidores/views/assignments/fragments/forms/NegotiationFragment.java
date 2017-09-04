package com.zecovery.android.zemedidores.views.assignments.fragments.forms;

import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.models.ResultadoInspeccion;
import com.zecovery.android.zemedidores.network.NegociacionCallback;
import com.zecovery.android.zemedidores.network.PostResult;
import com.zecovery.android.zemedidores.views.MainActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT_NEGOTIATION;
import static com.zecovery.android.zemedidores.data.Constant.PAYMENT_METHOD_1;
import static com.zecovery.android.zemedidores.data.Constant.PAYMENT_METHOD_2;
import static com.zecovery.android.zemedidores.data.Constant.PAYMENT_METHOD_3;
import static com.zecovery.android.zemedidores.data.Constant.PAYMENT_METHOD_4;
import static com.zecovery.android.zemedidores.data.Constant.SELECT_OPTION;
import static com.zecovery.android.zemedidores.data.Constant.URL_BASE_DESA;

public class NegotiationFragment extends Fragment implements NegociacionCallback, View.OnClickListener {

    private TextView socialRiskTextView;
    private TextView totalDebtTextView;
    private TextView arrangementTextView;
    private EditText commentEditText;

    private RadioButton negotiationAcceptedRadioButton;
    private RadioButton negotiationRejectedRadioButton;

    private Spinner paymentMethodSpinner;

    private ImageButton paymentMethodImageButton;
    private ImageButton personalIdImageButton;

    private Button endInspectionButton;

    private LocalDatabase db;

    private int token;

    private MultipartBody.Part foto_rechazo_inspeccion;
    private MultipartBody.Part foto_falla_medidor;
    private MultipartBody.Part foto_lectura_medidor;
    private MultipartBody.Part foto_numero_medidor;
    private MultipartBody.Part foto_panoramica_medidor;
    private MultipartBody.Part foto_numero_propiedad;
    private MultipartBody.Part foto_fechada_propiedad;

    public NegotiationFragment() {
    }

    public NegotiationFragment newInstance(int token) {

        NegotiationFragment negotiationFragment = new NegotiationFragment();
        Bundle args = new Bundle();
        args.putInt(ID_ASSIGNMENT_NEGOTIATION, token);
        negotiationFragment.setArguments(args);
        return negotiationFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_negotiation, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        token = getArguments().getInt(ID_ASSIGNMENT_NEGOTIATION);
        findViews(view);
        db = new LocalDatabase(getContext());

        Log.d("TAG", "NegotiationFragment");

        List<String> paymentMethod = new ArrayList<>();

        paymentMethod.add(SELECT_OPTION);
        paymentMethod.add(PAYMENT_METHOD_1);
        paymentMethod.add(PAYMENT_METHOD_2);
        paymentMethod.add(PAYMENT_METHOD_3);
        paymentMethod.add(PAYMENT_METHOD_4);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, paymentMethod);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentMethodSpinner.setAdapter(adapter);

        endInspectionButton.setOnClickListener(this);
    }

    private void findViews(View view) {
        socialRiskTextView = view.findViewById(R.id.socialRiskTextView);
        totalDebtTextView = view.findViewById(R.id.totalDebtTextView);
        arrangementTextView = view.findViewById(R.id.arrangementTextView);
        commentEditText = view.findViewById(R.id.commentEditText);
        negotiationAcceptedRadioButton = view.findViewById(R.id.negotiationAcceptedRadioButton);
        negotiationRejectedRadioButton = view.findViewById(R.id.negotiationRejectedRadioButton);
        paymentMethodSpinner = view.findViewById(R.id.paymentMethodSpinner);
        paymentMethodImageButton = view.findViewById(R.id.paymentMethodImageButton);
        personalIdImageButton = view.findViewById(R.id.personalIdImageButton);
        endInspectionButton = view.findViewById(R.id.endInspectionButton);
    }

    @Override
    public void guardaFormularioNegociacion() {
        startActivity(new Intent(getContext(), MainActivity.class));
    }

    @Override
    public void errorFormularioNegociacion() {
        Toast.makeText(getContext(), "No se pudo guarda la informacion", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        post(token);
        Log.d("NegotiationFragment", "onClick");

        String negotiationAccepted;
        String paymentMethod;

        if (negotiationAcceptedRadioButton.isChecked()) {
            negotiationAccepted = "SI";
        } else if (negotiationRejectedRadioButton.isChecked()) {
            negotiationAccepted = "NO";
        } else {
            negotiationAccepted = "-";
        }

        paymentMethod = paymentMethodSpinner.getSelectedItem().toString();


    }


    private void post(int token) {

        ResultadoInspeccion resultado = db.getResultadoInspeccion(token);

        RequestBody id_inspeccion_body = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(token));
        RequestBody lat_body = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(db.getCurrentDbLocation().latitude));
        RequestBody lng_body = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(db.getCurrentDbLocation().longitude));
        RequestBody estado_body = RequestBody.create(MediaType.parse("multipart/form-data"), "realizada");

        Calendar rightNow = Calendar.getInstance();
        int fechaAcutal = (int) (rightNow.getTimeInMillis() / 1000);
        RequestBody fecha_body = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(fechaAcutal));

        String test1 = resultado.getTestParte1().getTest1();
        String test2 = resultado.getTestParte1().getTest2();
        String test3 = resultado.getTestParte1().getTest3();
        String uso_imanes = resultado.getTestParte1().getUsoImanes();
        String invertir_tomas = resultado.getTestParte1().getInvertirTomas();
        String perfora_cupula = resultado.getTestParte1().getPerforaCupula();
        String corta_engranaje = resultado.getTestParte1().getCortaEngranaje();
        String uso_alambres = resultado.getTestParte1().getUsoAlambres();
        String prensado = resultado.getTestParte1().getPrensado();
        String otro_1 = resultado.getTestParte1().getOtro();
        String instalacion_paralela = resultado.getTestParte1().getInstalacionParalela();
        String bypass = resultado.getTestParte1().getBypass();
        String otro_2 = resultado.getTestParte1().getOtro2();

        String clase = resultado.getTestParte2().getClaseMedidor();
        String ano_medidor = resultado.getTestParte2().getAnoMedidor();
        String marca = resultado.getTestParte2().getMarca();
        String registrador = resultado.getTestParte2().getRegistrador();
        String instalacion = resultado.getTestParte2().getInstalacion();
        String tramo_antes = resultado.getTestParte2().getTramoAntes();
        String tramo_despues = resultado.getTestParte2().getTramoDespues();
        String observacion_1 = resultado.getTestParte2().getObservaciones();
        String verticales = resultado.getTestParte2().getEstadoVerticales();
        String cortes = resultado.getTestParte2().getEstadoCortes();
        String suministro_alternativo = resultado.getTestParte2().getSuministroAlternativo();
        String cumple_plano = resultado.getTestParte2().getCumplePlano();
        String observacion_2 = resultado.getTestParte2().getObservaciones2();

        String construccion_nueva = resultado.getTestParte3().getConstruccionNueva();
        String tipo_propiedad = resultado.getTestParte3().getTipoPropiedad();
        String habitantes = resultado.getTestParte3().getHabitantes();
        String banos = resultado.getTestParte3().getBanos();
        String sup_edificada = resultado.getTestParte3().getSuperficieEdificada();
        String sup_jardin = resultado.getTestParte3().getSuperficieJardin();
        String accesos = resultado.getTestParte3().getAcceso();
        String sup_terreno = resultado.getTestParte3().getSuperficieTerreno();
        String observacion_3 = resultado.getTestParte3().getObservaciones1();
        String auto_abastecimiento = resultado.getTestParte3().getAutoAbastecimiento();
        String tipo_fuente = resultado.getTestParte3().getTipoFuente();
        String uso = resultado.getTestParte3().getUso();
        String activo = resultado.getTestParte3().getActivo();
        String capacidad_bomba = resultado.getTestParte3().getCapacidadBomba();
        String resolucion_dga = resultado.getTestParte3().getResolucion();
        String caudal = resultado.getTestParte3().getCaudal();
        String observacion_4 = resultado.getTestParte3().getObservaciones2();

        RequestBody test1_body = RequestBody.create(MediaType.parse("multipart/form-data"), test1);
        RequestBody test2_body = RequestBody.create(MediaType.parse("multipart/form-data"), test2);
        RequestBody test3_body = RequestBody.create(MediaType.parse("multipart/form-data"), test3);
        RequestBody uso_imanes_body = RequestBody.create(MediaType.parse("multipart/form-data"), uso_imanes);
        RequestBody invertir_tomas_body = RequestBody.create(MediaType.parse("multipart/form-data"), invertir_tomas);
        RequestBody perfora_cupula_body = RequestBody.create(MediaType.parse("multipart/form-data"), perfora_cupula);
        RequestBody corta_engranaje_body = RequestBody.create(MediaType.parse("multipart/form-data"), corta_engranaje);
        RequestBody uso_alambres_body = RequestBody.create(MediaType.parse("multipart/form-data"), uso_alambres);
        RequestBody prensado_body = RequestBody.create(MediaType.parse("multipart/form-data"), prensado);
        RequestBody otro_body = RequestBody.create(MediaType.parse("multipart/form-data"), otro_1);
        RequestBody instalacion_paralela_body = RequestBody.create(MediaType.parse("multipart/form-data"), instalacion_paralela);
        RequestBody bypass_body = RequestBody.create(MediaType.parse("multipart/form-data"), bypass);
        RequestBody otro_2_body = RequestBody.create(MediaType.parse("multipart/form-data"), otro_2);

        RequestBody clase_body = RequestBody.create(MediaType.parse("multipart/form-data"), clase);
        RequestBody ano_medidor_body = RequestBody.create(MediaType.parse("multipart/form-data"), ano_medidor);
        RequestBody marca_body = RequestBody.create(MediaType.parse("multipart/form-data"), marca);
        RequestBody registrador_body = RequestBody.create(MediaType.parse("multipart/form-data"), registrador);
        RequestBody instalacion_body = RequestBody.create(MediaType.parse("multipart/form-data"), instalacion);
        RequestBody tramo_antes_body = RequestBody.create(MediaType.parse("multipart/form-data"), tramo_antes);
        RequestBody tramo_despues_body = RequestBody.create(MediaType.parse("multipart/form-data"), tramo_despues);
        RequestBody observacion_1_body = RequestBody.create(MediaType.parse("multipart/form-data"), observacion_1);
        RequestBody verticales_body = RequestBody.create(MediaType.parse("multipart/form-data"), verticales);
        RequestBody cortes_body = RequestBody.create(MediaType.parse("multipart/form-data"), cortes);
        RequestBody suministro_alternativo_body = RequestBody.create(MediaType.parse("multipart/form-data"), suministro_alternativo);
        RequestBody cumple_plano_body = RequestBody.create(MediaType.parse("multipart/form-data"), cumple_plano);
        RequestBody observacion_2_body = RequestBody.create(MediaType.parse("multipart/form-data"), observacion_2);
        RequestBody construccion_nueva_body = RequestBody.create(MediaType.parse("multipart/form-data"), construccion_nueva);
        RequestBody tipo_propiedad_body = RequestBody.create(MediaType.parse("multipart/form-data"), tipo_propiedad);
        RequestBody habitantes_body = RequestBody.create(MediaType.parse("multipart/form-data"), habitantes);
        RequestBody banos_body = RequestBody.create(MediaType.parse("multipart/form-data"), banos);
        RequestBody sup_edificada_body = RequestBody.create(MediaType.parse("multipart/form-data"), sup_edificada);
        RequestBody sup_jardin_body = RequestBody.create(MediaType.parse("multipart/form-data"), sup_jardin);
        RequestBody accesos_body = RequestBody.create(MediaType.parse("multipart/form-data"), accesos);
        RequestBody sup_terreno_body = RequestBody.create(MediaType.parse("multipart/form-data"), sup_terreno);
        RequestBody observacion_3_body = RequestBody.create(MediaType.parse("multipart/form-data"), observacion_3);
        RequestBody auto_abastecimiento_body = RequestBody.create(MediaType.parse("multipart/form-data"), auto_abastecimiento);
        RequestBody tipo_fuente_body = RequestBody.create(MediaType.parse("multipart/form-data"), tipo_fuente);
        RequestBody uso_body = RequestBody.create(MediaType.parse("multipart/form-data"), uso);
        RequestBody activo_body = RequestBody.create(MediaType.parse("multipart/form-data"), activo);
        RequestBody capacidad_bomba_body = RequestBody.create(MediaType.parse("multipart/form-data"), capacidad_bomba);
        RequestBody resolucion_dga_body = RequestBody.create(MediaType.parse("multipart/form-data"), resolucion_dga);
        RequestBody caudal_body = RequestBody.create(MediaType.parse("multipart/form-data"), caudal);
        RequestBody observacion_4_body = RequestBody.create(MediaType.parse("multipart/form-data"), observacion_4);

        if (resultado.getFotos().getRechazoInspeccion() != null) {
            File file_foto_rechazo = new File(resultado.getFotos().getRechazoInspeccion());
            RequestBody requestFileRechazo = RequestBody.create(MediaType.parse("image/*"), file_foto_rechazo);
            foto_rechazo_inspeccion = MultipartBody.Part.createFormData("foto_rechazo_inspeccion", "foto_rechazo_inspeccion", requestFileRechazo);
        } else {
            foto_rechazo_inspeccion = null;
        }

        if (resultado.getFotos().getFallaMedidor() != null) {
            File file_foto_falla = new File(resultado.getFotos().getFallaMedidor());
            RequestBody requestFileFalla = RequestBody.create(MediaType.parse("image/*"), file_foto_falla);
            foto_falla_medidor = MultipartBody.Part.createFormData("foto_falla_medidor", "", requestFileFalla);
        } else {
            foto_falla_medidor = null;
        }

        if (resultado.getFotos().getLecturaMedidor() != null) {
            File file_foto_lectura_medidor = new File(resultado.getFotos().getLecturaMedidor());
            RequestBody requestFileLectura = RequestBody.create(MediaType.parse("image/*"), file_foto_lectura_medidor);
            foto_lectura_medidor = MultipartBody.Part.createFormData("foto_lectura_medidor", "", requestFileLectura);
        } else {
            foto_lectura_medidor = null;
        }

        if (resultado.getFotos().getNumeroMedidor() != null) {
            File file_foto_numero_medidor = new File(resultado.getFotos().getNumeroMedidor());
            RequestBody requestFileNumeroMedidor = RequestBody.create(MediaType.parse("image/*"), file_foto_numero_medidor);
            foto_numero_medidor = MultipartBody.Part.createFormData("foto_numero_medidor", "", requestFileNumeroMedidor);
        } else {
            foto_numero_medidor = null;
        }

        if (resultado.getFotos().getPanoramicaMedidor() != null) {
            File file_foto_panoramica_medidor = new File(resultado.getFotos().getPanoramicaMedidor());
            RequestBody requestFilePanoMedidor = RequestBody.create(MediaType.parse("image/*"), file_foto_panoramica_medidor);
            foto_panoramica_medidor = MultipartBody.Part.createFormData("foto_panoramica_medidor", "", requestFilePanoMedidor);
        } else {
            foto_panoramica_medidor = null;
        }

        if (resultado.getFotos().getNumeroPropiedad() != null) {
            File file_foto_numero_propiedad = new File(resultado.getFotos().getNumeroPropiedad());
            RequestBody requestFileNumeroPorpiedad = RequestBody.create(MediaType.parse("image/*"), file_foto_numero_propiedad);
            foto_numero_propiedad = MultipartBody.Part.createFormData("foto_numero_propiedad", "", requestFileNumeroPorpiedad);
        } else {
            foto_numero_propiedad = null;
        }

        if (resultado.getFotos().getFachadaPropiedad() != null) {
            File file_foto_fachada_propiedad = new File(resultado.getFotos().getFachadaPropiedad());
            RequestBody requestFileFachadaPorpiedad = RequestBody.create(MediaType.parse("image/*"), file_foto_fachada_propiedad);
            foto_fechada_propiedad = MultipartBody.Part.createFormData("foto_fechada_propiedad", "", requestFileFachadaPorpiedad);
        } else {
            foto_fechada_propiedad = null;
        }

        String nombre_residente = String.valueOf(resultado.getResidente().getNombre());
        String rut_residente = String.valueOf(resultado.getResidente().getRut());
        String telefono_residente = String.valueOf(resultado.getResidente().getTelefono());
        String email_residente = String.valueOf(resultado.getResidente().getEmail());
        String fecha_residente = String.valueOf(resultado.getResidente().getFecha());

        RequestBody nombre_residente_body = RequestBody.create(MediaType.parse("multipart/form-data"), nombre_residente);
        RequestBody rut_residente_body = RequestBody.create(MediaType.parse("multipart/form-data"), rut_residente);
        RequestBody telefono_residente_body = RequestBody.create(MediaType.parse("multipart/form-data"), telefono_residente);
        RequestBody email_residente_body = RequestBody.create(MediaType.parse("multipart/form-data"), email_residente);
        RequestBody fecha_residente_body = RequestBody.create(MediaType.parse("multipart/form-data"), fecha_residente);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_BASE_DESA).build();
        PostResult service = retrofit.create(PostResult.class);

        Call<ResponseBody> call = service.postResultadoTest(
                id_inspeccion_body,
                lat_body,
                lng_body,
                estado_body,
                fecha_body,
                test1_body,
                test2_body,
                test3_body,
                uso_imanes_body,
                invertir_tomas_body,
                perfora_cupula_body,
                corta_engranaje_body,
                uso_alambres_body,
                prensado_body,
                otro_body,
                instalacion_paralela_body,
                bypass_body,
                otro_2_body,
                clase_body,
                ano_medidor_body,
                marca_body,
                registrador_body,
                instalacion_body,
                tramo_antes_body,
                tramo_despues_body,
                observacion_1_body,
                verticales_body,
                cortes_body,
                suministro_alternativo_body,
                cumple_plano_body,
                observacion_2_body,
                construccion_nueva_body,
                tipo_propiedad_body,
                habitantes_body,
                banos_body,
                sup_edificada_body,
                sup_jardin_body,
                accesos_body,
                sup_terreno_body,
                observacion_3_body,
                auto_abastecimiento_body,
                tipo_fuente_body,
                uso_body,
                activo_body,
                capacidad_bomba_body,
                resolucion_dga_body,
                caudal_body,
                observacion_4_body,
                nombre_residente_body,
                rut_residente_body,
                telefono_residente_body,
                email_residente_body,
                fecha_residente_body,
                foto_rechazo_inspeccion,
                foto_falla_medidor,
                foto_lectura_medidor,
                foto_numero_medidor,
                foto_panoramica_medidor,
                foto_numero_propiedad,
                foto_fechada_propiedad
        );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("NegotiationFragment", "onResponse");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                FirebaseCrash.log("Exception: " + t.getMessage());

            }
        });
    }
}
