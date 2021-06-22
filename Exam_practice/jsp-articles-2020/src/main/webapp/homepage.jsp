<%@ page import="jspapp.domain.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Homepage</title>
    <link rel="stylesheet" href="main.css">
    <% String user; user = (String) session.getAttribute("user"); %>
    <script type="text/javascript">
            const username='<%=user%>';
    </script>
    <script src="js/jquery-3.6.0.min.js"></script>
    <script src="js/ajax-utils.js"></script>
    <script src="js/main.js"></script>
</head>
<body>
<%
    if (user != null)
        out.println("Welcome "+user);
%>
<br><br>
<label>
    Journal name
    <input type="text" id="journal-name-input" placeholder="journal name">
    <button type="button" onclick="findArticles()">Find articles</button>
</label>
<br><br>
<section id="journal-articles"> </section>
<br><br>

<form>
    <label for="journal-name">Journal: </label> <input type="text" id="journal-name">
    <br><br>
    <label for="summary">Summary: </label> <input type="text" id="summary">
    <br><br>
    <button type="button" id="add-button" onclick="addArticle(username)">Add article</button>
</form>

</body>
</html>
