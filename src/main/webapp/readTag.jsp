<%-- 
    Document   : readTag
    Created on : 30 nov. 2016, 11:46:57
    Author     : Bruce
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <link rel="stylesheet" type="text/css" media="all" href="/css/selectize.bootstrap3.css">
        <link  rel="stylesheet" type="text/css" href="/css/main.css">
        <!-- Latest compiled and minified JavaScript -->
        <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file="/header.jsp" %>
        <center><h1>${tag.tagName}</h1></center>
        <div class="container">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Episode</th>
                        <th>Commentaire</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="activeTag" items="${usesList}">
                        <tr>
                            <td>${activeTag.episodeName}</td>
                            <td>${activeTag.note}</td>
                            <td><a href="/episode/read?episodeId=${activeTag.episodeId}"><span class="glyphicon pull-right glyphicon-eye-open"></span></a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
