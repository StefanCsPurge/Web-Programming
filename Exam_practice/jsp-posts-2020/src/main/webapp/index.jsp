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
    <style>
      form {
        margin-left: auto;
        margin-right: auto;
        width: 400px;
      }
      input{
        margin-left: auto;
        margin-right: auto;
      }
    </style>
  </head>

  <body>
  <form action="login-controller" method="post">
    Enter username : <input type="text" name="username">
    <input type="submit" value="Login"/>
  </form>
  </body>
</html>
