package com.prs.business.users;


public interface UserReader {
    String getUserNameByID(int userID);
    User getUserByUsernamePassword(String userName, String password);
}
