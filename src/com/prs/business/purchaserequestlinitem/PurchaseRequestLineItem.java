package com.prs.business.purchaserequestlinitem;

import java.io.Serializable;

public class PurchaseRequestLineItem implements Serializable {

    private int id;
    private int purchaseRequestID;
    private int productID;
    private int quantity;

    public PurchaseRequestLineItem() {
        id = 0;
        purchaseRequestID = 0;
        productID = 0;
        quantity = 0;
    }

    public PurchaseRequestLineItem(int purchaseRequestID, int productID, int quantity) {
        this.purchaseRequestID = purchaseRequestID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public PurchaseRequestLineItem(int productID, int quantity) {
        this.productID = productID;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPurchaseRequestID() {
        return purchaseRequestID;
    }

    public void setPurchaseRequestID(int purchaseRequestID) {
        this.purchaseRequestID = purchaseRequestID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
