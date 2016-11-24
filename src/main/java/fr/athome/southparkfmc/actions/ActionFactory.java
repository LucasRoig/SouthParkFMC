/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions;

import fr.athome.southparkfmc.actions.episode.AddApparition;
import fr.athome.southparkfmc.actions.episode.AddQuote;
import fr.athome.southparkfmc.actions.episode.AddTag;
import fr.athome.southparkfmc.actions.episode.ReadEpisode;
import fr.athome.southparkfmc.actions.episode.RemoveApparition;
import fr.athome.southparkfmc.actions.episode.RemoveQuote;
import fr.athome.southparkfmc.actions.episode.RemoveTag;
import fr.athome.southparkfmc.actions.episode.UpdateActiveTag;
import fr.athome.southparkfmc.actions.episode.UpdateApparition;
import fr.athome.southparkfmc.actions.episode.UpdateEpisode;
import fr.athome.southparkfmc.actions.episode.UpdateQuote;
import fr.athome.southparkfmc.actions.season.ReadSeason;
import fr.athome.southparkfmc.actions.tag.CreateTag;
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
        actions.put("POST/episode/addApparition", new AddApparition(this.daoManager));
        actions.put("POST/episode/addTag", new AddTag(this.daoManager));
        actions.put("POST/episode/addQuote", new AddQuote(this.daoManager));
        actions.put("POST/episode/removeQuote", new RemoveQuote(this.daoManager));
        actions.put("POST/episode/removeTag", new RemoveTag(this.daoManager));
        actions.put("POST/episode/removeApparition", new RemoveApparition(this.daoManager));
        actions.put("POST/episode/updateActiveTag", new UpdateActiveTag(this.daoManager));
        actions.put("POST/episode/updateApparition", new UpdateApparition(this.daoManager));
        actions.put("POST/episode/updateQuote", new UpdateQuote(this.daoManager));
        actions.put("POST/tag/create", new CreateTag(this.daoManager));
        actions.put("GET/season/read", new ReadSeason(this.daoManager));
    }
    
    public Action getAction(HttpServletRequest request){
        System.out.println(request.getMethod()+request.getServletPath()+request.getPathInfo());
        return actions.get(request.getMethod()+request.getServletPath()+request.getPathInfo());
    }
}
