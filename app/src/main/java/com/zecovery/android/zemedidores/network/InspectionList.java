package com.zecovery.android.zemedidores.network;

import android.os.AsyncTask;
import android.util.Log;

import com.zecovery.android.zemedidores.models.Inspection;
import com.zecovery.android.zemedidores.models.Inspector;

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
        Call<Inspector> call = request.get(123, "inspector8@zecovery.com");

        List<Inspection> inspectionsList = new ArrayList<>();

        try {
            Response<Inspector> response = call.execute();
            for (Inspection inspections : response.body().getInspections()) {
                inspectionsList.add(inspections);
            }
        } catch (Exception e) {
            Log.d("LOG_TAG", "Exception: " + e);
        }
        return inspectionsList;
    }
}
