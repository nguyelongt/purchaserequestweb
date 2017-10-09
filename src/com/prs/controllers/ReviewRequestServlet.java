package com.prs.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.prs.business.products.ProductDAO;
import com.prs.business.products.ProductFactory;
import com.prs.business.purchaserequest.PurchaseRequest;
import com.prs.business.purchaserequest.PurchaseRequestDAO;
import com.prs.business.purchaserequest.PurchaseRequestFactory;
import com.prs.business.purchaserequestlinitem.PurchaseRequestLineItemDAO;
import com.prs.business.purchaserequestlinitem.PurchaseRequestLineItemFactory;
import com.prs.business.users.UserDAO;
import com.prs.business.users.UserFactory;

@WebServlet(
		description = "This servlet handles processing of querying the DB for existing purchase requests and returning information to the user.", 
		urlPatterns = { 
				"/ReviewRequestServlet", 
				"/reviewRequests", 
				"/approveRequests",
				"/updateRequests"
		})
public class ReviewRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserDAO userDao;
	private static PurchaseRequestDAO purchaseRequestDao;
	private static PurchaseRequestLineItemDAO purchaseRequestLineItemDao;
	private static ProductDAO productDao;


    public ReviewRequestServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		userDao = UserFactory.getUserDAO();
		purchaseRequestDao = PurchaseRequestFactory.getPurchaseRequestDAO();
		purchaseRequestLineItemDao = PurchaseRequestLineItemFactory.getPurchaseRequestLineItemDAO();
		productDao = ProductFactory.getProductDAO();
		boolean success = false;
		
		response.getWriter().append("Served at: ").append(request.getContextPath());

		HttpSession session = request.getSession();
		String url = "";
		String action = request.getParameter("action");

		if (action == null) {
			action = "home";
		}

		switch (action) {
			case "home":

				request.setAttribute("message", "This function is not available.  Please select another function.");
				url = "/home.jsp";
				break;
			case "reviewRequests":

				int userID = (Integer) session.getAttribute("userid");
				if (userID > 0) {
					ArrayList<PurchaseRequest> purchaseRequests = purchaseRequestDao.getRequestsByUserID(userID);
					if (purchaseRequests != null) {
						if (purchaseRequests.size() == 0)
							request.setAttribute("message", "You do not have any submitted requests.");
						else {
							request.setAttribute("purchaseRequests", purchaseRequests);
							url = "/displayRequests.jsp";
							success = true;
						}
					} else
						request.setAttribute("message", "This function is not available.  Please select another function.");
				} else
					request.setAttribute("message", "This function is not available.  Please select another function.");
				if (success == false) {
					url = "/home.jsp";
				}
				break;
			case "approveRequests":

				ArrayList<PurchaseRequest> pendingRequests = purchaseRequestDao.getPendingRequests();
				if ((pendingRequests != null) && (pendingRequests.size() > 0)) {
					for (PurchaseRequest pr : pendingRequests) {
						int requestorID = pr.getUserID();
						pr.setUsername(userDao.getUserNameByID(requestorID));
					}
					request.setAttribute("purchaseRequests", pendingRequests);
					url = "/approveRequests.jsp";
					success = true;
				} else if ((pendingRequests != null) && (pendingRequests.size() == 0)) {
					request.setAttribute("message", "There were no requests to approve");
					url = "/home.jsp";
					success = true;
				}
				if (success == false) {
					request.setAttribute("message", "This function is not available.  Please select another function.");
					url = "/home.jsp";
				}
				break;
			case "updateRequests":

				int status;
				String message = "";

				for (String id : request.getParameterValues("requestid")) {
					String approved = request.getParameter("approved" + "_" + id);
					int requestID = Integer.parseInt(id);

					if ((approved != null) && (approved.equals("on"))) {
						status = 2;
					} else {    // The request is considered rejected
						status = 3;
					}
					if (purchaseRequestDao.updateRequestStatus(requestID, status))
						message = "Requests have been updated successfully";
					else
						message = "Requests have not been updated successfully";
				}

				request.setAttribute("message", message);
				url = "/home.jsp";
				break;
		}
		
		getServletContext()
        .getRequestDispatcher(url)
        .forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
