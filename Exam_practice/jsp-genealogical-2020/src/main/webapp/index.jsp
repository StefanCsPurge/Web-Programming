<%--
  Created by IntelliJ IDEA.
  User: mihal
  Date: 6/21/2021
  Time: 10:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="main.css">
  </head>

  <body>
  <button onclick="location.href='addParents.jsp'"> Add my mother and father </button>
  <br><br>
  <form action="login-controller" method="post">
     <label> Enter username :</label>
    <input class="field" type="text" name="username">
    <label> Enter father/mother name : </label>
    <input class="field" type="text" name="parentname">

    <br><br><br>
    <label>  </label>
    <input class="submit" type="submit" value="Login"/>
  </form>
  </body>
</html>
