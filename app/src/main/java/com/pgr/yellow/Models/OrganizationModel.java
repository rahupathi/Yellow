package com.pgr.yellow.Models;

import java.io.Serializable;

/**
 * Created by Thiyagu on 3/27/2015.
 */
public class OrganizationModel implements Serializable {
    private String facilityID;
    private String facilityName;
    private String facilityAddress;
    private String city;
    private String zip;
    private String phone;
    private String county;
    private String state;

    public String getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilityAddress() {
        return facilityAddress;
    }

    public void setFacilityAddress(String facilityAddress) {
        this.facilityAddress = facilityAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String _state) {
        this.state = _state;
    }
    public String getCounty() {
        return county;
    }

    public void setCounty(String _county) {
        this.county = _county;
    }
}
