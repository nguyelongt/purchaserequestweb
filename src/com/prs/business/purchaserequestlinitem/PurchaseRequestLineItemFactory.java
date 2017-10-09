package com.prs.business.purchaserequestlinitem;

import com.prs.database.PurchaseRequestLineItemDB;

public class PurchaseRequestLineItemFactory {
    public static PurchaseRequestLineItemDAO getPurchaseRequestLineItemDAO() {
        PurchaseRequestLineItemDAO purchaseRequestLineItemDao = new PurchaseRequestLineItemDB();
        return purchaseRequestLineItemDao;
    }
}
