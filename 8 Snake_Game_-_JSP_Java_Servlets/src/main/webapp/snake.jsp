<%--
  Created by IntelliJ IDEA.
  User: mihal
  Date: 5/11/2021
  Time: 10:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<%@ page import="java.sql.Time" %>
<%
    if (request.getParameter("level") != null && request.getParameter("ini_len") != null) {
        int $level = 1, $ini_len = 0, $user_hs = 0;
        String $user_n = null;
        try {
            $level = Integer.parseInt(request.getParameter("level"));
        } catch (Exception ignored) {}
        try {
            $ini_len = Integer.parseInt(request.getParameter("ini_len"));
        } catch (Exception ignored) {}
        try {
            $user_n = ((User)session.getAttribute("user")).getUsername();
        } catch (Exception ignored) {}
        try {
            $user_hs = ((User)session.getAttribute("user")).getHigh_score();
        } catch (Exception ignored) {}
        if ($level < 0) $level = 1;
        if ($level > 10) $level = 10;
        if ($ini_len < 1) $ini_len = 1;
        if ($ini_len > 20) $ini_len = 20;
    %>
    <!DOCTYPE html>
    <html>
    <head>
        <link rel="icon" href="snake_icon.png">
        <link rel="stylesheet" href="style.css" type="text/css">
        <title>Snake Play</title>
        <script type="text/javascript">
            let ini_len = <%=$ini_len%>;
            let level = <%=$level%>;
            let user_name = '<%=$user_n%>';
            let high_score = <%=$user_hs%>;
            if (!user_name || user_name === 'null' || user_name.length === 0)
                window.location.replace("http://localhost:8080/login.jsp");
        </script>
        <script src="snake_play.js" type="text/javascript"></script>  <!-- the game logic -->
    </head>
    <body>
    <%
        User user = (User) session.getAttribute("user");
        if(user!=null) {
            out.println("Welcome, " + user.getUsername() + "! ");
            out.println("Let's make that snake big AF! ");
            out.println("<b>HIGH SCORE: " + user.getHigh_score() + "</b>");
        }
    %>
    <form action="LogoutServlet" method="post">
        <input type="submit" name="logout" value="Logout">
    </form>
    <noscript>
        <h1>Please use a Javascript enabled web browser. Thank you.</h1>
    </noscript>
    </body>
    </html>

<% }
    else
{ %>
    <!DOCTYPE html>
    <html>
    <head>
        <title>Snake Play</title>
        <link rel="icon" href="snake_icon.png">
        <link rel="stylesheet" href="style.css" type="text/css">
        <script type="text/javascript">
            <%
            String $user_n = null;
            String $time_spent = null;
            String $high_score = null;
               try {
                   User user = (User)session.getAttribute("user");
                   $user_n = user.getUsername();
                   $time_spent = user.getTimeObjForTimeSpent().toString();
                   $high_score = user.getHigh_score().toString();
               } catch (Exception ignored) {}
            %>
            let user_name = '<%=$user_n%>';
            if (!user_name || user_name === 'null' || user_name.length === 0)
                window.location.replace("http://localhost:8080/login.jsp");
        </script>
    </head>
    <body>
    <br>
    <% out.println("Welcome, "+ $user_n + "! ");
       out.println("The time you have spent playing is (hh:mm:ss): " + $time_spent);
       out.println("<br><br><b>HIGH SCORE: " + $high_score + "</b>");
    %>
    <form action="LogoutServlet" method="post">
        <input type="submit" name="logout" value="Logout">
    </form>
    <br><br>
    <form method="POST" action="snake.jsp">
        Level:
        <label>
            <select name="level">
            <%
                for (int $i = 1; $i <= 10; $i++) {
            %>
                    <option value="<%= $i%>"><%= $i%></option>
            <%
                }
            %>
            </select>
        </label>
        <br><br>
        Initial Length:
        <label>
            <select name="ini_len">
            <%
                for (int $i = 1; $i <= 20; $i++) {
            %>
                    <option value="<%= $i%>"><%= $i%></option>
            <%
                }
            %>
            </select>
        </label>
        <br><br>
        <input type="submit" name="submit" value="Start Game" />
    </form>
    </body>
    </html>
<% }
%>
