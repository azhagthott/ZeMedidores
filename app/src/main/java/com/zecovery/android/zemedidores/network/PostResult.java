package com.zecovery.android.zemedidores.network;

import java.util.List;

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
    Call<ResponseBody> postMeterStatus(
            @Part("estado") RequestBody status,
            @Part("id_inspeccion") RequestBody idInspeccion,
            @Part("ubicacion_medidor") RequestBody ubicacionMedidor,
            @Part("comentario") RequestBody comment,
            @Part("lat") RequestBody lat,
            @Part("lng") RequestBody lng,
            @Part("fecha_inspeccion") RequestBody fecha,
            @Part List<MultipartBody.Part> files
    );


    @Multipart
    @POST("/wsMovil/wsputInspeccion.php")
    Call<ResponseBody> postResultadoTest(
            @Part("lat") RequestBody lat,
            @Part("lng") RequestBody lng,
            @Part("fecha_inspeccion") RequestBody fecha,
            @Part("test1") RequestBody test1,
            @Part("test2") RequestBody test2,
            @Part("test3") RequestBody test3,
            @Part("uso_imanes") RequestBody usoImanes,
            @Part("inv_tomas") RequestBody invertirTomas,
            @Part("perf_cupula") RequestBody perforaCupula,
            @Part("corta_eng") RequestBody cortaEnganaje,
            @Part("uso_alambre") RequestBody usoAlambres,
            @Part("prensado") RequestBody prensado,
            @Part("otro_1") RequestBody otro1,
            @Part("inst_paralela") RequestBody instalacionParalela,
            @Part("bypass") RequestBody bypass,
            @Part("otro_2") RequestBody otro2
    );


}
