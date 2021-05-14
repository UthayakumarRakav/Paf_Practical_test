<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="model.Customer"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/customer.js"></script>
<title>Customer Management</title>
</head>
<body>
<h1>Customer Management</h1>

<form id="formItem" name="formItem" method="post" action="items.jsp">
 name:
<input id="name" name="name" type="text"
 class="form-control form-control-sm">
<br> gender:
<input id="gender" name="gender" type="text"
 class="form-control form-control-sm">
<br> phone:
<input id="phone" name="phone" type="text"
 class="form-control form-control-sm">
<br> email:
<input id="email" name="email" type="email"
 class="form-control form-control-sm">
<br>city:
<input id="city" name="city" type="text"
 class="form-control form-control-sm">
<br> region:
<input id="region" name="region" type="text"
 class="form-control form-control-sm">

<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>


<br>
<div id="divItemsGrid">
<%
 Customer Obj = new Customer();
 out.print(Obj.readCustomer());
%>
</div>
</body>
</html>