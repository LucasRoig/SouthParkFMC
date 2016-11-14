<%@ page pageEncoding="UTF-8" %>

<div id="tag" class="tab-pane fade in">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Nom</th>
                <th>Commentaire</th>
                <th>Boutons</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="tag" items="${tagList}">
            <tr>
                <td>${tag.tagName}</td>
                <td>${tag.note}</td>
                <td><button class="btn btn-danger btn-sm btn-remove-tag" value="${tag.tagId}"><span class="glyphicon glyphicon-remove"/></button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button class="btn btn-primary pull-right" data-toggle="modal" data-target="#addTag"><span class ="glyphicon glyphicon-plus"></span> Ajouter un tag</button>
</div>
                
<!-- Modal Ajout tag
Envoie une requete avec les parametres suivants :
tagId
note
episodeId
-->
<div id="addTag" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Ajouter Un Tag</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="addTag" method="POST">
                    <div class="form-group">
                        <label class="control-label col-sm-4" for="tagId">Tag : </label>
                        <div class="col-sm-6">
                            <select class="select-selectize-createEnable" name="tagId" placeholder="Entrez le nom du tag...">
                                <option disabled selected value> -- Nom du tag -- </option>
                                <c:forEach  var="tag" items="${allTagsList}">
                                    <option value="${tag.tagId}">${tag.tagName}</option>
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