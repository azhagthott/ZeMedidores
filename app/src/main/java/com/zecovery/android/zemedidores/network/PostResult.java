package com.zecovery.android.zemedidores.network;

import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by moe on 28-08-17.
 */

public interface PostResult {
    @POST("wsMovil/wsputInspeccion.php")
    @FormUrlEncoded
    Call<JSONArray> post(@Field("a") JSONArray jsonArray);
}
