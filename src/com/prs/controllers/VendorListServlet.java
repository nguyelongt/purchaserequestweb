package com.prs.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prs.business.vendors.Vendor;
import com.prs.business.vendors.VendorDAO;
import com.prs.business.vendors.VendorFactory;

@WebServlet({ "/VendorListServlet", "/listVendors" })
public class VendorListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static VendorDAO vendorDao;

    public VendorListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String url = "";
		String action = request.getParameter("action");

		if (action == null) {
			action = "listVendors";
		}
		else if (action.equals("listVendors")) {

			vendorDao = VendorFactory.getVendorDAO();
			ArrayList<Vendor> vendors = vendorDao.getAllVendors();
			request.setAttribute("vendors", vendors);

	        url = "/listVendors.jsp";

		}
 
		getServletContext()
        .getRequestDispatcher(url)
        .forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
