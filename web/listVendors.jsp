<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.prs.business.vendors.Vendor" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PRS - Vendor List</title>
<link rel="stylesheet" href="css/main.css">
<script type="text/javascript" src="js/main.js"></script>
</head>
<body>
	<h1>List of Vendors</h1>
	<table>
		<tr>
			<th>&nbsp;</th>
			<th>Name</th>
			<th>Code</th>
			<th>Address</th>
			<th>City</th>
			<th>State</th>
			<th>Zip</th>
			<th>Phone Number</th>
			<th>Email</th>
			<th>PreApproved?</th>
		</tr>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<c:forEach var="vendor" items="${vendors}" >
		<tr>
		<td><a href="<c:url value='/listProducts?action=listProducts&vendorID=${vendor.id}' />">List Products</a></td>
		<td>${vendor.name}</td>
		<td>${vendor.code}</td>
		<td>${vendor.address}</td>
		<td>${vendor.city}</td>
		<td>${vendor.state}</td>
		<td>${vendor.zip}</td>
		<td>${vendor.phone}</td>
		<td>${vendor.email}</td>
		<td>${vendor.convertPreApprovedToString()}</td>
		</tr>
		</c:forEach>
	</table>
	<br>
	<button type="button" name="back" onclick="history.back()">Back</button><br>
	<br>
</body>
</html>