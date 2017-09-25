package com.zecovery.android.zemedidores.network;

import android.content.Context;
import android.support.annotation.Nullable;

import com.google.firebase.crash.FirebaseCrash;
import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.views.assignments.RechazoCallback;

import java.io.File;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.zecovery.android.zemedidores.data.Constant.URL_BASE;

/**
 * Created by fbarrios80 on 03-07-17.
 */

public class EnviaInspeccionRechazada {

    private RechazoCallback callback;

    public EnviaInspeccionRechazada(RechazoCallback callback) {
        this.callback = callback;
    }

    public void envia(int idInspeccion, String photoName, String reason, @Nullable String reasonText, String localPath, Context context) {

        LocalDatabase db = new LocalDatabase(context);


        String status = "rechazo";

        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), status);

        RequestBody lat = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(db.getCurrentDbLocation().latitude));
        RequestBody lng = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(db.getCurrentDbLocation().longitude));

        Calendar rightNow = Calendar.getInstance();
        int fechaAcutal = (int) (rightNow.getTimeInMillis() / 1000);
        RequestBody fecha = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(fechaAcutal));

        File file = new File(localPath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("foto_rechazo", photoName, requestFile);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_BASE).build();
        PostResult service = retrofit.create(PostResult.class);

        RequestBody idInspeccionBody = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(idInspeccion));
        RequestBody reasonBody = RequestBody.create(MediaType.parse("multipart/form-data"), reason);

        if (reasonText == null) {

            Call<ResponseBody> call = service.postInspectionRejected(description, idInspeccionBody, reasonBody, null, lat, lng, fecha, body);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    callback.enviar();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    FirebaseCrash.log("Exception: " + t.getMessage());
                    callback.error();
                }
            });

        } else {
            RequestBody reasonTextBody = RequestBody.create(MediaType.parse("multipart/form-data"), reasonText);
            Call<ResponseBody> call = service.postInspectionRejected(description, idInspeccionBody, reasonBody, reasonTextBody, lat, lng, fecha, body);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    callback.enviar();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    FirebaseCrash.log("Exception: " + t.getMessage());
                    callback.error();
                }
            });
        }
    }
}