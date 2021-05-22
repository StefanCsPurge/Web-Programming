<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update info</title>
    <script src="../main.js"></script>
    <link rel="stylesheet" href="../style.css" type="text/css">
</head>
<body>
<h2>Update info:</h2>
<form action="UpdateServlet" method="post" id="updateForm">
    <input type="time" name="time-spent" id="time-spent"><br>
    <button type="button" value="Update" onclick="validateUpdateInfo()">Update</button>
</form>
</body>
</html>
