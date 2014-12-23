<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Activities</title>

<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<%@include file="header.html"%>
	<h1>Activities</h1>
	<br />
	<table class="imagetable">
		<tr>
			<th>Activity ID</th>
			<th>Client ID</th>
			<th>Amount</th>
			<th>Date</th>
			<th>Commission</th>
			<th>Description</th>

		</tr>
		<c:forEach items="${activities}" var="a">
			<tr>
				<td>${a.activityID}</td>
				<td>${a.clientID}</td>
				<td>${a.amount}</td>
				<td>${a.activityDate}</td>
				<td>${a.commission}</td>
				<td>${a.description}</td>
			</tr>
		</c:forEach>
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