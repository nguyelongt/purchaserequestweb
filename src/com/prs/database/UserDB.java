package com.prs.database;

import com.prs.Utilities.DatabaseUtility;
import com.prs.business.users.User;
import com.prs.business.users.UserDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UserDB implements UserDAO {


    @Override
    public String getUserNameByID(int userID) {
        String sqlQuery = "SELECT UserName "
                + "From User "
                + "WHERE ID = ?";

        try (Connection connection = DatabaseUtility.getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlQuery))
        {
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                return rs.getString("UserName");
            }
            else
            {
                rs.close();
                return "";
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return "";
        }
    }

    @Override
    public User getUserByUsernamePassword(String username, String password) {
        String sqlQuery = "SELECT * FROM PRS.User WHERE UserName = ? AND Password = ?";
        try (Connection connection = DatabaseUtility.getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getUserFromRow(rs);
            } else {
                rs.close();
                return null;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

        private static User getUserFromRow(ResultSet rs) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("ID"));
            user.setUserName(rs.getString("UserName"));
            user.setPassword(rs.getString("Password"));
            user.setFirstName(rs.getString("FirstName"));
            user.setLastName(rs.getString("LastName"));
            user.setPhone(rs.getString("Phone"));
            user.setEmail(rs.getString("Email"));
            user.setAdmin(rs.getBoolean("IsAdmin"));
            return user;
        }
    }