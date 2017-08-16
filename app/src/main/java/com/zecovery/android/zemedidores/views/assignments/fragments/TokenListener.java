package com.zecovery.android.zemedidores.views.assignments.fragments;

/**
 * Created by fbarrios80 on 03-07-17.
 */

public interface TokenListener {

    void tokenToPhotoTest(String token);

    void tokenToExecuteTestPart1(String token);

    void tokenToExecuteTestPart2(String token);

    void tokenToExecuteTestPart3(String token);

    void tokenToNegotiation(String token);
}