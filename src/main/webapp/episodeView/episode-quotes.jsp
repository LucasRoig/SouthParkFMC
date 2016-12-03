<%-- 
    Document   : episode-repliques
    Created on : 9 nov. 2016, 23:16:20
    Author     : Lucas
--%>
<%@ page pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="replique" class="tab-pane fade in">
    <table class="table table-striped" id="tableQuote">
        <thead>
            <tr>
                <th>Personnage</th>
                <th>Texte</th>
                <th>Commentaire</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="quote" items="${quoteList}">
                <tr>
                    <td>${quote.characterName}</td>
                    <td>${quote.quoteText}</td>
                    <td>${quote.quoteNote}</td>
                    <td>
                        <div class="btn-group pull-right">
                            <c:if test="${(!empty sessionScope.user) && (sessionScope.user.privilege == 'ADMIN')}">
                                <button class="btn btn-danger btn-sm btn-remove-quote" value="${quote.quoteId}"><span class="glyphicon glyphicon-remove"/></button>
                                <button class="btn btn-default btn-sm btn-edit-quote" 
                                        data-toggle="modal" 
                                        data-target="#editQuote"
                                        data-quoteId ="${quote.quoteId}"
                                        data-characterId ="${quote.characterId}"
                                        data-note="${quote.quoteNote}"
                                        data-text="${quote.quoteText}"
                                        data-characterName="${quote.characterName}"
                                        ><span class="glyphicon glyphicon-pencil"/></button>
                            </c:if>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${(!empty sessionScope.user) && (sessionScope.user.privilege == 'ADMIN')}">
        <button class="btn btn-primary pull-right" data-toggle="modal" data-target="#addQuote"><span class ="glyphicon glyphicon-plus"></span> Ajouter une réplique</button>
    </c:if>
</div>

<!-- Modal Ajout quote
Envoie une requete avec les parametres suivants :
-->
<div id="addQuote" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Ajouter Un Réplique</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="addQuote" method="POST">
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="characterId">Personnage : </label>
                        <div class="col-sm-6">
                            <select class="select-selectize-createEnable" name="characterId" placeholder="Entrez le nom du personnage...">
                                <option disabled selected value> -- Character name -- </option>
                                <c:forEach  var="character" items="${apparitionList}">
                                    <option value="${character.characterId}">${character.characterName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="quoteText">Réplique : </label>
                        <div class="col-sm-6">
                            <textarea class="form-control" name="quoteText"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="note">Commentaire : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="quoteNote">
                        </div>
                    </div>
                    <input type="hidden" name="episodeId" value="${episode.episodeId}">
                    <div class="modal-footer">
                        <button type="reset" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-default">Valider</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal Edit quote
Envoie une requete avec les parametres suivants :
-->
<div id="editQuote" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Editer Une Réplique</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/episode/updateQuote" method="POST">
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="characterId">Personnage : </label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" id="editQuoteCharacterName" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="quoteText">Réplique : </label>
                        <div class="col-sm-6">
                            <textarea class="form-control" name="quoteText" id="editQuoteText"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="note">Commentaire : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="quoteNote" id="editQuoteNote">
                        </div>
                    </div>
                    <input type="hidden" name="episodeId" value="${episode.episodeId}">
                    <input type="hidden" name="quoteId" id="editQuoteId">
                    <input type="hidden" name="characterId" id="editQuoteCharacterId">
                    <div class="modal-footer">
                        <button type="reset" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-default">Valider</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>