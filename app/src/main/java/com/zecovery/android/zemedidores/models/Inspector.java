package com.zecovery.android.zemedidores.models;

public class Inspector {

    private String inspector;
    private Inspection[] inspections;

    public Inspector() {
    }

    public Inspector(String inspector, Inspection[] inspections) {
        this.inspector = inspector;
        this.inspections = inspections;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public Inspection[] getInspections() {
        return inspections;
    }

    public void setInspections(Inspection[] inspections) {
        this.inspections = inspections;
    }
}
