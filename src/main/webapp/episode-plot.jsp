<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="resume" class="tab-pane fade in active">
    <div class="row">
        <div class="col-sm-4">
            <div class="form-switch">
                <div class="front">
                    <div class="list-group panel panel-default" id="panelInfos">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-sm-10"><h4>Informations</h4></div>
                                <div class="col-sm-2"><button class="btn btn-primary pull-right btn-switch" id="editInfos"><span class ="glyphicon glyphicon-pencil"></span></button></div>
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
                                    <input type="number" id="seasonInput" name="seasonId" class="form-control" value="${episode.seasonId}">
                                </div>
                                <div class="form-group">
                                    <label for="indexInSeasonInput">Num�ro dans la saison :</label>
                                    <input type="number" id="indexInSeasonInput" name="indexInSeason" class="form-control" value="${episode.indexInSeason}">
                                </div>
                                <div class="form-group">
                                    <label for="productionCodeInput">Code Production :</label>
                                    <input type="number" id="productionCodeInput" name="productionCode" class="form-control" value="${episode.productionCode}">
                                </div>
                                <div class="form-group">
                                    <label for="nameVFInput">Nom VF :</label>
                                    <input type="text" id="nameVFInput" name="nameVF" class="form-control" value="${episode.nameVF}">
                                </div>
                                <div class="form-group">
                                    <label for="nameVOInput">Nom VO :</label>
                                    <input type="text" id="nameVOInput" name="nameVO" class="form-control" value="${episode.nameVO}">
                                </div>
                                <div class="form-group">
                                    <button type="reset" class="btn btn-primary btn-switch" id="btAnnulerFormEditInfos">Annuler</button> 
                                    <button type="submit" class="btn btn-primary">Valider</button> 
                                </div>
                                <input type="hidden" id="plotInput" name="plot" class="form-control" value="${episode.plot}">
                                <input type="hidden" id="episodeIdInput" name="episodeId" class="form-control" value="${episode.episodeId}">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-sm-10"><h4>Informations</h4></div>
                        <div class="col-sm-2"><button class="btn btn-primary pull-right"><span class ="glyphicon glyphicon-pencil"></span></button></div>
                    </div>
                </div>
                <div class="panel-body" id="plotValue">
                    ${episode.plot}
                </div>
            </div>
        </div>
    </div>
</div>