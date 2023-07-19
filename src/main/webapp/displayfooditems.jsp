<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="3" style="text-align: center; border-collapse: collapse;"cellpadding="10">
          <tr>
          <th>ID</th>
		  <th>FOODNAME</th>
		  <th>DESCRIPTION</th>
		  <th>IMAGE</th>
   		  <th>PRICE</th>
          <th colspan="2">ACTION</th>
          </tr>
          
           <c:forEach items="${allproducts }" var="product">
            <tr>
            <td>${product.getId()}</td>
            <td>${product.getName()}</td>
            <td>${product.getDescription()}</td>
            <td>${product.getImg()}</td>
            <td>${product.getPrice()}</td>
           
			<td>   <a href="addItem?id=${product.id }"> <input type="submit" value="add">  </td>
			</tr>
			
			
			</c:forEach>
			
			
			</table>
			<br>
			<center> <a href="cart"> <input type="submit" value="CART"> </a> </center>
			
</body>
</html>