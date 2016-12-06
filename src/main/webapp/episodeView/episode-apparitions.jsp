<%-- 
    Document   : episode-personnages
    Created on : 9 nov. 2016, 23:18:44
    Author     : Lucas
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="personnage" class="tab-pane fade in">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Nom</th>
                <th>Role</th>
                <th>Commentaire</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="apparition" items="${apparitionList}">
                <tr>
                    <td class="col-md-2"><a href="/character/read?characterId=${apparition.characterId}">${apparition.characterName}</a></td>
                    <td class="col-md-2">${apparition.role}</td>
                    <td class="col-md-6">${apparition.note}</td>
                    <td class="col-md-2">
                        <div class="btn-group pull-right">
                            <c:if test="${(!empty sessionScope.user) && (sessionScope.user.privilege == 'ADMIN')}">
                                <button class="btn btn-danger btn-sm btn-remove-apparition"
                                        value="${apparition.characterId}" ><span class="glyphicon glyphicon-remove"/></button>
                                <button class="btn btn-default btn-sm btn-edit-apparition"data-toggle="modal" 
                                        data-target="#editApparition"
                                        data-characterid="${apparition.characterId}"
                                        data-charactername="${apparition.characterName}"
                                        data-roleid="${apparition.roleId}"
                                        data-note="${apparition.note}"><span class="glyphicon glyphicon-pencil"/></button>
                            </c:if>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${(!empty sessionScope.user) && (sessionScope.user.privilege == 'ADMIN')}">
        <button class="btn btn-primary pull-right" data-toggle="modal" data-target="#addApparition"><span class ="glyphicon glyphicon-plus"></span> Ajouter un personnage</button>
    </c:if>
</div>
<!-- Modal Ajout apparition
Envoie une requete avec les parametres suivants :
characterId
roleId
note
episodeId
-->
<div id="addApparition" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Ajouter Un Personnage</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="addApparition" method="POST">
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="characterId">Personnage : </label>
                        <div class="col-sm-6">
                            <select class="select-selectize-createEnable" name="characterId" placeholder="Entrez le nom du personnage...">
                                <option disabled selected value> -- Nom du personnage -- </option>
                                <c:forEach  var="character" items="${allCharactersList}">
                                    <option value="${character.characterId}">${character.characterName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="roleId">Rôle : </label>
                        <div class="col-sm-6">
                            <select name="roleId" class="select-selectize-createDisable" placeholder="Sélectionnez un rôle...">
                                <option disabled selected value> -- Sélectionnez un rôle -- </option>
                                <c:forEach  var="role" items="${allRolesList}">
                                    <option value="${role.roleId}">${role.roleName}</option>
                                </c:forEach>
                            </select>
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

<!-- Modal Ajout apparition
Envoie une requete avec les parametres suivants :
characterId
roleId
note
episodeId
-->
<div id="editApparition" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Editer Une Apparition</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/episode/updateApparition" method="POST">
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="editApparitionCharacterName">Personnage : </label>
                        <div class="col-sm-6">
                            <input type="text" id="editApparitionCharacterName" class="form-control" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="roleId">Rôle : </label>
                        <div class="col-sm-6">
                            <select name="roleId" id="editApparitionRoleId" class="select-selectize-createDisable" placeholder="Sélectionnez un rôle...">
                                <option disabled selected value> -- Sélectionnez un rôle -- </option>
                                <c:forEach  var="role" items="${allRolesList}">
                                    <option value="${role.roleId}">${role.roleName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="note">Commentaire : </label>
                        <div class="col-sm-6">
                            <input type="text" id="editApparitionNote" class="form-control" name="note">
                        </div>
                    </div>
                    <input type="hidden" name="characterId" id="editApparitionCharacterId">
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