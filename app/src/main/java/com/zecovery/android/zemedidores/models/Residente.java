package com.zecovery.android.zemedidores.models;

/**
 * Created by moe on 02-09-17.
 */

public class Residente {

    private String nombreResidente;
    private String rutResidente;
    private String telefonoResidente;
    private String emailResidente;
    private String fechaResidente;
    private String riesgoSocial;
    private String direccionResidente;

    public Residente() {
    }

    public String getNombreResidente() {
        return nombreResidente;
    }

    public void setNombreResidente(String nombreResidente) {
        this.nombreResidente = nombreResidente;
    }

    public String getRutResidente() {
        return rutResidente;
    }

    public void setRutResidente(String rutResidente) {
        this.rutResidente = rutResidente;
    }

    public String getTelefonoResidente() {
        return telefonoResidente;
    }

    public void setTelefonoResidente(String telefonoResidente) {
        this.telefonoResidente = telefonoResidente;
    }

    public String getEmailResidente() {
        return emailResidente;
    }

    public void setEmailResidente(String emailResidente) {
        this.emailResidente = emailResidente;
    }

    public String getFechaResidente() {
        return fechaResidente;
    }

    public void setFechaResidente(String fechaResidente) {
        this.fechaResidente = fechaResidente;
    }

    public String getRiesgoSocial() {
        return riesgoSocial;
    }

    public void setRiesgoSocial(String riesgoSocial) {
        this.riesgoSocial = riesgoSocial;
    }

    public String getDireccionResidente() {
        return direccionResidente;
    }

    public void setDireccionResidente(String direccionResidente) {
        this.direccionResidente = direccionResidente;
    }
}
