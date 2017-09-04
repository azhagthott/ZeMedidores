package com.zecovery.android.zemedidores.views.assignments.fragments.forms;

import android.content.Context;

import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.models.Residente;

/**
 * Created by moe on 02-09-17.
 */

public class SaveResidentData {

    private SaveResidentialForm callback;
    private Context context;

    public SaveResidentData(SaveResidentialForm callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    public void save(Residente resident, int token) {
        if (resident != null) {
            LocalDatabase db = new LocalDatabase(context);
            db.guardaDatosResidente(resident, token);
            callback.save();
        } else {
            callback.error();
        }


    }
}
