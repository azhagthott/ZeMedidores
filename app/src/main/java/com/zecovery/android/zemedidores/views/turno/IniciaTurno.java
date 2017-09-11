package com.zecovery.android.zemedidores.views.turno;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ServerValue;
import com.zecovery.android.zemedidores.data.CurrentUser;
import com.zecovery.android.zemedidores.data.Nodes;
import com.zecovery.android.zemedidores.views.turno.IniciaTurnoCallback;

/**
 * Created by fbarrios80 on 12-05-17.
 */

public class IniciaTurno {

    private IniciaTurnoCallback callback;

    public IniciaTurno(IniciaTurnoCallback callback) {
        this.callback = callback;
    }

    public void init() {

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            CurrentUser currentUser = new CurrentUser();
            String sanitized = currentUser.sanitizedEmail(currentUser.email());
            new Nodes().shifts(sanitized).push().child("start").setValue(ServerValue.TIMESTAMP);
            callback.iniciaTurnoOk();
        } else {
            callback.iniciaTurnoError();
        }
    }
}
