<%-- 
    Document   : all-tags
    Created on : 30 nov. 2016, 11:16:00
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
    </head>
    <body>
        <%@include file="/header.jsp" %>
        <center><h1>Tags </h1></center>
        <div class="container">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Nom</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="tag" items="${tagList}">
                        <tr>
                            <td>${tag.tagName}</td>
                            <td><a href="/tag/read?tagId=${tag.tagId}"><span class="glyphicon pull-right glyphicon-eye-open"></span></a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
