package com.zecovery.android.zemedidores.models;

/**
 * Created by fbarrios80 on 04-09-17.
 */

public class ResultadoInspeccion {

    private TestParte1 testParte1;
    private TestParte2 testParte2;
    private TestParte3 testParte3;
    private Foto fotos;
    private Residente residente;
    private Medidor medidor;
    private String empresaInspeccion;

    public ResultadoInspeccion() {
    }

    public TestParte1 getTestParte1() {
        return testParte1;
    }

    public void setTestParte1(TestParte1 testParte1) {
        this.testParte1 = testParte1;
    }

    public TestParte2 getTestParte2() {
        return testParte2;
    }

    public void setTestParte2(TestParte2 testParte2) {
        this.testParte2 = testParte2;
    }

    public TestParte3 getTestParte3() {
        return testParte3;
    }

    public void setTestParte3(TestParte3 testParte3) {
        this.testParte3 = testParte3;
    }

    public Foto getFotos() {
        return fotos;
    }

    public void setFotos(Foto fotos) {
        this.fotos = fotos;
    }

    public Residente getResidente() {
        return residente;
    }

    public void setResidente(Residente residente) {
        this.residente = residente;
    }

    public Medidor getMedidor() {
        return medidor;
    }

    public void setMedidor(Medidor medidor) {
        this.medidor = medidor;
    }

    public String getEmpresaInspeccion() {
        return empresaInspeccion;
    }

    public void setEmpresaInspeccion(String empresaInspeccion) {
        this.empresaInspeccion = empresaInspeccion;
    }
}
