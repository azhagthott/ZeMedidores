package com.zecovery.android.zemedidores.models;

/**
 * Created by fbarrios80 on 03-09-17.
 */

public class Medidor {

    private String ubicacion;
    private String estado;
    private String descripcionFalla;

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
}
