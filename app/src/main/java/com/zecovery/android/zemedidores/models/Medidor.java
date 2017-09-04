package com.zecovery.android.zemedidores.models;

/**
 * Created by fbarrios80 on 03-09-17.
 */

public class Medidor {

    private String ubicacion;
    private String estado;
    private String descripcionFalla;
    private String fotoFalla;
    private String fotoLectura;
    private String fotoNumeroMedidor;
    private String fotoPanoramica;
    private String fotoNumeroPropiedad;
    private String fotoFachada;

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

    public String getFotoFalla() {
        return fotoFalla;
    }

    public void setFotoFalla(String fotoFalla) {
        this.fotoFalla = fotoFalla;
    }

    public String getFotoLectura() {
        return fotoLectura;
    }

    public void setFotoLectura(String fotoLectura) {
        this.fotoLectura = fotoLectura;
    }

    public String getFotoNumeroMedidor() {
        return fotoNumeroMedidor;
    }

    public void setFotoNumeroMedidor(String fotoNumeroMedidor) {
        this.fotoNumeroMedidor = fotoNumeroMedidor;
    }

    public String getFotoPanoramica() {
        return fotoPanoramica;
    }

    public void setFotoPanoramica(String fotoPanoramica) {
        this.fotoPanoramica = fotoPanoramica;
    }

    public String getFotoNumeroPropiedad() {
        return fotoNumeroPropiedad;
    }

    public void setFotoNumeroPropiedad(String fotoNumeroPropiedad) {
        this.fotoNumeroPropiedad = fotoNumeroPropiedad;
    }

    public String getFotoFachada() {
        return fotoFachada;
    }

    public void setFotoFachada(String fotoFachada) {
        this.fotoFachada = fotoFachada;
    }
}
