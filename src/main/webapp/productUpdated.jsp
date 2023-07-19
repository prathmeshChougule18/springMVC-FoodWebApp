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
      <h1>${productUpdated}</h1>
     <form:form action="productupdated" modelAttribute="result">
     Id:<form:input path="id"/><br>
     Name:<form:input path="name"/><br>
     Description:<form:input path="description"/><br>
     Image:<form:input path="img"/><br>
     Price:<form:input path="price"/><br>
     <form:button>Save</form:button>
     </form:form>
</body>
</html>