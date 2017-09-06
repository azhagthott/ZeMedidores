package com.zecovery.android.zemedidores.models;

/**
 * Created by moe on 04-09-17.
 */

public class Foto {

    private String rechazoInspeccion;
    private String fallaMedidor;
    private String lecturaMedidor;
    private String numeroMedidor;
    private String panoramicaMedidor;
    private String numeroPropiedad;
    private String fachadaPropiedad;
    private String intervencionRed;
    private String bypass;
    private String otro;

    public Foto() {

    }

    public String getRechazoInspeccion() {
        return rechazoInspeccion;
    }

    public void setRechazoInspeccion(String rechazoInspeccion) {
        this.rechazoInspeccion = rechazoInspeccion;
    }

    public String getFallaMedidor() {
        return fallaMedidor;
    }

    public void setFallaMedidor(String fallaMedidor) {
        this.fallaMedidor = fallaMedidor;
    }

    public String getLecturaMedidor() {
        return lecturaMedidor;
    }

    public void setLecturaMedidor(String lecturaMedidor) {
        this.lecturaMedidor = lecturaMedidor;
    }

    public String getNumeroMedidor() {
        return numeroMedidor;
    }

    public void setNumeroMedidor(String numeroMedidor) {
        this.numeroMedidor = numeroMedidor;
    }

    public String getPanoramicaMedidor() {
        return panoramicaMedidor;
    }

    public void setPanoramicaMedidor(String panoramicaMedidor) {
        this.panoramicaMedidor = panoramicaMedidor;
    }

    public String getNumeroPropiedad() {
        return numeroPropiedad;
    }

    public void setNumeroPropiedad(String numeroPropiedad) {
        this.numeroPropiedad = numeroPropiedad;
    }

    public String getFachadaPropiedad() {
        return fachadaPropiedad;
    }

    public void setFachadaPropiedad(String fachadaPropiedad) {
        this.fachadaPropiedad = fachadaPropiedad;
    }

    public String getIntervencionRed() {
        return intervencionRed;
    }

    public void setIntervencionRed(String intervencionRed) {
        this.intervencionRed = intervencionRed;
    }

    public String getBypass() {
        return bypass;
    }

    public void setBypass(String bypass) {
        this.bypass = bypass;
    }

    public String getOtro() {
        return otro;
    }

    public void setOtro(String otro) {
        this.otro = otro;
    }
}
