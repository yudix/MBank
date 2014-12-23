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

<link rel="stylesheet" type="text/css" href="style.css">
<script src="numbersonly.js"></script>

<title>Create Deposit</title>
</head>
<body>
	<%@include file="header.html"%>
	<form action="Controller" method="post">
		<br />
		<h3>Create new Deposit</h3>
		<br />
		<table class="imagetable">
			<tr>
				<td>Enter Amount to Deposit :</td>
				<td><input type="text" name="sum" id="sum"
					onKeyPress="return numbersonly(event)" /><br /></td>
			</tr>
		</table>
		<h3>For how long?</h3>
		<table class="imagetable">
			<tr>
				<th>Years:</th>
				<th>Months:</th>
			</tr>
			<tr>
				<td><input type="text" name="years" id="years" value="0"
					onKeyPress="return numbersonly(event)" /></td>
				<td><input type="text" name="months" id="months" value="0"
					onKeyPress="return numbersonly(event)" /></td>
				
		</table>
		<br /> <span style="color: red;">${error}</span><br /> <br /> <input
			type="hidden" value="CreateDepositResult" name="command" id="command" />
		<input type="submit" value="Create!" />
	</form>
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