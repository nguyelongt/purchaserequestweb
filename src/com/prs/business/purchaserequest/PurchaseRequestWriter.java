package com.prs.business.purchaserequest;

public interface PurchaseRequestWriter {
    boolean createRequest(PurchaseRequest inRequest);
    boolean updateRequestStatus(int inRequestID, int inStatus);
}
