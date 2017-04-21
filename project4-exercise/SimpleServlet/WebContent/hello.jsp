<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello</title>
</head>
<body>
	<h1>Add Student</h1>
	<form action="StudentServlet" method="post">
		Name: <input name="name">
		<input type="submit" value="add">
	</form>
	
	<c:forEach items="${students}" var="student">
   		<p><c:out value="${student.getId()}"/>. <c:out value="${student.getName()}"/><p>
		<form method="post" action="StudentServlet">
			<input type="hidden" value="${student.getId()}" name="deleteTarget">
			<input type="submit" value="remove">
		</form>
	</c:forEach>
</body>
</html>