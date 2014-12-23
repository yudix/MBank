<%@page import="beans.Account"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<%@ page errorPage="error.jsp"%>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<meta http-equiv="Cache-Control" content="no-store">
<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
<title>View Account</title>

<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
	<%@include file="header.html"%>
	<h3>Your Account details are:</h3>
	<br />
	<br />
	<table class="imagetable">
		<tr>
			<th>Account ID</th>
			<td>${account.accountID}</td>
		</tr>
		<tr>
			<th>Client ID</th>
			<td>${account.clientID}</td>
		</tr>
		<tr>
			<th>Credit Limit</th>
			<td>${account.creditLimit}</td>
		</tr>
		<tr>
			<th>Balance</th>
			<td>${account.balance}</td>
		</tr>
		<tr>
			<th>Comment</th>
			<td>${account.comment}</td>
		</tr>
	</table>
	<br />
	<table class="imagetable">
		<tr>
			<td><input type="button" value="Back to Account"
				style="font-weight: bold;"
				onClick="window.location.href='accountDetails.jsp'"
				style="FONT-SIZE: medium;"></td>
		</tr>
	</table>
	<br />
	<jsp:include page="/footer.jsp" />
</body>
</html>