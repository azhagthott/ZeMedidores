package com.zecovery.android.zemedidores.network;

/**
 * Created by fbarrios80 on 10-09-17.
 */

public interface EnviaInspeccionCallback {

    void enviaInspeccionOk(int code);

    void enviarInspeccionError(int code);
}
