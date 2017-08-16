package com.zecovery.android.zemedidores.views.assignments.fragments.executetest;

import com.google.firebase.auth.FirebaseAuth;
import com.zecovery.android.zemedidores.data.CurrentUser;
import com.zecovery.android.zemedidores.data.Nodes;

/**
 * Created by fbarrios80 on 03-07-17.
 */

public class MeterLocation {

    private MeterLocationCallback callback;

    public MeterLocation(MeterLocationCallback callback) {
        this.callback = callback;
    }

    public void saveLocation(String token, String location) {

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            if (token != null && location != null) {
                new Nodes().meterLocation(new CurrentUser().uid(), token).setValue(location);
                callback.saveMeterLocation();
            } else {
                callback.errorSavingMeterLocation();
            }
        }
    }
}