<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PRS - Home Page</title>
<link href="css/main.css" type="text/css" rel="stylesheet">
<link href="css/home.css" type="text/css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src='js/home.js' type='text/javascript'></script>
</head>
<body>
	<h2>${message}</h2>
	<nav class="clearfix">
		<ul class="clearfix">
			<li><a href="home.jsp">Home</a></li>
			<li><a a href="javascript:{}" onclick="document.getElementById('createREQUEST').submit();">Order</a></li>
			<li><a a href="javascript:{}" onclick="document.getElementById('reviewREQUESTS').submit();">Review Order</a></li>
			<li><a a href="javascript:{}" onclick="document.getElementById('approveREQUESTS').submit();">Approve Order</a></li>
			<li><a a href="javascript:{}" onclick="document.getElementById('listVENDORS').submit();">Vendors</a></li>
			<li><a a href="javascript:{}" onclick="document.getElementById('listPRODUCTS').submit();">Products</a></li>
			<li><a a href="javascript:{}" onclick="document.getElementById('LOGOUT').submit();">Logout</a></li>
		</ul>
		<a href="#" id="pull">Menu</a>
	</nav>

	<form id="createREQUEST" name="createRequest" method="POST" action="createRequest">
		<input type="hidden" name="action" value="createRequest">
		<input type="hidden" name="userID" value="${userid}">
		<input type="hidden" name="userName" value="${username}">
	</form>

	<form id="reviewREQUESTS" name="reviewRequests" method="POST" action="reviewRequests">
		<input type="hidden" name="action" value="reviewRequests">
		<button type="submit">Review Your Requests</button><br>
	</form>

	<c:if test="${user.isAdmin() == true}">
	<form id="approveREQUESTS" name="approveRequests" method="POST" action="approveRequests">
		<input type="hidden" name="action" value="approveRequests">
	</form>

	</c:if>
	<form id="listVENDORS" name="listVendors" method="POST" action="listVendors">
		<input type="hidden" name="action" value="listVendors">
	</form>



	<form id="listPRODUCTS" name="listProducts" method="POST" action="listProducts">
		<input type="hidden" name="action" value="listProducts">
	</form>



	<form id="LOGOUT" name="logout" method="POST" action="logout">
		<input type="hidden" name="action" value="logout">
	</form>

</body>
</html>