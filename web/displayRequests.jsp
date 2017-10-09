<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PRS - Purchase Requests</title>
<link rel="stylesheet" href="css/main.css">
<script type="text/javascript" src="js/main.js"></script>
</head>
<body>
	<h2>Submitted Purchase Requests For ${username}</h2>
	<table>
	<tr>
		<th>Date Submitted</th>
		<th>Description</th>
		<th>Justification</th>
		<th>Date Needed By</th>
		<th>Delivery Mode</th>
		<th>Status</th>
		<th>Total</th>
	</tr>
	<c:forEach var="pr" items="${purchaseRequests}" >
	<tr>
		<td>${pr.submittedDate}</td>
		<td>${pr.description}</td>
		<td>${pr.justification}</td>
		<td>${pr.dateNeeded}</td>
		<td>${pr.deliveryMode}</td>
		<c:choose>
			<c:when test="${pr.statusID == 3}">
				<td><span style="color:red">${pr.convertStatusIDToString()}</span></td>
			</c:when>
			<c:when test="${pr.statusID == 2}">
				<td><span style="color:blue">${pr.convertStatusIDToString()}</span></td>
			</c:when>
			<c:otherwise>
				<td>${pr.convertStatusIDToString()}</td>
			</c:otherwise>
		</c:choose>
		<td>${pr.formattedTotal}</td>
	</tr>
	</c:forEach>
	</table>
	<br>
	<button type="button" name="back" onclick="history.back()">Back</button>
	<br>
</body>
</html>