package com.prs.business.purchaserequest;


import java.util.ArrayList;

public interface PurchaseRequestReader {
    ArrayList <PurchaseRequest> getRequestsByUserID(int userID);
    ArrayList<PurchaseRequest> getPendingRequests();
    int getLastInsertID();
}
