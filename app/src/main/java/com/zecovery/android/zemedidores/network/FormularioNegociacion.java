package com.zecovery.android.zemedidores.network;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.zecovery.android.zemedidores.data.CurrentUser;
import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.data.Nodes;

/**
 * Created by fbarrios80 on 03-07-17.
 */

public class FormularioNegociacion {

    private NegociacionCallback callback;

    public FormularioNegociacion(NegociacionCallback callback) {
        this.callback = callback;
    }

    public void guardaFormulario(Context context, int token, String accepted, String paymenMethod) {

        LocalDatabase db = new LocalDatabase(context);


        db.guardaFormularioNegociacion(token);

    }
}