package com.zecovery.android.zemedidores.models;

/**
 * Created by fbarrios80 on 03-09-17.
 */

public class Medidor {

    private String ubicacion;
    private String estado;
    private String descripcionFalla;
    private String numeroMedidor;
    private String diametroMedidor;
    private String lecturaMedidor;

    public Medidor() {
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcionFalla() {
        return descripcionFalla;
    }

    public void setDescripcionFalla(String descripcionFalla) {
        this.descripcionFalla = descripcionFalla;
    }

    public String getNumeroMedidor() {
        return numeroMedidor;
    }

    public void setNumeroMedidor(String numeroMedidor) {
        this.numeroMedidor = numeroMedidor;
    }

    public String getDiametroMedidor() {
        return diametroMedidor;
    }

    public void setDiametroMedidor(String diametroMedidor) {
        this.diametroMedidor = diametroMedidor;
    }

    public String getLecturaMedidor() {
        return lecturaMedidor;
    }

    public void setLecturaMedidor(String lecturaMedidor) {
        this.lecturaMedidor = lecturaMedidor;
    }
}
