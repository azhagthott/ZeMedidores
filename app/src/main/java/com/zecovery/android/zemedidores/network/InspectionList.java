package com.zecovery.android.zemedidores.network;

import android.os.AsyncTask;
import android.util.Log;

import com.zecovery.android.zemedidores.models.Inspect;
import com.zecovery.android.zemedidores.models.Inspection;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by moe on 19-08-17.
 */

public class InspectionList extends AsyncTask<String, Integer, List<Inspection>> {
    @Override
    protected List<Inspection> doInBackground(String... strings) {

        InspectionInterface request = InspectionInterceptor.get();
        Call<Inspect> call = request.get(123, "inspector4@zecovery.com");

        List<Inspection> inspectionsList = new ArrayList<>();

        try {
            Response<Inspect> response = call.execute();
            Log.d("InspectionList", "response: " + response);
            Log.d("InspectionList", "body: " + response.body());

            for (Inspection inspection: response.body().getInspecciones()) {
                inspectionsList.add(inspection);
            }

        } catch (Exception e) {
            Log.d("InspectionList", "Exception: " + e);
        }
        return inspectionsList;
    }
}
