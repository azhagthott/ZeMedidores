package com.zecovery.android.zemedidores.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.zecovery.android.zemedidores.data.Constant.URL_BASE;

/**
 * Created by moe on 28-08-17.
 */

public class PostResultInterceptor {

    public PostResult post() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return interceptor.create(PostResult.class);
    }
}