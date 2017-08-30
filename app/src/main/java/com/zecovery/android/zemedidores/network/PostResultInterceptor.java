package com.zecovery.android.zemedidores.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zecovery.android.zemedidores.data.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by moe on 28-08-17.
 */

public class PostResultInterceptor {

    public PostResult post() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE_DESA)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return interceptor.create(PostResult.class);
    }

    public PostResult uploadFile() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE_DESA)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return interceptor.create(PostResult.class);
    }
}