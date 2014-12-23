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
<title>MBank Main Menu</title>

<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
	<%@include file="header.html"%>

	<h2>
		Hello <b>${client.clientName}</b>
		
		! <br /> Welcome to MBank
	</h2>
	<h3>
		
		Your Last visit <br/>
		${lastVisit}</h3>
	<h2>Main Menu</h2>
	<table class="imagetable">
		<tr>
			<th><input type="button" value="Client Details"
				style="FONT-SIZE: medium; FONT-WEIGHT: bold; width: 160px"
				onClick="window.location.href='clientDetails.jsp'"></th>
		</tr>
		<tr>
			<th><input type="button" value="Account"
				style="FONT-SIZE: medium; FONT-WEIGHT: bold; width: 160px"
				onClick="window.location.href='accountDetails.jsp'"></th>
		</tr>
		<tr>
			<th><input type="button" value="Deposits"
				style="FONT-SIZE: medium; FONT-WEIGHT: bold; width: 160px"
				onClick="window.location.href='deposits.jsp'"></th>
		</tr>
		<tr>
			<th><input type="button" value="View Property"
				style="FONT-SIZE: medium; FONT-WEIGHT: bold; width: 160px"
				onClick="window.location.href='Controller?command=ViewProperty'">
			</th>
		</tr>

	</table>

	<div>
		<br /> <input type="button" value="Logout"
			style="FONT-SIZE: medium; COLOR: #ffffff; FONT-WEIGHT: bold; BACKGROUND-COLOR: #000000; width: 160px"
			onClick="window.location.href='Controller?command=LogOut'">
	</div>
</body>
</html>