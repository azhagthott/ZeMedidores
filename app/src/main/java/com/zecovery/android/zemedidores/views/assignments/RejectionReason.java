package com.zecovery.android.zemedidores.views.assignments;

import android.support.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.zecovery.android.zemedidores.data.CurrentUser;
import com.zecovery.android.zemedidores.data.Nodes;

/**
 * Created by fbarrios80 on 02-07-17.
 */

public class RejectionReason {

    private RejectionCallback callback;

    public RejectionReason(RejectionCallback callback) {
        this.callback = callback;
    }

    public void sendRejectionReason(String token, String reason, @Nullable String anotherReason) {

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            CurrentUser currentUser = new CurrentUser();
            String uid = currentUser.sanitizedEmail(currentUser.uid());

            if (anotherReason == null) {
                new Nodes().rejections(uid).child(token).child("reason").setValue(reason);
            } else {
                new Nodes().rejections(uid).child(token).child("reason").setValue(reason);
                new Nodes().rejections(uid).child(token).child("reason_text").setValue(anotherReason);
            }
            callback.send();
        } else {
            callback.error();
        }
    }
}