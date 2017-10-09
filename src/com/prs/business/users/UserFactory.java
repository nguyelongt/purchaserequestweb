package com.prs.business.users;


import com.prs.database.UserDB;

public class UserFactory {
    public static UserDAO getUserDAO() {
        UserDAO userDao = new UserDB();
        return userDao;
    }
}
