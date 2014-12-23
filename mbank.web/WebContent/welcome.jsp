<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<meta http-equiv="Refresh" content="3;  URL=login.jsp">
<link rel="shortcut icon" href="favicon.ico">
<title>MBank Welcome</title>
<%@include file="style.css"%>
</head>
<body>
	
	<center>
		<div>
			<img alt="Welcome" src="logo-big.jpg" style="width : 444px; height : 318px;">
		</div>
		<div style="height: 60px;">
			<h1>Welcome to MBank</h1>
		</div>
	</center>
	
	<br />
	<form>
		<table>
			<tr>
				<td><input type="button" value="Login"
					style="width: 160px; font-size: medium; border-style: ridge; font-weight: bold;"
					onClick="window.location.href='login.jsp'" ></td>
			</tr>
		</table>
	</form>

</body>
</html>