
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="2" style="text-align: center; border-collapse: collapse;"cellpadding="10">
   <tr>
   <th>ID</th>
   <th>Name</th>
   <th>UserName</th>
   <th>Password</th>
   </tr>
 <c:forEach var="admin" items="${Adminview}">
 <tr>
 <td>${admin.getId()}</td>
 <td>${admin.getName()}</td>
 <td>${admin.getUsername()}</td>
 <td>${admin.getPassword()}</td>
 <td> <a href="delete?id=${admin.getId()}">Deleted</a></td>
 <td><a href="update?id=${admin.getId()}">Updated</a></td>
 </tr>
 </c:forEach>
</table>
</body>
</html>