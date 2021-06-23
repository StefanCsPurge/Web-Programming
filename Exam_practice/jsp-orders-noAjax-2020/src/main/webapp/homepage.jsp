<%@ page import="jspapp.domain.User" %>
<%@ page import="jspapp.controller.ProductController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Homepage</title>
    <link rel="stylesheet" href="main.css">
<%--    <script src="js/jquery-3.6.0.min.js"></script>--%>
</head>
<body>
<%! String user; %>
<%  user = (String) session.getAttribute("user");
    if (user != null)
        out.println("Welcome "+user);
%>
<br><br>
<form action="ProductController" method="POST">
    <label class="label1">Name</label> <input class="field" type="text" name="prod_name"> <BR>
    <label class="label1">Description</label> <input class="field" type="text" name="prod_description"> <BR>
    <label class="label1"> </label> <input class="submit" type="submit" name="add_submit" value="Add product"/>
</form>
<br><br><br><br>

<form action="ProductController" method="GET">
    <label>Search by product name</label> <input type="text" name="prod_name">
    <input type="submit" name="search_prod" value="Search product"/>
</form>
<br>

<section id="filtered-products">
    <p> Found products: </p>
    <%
     String content = ProductController.getProductsByPrefix();
     out.println(content);
    %>
</section>

<br>
<form action="ProductController" method="POST">
    <label class="label1">Product name</label> <input class="field" type="text" name="p_name"> <BR>
    <label class="label1">Quantity</label> <input class="field" type="number" name="quantity"> <BR>
    <label class="label1"> </label> <input class="submit" type="submit" name="order_submit" value="Submit order"/>
</form>
<br><br><br>
</body>
</html>
