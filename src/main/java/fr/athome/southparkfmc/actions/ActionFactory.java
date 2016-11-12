/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions;

import fr.athome.southparkfmc.actions.episode.ReadEpisode;
import fr.athome.southparkfmc.actions.episode.UpdateEpisode;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Lucas
 */
public class ActionFactory {
    private DaoManager daoManager;
    HashMap<String, Action> actions = new HashMap<>();

    public ActionFactory(DaoManager daoManager) {
        this.daoManager = daoManager;
        actions.put("GET/episode/read", new ReadEpisode(this.daoManager));
        actions.put("POST/episode/update", new UpdateEpisode(this.daoManager));
    }
    
    public Action getAction(HttpServletRequest request){
        return actions.get(request.getMethod()+request.getServletPath()+request.getPathInfo());
    }
}
