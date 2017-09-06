package com.zecovery.android.zemedidores.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.zecovery.android.zemedidores.data.Constant.URL_BASE_DESA;

/**
 * Created by fbarrios80 on 31-08-17.
 */

public class ServicesGenerator {

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(URL_BASE_DESA)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);

    }
}
