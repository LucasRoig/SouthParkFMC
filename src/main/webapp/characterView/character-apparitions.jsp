<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="apparition" class="tab-pane fade in">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Nom</th>
                <th>Role</th>
                <th>Episode</th>
                <th>Commentaire</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="apparition" items="${apparitionList}">
                <tr>
                    <td>${apparition.characterName}</td>
                    <td>${apparition.role}</td>
                    <td>${apparition.episodeName}</td>
                    <td>${apparition.note}</td>
                    <td>
                        <div class="btn-group pull-right">
                            <button class="btn btn-danger btn-sm btn-remove-apparition" value="${apparition.characterId}|${apparition.episodeId}"><span class="glyphicon glyphicon-remove"/></button>
                            <button class="btn btn-default btn-sm btn-edit-apparition"data-toggle="modal" 
                                    data-target="#editApparition"
                                    data-characterid="${apparition.characterId}"
                                    data-charactername="${apparition.characterName}"
                                    data-episodeid="${apparition.episodeId}"
                                    data-roleid="${apparition.roleId}"
                                    data-note="${apparition.note}"><span class="glyphicon glyphicon-pencil"/></button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <!--<button class="btn btn-primary pull-right" data-toggle="modal" data-target="#addApparition"><span class ="glyphicon glyphicon-plus"></span> Ajouter une apparition</button>-->

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
                <form class="form-horizontal" action="/character/updateApparition" method="POST">
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
                    <input type="hidden" name="episodeId" id="editApparitionEpisodeId">
                    <div class="modal-footer">
                        <button type="reset" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-default">Valider</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>