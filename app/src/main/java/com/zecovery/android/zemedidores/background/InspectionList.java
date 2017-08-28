package com.zecovery.android.zemedidores.background;

import android.os.AsyncTask;
import android.util.Log;

import com.zecovery.android.zemedidores.models.Inspect;
import com.zecovery.android.zemedidores.models.Inspection;
import com.zecovery.android.zemedidores.network.InspectionInterceptor;
import com.zecovery.android.zemedidores.network.InspectionInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by moe on 19-08-17.
 */

public class InspectionList extends AsyncTask<InspectionParams, Integer, List<Inspection>> {

    private InspectionParams params;

    public InspectionList(InspectionParams params) {
        this.params = params;
    }

    @Override
    protected List<Inspection> doInBackground(InspectionParams... inspections) {

        InspectionInterface request = InspectionInterceptor.get();
        Call<Inspect> call = request.get(params.getKey(),params.getInspectorEmail());

        List<Inspection> inspectionsList = new ArrayList<>();

        try {
            Response<Inspect> response = call.execute();
            Log.d("InspectionList", "response: " + response);
            Log.d("InspectionList", "body: " + response.body());

            Collections.addAll(inspectionsList, response.body().getInspecciones());

            /*for (Inspection inspection : response.body().getInspecciones()) {
                inspectionsList.add(inspection);
            }*/

        } catch (Exception e) {
            Log.d("InspectionList", "Exception: " + e);
        }
        return inspectionsList;
    }
}
