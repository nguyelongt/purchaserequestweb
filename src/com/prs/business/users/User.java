package com.prs.business.users;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private boolean isReviewer;
    private boolean isAdmin;
    private int updatedByUser;

    public User() {

    }

    public User(int id, String userName, String password, String firstName, String lastName, String phone, String email, boolean isReviewer, boolean isAdmin, boolean active, String dateCreated, String dateUpdated, int updatedByUser) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.isReviewer = isReviewer;
        this.isAdmin = isAdmin;
        this.updatedByUser = updatedByUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isReviewer() {
        return isReviewer;
    }

    public void setReviewer(boolean reviewer) {
        isReviewer = reviewer;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(int updatedByUser) {
        this.updatedByUser = updatedByUser;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
