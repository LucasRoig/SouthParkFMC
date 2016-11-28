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

            <button class="btn btn-default pull-right" data-toggle="modal" data-target="#addSeason"> <span class="glyphicon glyphicon-plus"> </span> Ajouter une saison</button>
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
            <button class="btn btn-default pull-right" data-toggle="modal" data-target="#addEpisode"> <span class="glyphicon glyphicon-plus"> </span> Ajouter un épisode</button>
        </div>


        <!-- Modal Ajout Saison
        -->
        <div id="addSeason" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Ajouter Une Saison</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" action="/season/create" method="POST">
                            <div class="form-group">
                                <label class="control-label col-sm-4">Numéro Saison : </label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" value="${nbSeason + 1}" name="seasonId" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-4">Année de diffusion : </label>
                                <div class="col-sm-6">
                                    <input type="number" class="form-control" value="2000" name="diffusionYear">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="reset" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-default">Valider</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal Ajout Episode
        -->
        <div id="addEpisode" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Ajouter Un Episode</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" action="/episode/create" method="POST">
                            <div class="form-group">
                                <label class="control-label col-sm-4">Numéro Saison : </label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" value="${selectedSeason}" name="seasonId" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-4">Code Production : </label>
                                <div class="col-sm-6">
                                    <input type="number" class="form-control"  name="productionCode">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-4">Index dans la saison : </label>
                                <div class="col-sm-6">
                                    <input type="number" class="form-control"  name="indexInSeason">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-4">Nom VO : </label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" name="nameVO">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-4">Nom VF : </label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" name="nameVF">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="reset" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-default">Valider</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
