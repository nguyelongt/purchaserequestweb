package com.prs.database;

import com.prs.Utilities.DatabaseUtility;
import com.prs.business.products.Product;
import com.prs.business.products.ProductDAO;
import com.prs.business.vendors.Vendor;
import com.prs.business.vendors.VendorDAO;
import com.prs.business.vendors.VendorFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDB implements ProductDAO {

    private static VendorDAO vendorDao;

    public ProductDB() {
        try {
            DatabaseUtility.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        vendorDao = VendorFactory.getVendorDAO();
        vendorDao.vendorIDMap();

    }

    @SuppressWarnings("Duplicates")
    @Override
    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> products = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Product";
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int vID = rs.getInt(2);
                Vendor vendorID = vendorDao.vendorIDMap().get(vID);
                String partNumber = rs.getString(3);
                String name = rs.getString(4);
                double price = rs.getDouble(5);
                String unit = rs.getString(6);
                String photoPath = rs.getString(7);
                int updatedByUser = rs.getInt(11);
                Product product = new Product(id, partNumber, name, vendorID, price, unit, photoPath, updatedByUser);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public ArrayList<Product> getProductByVendorID(int vendorByID) {
        ArrayList<Product> products = new ArrayList<>();
        String sqlQuery = ("SELECT * FROM Product WHERE vendorID = " + vendorByID);
        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int vID = rs.getInt(2);
                Vendor vendorID = vendorDao.vendorIDMap().get(vID);
                String partNumber = rs.getString(3);
                String name = rs.getString(4);
                double price = rs.getDouble(5);
                String unit = rs.getString(6);
                String photoPath = rs.getString(7);
                int updatedByUser = rs.getInt(8);
                Product product = new Product(id, partNumber, name, vendorID, price, unit, photoPath, updatedByUser);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
