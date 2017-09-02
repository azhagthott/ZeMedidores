package com.zecovery.android.zemedidores.models;

/**
 * Created by moe on 02-09-17.
 */

public class Resident {

    private String nombre;
    private String rut;
    private String telefono;
    private String email;
    private String fecha;

    public Resident() {
    }

    public Resident(String nombreResidente, String rut, String telefono, String fecha) {
        this.nombre = nombreResidente;
        this.rut = rut;
        this.telefono = telefono;
        this.fecha = fecha;
    }

    public Resident(String nombreResidente, String rut, String telefono, String email, String fecha) {
        this.nombre = nombreResidente;
        this.rut = rut;
        this.telefono = telefono;
        this.email = email;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
