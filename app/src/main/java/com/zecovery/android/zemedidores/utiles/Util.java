package com.zecovery.android.zemedidores.utiles;

import android.util.Log;
import android.util.Patterns;

import com.google.firebase.crash.FirebaseCrash;

/**
 * Created by moe on 16-10-17.
 */

public class Util {

    public static boolean validaFormatoCorreo(CharSequence correo) {
        try {
            return Patterns.EMAIL_ADDRESS.matcher(correo).matches();
        } catch (Exception e) {
            Log.d("TAG", "validaFormatoCorreo: " + e);
        }
        return false;
    }

    public static boolean validaFormatoRut(String rut) {
        boolean validacion = false;

        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
            char dv = rut.charAt(rut.length() - 1);
            int m = 0;
            int s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }
        } catch (java.lang.NumberFormatException num) {
            FirebaseCrash.log("NumberFormatException: " + num);
        } catch (Exception e) {
            FirebaseCrash.log("Exception: " + e);
        }
        return validacion;
    }

    public static boolean validaCampo(boolean requiereFoto) {
        return false;
    }
}
