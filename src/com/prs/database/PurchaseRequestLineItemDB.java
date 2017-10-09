package com.prs.database;


import com.prs.Utilities.DatabaseUtility;
import com.prs.business.purchaserequestlinitem.PurchaseRequestLineItem;
import com.prs.business.purchaserequestlinitem.PurchaseRequestLineItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PurchaseRequestLineItemDB implements PurchaseRequestLineItemDAO {

    ArrayList<PurchaseRequestLineItem> lineItems = null;

    @Override
    public boolean addLineItem(PurchaseRequestLineItem inLineItem) {
        String sqlQuery = "INSERT INTO PurchaseRequestLineItem (PurchaseRequestID, ProductID, Quantity) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery))
        {
            ps.setInt(1, inLineItem.getPurchaseRequestID());
            ps.setInt(2, inLineItem.getProductID());
            ps.setInt(3, inLineItem.getQuantity());
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<PurchaseRequestLineItem> getLineItemsByRequestID(int inRequestID) {
        lineItems = new ArrayList<>();
        String sqlQuery = "SELECT ID, RequestID, ProductID, Quantity "
                + "FROM PurchaseRequestItem "
                + "WHERE RequestID = ? "
                + "ORDER BY ID";

        try (Connection connection = DatabaseUtility.getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlQuery))
        {
            ps.setInt(1, inRequestID);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                PurchaseRequestLineItem li = getLineItemFromRow(rs);
                lineItems.add(li);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return null;
        }
        return lineItems;
    }

    private static PurchaseRequestLineItem getLineItemFromRow(ResultSet rs) throws SQLException {

        PurchaseRequestLineItem lineItem = new PurchaseRequestLineItem();

        lineItem.setId(rs.getInt("ID"));
        lineItem.setPurchaseRequestID(rs.getInt("PurchaseRequestID"));
        lineItem.setProductID(rs.getInt("ProductID"));
        lineItem.setQuantity(rs.getInt("Quantity"));

        return lineItem;
    }
}
