package com.prs.business.purchaserequest;


import com.prs.database.PurchaseRequestDB;

public class PurchaseRequestFactory {
    public static PurchaseRequestDAO getPurchaseRequestDAO() {
        PurchaseRequestDAO purchaseRequestDao = new PurchaseRequestDB();
        return purchaseRequestDao;
    }
}
