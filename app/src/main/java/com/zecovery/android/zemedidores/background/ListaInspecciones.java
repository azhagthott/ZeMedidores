package com.zecovery.android.zemedidores.background;

import android.os.AsyncTask;

import com.google.firebase.crash.FirebaseCrash;
import com.zecovery.android.zemedidores.models.Inspect;
import com.zecovery.android.zemedidores.models.Inspection;
import com.zecovery.android.zemedidores.network.InspeccionInterceptor;
import com.zecovery.android.zemedidores.network.InspectionInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by moe on 19-08-17.
 */

public class ListaInspecciones extends AsyncTask<InspeccionParams, Integer, List<Inspection>> {

    private InspeccionParams params;

    public ListaInspecciones(InspeccionParams params) {
        this.params = params;
    }

    @Override
    protected List<Inspection> doInBackground(InspeccionParams... inspections) {

        InspectionInterface request = InspeccionInterceptor.get();
        Call<Inspect> call = request.get(params.getKey(), params.getInspectorEmail());

        List<Inspection> inspectionsList = new ArrayList<>();

        try {
            Response<Inspect> response = call.execute();
            Collections.addAll(inspectionsList, response.body().getInspecciones());

        } catch (Exception e) {
            FirebaseCrash.log("ERROR: " + e);
        }
        return inspectionsList;
    }
}
