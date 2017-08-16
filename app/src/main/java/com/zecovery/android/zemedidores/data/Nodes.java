package com.zecovery.android.zemedidores.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fbarrios80 on 08-05-17.
 */

public class Nodes {

    public void setPersistance() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        root().keepSynced(true);
    }

    private DatabaseReference root() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public Query assignments() {
        String today = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        return root().child("assignments")
                .child(new CurrentUser().uid()).orderByChild("visibility_date").endAt("true_" + today);
    }

    public DatabaseReference preLoadedData(String token) {
        String uid = new CurrentUser().uid();
        return root().child("assignments").child(uid).child(token);
    }

    public DatabaseReference registrations(String sanitizedEmail) {
        return root().child("registrations").child(sanitizedEmail);
    }

    public DatabaseReference shifts(String sanitizedEmail) {
        return root().child("shifts").child(sanitizedEmail);
    }

    public DatabaseReference rejections(String uid) {
        return root().child("rejections").child(uid);
    }

    public DatabaseReference brokenMeter(String uid, String token) {
        return root().child("broken_meter").child(uid).child(token);
    }

    public DatabaseReference meterLocation(String uid, String token) {
        return root().child("meter_location").child(uid).child(token).child("ubicacion_medidor");
    }

    public DatabaseReference testResults(String uid) {
        return root().child("test_results").child(uid);
    }

    public DatabaseReference negotiationForms(String uid, String token) {
        return root().child("negotiation_forms").child(uid).child(token);
    }
}