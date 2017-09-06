package com.zecovery.android.zemedidores.models;

public class Inspection {

    private String date;
    private String address;
    private String comuna;
    private int origin;
    private int id_inspeccion;
    private String inspector;
    private int assigment_type;
    private String name_resident;
    private String rut;
    private String polygon;
    private String orden;
    private String sector;
    private String zone;

    private String medidor;

    private double lng;
    private double lat;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public int getId_inspeccion() {
        return id_inspeccion;
    }

    public void setId_inspeccion(int id_inspeccion) {
        this.id_inspeccion = id_inspeccion;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public int getAssigment_type() {
        return assigment_type;
    }

    public void setAssigment_type(int assigment_type) {
        this.assigment_type = assigment_type;
    }

    public String getName_resident() {
        return name_resident;
    }

    public void setName_resident(String name_resident) {
        this.name_resident = name_resident;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getPolygon() {
        return polygon;
    }

    public void setPolygon(String polygon) {
        this.polygon = polygon;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getMedidor() {
        return medidor;
    }

    public void setMedidor(String medidor) {
        this.medidor = medidor;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
