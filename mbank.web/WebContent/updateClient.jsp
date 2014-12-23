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
<title>Update Client Details</title>

<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
	<%@include file="header.html"%>
	<br />
	<h3>
		Update Client Details:<br />Please enter the Details you want to
		Update
	</h3>
	<br />
	<span style="color: red;"> ${error} </span>
	<span style="color: purple;"> ${success} </span>
	<br />
	<form action="Controller" method="post">
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
				<td><input type="text" value="${client.address}" name="address"
					id="address" /></td>
			</tr>
			<tr>
				<th>Password</th>
				<td>${client.password}</td>
			</tr>
			<tr>
				<th>Phone</th>
				<td><input type="text" value="${client.phone}" name="phone"
					id="phone" /></td>
			</tr>
			<tr>
				<th>Email</th>
				<td><input type="text" value="${client.email}" name="email"
					id="email" /></td>
			</tr>
			<tr>
				<th>Comment</th>
				<td>${client.comment}</td>
			</tr>
		</table>
		<br /> <input type="hidden" value="UpdateClient" name="command"
			id="command" /> <input type="submit" value="Update!" />
	</form>
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