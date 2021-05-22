<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <script src="main.js"></script>
    <link rel="icon" href="snake_icon.png">
    <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body> <br>
<h2>Register to game</h2>
<form action="RegisterServlet" method="post" id="registerForm">
    <label for="username"></label>
    <input type="text" name="username" placeholder="username" id="username"> <BR>
    <label for="password"></label>
    <input type="password" name="password" placeholder="password" id="password"> <BR>
    <input type="button" value="Register" onclick="validateRegister()"/>
</form>
</body>
</html>
