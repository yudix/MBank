<%@page import="beans.Client"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<meta http-equiv="Cache-Control" content="no-store">
<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">

<title>Client Personal Details</title>
<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
	<%@include file="header.html"%>
</head>
<body>
	<table class="imagetable">
		<tr>
			<th><a href="Controller?command=ViewClient"
				style="COLOR: #400080; text-decoration: none;"> View Client
					Details</a></th>
		</tr>
		<tr>
			<th><a href="Controller?command=UpdateClientPage"
				style="COLOR: #400080; text-decoration: none;"> Update Client
					Details</a></th>
		</tr>
		<tr>
			<th><a href="Controller?command=ViewActivities"
				style="COLOR: #400080; text-decoration: none;">View Activities</a></th>
		</tr>
	</table>
	<br />
	<jsp:include page="/footer.jsp" />
</body>
</html>