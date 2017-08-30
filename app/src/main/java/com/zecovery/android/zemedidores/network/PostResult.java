package com.zecovery.android.zemedidores.network;

import com.zecovery.android.zemedidores.models.InspectionPhoto;

import org.json.JSONArray;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by moe on 28-08-17.
 */

public interface PostResult {
    @POST("wsMovil/wsputInspeccion.php")
    @FormUrlEncoded
    Call<JSONArray> post(@Field("inspeccion_json") JSONArray jsonArray);

    @Multipart
    @POST("wsMovil/wsputInspeccion.php")
    Call<InspectionPhoto> uploadFile(@Part MultipartBody.Part file);
}
