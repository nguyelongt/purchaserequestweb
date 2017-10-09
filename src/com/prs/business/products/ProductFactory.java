package com.prs.business.products;

import com.prs.database.ProductDB;

public class ProductFactory {
    public static ProductDAO getProductDAO() {
        ProductDAO productDao = new ProductDB();
        return productDao;
    }
}
