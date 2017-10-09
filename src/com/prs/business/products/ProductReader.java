package com.prs.business.products;

import java.util.ArrayList;

public interface ProductReader {
    ArrayList<Product> getAllProduct();
    ArrayList<Product> getProductByVendorID(int vendorID);
}
