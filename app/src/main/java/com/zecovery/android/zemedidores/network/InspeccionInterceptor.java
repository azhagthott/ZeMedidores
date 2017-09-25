package com.zecovery.android.zemedidores.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.zecovery.android.zemedidores.data.Constant.READ_TIME_OUT;
import static com.zecovery.android.zemedidores.data.Constant.TIME_OUT;
import static com.zecovery.android.zemedidores.data.Constant.URL_BASE;

/**
 * Created by moe on 19-08-17.
 */

public class InspeccionInterceptor {

    public static InspectionInterface get() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);

        OkHttpClient client = httpClient.build();

        Gson gson = new GsonBuilder().setLenient().create();


        Log.d("TAG", "get: " + gson.toString());


        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return interceptor.create(InspectionInterface.class);
    }
}