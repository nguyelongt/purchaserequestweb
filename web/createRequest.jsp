
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>PRS - Create Purchase Request</title>
    <link rel="stylesheet" href="css/main.css">
    <script src="js/jquery.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/main.js"></script>
</head>
<body>
<h2>Create Purchase Request</h2>
<form name="newRequestForm" method="POST" action="newRequest">
    <input type="hidden" name="action" value="newRequest">
    <label class="pad_top">Description: </label>
    <input type="text" name="description" required><br>
    <label class="pad_top">Justification: </label>
    <textarea name="justification" rows="10" cols="30"></textarea><br>
    <label class="pad_top">Date Needed: </label>
    <input type="date" name="dateNeeded" id="datePicker"><br>
    <label class="pad_top">Delivery Mode: </label>
    <input list="deliveryMode" name="deliveryMode">
    <datalist id="deliveryMode">
        <option value="Pickup">
        <option value="Mail">
    </datalist><br>
    <label class="pad_top">Phone Number: </label>
    <input type="tel" name="phoneNumber" value="${user.phone}" required><br>
    <label class="pad_top">Email: </label>
    <input type="email" name="email" value="${user.email}" required><br><br>
    <br>
    <button type="submit">Select Vendor</button><br>
</form>
<br>
    <button type="button" name="back" onclick="history.back()">Back</button>
<br>
</body>
</html>