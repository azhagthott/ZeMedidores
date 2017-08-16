package com.zecovery.android.zemedidores.views.assignments.fragments.forms;

import com.google.firebase.auth.FirebaseAuth;
import com.zecovery.android.zemedidores.data.CurrentUser;
import com.zecovery.android.zemedidores.data.Nodes;

/**
 * Created by fbarrios80 on 03-07-17.
 */

public class NegotiationFormSave {

    private NegotiationCallback callback;

    public NegotiationFormSave(NegotiationCallback callback) {
        this.callback = callback;
    }

    public void saveForm(String token, String accepted, String paymenMethod) {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            if (token != null) {
                new Nodes().negotiationForms(new CurrentUser().uid(), token).child("negotiation_accepted").setValue(accepted);
                new Nodes().negotiationForms(new CurrentUser().uid(), token).child("payment_methos").setValue(paymenMethod);
                callback.saveNegotiationForm();
            } else {
                callback.negotiationFormError();
            }
        }
    }
}