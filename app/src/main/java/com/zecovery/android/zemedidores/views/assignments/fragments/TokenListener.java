package com.zecovery.android.zemedidores.views.assignments.fragments;

/**
 * Created by fbarrios80 on 03-07-17.
 */

public interface TokenListener {

    void tokenToPhotoTest(int token);

    void tokenToExecuteTestPart1(int token);

    void tokenToExecuteTestPart2(int token);

    void tokenToExecuteTestPart3(int token);

    void tokenToNegotiation(int token);
}