package com.zecovery.android.zemedidores.views.login;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by fbarrios80 on 08-05-17.
 */

public class LoginValidation {

    private LoginValidationCallback callback;

    public LoginValidation(LoginValidationCallback callback) {
        this.callback = callback;
    }

    public void init() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            callback.logged();
        } else {
            callback.signIn();
        }
    }
}
