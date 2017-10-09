<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.prs.business.products.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PRS - Product List</title>
<link rel="stylesheet" href="css/main.css">
<script type="text/javascript" src="js/main.js"></script>
</head>
<body>
	<h1>List of Products</h1>
	<form name="submitRequest" action="submitRequest" method="post">
		<table>
			<tr>
				<th>Quantity</th>
				<th>Name</th>
				<th>Part Number</th>
				<th>Price</th>
				<th>Unit</th>
				<th>Image</th>
			</tr>
			<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			<c:forEach var="product" items="${purchaseRequestProducts}" >
			<tr>
				<td>
					<input type=hidden name="productid" value="${product.id}" />
					<input type=text name="quantity_${product.id}" value="0" />
				</td>
				<td>${product.name}</td>
				<td>${product.partNumber}</td>
				<td>${product.getPriceFormatted()}</td>
				<td>${product.unit}</td>
				<td><img src='${product.photoPath}' width="100" height="100"></td>
			</tr>
			</c:forEach>
		</table><br>
		<input type="hidden" name="action" value="submitRequest">
		<button type="submit">Submit Request</button>
	</form>
	<br>
		<button type="button" name="back" onclick="history.back()">Back</button>
	<br>
</body>
</html>