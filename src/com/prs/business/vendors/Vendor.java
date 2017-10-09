package com.prs.business.vendors;

import java.io.Serializable;

public class Vendor implements Serializable {

    private int id;
    private String code;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String email;
    private boolean preApproved;
    private boolean active;
    private String dateCreated;
    private String dateUpdated;
    private int updatedByUser;

    public Vendor() {

    }


    public Vendor(int id, String code, String name, String address, String city, String state, String zip, String phone, String email, boolean preApproved, boolean active, String dateCreated, String dateUpdated, int updatedByUser) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
        this.preApproved = preApproved;
        this.active = active;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.updatedByUser = updatedByUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPreApproved() {
        return preApproved;
    }

    public void setPreApproved(boolean preApproved) {
        this.preApproved = preApproved;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public int getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(int updatedByUser) {
        this.updatedByUser = updatedByUser;
    }

    public String convertPreApprovedToString() {
        if (this.isPreApproved())
            return "Yes";
        else
            return "No";
    }
}
