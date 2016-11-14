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
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas
 */
public class RemoveApparition implements Action{
    DaoManager daoManager;
    int episodeId;
    int characterId;
    
    public RemoveApparition(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        gatherParameters(request);
        EpisodeDao dao = daoManager.getEpisodeDao();
        boolean result = dao.removeApparition(episodeId, characterId);
        
        response.setContentType("application/json");
        String json;
        if(result){
            //Pas d'erreur
            json = "{\"result\":true,\"apparitionId\":" + characterId + "}";
        }else{
            json = "{\"result\":false,\"apparitionId\":" + characterId + "}";
        }
        try {
            PrintWriter out = response.getWriter();
            out.print(json);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "#";
    }
    
    private void gatherParameters(HttpServletRequest request){
        this.episodeId = Integer.valueOf(request.getParameter(EpisodeController.PARAM_EPISODEID));
        this.characterId = Integer.valueOf(request.getParameter(EpisodeController.PARAM_CHARACTERID));
    }
}
