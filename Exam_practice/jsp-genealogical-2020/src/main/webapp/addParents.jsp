<%--
  Created by IntelliJ IDEA.
  User: mihal
  Date: 6/22/2021
  Time: 11:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add my parents</title>
    <link rel="stylesheet" href="main.css">
</head>
<body>
<form action="FamilyController" method="post">
    <label> Enter username : </label> <input class="field" type="text" name="username"> <BR>
    <label> Enter mother name : </label> <input class="field" type="text" name="mother"> <BR>
    <label> Enter father name : </label> <input class="field" type="text" name="father"> <BR>
    <label> </label> <input class="submit" type="submit" value="Add parents"/>
</form>
</body>
</html>
