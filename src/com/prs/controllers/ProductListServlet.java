package com.prs.controllers;


import com.prs.business.products.Product;
import com.prs.business.products.ProductDAO;
import com.prs.business.products.ProductFactory;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/ProductListServlet", "/listProducts" })
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static ProductDAO productDao;

    public ProductListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	// Set empty string to use within method
		String url = "";

		productDao = ProductFactory.getProductDAO();
		String action = request.getParameter("action");

		if (action == null) {
			action = "listProducts";
		}
		if (action.equals("listProducts")) {
			ArrayList<Product> products = productDao.getAllProduct();
			
			request.setAttribute("products", products);
	        url = "/listProducts.jsp";
		}

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
