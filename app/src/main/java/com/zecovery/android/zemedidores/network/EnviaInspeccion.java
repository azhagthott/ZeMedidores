package com.zecovery.android.zemedidores.network;

import android.content.Context;
import android.util.Log;

import com.google.firebase.crash.FirebaseCrash;
import com.zecovery.android.zemedidores.data.CurrentUser;
import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.models.ResultadoInspeccion;

import java.io.File;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.zecovery.android.zemedidores.data.Constant.URL_BASE;

/**
 * Created by fbarrios80 on 10-09-17.
 */

public class EnviaInspeccion {

    private EnviaInspeccionCallback callback;

    public EnviaInspeccion(EnviaInspeccionCallback callback) {
        this.callback = callback;
    }

    public void enviaInpeccion(Context context, int idInspeccion) {

        LocalDatabase db = new LocalDatabase(context);
        ResultadoInspeccion resultado = db.getResultadoInspeccion(idInspeccion);

        Calendar rightNow = Calendar.getInstance();
        int fechaAcutal = (int) (rightNow.getTimeInMillis() / 1000);
        RequestBody fecha_body = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(fechaAcutal));

        String test1 = resultado.getTestParte1().getTest1();
        String test2 = resultado.getTestParte1().getTest2();
        String test3 = resultado.getTestParte1().getTest3();
        String test4 = resultado.getTestParte1().getTest4();
        String test5 = resultado.getTestParte1().getTest5();
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

        String nombre_residente = String.valueOf(resultado.getResidente().getNombreResidente());
        String rut_residente = String.valueOf(resultado.getResidente().getRutResidente());
        String telefono_residente = String.valueOf(resultado.getResidente().getTelefonoResidente());
        String email_residente = String.valueOf(resultado.getResidente().getEmailResidente());
        String fecha_residente = String.valueOf(resultado.getResidente().getFechaResidente());

        String numero_medidor = String.valueOf(resultado.getMedidor().getNumeroMedidor());
        String diametro_medidor = String.valueOf(resultado.getMedidor().getNumeroMedidor());
        String lectura_medidor = String.valueOf(resultado.getMedidor().getLecturaMedidor());

        String numero_cliente = String.valueOf(resultado.getResidente().getNumeroCliente());
        String empresa_inspeccion = String.valueOf(resultado.getEmpresaInspeccion());


        RequestBody id_inspeccion_body = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(idInspeccion));
        RequestBody lat_body = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(db.getCurrentDbLocation().latitude));
        RequestBody lng_body = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(db.getCurrentDbLocation().longitude));
        RequestBody estado_body = RequestBody.create(MediaType.parse("multipart/form-data"), "realizada");
        RequestBody test1_body = RequestBody.create(MediaType.parse("multipart/form-data"), test1);
        RequestBody test2_body = RequestBody.create(MediaType.parse("multipart/form-data"), test2);
        RequestBody test3_body = RequestBody.create(MediaType.parse("multipart/form-data"), test3);
        RequestBody test4_body = RequestBody.create(MediaType.parse("multipart/form-data"), test4);
        RequestBody test5_body = RequestBody.create(MediaType.parse("multipart/form-data"), test5);
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
        RequestBody numero_medidor_body = RequestBody.create(MediaType.parse("multipart/form-data"), numero_medidor);
        RequestBody diametro_medidor_body = RequestBody.create(MediaType.parse("multipart/form-data"), diametro_medidor);
        RequestBody lectura_medidor_body = RequestBody.create(MediaType.parse("multipart/form-data"), lectura_medidor);
        RequestBody nombre_residente_body = RequestBody.create(MediaType.parse("multipart/form-data"), nombre_residente);
        RequestBody rut_residente_body = RequestBody.create(MediaType.parse("multipart/form-data"), rut_residente);
        RequestBody telefono_residente_body = RequestBody.create(MediaType.parse("multipart/form-data"), telefono_residente);
        RequestBody email_residente_body = RequestBody.create(MediaType.parse("multipart/form-data"), email_residente);
        RequestBody fecha_residente_body = RequestBody.create(MediaType.parse("multipart/form-data"), fecha_residente);

        RequestBody numero_cliente_body = RequestBody.create(MediaType.parse("multipart/form-data"), numero_cliente);
        RequestBody empresa_inspeccion_body = RequestBody.create(MediaType.parse("multipart/form-data"), empresa_inspeccion);

        String email_inspector = new CurrentUser().email();
        RequestBody email_inspector_body = RequestBody.create(MediaType.parse("multipart/form-data"), email_inspector);


        MultipartBody.Part foto_falla_medidor;
        MultipartBody.Part foto_lectura_medidor;
        MultipartBody.Part foto_numero_medidor;
        MultipartBody.Part foto_panoramica_medidor;
        MultipartBody.Part foto_numero_propiedad;
        MultipartBody.Part foto_fechada_propiedad;
        MultipartBody.Part foto_intervension_red;
        MultipartBody.Part foto_bypass;
        MultipartBody.Part foto_otro;

        if (resultado.getFotos().getFallaMedidor() != null) {
            File file_foto_falla = new File(resultado.getFotos().getFallaMedidor());
            RequestBody requestFileFalla = RequestBody.create(MediaType.parse("image/*"), file_foto_falla);
            foto_falla_medidor = MultipartBody.Part.createFormData("foto_falla_medidor", "foto_falla_medidor", requestFileFalla);
        } else {
            foto_falla_medidor = null;
        }

        if (resultado.getFotos().getLecturaMedidor() != null) {
            File file_foto_lectura_medidor = new File(resultado.getFotos().getLecturaMedidor());
            RequestBody requestFileLectura = RequestBody.create(MediaType.parse("image/*"), file_foto_lectura_medidor);
            foto_lectura_medidor = MultipartBody.Part.createFormData("foto_lectura_medidor", "foto_lectura_medidor", requestFileLectura);
        } else {
            foto_lectura_medidor = null;
        }

        if (resultado.getFotos().getNumeroMedidor() != null) {
            File file_foto_numero_medidor = new File(resultado.getFotos().getNumeroMedidor());
            RequestBody requestFileNumeroMedidor = RequestBody.create(MediaType.parse("image/*"), file_foto_numero_medidor);
            foto_numero_medidor = MultipartBody.Part.createFormData("foto_numero_medidor", "foto_numero_medidor", requestFileNumeroMedidor);
        } else {
            foto_numero_medidor = null;
        }

        if (resultado.getFotos().getPanoramicaMedidor() != null) {
            File file_foto_panoramica_medidor = new File(resultado.getFotos().getPanoramicaMedidor());
            RequestBody requestFilePanoMedidor = RequestBody.create(MediaType.parse("image/*"), file_foto_panoramica_medidor);
            foto_panoramica_medidor = MultipartBody.Part.createFormData("foto_panoramica_medidor", "foto_panoramica_medidor", requestFilePanoMedidor);
        } else {
            foto_panoramica_medidor = null;
        }

        if (resultado.getFotos().getNumeroPropiedad() != null) {
            File file_foto_numero_propiedad = new File(resultado.getFotos().getNumeroPropiedad());
            RequestBody requestFileNumeroPorpiedad = RequestBody.create(MediaType.parse("image/*"), file_foto_numero_propiedad);
            foto_numero_propiedad = MultipartBody.Part.createFormData("foto_numero_propiedad", "foto_numero_propiedad", requestFileNumeroPorpiedad);
        } else {
            foto_numero_propiedad = null;
        }

        if (resultado.getFotos().getFachadaPropiedad() != null) {
            File file_foto_fachada_propiedad = new File(resultado.getFotos().getFachadaPropiedad());
            RequestBody requestFileFachadaPorpiedad = RequestBody.create(MediaType.parse("image/*"), file_foto_fachada_propiedad);
            foto_fechada_propiedad = MultipartBody.Part.createFormData("foto_fachada_propiedad", "foto_fachada_propiedad", requestFileFachadaPorpiedad);
        } else {
            foto_fechada_propiedad = null;
        }

        if (resultado.getFotos().getIntervencionRed() != null) {
            File file_foto_intervension_red = new File(resultado.getFotos().getIntervencionRed());
            RequestBody requestFileIntervensionRed = RequestBody.create(MediaType.parse("image/*"), file_foto_intervension_red);
            foto_intervension_red = MultipartBody.Part.createFormData("foto_intervension_red", "foto_intervension_red", requestFileIntervensionRed);
        } else {
            foto_intervension_red = null;
        }

        if (resultado.getFotos().getBypass() != null) {
            File file_foto_bypass = new File(resultado.getFotos().getBypass());
            RequestBody requestFileBypass = RequestBody.create(MediaType.parse("image/*"), file_foto_bypass);
            foto_bypass = MultipartBody.Part.createFormData("foto_bypass", "foto_bypass", requestFileBypass);
        } else {
            foto_bypass = null;
        }

        if (resultado.getFotos().getOtro() != null) {
            File file_foto_otro = new File(resultado.getFotos().getOtro());
            RequestBody requestFileOtro = RequestBody.create(MediaType.parse("image/*"), file_foto_otro);
            foto_otro = MultipartBody.Part.createFormData("foto_otro", "foto_otro", requestFileOtro);
        } else {
            foto_otro = null;
        }

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_BASE).build();
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
                test4_body,
                test5_body,
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
                numero_medidor_body,
                diametro_medidor_body,
                lectura_medidor_body,
                numero_cliente_body,
                empresa_inspeccion_body,
                email_inspector_body,
                foto_falla_medidor,
                foto_lectura_medidor,
                foto_numero_medidor,
                foto_panoramica_medidor,
                foto_numero_propiedad,
                foto_fechada_propiedad,
                foto_intervension_red,
                foto_bypass,
                foto_otro
        );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.d("onResponse", "code:" + response.raw().code());
                Log.d("onResponse", "message:" + response.raw().message());
                Log.d("onResponse", "body:" + response.raw().body());

                if (200 == response.code()) {
                    callback.enviaInspeccionOk(200);
                } else {
                    callback.enviarInspeccionError(response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                FirebaseCrash.log("Exception: " + t.getMessage());
            }
        });
    }
}
