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
public class DeleteEpisode implements Action{
    private DaoManager daoManager;
    private int episodeId;
    public DeleteEpisode(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        EpisodeDao dao = daoManager.getEpisodeDao();
        dao.delete(episodeId);
        return "/season/readAll";
    }
 
    private void gatherParameters(HttpServletRequest request){
        episodeId = Integer.valueOf(request.getParameter(EpisodeController.PARAM_EPISODEID));
    }
}
