<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
     <%  Object obj=session.getAttribute("adminid"); %>

	<form:form action="saveStaff" modelAttribute="Satffmodel">
	
	Name <form:input path="name"/> <br>
	UserName <form:input path="username"/> <br>
	Password <form:input path="password"/> <br>
	
	Owner Id <input type="number" value="<%=obj %>" readonly="readonly"> <br>
	
	<input type="submit" >
	
	</form:form>
</body>
</html>