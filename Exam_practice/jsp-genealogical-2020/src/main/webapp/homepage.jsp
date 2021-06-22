
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Homepage</title>
    <% String user; user = (String) session.getAttribute("user"); %>
    <script type="text/javascript">
        const username='<%=user%>';
    </script>
    <script src="js/jquery-3.6.0.min.js"></script>
    <script src="js/main.js"></script>
</head>
<body>
<%
    if (user != null)
        out.println("Welcome "+user);
%>
<br><br>
<button type="button" onclick="showFatherLine(username)">Show father family line</button>
<br>
<section id="father-line"></section>
<br>
<button type="button" onclick="showMotherLine(username)">Show mother family line</button>
<br>
<section id="mother-line"></section>
</body>
</html>
