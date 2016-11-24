<%-- 
    Document   : readSeason
    Created on : 23 nov. 2016, 17:23:16
    Author     : Lucas
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
        <div class="container">
            <center><h1>Saison ${selectedSeason}</h1></center>
            <ul class="nav nav-pills">
                <c:forEach var="i" begin="1" end="${nbSeason}">
                    <c:if test="${i == selectedSeason}">
                        <li class="active"> <a href="/season/read?selectedSeason=${i}">${i}</a></li>
                        </c:if>
                        <c:if test="${i != selectedSeason}">
                        <li> <a href="/season/read?selectedSeason=${i}">${i}</a></li>
                        </c:if>
                    </c:forEach>
            </ul>
        </div>
        
        <div class="container">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Code Prod</th>
                        <th>Num</th>
                        <th>Nom VO</th>
                        <th>Nom VF</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="episode" items="${episodeList}">
                        <tr>
                            <td>${episode.productionCode}</td>
                            <td>${episode.indexInSeason}</td>
                            <td>${episode.nameVO}</td>
                            <td>${episode.nameVF}</td>
                            <td><a href="/episode/read?episodeId=${episode.episodeId}"><span class="glyphicon glyphicon-eye-open"></span></a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
