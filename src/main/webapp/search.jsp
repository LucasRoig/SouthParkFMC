<%-- 
    Document   : search
    Created on : 3 déc. 2016, 19:45:27
    Author     : Lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

        <link  rel="stylesheet" type="text/css" href="/css/main.css">
        <!-- Latest compiled and minified JavaScript -->
        <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
        <ul>
            <c:forEach var="result" items="${results}">
                <li><a href="/episode/read?episodeId=${result.episodeId}">${result.nameVF} [${result.score}]</a></li>
            </c:forEach>
        </ul>
        </div>
    </body>
</html>
