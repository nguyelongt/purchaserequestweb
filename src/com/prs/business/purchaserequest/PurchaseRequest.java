package com.prs.business.purchaserequest;

import java.io.Serializable;
import java.util.Date;

public class PurchaseRequest implements Serializable {

    private int id;
    private int userID;
    private String username;
    private String description;
    private String justification;
    private Date dateNeeded;
    private String deliveryMode;
    private int statusID;
    double total;
    private String formattedTotal;
    private Date submittedDate;
    private String reasonForRejection;
    private int updatedByUser;


    public PurchaseRequest() {

    }

    public PurchaseRequest(int userID, String username, String description, String justification, Date dateNeeded, String deliveryMode, int statusID, double total, Date submittedDate) {
        this.userID = userID;
        this.username = username;
        this.description = description;
        this.justification = justification;
        this.dateNeeded = dateNeeded;
        this.deliveryMode = deliveryMode;
        this.statusID = statusID;
        this.total = total;

        this.submittedDate = submittedDate;
    }

    public PurchaseRequest(int userID, String description, String justification, Date dateNeeded, String deliveryMode, int statusID, double total, Date submittedDate) {
        this.userID = userID;
        this.username = "";
        this.description = description;
        this.justification = justification;
        this.dateNeeded = dateNeeded;
        this.deliveryMode = deliveryMode;
        this.statusID = statusID;
        this.total = total;
        this.submittedDate = submittedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public Date getDateNeeded() {
        return dateNeeded;
    }

    public void setDateNeeded(Date dateNeeded) {
        this.dateNeeded = dateNeeded;
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }

    public String getReasonForRejection() {
        return reasonForRejection;
    }

    public void setReasonForRejection(String reasonForRejection) {
        this.reasonForRejection = reasonForRejection;
    }

    public int getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(int updatedByUser) {
        this.updatedByUser = updatedByUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFormattedTotal() {
        return formattedTotal;
    }

    public void setFormattedTotal(String formattedTotal) {
        this.formattedTotal = formattedTotal;
    }

    public String convertStatusIDToString() {
        String statusDescription = "";
        if (getStatusID() == 1) {
            statusDescription = "New";
        } else if (getStatusID() == 2) {
            statusDescription = "Approved";
        } else
            statusDescription = "Rejected";
        return statusDescription;
    }
}
