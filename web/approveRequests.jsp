<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PRS - Approve Requests</title>
<link rel="stylesheet" href="css/main.css">
<script type="text/javascript" src="js/main.js"></script>
</head>
<body>
	<h2>Purchase Requests below are pending approval:</h2>
	<p>Note: Any quests that are left unchecked are 'Rejected'.</p>
	<form name="updateRequests" action="updateRequests" method="post">
		<table>
		<tr>
			<th>Approve?</th>
			<th>User Name</th>
			<th>Date Submitted</th>
			<th>Description</th>
			<th>Justification</th>
			<th>Date Needed By</th>
			<th>Delivery Mode</th>
			<th>Status</th>
			<th>Total</th>
		</tr>
		<c:forEach var="pr" items="${purchaseRequests}" varStatus="statusID">
			<tr>
				<td>
					<input type=hidden name="requestid" value="${pr.id}" />
					<input type=checkbox name="approved_${pr.id}" />
				</td>
				<td>${pr.username}</td>
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
						<td><span style="color:red">${pr.convertStatusIDToString()}</span></td>
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
		<input type="hidden" name="action" value="updateRequests">
		<button type="submit">Submit</button>
	</form>
	<br>
	<button type="button" name="back" onclick="history.back()">Back</button>
	<br>
</body>
</html>