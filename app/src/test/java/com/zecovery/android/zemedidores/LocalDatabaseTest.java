package com.zecovery.android.zemedidores;

import android.content.Context;

import com.zecovery.android.zemedidores.data.LocalDatabase;

import org.junit.Test;

/**
 * Created by moe on 28-08-17.
 */

public class LocalDatabaseTest extends LocalDatabase {

private LocalDatabase db;

    public LocalDatabaseTest(Context context) {
        super(context);
    }

    @Test
    public void getCurrentLocationTest() {
        //assertEquals(LatLng, db.getCurrentDbLocation());
    }

    @Test
    public void guardaUbicacionTest(double lat, double lng) {
        //assertEquals(guardaUbicacionActual(lat, lng));
    }
}
