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
<title>Deposit to Account</title>

<link rel="stylesheet" type="text/css" href="style.css">
<script src="numbersonly.js"></script>

</head>
<body>
	<%@include file="header.html"%>
	<h1>Deposit to Account</h1>
	<br />
	<span style="color: red;">${error}</span>
	<br />
	<form action="Controller" method="post">
		<br />
		<table>
			<tr>
				<td>Enter Amount to Deposit:</td>
				<td><input type="text" name="depositAmount" id="depositAmount"
					onKeyPress="return numbersonly(event)" /><br /></td>
			</tr>
		</table>
		<input type="hidden" value="DepositToAccount" name="command"
			id="command" /> <input type="submit" value="Deposit" />
	</form>
	<span style="color: purple;">${success}</span>
	<br />
	<br />
	<table class="imagetable">
		<tr>
			<th>Client ID</th>
			<td>${account.clientID}</td>
		</tr>
		<tr>
			<th>Account ID</th>
			<td>${account.accountID}</td>
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