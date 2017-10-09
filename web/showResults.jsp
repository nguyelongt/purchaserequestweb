<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PRS - Purchase Request Summary</title>
<link rel="stylesheet" href="css/main.css">
<script type="text/javascript" src="js/main.js"></script>
</head>
<body>
	<h2>${result}</h2>
	<h3>Purchase Request Summary:</h3>
	Description: ${newRequest.description}<br><br>
	Justification: ${newRequest.justification}<br><br>
	Date Needed By: ${newRequest.dateNeeded}<br><br>
	Total: ${newRequest.formattedTotal}<br><br>
	Status: ${newRequest.statusID}<br><br>
	
	<table>
		<tr>
			<th>Quantity</th>
			<th>Name</th>
			<th>Part Number</th>
			<th>Price</th>
			<th>Unit</th>
		</tr>
		<c:forEach var="lineItem" items="${lineItems}" >
			<c:forEach var="product" items="${purchaseRequestProducts}" >
				<c:if test="${lineItem.quantity > 0}">
					<c:if test="${lineItem.productID == product.id}">
						<tr>
						<td>${lineItem.quantity}</td>
						<td>${product.name}</td>
						<td>${product.partNumber}</td>
						<td>${product.getPriceFormatted()}</td>
						<td>${product.unit}</td>
						</tr>
					</c:if>
				</c:if>
			</c:forEach>
		</c:forEach>
	</table>
	<br>
	<form name="home" method="POST" action="home">
		<input type="hidden" name="action" value="home">
		<button type="submit">Home Page</button><br>
	</form>
	<br>
	<form name="logout" method="POST" action="logout">
		<input type="hidden" name="action" value="logout">
		<button type="submit">Logout</button><br>
	</form>
	<br>
</body>
</html>