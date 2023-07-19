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

    <% Object id= session.getAttribute("adminid"); %>
      <form:form action="saveProduct" modelAttribute="productmodel">
    Name:<form:input path="name"/><br>
    Description:<form:input path="description"/><br>
    Image:<form:input path="img"/><br>
    Price:<form:input path="price"/><br>
      
    Admin Id<input type="text" readonly="readonly" value="<%=id%>">   
	<form:button>Save</form:button>
	</form:form>   
</body>
</html>