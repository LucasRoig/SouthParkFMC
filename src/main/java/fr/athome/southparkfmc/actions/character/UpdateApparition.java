
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.character;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.EpisodeDao;
import fr.athome.southparkfmc.servlets.EpisodeController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas
 */
public class UpdateApparition implements Action{
    DaoManager daoManager;
    int episodeId;
    int characterId;
    int roleId;
    String note;
    
    public UpdateApparition(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        gatherParameters(request);
        EpisodeDao dao = daoManager.getEpisodeDao();
        dao.updateApparition(episodeId, characterId, roleId, note);
        request.setAttribute(EpisodeController.PARAM_EPISODEID, episodeId);
        return "read?characterId=" + characterId;
    }
    
    private void gatherParameters(HttpServletRequest request){
        this.episodeId = Integer.valueOf(request.getParameter(EpisodeController.PARAM_EPISODEID));
        this.characterId = Integer.valueOf(request.getParameter(EpisodeController.PARAM_CHARACTERID));
        this.roleId = Integer.valueOf(request.getParameter(EpisodeController.PARAM_ROLE_ID));
        this.note = request.getParameter(EpisodeController.PARAM_NOTE);
    }
}
