package com.zecovery.android.zemedidores.background;

/**
 * Created by moe on 28-08-17.
 */

public class InspeccionParams {

    private int key;
    private String inspectorEmail;

    public InspeccionParams(int key, String inspectorEmail) {
        this.key = key;
        this.inspectorEmail = inspectorEmail;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getInspectorEmail() {
        return inspectorEmail;
    }

    public void setInspectorEmail(String inspectorEmail) {
        this.inspectorEmail = inspectorEmail;
    }
}
