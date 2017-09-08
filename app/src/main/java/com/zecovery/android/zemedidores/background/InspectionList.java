package com.zecovery.android.zemedidores.background;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.crash.FirebaseCrash;
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
        Call<Inspect> call = request.get(params.getKey(), params.getInspectorEmail());
        //Call<Inspect> call = request.get(123, "inspector4@zecovery.com");

        Log.d("doInBackground", "request: " + request);
        Log.d("doInBackground", "call: " + call);

        List<Inspection> inspectionsList = new ArrayList<>();

        try {
            Response<Inspect> response = call.execute();

            Log.d("doInBackground", "response: " + response);

            Collections.addAll(inspectionsList, response.body().getInspecciones());

        } catch (Exception e) {
            FirebaseCrash.log("ERROR: " + e);
            Log.d("InspectionList", "Exception: " + e);
        }

        Log.d("doInBackground", "inspectionsList: " + inspectionsList);

        return inspectionsList;
    }
}
