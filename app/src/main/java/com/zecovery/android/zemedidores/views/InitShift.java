package com.zecovery.android.zemedidores.views;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ServerValue;
import com.zecovery.android.zemedidores.data.CurrentUser;
import com.zecovery.android.zemedidores.data.Nodes;

/**
 * Created by fbarrios80 on 12-05-17.
 */

public class InitShift {

    private ShiftCallback callback;

    public InitShift(ShiftCallback callback) {
        this.callback = callback;
    }

    public void init() {

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            CurrentUser currentUser = new CurrentUser();
            String sanitized = currentUser.sanitizedEmail(currentUser.email());
            new Nodes().shifts(sanitized).push().child("start").setValue(ServerValue.TIMESTAMP);
            callback.success();
        } else {
            callback.error();
        }
    }
}
