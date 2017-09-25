package com.zecovery.android.zemedidores.views.assignments.fragments.forms;

import android.content.Context;

import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.models.Residente;

/**
 * Created by moe on 22-09-17.
 */

public class NuevaInspeccion {

    private NuevaInspeccionCallback callback;
    private Context context;

    public NuevaInspeccion(NuevaInspeccionCallback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    public void guardaInspeccion(Residente residente, int idInspeccion) {

        LocalDatabase db = new LocalDatabase(context);

        db.guardaDatosResidente(residente, idInspeccion);

        if (residente != null && idInspeccion < 0) {
            callback.guardarNuevbaInspeccion();
        } else {
            callback.errorGuardaNuevaInspeccion();
        }
    }
}
