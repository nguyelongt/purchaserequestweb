package com.prs.business.vendors;


import java.util.ArrayList;
import java.util.HashMap;

public interface VendorReader {

    HashMap<Integer, Vendor> vendorIDMap();
    ArrayList<Vendor> getAllVendors();
}
