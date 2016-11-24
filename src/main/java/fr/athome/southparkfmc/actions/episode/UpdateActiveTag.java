/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.episode;

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
public class UpdateActiveTag implements Action{
    DaoManager daoManager;
    int episodeId;
    int tagId;
    String note;
    
    public UpdateActiveTag(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        gatherParameters(request);
        EpisodeDao dao = daoManager.getEpisodeDao();
        dao.updateActiveTag(episodeId, tagId, note);
        
        request.setAttribute(EpisodeController.PARAM_EPISODEID, episodeId);
        return "read?episodeId=" + this.episodeId;
    }
    
    private void gatherParameters(HttpServletRequest request){
        this.episodeId = Integer.valueOf(request.getParameter(EpisodeController.PARAM_EPISODEID));
        this.tagId = Integer.valueOf(request.getParameter(EpisodeController.PARAM_TAG_ID));
        this.note = request.getParameter(EpisodeController.PARAM_NOTE);
    }
}
