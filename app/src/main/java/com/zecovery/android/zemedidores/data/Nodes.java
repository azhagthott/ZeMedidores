package com.zecovery.android.zemedidores.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    public DatabaseReference users() {
        return root().child("users");
    }

    public DatabaseReference assignments() {
        String uid = new CurrentUser().uid();
        return root().child("assignments").child(uid);
    }

    public DatabaseReference forms() {
        return root().child("forms");
    }

    public DatabaseReference registrations(String sanitizedEmail) {
        return root().child("registrations").child(sanitizedEmail);
    }

    public DatabaseReference shifts(String sanitizedEmail) {
        return root().child("shifts").child(sanitizedEmail);
    }

}
