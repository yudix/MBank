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
<title>MBank - Login</title>
<style type="text/css">
table.gridtable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}

table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}

table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>

<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
	<%@include file="header.html"%>
	<span style="color: red;">${error}</span>
	<br />
	<form action="LoginServlet" method="post">
		<input type="hidden" value="login" name="action" id="action" /><br />

		<table class="gridtable">
			<tr>
				<th>User Name</th>
				<td><input type="text" name="user" /></td>
			</tr>
			<tr>
				<th>Password</th>
				<td><input type="password" name="pass" /><br />
			</tr>
		</table>
		<br /> <input type="submit" value="Login" />
	</form>
</body>
</html>