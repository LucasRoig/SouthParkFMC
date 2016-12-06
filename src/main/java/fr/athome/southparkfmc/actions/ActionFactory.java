/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions;

import fr.athome.southparkfmc.actions.stat.StatTag;
import fr.athome.southparkfmc.actions.stat.StatMainData;
import fr.athome.southparkfmc.actions.stat.StatCharacter;
import fr.athome.southparkfmc.actions.stat.StatPage;
import fr.athome.southparkfmc.actions.authentication.GetLoginPage;
import fr.athome.southparkfmc.actions.authentication.LoginAction;
import fr.athome.southparkfmc.actions.authentication.LogoutAction;
import fr.athome.southparkfmc.actions.character.CreateCharacter;
import fr.athome.southparkfmc.actions.character.ReadAllCharacter;
import fr.athome.southparkfmc.actions.character.ReadCharacter;
import fr.athome.southparkfmc.actions.character.UpdateCharacter;
import fr.athome.southparkfmc.actions.episode.AddApparition;
import fr.athome.southparkfmc.actions.episode.AddQuote;
import fr.athome.southparkfmc.actions.episode.AddTag;
import fr.athome.southparkfmc.actions.episode.CreateEpisode;
import fr.athome.southparkfmc.actions.episode.RemoveApparition;
import fr.athome.southparkfmc.actions.episode.RemoveQuote;
import fr.athome.southparkfmc.actions.episode.RemoveTag;
import fr.athome.southparkfmc.actions.episode.UpdateActiveTag;
import fr.athome.southparkfmc.actions.episode.UpdateApparition;
import fr.athome.southparkfmc.actions.episode.UpdateQuote;
import fr.athome.southparkfmc.actions.season.ReadSeason;
import fr.athome.southparkfmc.actions.tag.CreateTag;
import fr.athome.southparkfmc.actions.episode.ReadEpisode;
import fr.athome.southparkfmc.actions.episode.UpdateEpisode;
import fr.athome.southparkfmc.actions.search.SearchEpisode;
import fr.athome.southparkfmc.actions.season.CreateSeason;
import fr.athome.southparkfmc.actions.tag.ReadAllTag;
import fr.athome.southparkfmc.actions.tag.ReadTag;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        actions.put("POST/episode/create", new CreateEpisode(this.daoManager));
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
        actions.put("GET/tag/readAll", new ReadAllTag(this.daoManager));
        actions.put("GET/tag/read", new ReadTag(this.daoManager));
        actions.put("GET/season/read", new ReadSeason(this.daoManager));
        actions.put("POST/season/create", new CreateSeason(this.daoManager));
        actions.put("POST/character/create", new CreateCharacter(this.daoManager));
        actions.put("GET/character/readAll", new ReadAllCharacter(this.daoManager));
        actions.put("GET/character/read", new ReadCharacter(this.daoManager));
        actions.put("POST/character/update", new UpdateCharacter(this.daoManager));
        //Gestion des apparitions côté personnage
        actions.put("POST/character/updateApparition", new fr.athome.southparkfmc.actions.character.UpdateApparition(this.daoManager));
        actions.put("POST/character/removeApparition", new fr.athome.southparkfmc.actions.character.RemoveApparition(this.daoManager));
        actions.put("GET/authentication/login", new GetLoginPage());
        actions.put("POST/authentication/login", new LoginAction(this.daoManager));
        actions.put("GET/authentication/logout", new LogoutAction());
        actions.put("GET/episode/search", new SearchEpisode(this.daoManager));
        //Gestion des statistiques
        actions.put("GET/stat/", new StatPage());
        actions.put("GET/stat/mainData", new StatMainData(this.daoManager));
        actions.put("GET/stat/character", new StatCharacter(this.daoManager));
        actions.put("GET/stat/tag", new StatTag(this.daoManager));
    }

    public Action getAction(HttpServletRequest request){
        Action action = actions.get(request.getMethod()+request.getServletPath()+request.getPathInfo());
        if(action == null) action = new Action() {
            @Override
            public String execute(HttpServletRequest request, HttpServletResponse response) {
                return "404.jsp";
            }
        };
        return action;
    }
}
