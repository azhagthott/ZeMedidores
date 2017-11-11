package com.zecovery.android.zemedidores;

import com.zecovery.android.zemedidores.utiles.Util;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by moe on 19-10-17.
 */

public class UtilTest extends Util {

    @Test
    public void validaFormatoRutTest() {
        String[] rut = {
                "9982249-k",
                "9982249-K",
                "9982249k",
                "09982249-k",
                "09982249k",
                "9982249-k"};

        for (String rutTest : rut) {
            assertEquals(true, validaFormatoRut(rutTest));
        }
    }

    /*@Test
    public void validaFormatoCorreoTest() {
        CharSequence email = "f@s";
        assertEquals(true, validaFormatoCorreo(email));
    }*/
}
