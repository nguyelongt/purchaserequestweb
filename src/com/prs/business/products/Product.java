package com.prs.business.products;

import com.prs.Utilities.StringUtility;
import com.prs.business.vendors.Vendor;

import java.io.Console;
import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String partNumber;
    private String name;
    private Vendor vendor;
    private double price;
    private String unit;
    private String photoPath;
    private int updatedByUser;

    public Product() {

    }


    public Product(int id, String partNumber, String name, Vendor vendor, double price, String unit, String photoPath, int updatedByUser) {
        this.id = id;
        this.partNumber = partNumber;
        this.name = name;
        this.vendor = vendor;
        this.price = price;
        this.unit = unit;
        this.photoPath = photoPath;
        this.updatedByUser = updatedByUser;
    }



    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }


    public int getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(int updatedByUser) {
        this.updatedByUser = updatedByUser;
    }

    public String getPriceFormatted() {
        return StringUtility.getCurrencyFormat(getPrice());
    }
}
