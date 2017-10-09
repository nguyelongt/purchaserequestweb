package com.prs.database;

import com.prs.Utilities.DatabaseUtility;
import com.prs.business.vendors.Vendor;
import com.prs.business.vendors.VendorDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class VendorDB implements VendorDAO {

    private ArrayList<Vendor> vendors = null;

    @Override
    public HashMap<Integer, Vendor> vendorIDMap() {
        HashMap <Integer, Vendor> vendorIDLookup = new HashMap<>();
        String sqlQuery = "SELECT * FROM Vendor";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String code = rs.getString(2);
                String name = rs.getString(3);
                String address = rs.getString(4);
                String city = rs.getString(5);
                String state = rs.getString(6);
                String zip = rs.getString(7);
                String phone = rs.getString(8);
                String email = rs.getString(9);
                boolean isPreApproved = rs.getBoolean(10);
                boolean active = rs.getBoolean(11);
                String dateCreated = rs.getString(12);
                String dateUpdated = rs.getString(13);
                int updatedByUser = rs.getInt(14);
                Vendor v = new Vendor(id, code, name, address, city, state, zip, phone, email, isPreApproved, active, dateCreated, dateUpdated, updatedByUser);
                vendorIDLookup.put(id, v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendorIDLookup;
    }

    @Override
    public ArrayList<Vendor> getAllVendors() {
        vendors = new ArrayList<>();
        String sql = "SELECT ID, Code, Name, Address, City, State, Zip, Phone, EMail, IsPreApproved "
                + "FROM Vendor "
                + "ORDER BY ID";

        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
            {
                Vendor v = getVendorFromRow(rs);
                vendors.add(v);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return null;
        }
        return vendors;
    }


    private static Vendor getVendorFromRow(ResultSet rs) throws SQLException {
        Vendor vendor = new Vendor();

        vendor.setId(rs.getInt("ID"));
        vendor.setCode(rs.getString("Code"));
        vendor.setName(rs.getString("Name"));
        vendor.setAddress(rs.getString("Address"));
        vendor.setCity(rs.getString("City"));
        vendor.setState(rs.getString("State"));
        vendor.setZip(rs.getString("Zip"));
        vendor.setPhone(rs.getString("Phone"));
        vendor.setEmail(rs.getString("EMail"));
        vendor.setPreApproved(rs.getBoolean("IsPreApproved"));
        return vendor;
    }
}
