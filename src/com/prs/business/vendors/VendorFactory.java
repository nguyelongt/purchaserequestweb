package com.prs.business.vendors;


import com.prs.database.VendorDB;

public class VendorFactory {
    public static VendorDAO getVendorDAO() {
        VendorDAO vendorDao = new VendorDB();
        return vendorDao;
    }
}
