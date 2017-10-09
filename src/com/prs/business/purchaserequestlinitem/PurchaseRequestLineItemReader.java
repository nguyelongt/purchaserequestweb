package com.prs.business.purchaserequestlinitem;

import java.util.ArrayList;

public interface PurchaseRequestLineItemReader {

    ArrayList<PurchaseRequestLineItem> getLineItemsByRequestID(int inRequestID);
}
