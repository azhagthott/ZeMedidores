package com.zecovery.android.zemedidores.network;

import com.zecovery.android.zemedidores.models.InspectionPhoto;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by moe on 30-08-17.
 */

public interface UploadImageInterface {
    @Multipart
    @POST("wsMovil/wsputInspeccion.php")
    Call<InspectionPhoto> uploadFile(@Part MultipartBody.Part file, @Part("name") RequestBody name);
}
