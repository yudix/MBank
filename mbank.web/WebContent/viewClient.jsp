<%@page import="beans.Client"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<meta http-equiv="Cache-Control" content="no-store">
<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
<title>View Client</title>

<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
	<%@include file="header.html"%>
	<h3>
		Hello ${client.clientName}<br /> Your details are:
	</h3>
	<br />
	<table class="imagetable">
		<tr>
			<th>Name</th>
			<td>${client.clientName}</td>
		</tr>
		<tr>
			<th>ID</th>
			<td>${client.clientID}</td>
		</tr>
		<tr>
			<th>Type</th>
			<td>${client.type}</td>
		</tr>
		<tr>
			<th>Address</th>
			<td>${client.address}</td>
		</tr>
		<tr>
			<th>Password</th>
			<td>${client.password}</td>
		</tr>
		<tr>
			<th>Phone</th>
			<td>${client.phone}</td>
		</tr>
		<tr>
			<th>Email</th>
			<td>${client.email}</td>
		</tr>
		<tr>
			<th>Comment</th>
			<td>${client.comment}</td>
		</tr>
	</table>
	<br />
	<table class="imagetable">
		<tr>
			<td><input type="button" value="Back to Client Details"
				style="font-weight: bold;"
				onClick="window.location.href='clientDetails.jsp'"
				style="FONT-SIZE: medium;"></td>
		</tr>
	</table>
	<br />
	<jsp:include page="/footer.jsp" />
</body>
</html>