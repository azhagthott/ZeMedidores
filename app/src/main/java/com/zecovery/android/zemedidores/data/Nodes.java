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

    private DatabaseReference root() {
        return FirebaseDatabase.getInstance().getReference();
    }

    public Query assignments() {
        String today = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        return root().child("assignments")
                .child(new CurrentUser().uid()).orderByChild("visibility_date").endAt("true_" + today);
    }

    public DatabaseReference registrations(String sanitizedEmail) {
        return root().child("registrations").child(sanitizedEmail);
    }

    public DatabaseReference shifts(String sanitizedEmail) {
        return root().child("shifts").child(sanitizedEmail);
    }
}