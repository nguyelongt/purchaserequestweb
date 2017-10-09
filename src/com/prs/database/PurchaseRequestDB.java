package com.prs.database;
import com.prs.Utilities.DatabaseUtility;
import com.prs.Utilities.StringUtility;
import com.prs.business.purchaserequest.PurchaseRequest;
import com.prs.business.purchaserequest.PurchaseRequestDAO;


import java.sql.*;
import java.util.ArrayList;



public class PurchaseRequestDB implements PurchaseRequestDAO {

    ArrayList<PurchaseRequest> purchaseRequests = null;

    @Override
    public boolean createRequest(PurchaseRequest inRequest) {
        String sqlQuery = "INSERT INTO PurchaseRequest (UserID, Description, Justification, DateNeeded, DeliveryMode, StatusID, Total, SubmittedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlQuery))
        {
            ps.setInt(1, inRequest.getUserID());
            ps.setString(2, inRequest.getDescription());
            ps.setString(3, inRequest.getJustification());
            ps.setDate(4,  StringUtility.convertUtilToSql(inRequest.getDateNeeded()));
            ps.setString(5, inRequest.getDeliveryMode());
            ps.setInt(6, inRequest.getStatusID());
            ps.setDouble(7, inRequest.getTotal());
            ps.setDate(8, StringUtility.convertUtilToSql(inRequest.getSubmittedDate()));
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
    public ArrayList<PurchaseRequest> getRequestsByUserID(int userID) {

        purchaseRequests = new ArrayList<>();
        String sqlQuery = "SELECT ID, Description, Justification, DateNeeded, UserID, DeliveryMode, StatusID, Total, SubmittedDate "
                + "FROM PurchaseRequest "
                + "WHERE UserID = ? "
                + "ORDER BY SubmittedDate, DateNeeded";

        try (Connection connection = DatabaseUtility.getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlQuery))
        {
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                PurchaseRequest pr = getPurchaseRequestFromRow(rs);
                purchaseRequests.add(pr);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return null;
        }
        return purchaseRequests;
    }

    @Override
    public ArrayList<PurchaseRequest> getPendingRequests() {

        purchaseRequests = new ArrayList<>();
        String sqlQuery = "SELECT ID, Description, Justification, DateNeeded, UserID, DeliveryMode, StatusID, Total, SubmittedDate "
                + "FROM PurchaseRequest "
                + "WHERE StatusID = 1 "
                + "ORDER BY ID";

        try (Connection connection = DatabaseUtility.getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlQuery))
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                PurchaseRequest pr = getPurchaseRequestFromRow(rs);
                purchaseRequests.add(pr);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return null;
        }
        return purchaseRequests;
    }

    @Override
    public int getLastInsertID() {

        String lastInsertIDSql = "SELECT LAST_INSERT_ID()";

        try (Connection connection = DatabaseUtility.getConnection();
                PreparedStatement ps = connection.prepareStatement(lastInsertIDSql))
        {
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                return rs.getInt(1);
            }
            else
            {
                rs.close();
                return -1;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean updateRequestStatus(int inRequestID, int inStatus) {

        String updateSql = "UPDATE PurchaseRequest SET StatusID = ? WHERE ID = ?";

        try (Connection connection = DatabaseUtility.getConnection();
                PreparedStatement ps = connection.prepareStatement(updateSql))
        {
            ps.setInt(1, inStatus);
            ps.setInt(2, inRequestID);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("Oops:" + e);
            return false;
        }
        return true;
    }

    private static PurchaseRequest getPurchaseRequestFromRow(ResultSet rs) throws SQLException
    {
        PurchaseRequest purchaseRequest = new PurchaseRequest();

        purchaseRequest.setId(rs.getInt("ID"));
        purchaseRequest.setDescription(rs.getString("Description"));
        purchaseRequest.setJustification(rs.getString("Justification"));
        purchaseRequest.setDateNeeded(rs.getDate("DateNeeded"));
        purchaseRequest.setUserID(rs.getInt("UserID"));
        purchaseRequest.setDeliveryMode(rs.getString("DeliveryMode"));
        purchaseRequest.setStatusID(rs.getInt("StatusID"));
        purchaseRequest.setTotal(rs.getDouble("Total"));
        purchaseRequest.setFormattedTotal(StringUtility.getFormattedDouble(purchaseRequest.getTotal()));
        purchaseRequest.setSubmittedDate(rs.getDate("SubmittedDate"));
        return purchaseRequest;
    }
}