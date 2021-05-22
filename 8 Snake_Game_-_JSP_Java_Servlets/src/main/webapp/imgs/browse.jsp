<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
    <script src="../main.js"></script>
    <link rel="stylesheet" href="../style.css" type="text/css">
</head>
<body>
<form action="LogoutServlet" method="post">
    <input type="submit" name="logout" value="Logout">
</form>

<h2>Browse</h2>
<%
    List<User> users = (ArrayList<User>)session.getAttribute("users");
    out.println("<table>");
    out.println("<thead>");
    out.println("<th>Time spent</th>");
    out.println("</thead>");
    out.println("<tbody>");
    if (users!=null)
        for(User user:users){
            out.println("<tr>");
            out.println("<td>" + user.getTime_spent() +"</td>");
            out.println("</tr>");
        }
    out.println("</tbody>");
    out.println("</table>");
%>
</body>
</html>
