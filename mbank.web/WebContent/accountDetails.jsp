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

<title>Client Account Details</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

	<%@include file="header.html"%>
</head>
<body>
	<table class="imagetable">

		<tr>
			<th><a href="Controller?command=ViewAccount"
				style="COLOR: #400080; text-decoration: none;">View Account</a></th>
		</tr>
		<tr>
			<th><a href="Controller?command=WithdrawFromAccountPage"
				style="COLOR: #400080; text-decoration: none;">Withdraw</a></th>
		</tr>
		<tr>
			<th><a href="Controller?command=DepositToAccountPage"
				style="COLOR: #400080; text-decoration: none;">Deposit</a></th>
		</tr>

	</table>
	<jsp:include page="/footer.jsp" />
</body>
</html>