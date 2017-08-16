package com.zecovery.android.zemedidores.views.login;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.zecovery.android.zemedidores.data.CurrentUser;
import com.zecovery.android.zemedidores.data.Nodes;

/**
 * Created by fbarrios80 on 08-05-17.
 */

public class LoginAuthorization implements ValueEventListener {

    private LoginAuthorizationCallback callback;

    public LoginAuthorization(LoginAuthorizationCallback callback) {
        this.callback = callback;
    }

    public void init() {
        final CurrentUser currentUser = new CurrentUser();
        final String email = currentUser.email();
        final String sanitized = currentUser.sanitizedEmail(email);
        new Nodes().registrations(sanitized).addListenerForSingleValueEvent(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if (dataSnapshot.exists()) {
            callback.authorized();
        } else {
            callback.unAuthorized();
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        callback.unAuthorized();
    }
}