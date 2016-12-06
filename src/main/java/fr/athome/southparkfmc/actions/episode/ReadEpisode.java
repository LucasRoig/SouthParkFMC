/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.episode;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.CharacterDao;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.EpisodeDao;
import fr.athome.southparkfmc.dataaccess.RoleDao;
import fr.athome.southparkfmc.dataaccess.TagDao;
import fr.athome.southparkfmc.model.ActiveTag;
import fr.athome.southparkfmc.model.Apparition;
import fr.athome.southparkfmc.model.CharacterEntity;
import fr.athome.southparkfmc.model.Episode;
import fr.athome.southparkfmc.model.Quote;
import fr.athome.southparkfmc.model.Role;
import fr.athome.southparkfmc.model.Tag;
import fr.athome.southparkfmc.servlets.EpisodeController;
import java.util.List;
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
        EpisodeDao episodeDao = this.daoManager.getEpisodeDao();
        CharacterDao characterDao = this.daoManager.getCharacterDao();
        RoleDao roleDao = this.daoManager.getRoleDao();
        TagDao tagDao = this.daoManager.getTagDao();
        try {
            Episode selectedEpisode = episodeDao.find(episodeId);
            if(selectedEpisode == null) return "404.jsp";
            List<Apparition> apparitionList = episodeDao.findApparition(episodeId);
            List<Quote> quoteList = episodeDao.findQuotes(episodeId);
            List<ActiveTag> tagList = episodeDao.findActiveTag(episodeId);
            List<CharacterEntity> allCharactersList = characterDao.findAll();
            List<Role> allRolesList = roleDao.findAll();
            List<Tag> allTagsList = tagDao.findAll();

            request.setAttribute(EpisodeController.PARAM_SELECTED_EPISODE, selectedEpisode);
            request.setAttribute("apparitionList",apparitionList);
            request.setAttribute("quoteList",quoteList);
            request.setAttribute("tagList",tagList);
            request.setAttribute("allCharactersList", allCharactersList);
            request.setAttribute("allRolesList", allRolesList);
            request.setAttribute("allTagsList", allTagsList);
            return "readEpisode.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "404.jsp";
    }

    private void gatherParameters(HttpServletRequest request){
        this.episodeId = Integer.valueOf(request.getParameter(EpisodeController.PARAM_EPISODEID));
    }

}
