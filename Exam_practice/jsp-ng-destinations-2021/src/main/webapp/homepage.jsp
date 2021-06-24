<%--<%@ page import="model.Asset" %>--%>
<%--<%@ page import="java.util.List" %>--%>
<%--<%@ page import="controller.AppController" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Assets</title>
    <link rel="stylesheet" href="main.css">
    <script src="js/jquery-2.0.3.js"></script>
    <script src="js/main.js"></script>
</head>

<body>
<h1>Home</h1>
<section id="all-assets">
    <%
//        int userId = (int) session.getAttribute("userId");
//        List<Asset> assets = AppController.getAssetsOfUser(userId);
//
//        String content = "<table><tr><th>Id</th><th>Name</th><th>Description</th><th>Value</th></tr>";
//        for (Asset asset: assets){
//            String color = "";
//            if (asset.getValue() > 10) {
//                color = "style=\"background-color:#FF0000\"";
//            }
//            content += "<tr " + color + "><td>" + asset.getId() + "</td>" +
//                    "<td>" + asset.getName() + "</td>" +
//                    "<td>" + asset.getDescription() + "</td>" +
//                    "<td>" + asset.getValue() + "</td>" +
//                    "</tr>";
//        }
//        content += "</table>";
//        out.println(content);
    %>
</section>
<br>
<P>Push first, send later ;)</P>
<form>
    <label for="Name">Name:</label>
    <input type="text" id="name" name="name"><br><br>
    <label for="Description">Description:</label>
    <input type="text" id="description" name="description"><br><br>
    <label for="Value">Value:</label>
    <input type="text" id="value" name="value"><br><br>
    <button type="button" id="push-button">Push</button>
    <button type="button" id="send-button">Send</button>

</form>

<section id="assets-array">
</section>

</body>
</html>
