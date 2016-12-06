/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.search;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.SearchDao;
import fr.athome.southparkfmc.model.SearchCharacterResult;
import fr.athome.southparkfmc.model.SearchEpisodeResult;
import fr.athome.southparkfmc.model.SearchTagResult;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas
 */
public class SearchEpisode implements Action {

    DaoManager dao;
    String text;

    public SearchEpisode(DaoManager dao) {
        this.dao = dao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        request.setAttribute("text", text);
        SearchDao searchDao = dao.getSearchDao();
        text = text.toLowerCase();
        text = text.trim();
        text = text.replaceAll("  ", " ");
        String textOu = text.replaceAll(" ", "|");
        String textEt = text.replaceAll(" ", "&");

        try {
            List<SearchEpisodeResult> resultEpisodeEt = searchDao.searchEpisode(textEt);
            List<SearchEpisodeResult> resultEpisodeOu = searchDao.searchEpisode(textOu);
            for (SearchEpisodeResult ou : resultEpisodeOu) {
                boolean trouve = false;
                for (SearchEpisodeResult et : resultEpisodeEt) {
                    if (et.getEpisodeId() == ou.getEpisodeId()) {
                        trouve = true;
                        break;
                    }
                }
                if (!trouve) {
                    resultEpisodeEt.add(ou);
                }
            }
            resultEpisodeEt.sort(new Comparator<SearchEpisodeResult>() {
                @Override
                public int compare(SearchEpisodeResult o1, SearchEpisodeResult o2) {
                    if (o1.getScore() < o2.getScore()) {
                        return 1;
                    }
                    if (o1.getScore() > o2.getScore()) {
                        return -1;
                    }
                    return 0;
                }
            });
            request.setAttribute("episodeResults", resultEpisodeEt);

            List<SearchCharacterResult> resultCharacEt = searchDao.searchCharacter(textEt);
            List<SearchCharacterResult> resultCharacOu = searchDao.searchCharacter(textOu);
            for (SearchCharacterResult ou : resultCharacOu) {
                boolean trouve = false;
                for (SearchCharacterResult et : resultCharacEt) {
                    if (et.getCharacterId() == ou.getCharacterId()) {
                        trouve = true;
                        break;
                    }
                }
                if (!trouve) {
                    resultCharacEt.add(ou);
                }
            }
            resultCharacEt.sort(new Comparator<SearchCharacterResult>() {
                @Override
                public int compare(SearchCharacterResult o1, SearchCharacterResult o2) {
                    if (o1.getScore() < o2.getScore()) {
                        return 1;
                    }
                    if (o1.getScore() > o2.getScore()) {
                        return -1;
                    }
                    return 0;
                }
            });
            request.setAttribute("characterResults", resultCharacEt);

            List<SearchTagResult> resultTagEt = searchDao.searchTag(textEt);
            List<SearchTagResult> resultTagOu = searchDao.searchTag(textOu);
            for (SearchTagResult ou : resultTagOu) {
                boolean trouve = false;
                for (SearchTagResult et : resultTagEt) {
                    if (et.getTagId() == ou.getTagId()) {
                        trouve = true;
                        break;
                    }
                }
                if (!trouve) {
                    resultTagEt.add(ou);
                }
            }
            resultTagEt.sort(new Comparator<SearchTagResult>() {
                @Override
                public int compare(SearchTagResult o1, SearchTagResult o2) {
                    if (o1.getScore() < o2.getScore()) {
                        return 1;
                    }
                    if (o1.getScore() > o2.getScore()) {
                        return -1;
                    }
                    return 0;
                }
            });
            request.setAttribute("tagResults", resultTagEt);

        } catch (SQLException ex) {
            Logger.getLogger(SearchEpisode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/search.jsp";
    }

    public void gatherParameters(HttpServletRequest request) {
        this.text = request.getParameter("text");
    }

}
