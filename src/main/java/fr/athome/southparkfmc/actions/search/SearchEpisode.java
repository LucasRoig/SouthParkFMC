/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.search;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.SearchDao;
import fr.athome.southparkfmc.model.SearchResult;
import java.sql.SQLException;
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
public class SearchEpisode implements Action{
    DaoManager dao;
    String text;
    public SearchEpisode(DaoManager dao) {
        this.dao = dao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        SearchDao searchDao = dao.getSearchDao();
        text = text.toLowerCase();
        text = text.trim();
        text = text.replaceAll("  ", " ");
        String textOu = text.replaceAll(" ", "|");
        String textEt = text.replaceAll(" ", "&");
        
        try {
            List<SearchResult> resultEt = searchDao.search(textEt);
            List<SearchResult> resultOu = searchDao.search(textOu);
            for(SearchResult ou : resultOu){
                boolean trouve = false;
                for(SearchResult et : resultEt){
                    if(et.getEpisodeId() == ou.getEpisodeId()){
                        trouve = true;
                        break;
                    }
                }
                if(!trouve){
                    resultEt.add(ou);
                }
            }
            resultEt.sort(new Comparator<SearchResult>() {
                @Override
                public int compare(SearchResult o1, SearchResult o2) {
                    if(o1.getScore() < o2.getScore()) return 1;
                    if(o1.getScore() > o2.getScore()) return -1;
                    return 0;
                }
            });
            request.setAttribute("results", resultEt);
        } catch (SQLException ex) {
            Logger.getLogger(SearchEpisode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/search.jsp";
    }
    
    public void gatherParameters(HttpServletRequest request){
        this.text = request.getParameter("text");
    }
    
    
}
