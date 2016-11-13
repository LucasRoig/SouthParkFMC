<%-- 
    Document   : episode-repliques
    Created on : 9 nov. 2016, 23:16:20
    Author     : Lucas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="replique" class="tab-pane fade in">
    <table class="table table-striped" id="tableQuote">
        <thead>
            <tr>
                <th>Personnage</th>
                <th>Texte</th>
                <th>Commentaire</th>
                <th>Boutons</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="quote" items="${quoteList}">
                <tr>
                    <td>${quote.characterName}</td>
                    <td>${quote.quoteText}</td>
                    <td>${quote.quoteNote}</td>
                    <td></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <button class="btn btn-primary pull-right" data-toggle="modal" data-target="#addQuote"><span class ="glyphicon glyphicon-plus"></span> Ajouter une r�plique</button>
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
                <h4 class="modal-title">Ajouter Une R�plique</h4>
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
                        <label class="control-label col-sm-4" for="quoteText">R�plique : </label>
                        <div class="col-sm-6">
                            <textarea class="form-control" name="quoteText"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="note">Commentaire : </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="note">
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