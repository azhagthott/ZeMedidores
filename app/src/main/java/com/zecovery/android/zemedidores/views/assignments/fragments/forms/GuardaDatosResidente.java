package com.zecovery.android.zemedidores.views.assignments.fragments.forms;

import android.content.Context;

import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.models.Residente;

/**
 * Created by moe on 02-09-17.
 */

public class GuardaDatosResidente {

    private SaveResidentialForm callback;
    private Context context;

    public GuardaDatosResidente(SaveResidentialForm callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    public void guardaDatos(Residente resident, int token) {
        if (resident != null) {
            LocalDatabase db = new LocalDatabase(context);
            db.guardaDatosResidente(resident, token);
            callback.save();
        } else {
            callback.error();
        }
    }
}