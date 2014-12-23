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

<link rel="shortcut icon" href="favicon.ico">
<title>PreOPen Deposit</title>

<link rel="stylesheet" type="text/css" href="style.css">
<script src="numbersonly.js"></script>

</head>
<body>
	<%@include file="header.html"%>
	<form action="Controller" method="post">
		<h3>Pre Open Deposit:</h3>
		<br /> Please enter your deposit id you want to pre-open<br /> <input
			type="text" name="depositId" id="depositId"
			onKeyPress="return numbersonly(event)" /><br /> <input type="hidden"
			value="PreOpenDeposit" name="command" id="command" /> <input
			type="submit" value="PreOpen" />
	</form>
	<br />
	<span style="color: red;"> ${error} </span>
	<span style="color: purple;"> ${success} <br /> Your Balance
		now: ${account.balance}
	</span>
	<br />
	<table class="imagetable">
		<tbody>
			<tr>
				<th>DepositID</th>
				<th>ClientID</th>
				<th>Balance</th>
				<th>Estimated_balance</th>
				<th>Deposit_type</th>
				<th>Opening_date</th>
				<th>Closinging_date</th>
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