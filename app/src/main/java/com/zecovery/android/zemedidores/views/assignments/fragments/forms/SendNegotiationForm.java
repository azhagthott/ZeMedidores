package com.zecovery.android.zemedidores.views.assignments.fragments.forms;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by fbarrios80 on 03-07-17.
 */

public class SendNegotiationForm {

    private NegotiationCallback callback;

    public SendNegotiationForm(NegotiationCallback callback) {
        this.callback = callback;
    }

    public void save(String token) {

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            if (token != null) {
                callback.saveNegotiationForm();
            } else {
                callback.negotiationFormError();
            }
        }
    }
}