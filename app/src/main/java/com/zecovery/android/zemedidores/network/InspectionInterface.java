package com.zecovery.android.zemedidores.network;

import com.zecovery.android.zemedidores.models.Inspector;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by moe on 19-08-17.
 */

public interface InspectionInterface {

    @GET("wsMovil/wsgetRealizarInspecciones.php")
    Call<Inspector> get(@Query("key") int key, @Query("inspector") String inspector);

}
