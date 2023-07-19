<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Create Admin!</h1>
	<form:form action="adminsave" modelAttribute="admin">
     Name:<form:input path="name"/><br>
    username:<form:input path="username"/><br>
        password:<form:input path="password"/><br>
    
    
	<form:button>Save</form:button>
	</form:form>
</body>
</html>