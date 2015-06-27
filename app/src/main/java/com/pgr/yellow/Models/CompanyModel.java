package com.pgr.yellow.Models;

import java.io.Serializable;

/**
 * Created by Thiyagu on 3/27/2015.
 */
public class CompanyModel implements Serializable {
    private String companyID;
    private String companyName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String county;
    private String fax;
    private String alphabets;

    public String getAlphabets() {
        return alphabets;
    }
    public void setAlphabets(String _alphabets) {
        this.alphabets = _alphabets;
    }


    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String _companyID) {
        this.companyID = _companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String _companyName) {
        this.companyName = _companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String _address) {
        this.address = _address;
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

    public String getFax() {
        return fax;
    }

    public void setFax(String _fax) {
        this.fax = _fax;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String _county) {
        this.county = _county;
    }
}
