<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homepage</title>
    <script src="../main.js"></script>
    <link rel="stylesheet" href="../style.css" type="text/css">
</head>
<body>
<form action="LogoutServlet" method="post">
    <input type="submit" name="logout" value="Logout">
</form>
<h2>User info</h2>
<%
    User user = (User) session.getAttribute("user");
    if(user!=null){
        out.println("Welcome, "+user.getUsername());
        out.println("<ul>");
        out.println("<li>Time spent playing: " + user.getTime_spent() + "</li>");
        out.println("</ul>");
    }

%>
<h2>Search:</h2>
<form action="SearchServlet" method="get" id="searchForm">
    <input type="text" name="name" placeholder="username" id="name"><br>
    <button type="button" value="Search" onclick="validateSearch()">Search</button>
</form>
<br><br><br>
<button type="button" onclick="location.href='update.jsp'">Update your profile</button>
</body>
</html>
