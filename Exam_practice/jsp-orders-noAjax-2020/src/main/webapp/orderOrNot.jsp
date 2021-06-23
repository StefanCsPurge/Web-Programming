<%--
  Created by IntelliJ IDEA.
  User: mihal
  Date: 6/23/2021
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="jspapp.controller.ProductController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cancel or finalize</title>
</head>
<body>
<p>
You have selected <%out.println(ProductController.noOfOrders);%> orders.
</p>
<br>
<form action="ProductController" method="GET">
    <input type="submit" name="finalize_submit" value="Finalize Command"/>
    <input type="submit" name="cancel_submit" value="Cancel Command"/>
</form>
<br>
<button type="button" onclick="location.href='./homepage.jsp'">Add another product to orders</button>
</body>
</html>
