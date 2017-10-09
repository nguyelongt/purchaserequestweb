package com.prs.controllers;

import com.prs.Utilities.StringUtility;
import com.prs.business.products.Product;
import com.prs.business.products.ProductDAO;
import com.prs.business.products.ProductFactory;
import com.prs.business.purchaserequest.PurchaseRequest;
import com.prs.business.purchaserequest.PurchaseRequestConstants;
import com.prs.business.purchaserequest.PurchaseRequestDAO;
import com.prs.business.purchaserequest.PurchaseRequestFactory;
import com.prs.business.purchaserequestlinitem.PurchaseRequestLineItem;
import com.prs.business.purchaserequestlinitem.PurchaseRequestLineItemDAO;
import com.prs.business.purchaserequestlinitem.PurchaseRequestLineItemFactory;
import com.prs.business.vendors.Vendor;
import com.prs.business.vendors.VendorDAO;
import com.prs.business.vendors.VendorFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;


@WebServlet({ "/PurchaseRequestServlet", "/logout", "/home", "/createRequest", "/newRequest", "/submitRequest", "/showVendors", "/showProducts" })
public class ProductRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static VendorDAO vendorDao;
    private static ProductDAO productDao;
    private static PurchaseRequestDAO purchaseRequestDao;
    private static PurchaseRequestLineItemDAO purchaseLineItemDao;

    public ProductRequestServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String url = "";
        String action = request.getParameter("action");

        if (action == null) {
            action = "logout";
        }
        if (action.equals("logout") || action.equals("home")) {
            // Remove Attribute if user log out or go to home page
            // so it doesn't add together if user go back to order entry
            session.removeAttribute("newRequest");
            session.removeAttribute("lineItems");
            session.removeAttribute("purchaseRequestProducts");
            if (action.equals("logout")) {
                // Remove cache for username + userid
                session.removeAttribute("userid");
                session.removeAttribute("username");
                // Go to login page
                url = "/loginPage.jsp";
            } else {
                url = "/home.jsp";
            }
        }


        switch (action) {
            case "createRequest":
                url = "/createRequest.jsp";
                break;
            case "newRequest":
            case "showVendors":
                // Get data from requests
                int uID = (Integer) request.getSession().getAttribute("userid");
                String userName = (String) request.getSession().getAttribute("username");
                String description = request.getParameter("description");
                String justification = request.getParameter("justification");
                String neededDate = request.getParameter("dateNeeded");
                Date dateNeeded = StringUtility.convertStringToSQLDate(neededDate);
                String deliveryMode = request.getParameter("deliveryMode");
                int statusID = 1;
                Date submittedDate = StringUtility.convertTodaysDateToSQLDate();

                // Insert data from request to PurchaseRequest Object
                PurchaseRequest newRequest = new PurchaseRequest(uID, userName, description, justification, dateNeeded, deliveryMode, statusID, 0.0, submittedDate);
                // Place the newly created Object in session
                session.setAttribute("newRequest", newRequest);

                // Show a list of Vendors to User
                vendorDao = VendorFactory.getVendorDAO();
                ArrayList<Vendor> vendors = vendorDao.getAllVendors();
                // Also, stores it in a request
                request.setAttribute("vendors", vendors);

                // Send Vendors Request to showVendors.jsp page
                url = "/showVendors.jsp";

                break;
            case "showProducts": {
                productDao = ProductFactory.getProductDAO();
                // Store Vendor ID of the selected Vendor
                String vendorID = request.getParameter("vendorID");
                int id = Integer.parseInt(vendorID);

                // Get Purchase Request that was stored in session
                PurchaseRequest purchaseRequest = (PurchaseRequest) session.getAttribute("newRequest");

                // Get Products based on the Vendor ID
                ArrayList<Product> products = productDao.getProductByVendorID(id);
                // Store into session
                session.setAttribute("purchaseRequestProducts", products);

                // Stores it into Purchase Request
                session.setAttribute("newRequest", purchaseRequest);

                // Send request to showProducts.jsp page
                url = "/showProducts.jsp";

                break;
            }
            case "submitRequest": {

                ArrayList<PurchaseRequestLineItem> purchaseRequestLineItems = new ArrayList<>();
                ArrayList<Integer> quantities = new ArrayList<>();
                String result = "";
                double total = 0.0;
                int quantity = 0;
                int productIndex = 0;

                // Get Products from session
                ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("purchaseRequestProducts");

                // Get Purchase Request from session
                PurchaseRequest purchaseRequest = (PurchaseRequest) session.getAttribute("newRequest");

                // Get Parameters from request
                for (String id : request.getParameterValues("productid")) {
                    String q = request.getParameter("quantity" + "_" + id);
                    if (q.length() > 0)
                        quantity = Integer.parseInt(q);
                    else
                        quantity = 0;
                    quantities.add(quantity);
                }
                for (Product product : products) {
                    quantity = quantities.get(productIndex);

                    if (quantity > 0) {
                        total += quantity * product.getPrice();
                        PurchaseRequestLineItem lineitem = new PurchaseRequestLineItem(product.getId(), quantity);
                        purchaseRequestLineItems.add(lineitem);
                    }
                    productIndex++;
                }

                // Set Total to show later
                purchaseRequest.setTotal(total);
                purchaseRequest.setFormattedTotal(StringUtility.getFormattedDouble(purchaseRequest.getTotal()));

                // Set status of the order. If under $50, it'll auto approve
                if (purchaseRequest.getStatusID() == 1 && (total < PurchaseRequestConstants.AUTO_APPROVE_LIMIT)) {
                    purchaseRequest.setStatusID(2);
                }

                purchaseRequestDao = PurchaseRequestFactory.getPurchaseRequestDAO();
                if (purchaseRequestDao.createRequest(purchaseRequest)) {
                    int requestID = purchaseRequestDao.getLastInsertID();
                    purchaseLineItemDao = PurchaseRequestLineItemFactory.getPurchaseRequestLineItemDAO();
                    for (PurchaseRequestLineItem lineItem : purchaseRequestLineItems) {
                        lineItem.setPurchaseRequestID(requestID);
                        purchaseLineItemDao.addLineItem(lineItem);
                    }
                    result = "Your request was added successfully";
                } else {
                    result = "Your request was not added successfully";
                }

                // Store purchase request into session
                session.setAttribute("newRequest", purchaseRequest);

                // Add line item to session
                session.setAttribute("lineItems", purchaseRequestLineItems);

                // Store message in session
                request.setAttribute("result", result);

                // Forward request to showResults.jsp page (Receipt)
                url = "/showResults.jsp";
                break;
            }
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
