/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.episode;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.EpisodeDao;
import fr.athome.southparkfmc.model.Episode;
import fr.athome.southparkfmc.servlets.EpisodeController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas
 */
public class ReadEpisode implements Action{

    DaoManager daoManager;
    int episodeId;

    public ReadEpisode(DaoManager daoManager) {
        this.daoManager = daoManager;
    }
    
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        EpisodeDao dao = this.daoManager.getEpisodeDao();
        try {
            Episode selectedEpisode = dao.find(episodeId);
            request.setAttribute(EpisodeController.PARAM_SELECTED_EPISODE, selectedEpisode);
            return "readEpisode.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "404";
    }
    
    private void gatherParameters(HttpServletRequest request){
        this.episodeId = Integer.valueOf(request.getParameter(EpisodeController.PARAM_EPISODEID));
    }
    
}
