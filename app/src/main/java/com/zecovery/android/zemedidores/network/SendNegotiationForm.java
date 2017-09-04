package com.zecovery.android.zemedidores.network;

import com.google.firebase.auth.FirebaseAuth;
import com.zecovery.android.zemedidores.network.NegociacionCallback;

/**
 * Created by fbarrios80 on 03-07-17.
 */

public class SendNegotiationForm {

    private NegociacionCallback callback;

    public SendNegotiationForm(NegociacionCallback callback) {
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