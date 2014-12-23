<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@page import="beans.Deposit"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<meta http-equiv="Cache-Control" content="no-store">
<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
<title>View Deposit</title>

<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
	<%@include file="header.html"%>
	<h2>Your details are:</h2>
	<br />
	<table class="imagetable">
		<tbody>
			<tr>
				<th>DepositID</th>
				<th>ClientID</th>
				<th>Balance</th>
				<th>EstimatedBalance</th>
				<th>DepositType</th>
				<th>OpeningDate</th>
				<th>ClosingingDate</th>
			</tr>

			<c:forEach items="${deposits}" var="d">
				<tr>
					<td>${d.depositID}</td>
					<td>${d.clientID}</td>
					<td>${d.balance}</td>
					<td>${d.estimatedBalance}</td>
					<td>${d.depositType}</td>
					<td>${d.openingDate}</td>
					<td>${d.closingDate}</td>
				</tr>
			</c:forEach>
	</table>
	<br />
	<table class="imagetable">
		<tr>
			<td><input type="button" value="Back to Deposits"
				style="font-weight: bold;"
				onClick="window.location.href='deposits.jsp'"
				style="FONT-SIZE: medium;"></td>
		</tr>
	</table>
	<br />
	<jsp:include page="/footer.jsp" />
</body>
</html>