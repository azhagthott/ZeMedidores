package com.zecovery.android.zemedidores.models;

public class Inspection {
    private int inspectionId;
    private String orden;
    private String medidor;
    private int origin;
    private int assigment_type;
    private String date;
    private String name_resident;
    private String comuna;
    private String address;
    private String rut;
    private String polygon;
    private String zone;
    private String sector;
    private double lat;
    private double lng;

    public Inspection() {
    }

    public Inspection(int inspectionId, String orden, String medidor, int origin, int assigment_type, String date, String name_resident, String comuna, String address, String rut, String polygon, String zone, String sector, double lat, double lng) {
        this.inspectionId = inspectionId;
        this.orden = orden;
        this.medidor = medidor;
        this.origin = origin;
        this.assigment_type = assigment_type;
        this.date = date;
        this.name_resident = name_resident;
        this.comuna = comuna;
        this.address = address;
        this.rut = rut;
        this.polygon = polygon;
        this.zone = zone;
        this.sector = sector;
        this.lat = lat;
        this.lng = lng;
    }

    public int getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(int inspectionId) {
        this.inspectionId = inspectionId;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getMedidor() {
        return medidor;
    }

    public void setMedidor(String medidor) {
        this.medidor = medidor;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public int getAssigment_type() {
        return assigment_type;
    }

    public void setAssigment_type(int assigment_type) {
        this.assigment_type = assigment_type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName_resident() {
        return name_resident;
    }

    public void setName_resident(String name_resident) {
        this.name_resident = name_resident;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
