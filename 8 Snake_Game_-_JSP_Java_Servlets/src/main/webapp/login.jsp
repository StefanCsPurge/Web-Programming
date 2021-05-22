<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="icon" href="snake_icon.png">
    <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body> <br>
<form action="LoginServlet" method="post">
    Enter username :
    <label>
    <input type="text" name="username">
    </label> <BR>
    Enter password :
    <label>
    <input type="password" name="password">
    </label> <BR>
    <input type="submit" value="Login"/>
</form>
</body>
</html>
