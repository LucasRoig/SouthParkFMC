<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="resume" class="tab-pane fade in active">
    <div class="row">
        <div class="col-sm-4">
            <div class="form-switch">
                <div class="front">
                    <div class="list-group panel panel-default" id="panelInfos">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-sm-10"><h4>Informations</h4></div>
                                <div class="col-sm-2">
                                    <c:if test="${(!empty sessionScope.user) && (sessionScope.user.privilege == 'ADMIN')}">
                                        <button class="btn btn-primary pull-right btn-switch" id="editInfos"><span class ="glyphicon glyphicon-pencil"></span></button>
                                        </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="list-group-item">
                            <h4 class="list-group-item-heading" id="infosSeason">Saison ${episode.seasonId} Episode ${episode.indexInSeason}</h4>
                        </div>
                        <div class="list-group-item">
                            <h4 class="list-group-item-heading" id="infosProductionCode">Code Production : ${episode.productionCode}</h4>
                        </div>
                        <div class="list-group-item">
                            <h4 class="list-group-item-heading" id="infosNameVO">${episode.nameVO}</h4>
                        </div>
                        <c:if test="${!empty episode.nameVF}">
                            <div class="list-group-item">
                                <h4 class="list-group-item-heading" id="infosNameVF">${episode.nameVF}</h4>
                            </div>
                        </c:if>
                    </div> 
                </div>
                <div class="back">
                    <div class="panel panel-default" id="formInfosEdit">
                        <div class="panel-heading">
                            <h4>Editer les informations</h4>
                        </div>
                        <div class="panel-body">
                            <form method="POST" action="update">
                                <div class="form-group">
                                    <label for="seasonInput">Saison :</label>
                                    <input type="number" name="seasonId" class="form-control" value="${episode.seasonId}">
                                </div>
                                <div class="form-group">
                                    <label for="indexInSeasonInput">Numéro dans la saison :</label>
                                    <input type="number" name="indexInSeason" class="form-control" value="${episode.indexInSeason}">
                                </div>
                                <div class="form-group">
                                    <label for="productionCodeInput">Code Production :</label>
                                    <input type="number" name="productionCode" class="form-control" value="${episode.productionCode}">
                                </div>
                                <div class="form-group">
                                    <label for="nameVFInput">Nom VF :</label>
                                    <input type="text" name="nameVF" class="form-control" value="${episode.nameVF}">
                                </div>
                                <div class="form-group">
                                    <label for="nameVOInput">Nom VO :</label>
                                    <input type="text" name="nameVO" class="form-control" value="${episode.nameVO}">
                                </div>
                                <div class="form-group">
                                    <button type="reset" class="btn btn-primary btn-switch" id="btAnnulerFormEditInfos">Annuler</button> 
                                    <button type="submit" class="btn btn-primary">Valider</button> 
                                </div>
                                <input type="hidden" name="plot" class="form-control" value="${episode.plot}">
                                <input type="hidden" id="episodeId" name="episodeId" class="form-control" value="${episode.episodeId}">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="form-switch">
                <div class="front">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-sm-10"><h4>Résumé</h4></div>
                                <div class="col-sm-2">
                                    <c:if test="${(!empty sessionScope.user) && (sessionScope.user.privilege == 'ADMIN')}">
                                        <button class="btn btn-primary pull-right btn-switch"><span class ="glyphicon glyphicon-pencil"></span></button>
                                        </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">                            
                            <% pageContext.setAttribute("newLineChar", "\n");%> 
                            <c:forEach var="p" items="${fn:split(episode.plot, newLineChar)}">
                                <p>${p}</p>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class ="back">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4>Editer le résumé</h4>
                        </div>
                        <div class="panel-body">
                            <form method="POST" action="/episode/update">
                                <div class="form-group">
                                    <textarea name="plot" class="form-control">${episode.plot}</textarea>
                                </div>
                                <div class="form-group">
                                    <button type="reset" class="btn btn-primary btn-switch" id="btAnnulerFormEditInfos">Annuler</button> 
                                    <button type="submit" class="btn btn-primary">Valider</button> 
                                </div>
                                <input type="hidden" name="episodeId" class="form-control" value="${episode.episodeId}">
                                <input type="hidden" name="seasonId" class="form-control" value="${episode.seasonId}">
                                <input type="hidden" name="indexInSeason" class="form-control" value="${episode.indexInSeason}">
                                <input type="hidden" name="productionCode" class="form-control" value="${episode.productionCode}">
                                <input type="hidden" name="nameVF" class="form-control" value="${episode.nameVF}">
                                <input type="hidden" name="nameVO" class="form-control" value="${episode.nameVO}">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>