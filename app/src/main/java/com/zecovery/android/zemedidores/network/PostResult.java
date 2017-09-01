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
            @Part MultipartBody.Part file
    );


    @Multipart
    @POST("/wsMovil/wsputInspeccion.php")
    Call<ResponseBody> postMeterStatus(
            @Part("estado") RequestBody status,
            @Part("id_inspeccion") RequestBody idInspeccion,
            @Part("ubicacion_medidor") RequestBody ubicacionMedidor,
            @Part("comentario") RequestBody comment,
            @Part List<MultipartBody.Part> files
    );

}
