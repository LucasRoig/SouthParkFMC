<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="background" class="tab-pane fade in active">
    <div class="row">
        <div class="col-sm-12">
            <div class="form-switch">
                <div class="front">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-sm-10"><h4>Background</h4></div>
                                <c:if test="${(!empty sessionScope.user) && (sessionScope.user.privilege == 'ADMIN')}">
                                    <div class="col-sm-2"><button class="btn btn-primary pull-right btn-switch"><span class ="glyphicon glyphicon-pencil"></span></button></div>
                                </c:if>
                            </div>
                        </div>
                        <div class="panel-body">
                             <% pageContext.setAttribute("newLineChar", "\n"); %> 
                            <c:forEach var="p" items="${fn:split(character.background, newLineChar)}">
                                <p>${p}</p>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class ="back">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4>Editer le background</h4>
                        </div>
                        <div class="panel-body">
                            <form method="POST" action="/character/update">
                                <div class="form-group">
                                    <textarea name="background" class="form-control">${character.background}</textarea>
                                </div>
                                <div class="form-group">
                                    <button type="reset" class="btn btn-primary btn-switch" id="btAnnulerFormEditInfos">Annuler</button> 
                                    <button type="submit" class="btn btn-primary">Valider</button> 
                                </div>
                                <input type="hidden" name="characterId" class="form-control" value="${character.characterId}">
                                <input type="hidden" name="characterName" class="form-control" value="${character.characterName}">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>