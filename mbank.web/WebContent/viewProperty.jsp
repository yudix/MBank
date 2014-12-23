<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.ArrayList"%>
<%@page import="action.ClientAction"%>
<%@page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<meta http-equiv="Cache-Control" content="no-store">
<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
<title>Properties</title>

<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
	<%@include file="header.html"%>
	<h1>Properties</h1>
	<br />
	<table class="imagetable">
		<tr>
			<th>Properties Keys</th>
			<th>Properties Values</th>
		</tr>
		<c:forEach items="${prop}" var="p">
			<tr>
				<td><b>${p.propKey}</b></td>
				<td>${p.propValue}</td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<jsp:include page="/footer.jsp" />
</body>
</html>