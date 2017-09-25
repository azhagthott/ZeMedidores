package com.zecovery.android.zemedidores.network;

import android.support.annotation.Nullable;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by moe on 28-08-17.
 */

public interface PostResult {

    @Multipart
    @POST("/wsMovil/wsputInspeccion.php")
    Call<ResponseBody> postInspectionRejected(
            @Part("estado") RequestBody status,
            @Part("id_inspeccion") RequestBody idInspeccion,
            @Part("razon") RequestBody reason,
            @Part("razon_texto") RequestBody reasonText,
            @Part("lat") RequestBody lat,
            @Part("lng") RequestBody lng,
            @Part("fecha_inspeccion") RequestBody fecha,
            @Part MultipartBody.Part file
    );

    @Multipart
    @POST("/wsMovil/wsputInspeccion.php")
    Call<ResponseBody> postResultadoTest(
            @Part("id_inspeccion") RequestBody id_inspeccion_body,
            @Part("lat") RequestBody lat_body,
            @Part("lng") RequestBody lng_body,
            @Part("estado") RequestBody estado_body,
            @Part("fecha") RequestBody fecha_body,
            @Part("test1") RequestBody test1_body,
            @Part("test2") RequestBody test2_body,
            @Part("test3") RequestBody test3_body,
            @Part("test4") RequestBody test4_body,
            @Part("test5") RequestBody test5_body,
            @Part("uso_imanes") RequestBody uso_imanes_body,
            @Part("invertir_tomas") RequestBody invertir_tomas_body,
            @Part("perfora_cupula") RequestBody perfora_cupula_body,
            @Part("corta_engranaje") RequestBody corta_engranaje_body,
            @Part("uso_alambres") RequestBody uso_alambres_body,
            @Part("prensado") RequestBody prensado_body,
            @Part("otro_1") RequestBody otro_body,
            @Part("instalacion_paralela") RequestBody instalacion_paralela_body,
            @Part("bypass") RequestBody bypass_body,
            @Part("otro_2") RequestBody otro_2_body,
            @Part("clase") RequestBody clase_body,
            @Part("ano_medidor") RequestBody ano_medidor_body,
            @Part("marca") RequestBody marca_body,
            @Part("registrador") RequestBody registrador_body,
            @Part("instalacion") RequestBody instalacion_body,
            @Part("tramo_antes") RequestBody tramo_antes_body,
            @Part("tramo_despues") RequestBody tramo_despues_body,
            @Part("observacion_1") RequestBody observacion_1_body,
            @Part("verticales") RequestBody verticales_body,
            @Part("cortes") RequestBody cortes_body,
            @Part("suministro_alternativo") RequestBody suministro_alternativo_body,
            @Part("cumple_plano") RequestBody cumple_plano_body,
            @Part("observacion_2") RequestBody observacion_2_body,
            @Part("construccion_nueva") RequestBody construccion_nueva_body,
            @Part("tipo_propiedad") RequestBody tipo_propiedad_body,
            @Part("habitantes") RequestBody habitantes_body,
            @Part("banos") RequestBody banos_body,
            @Part("sup_edificada") RequestBody sup_edificada_body,
            @Part("sup_jardin") RequestBody sup_jardin_body,
            @Part("accesos") RequestBody accesos_body,
            @Part("sup_terreno") RequestBody sup_terreno_body,
            @Part("observacion_3") RequestBody observacion_3_body,
            @Part("auto_abastecimiento") RequestBody auto_abastecimiento_body,
            @Part("tipo_fuente") RequestBody tipo_fuente_body,
            @Part("uso") RequestBody uso_body,
            @Part("activo") RequestBody activo_body,
            @Part("capacidad_bomba") RequestBody capacidad_bomba_body,
            @Part("resolucion_dga") RequestBody resolucion_dga_body,
            @Part("caudal") RequestBody caudal_body,
            @Part("observacion_4") RequestBody observacion_4_body,
            @Part("nombre_residente") RequestBody nombre_residente_body,
            @Part("rut_residente") RequestBody rut_residente_body,
            @Part("telefono_residente") RequestBody telefono_residente_body,
            @Part("email_residente") RequestBody email_residente_body,
            @Part("fecha_residente") RequestBody fecha_residente_body,
            @Part("numero_medidor") RequestBody numero_medidor_body,
            @Part("diametro_medidor") RequestBody diametro_medidor_body,
            @Part("lectura_medidor") RequestBody lectura_medidor_body,
            @Part("numero_cliente") RequestBody numero_cliente,
            @Part("empresa_inspeccion") RequestBody empresa_inspeccion,
            @Nullable @Part MultipartBody.Part foto_falla_medidor,
            @Nullable @Part MultipartBody.Part foto_lectura_medidor,
            @Nullable @Part MultipartBody.Part foto_numero_medidor,
            @Nullable @Part MultipartBody.Part foto_panoramica_medidor,
            @Nullable @Part MultipartBody.Part foto_numero_propiedad,
            @Nullable @Part MultipartBody.Part foto_fechada_propiedad,
            @Nullable @Part MultipartBody.Part foto_intervension_red,
            @Nullable @Part MultipartBody.Part foto_bypass,
            @Nullable @Part MultipartBody.Part foto_otro
    );
}
