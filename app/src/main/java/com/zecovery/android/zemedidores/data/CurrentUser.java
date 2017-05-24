package com.zecovery.android.zemedidores.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by fbarrios80 on 08-05-17.
 */

public class CurrentUser {

    private final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    public FirebaseUser get() {
        return currentUser;
    }

    public String email() {
        return get().getEmail();
    }

    public String sanitizedEmail(String email) {
        return email.replace("@", "AT").replace(".", "DOT");
    }

    public String name() {
        return get().getDisplayName();
    }

    public String uid() {
        return get().getUid();
    }

}
