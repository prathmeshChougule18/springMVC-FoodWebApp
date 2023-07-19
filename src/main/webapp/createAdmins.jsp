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
   <form:form action="saveAdmin" modelAttribute="adminmodel1">
   Name:<form:input path="name"/><br>
    username:<form:input path="username"/><br>
        password:<form:input path="password"/><br>
    
    <form:button>Submit</form:button>
   </form:form>
</body>
</html>