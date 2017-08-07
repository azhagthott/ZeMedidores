package com.zecovery.android.zemedidores.views.assignments.fragments.executetest;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;
import com.zecovery.android.zemedidores.data.CurrentUser;
import com.zecovery.android.zemedidores.data.Nodes;

/**
 * Created by fbarrios80 on 03-07-17.
 */

public class BrokenMeter extends Activity {

    private BrokenMeterCallback callback;

    public BrokenMeter(BrokenMeterCallback callback) {
        this.callback = callback;
    }

    public void inform(String token, String failure, String location, String urlPhoto) {

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            String uid = new CurrentUser().uid();
            Nodes node = new Nodes();
            node.meterLocation(uid, token).setValue(location);
            node.brokenMeter(uid, token).child("tipo_falla").setValue(failure);
            node.brokenMeter(uid, token).child("url_photo").setValue(urlPhoto);

            callback.informBrokenMeter();
        } else {
            callback.informBrokenMeterError();
        }
    }
}
