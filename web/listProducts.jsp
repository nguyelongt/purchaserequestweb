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
<table>
    <tr>
        <th>Name</th>
        <th>Part Number</th>
        <th>Price</th>
        <th>Unit</th>
    </tr>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:forEach var="product" items="${products}" >
        <tr>
            <td>${product.name}</td>
            <td>${product.partNumber}</td>
            <td>${product.getPriceFormatted()}</td>
            <td>${product.unit}</td>
        </tr>
    </c:forEach>
</table>
<br>
    <button type="button" name="back" onclick="history.back()">Back</button>
<br>
</body>
</html>