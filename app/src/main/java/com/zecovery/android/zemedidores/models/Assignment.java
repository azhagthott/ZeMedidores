package com.zecovery.android.zemedidores.models;

/**
 * Created by fbarrios80 on 10-05-17.
 */

public class Assignment {

    private String name, description, origin, address, date, uid, visibility_date, email_resident, measurer_locaction, measurer_status,
            assignment_type, push_key, polygon, zone, sector;
    private boolean visibility;
    private double lng, lat;

    public Assignment() {
    }

    public String getMeasurer_locaction() {
        return measurer_locaction;
    }

    public void setMeasurer_locaction(String measurer_locaction) {
        this.measurer_locaction = measurer_locaction;
    }

    public String getMeasurer_status() {
        return measurer_status;
    }

    public void setMeasurer_status(String measurer_status) {
        this.measurer_status = measurer_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public String getVisibility_date() {
        return visibility_date;
    }

    public void setVisibility_date(String visibility_date) {
        this.visibility_date = visibility_date;
    }

    public String getAssignment_type() {
        return assignment_type;
    }

    public void setAssignment_type(String assignment_type) {
        this.assignment_type = assignment_type;
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

    public String getPush_key() {
        return push_key;
    }

    public void setPush_key(String push_key) {
        this.push_key = push_key;
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

    public String getEmail_resident() {
        return email_resident;
    }

    public void setEmail_resident(String email_resident) {
        this.email_resident = email_resident;
    }
}
