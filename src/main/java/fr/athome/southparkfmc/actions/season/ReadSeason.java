/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.season;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.SeasonDao;
import fr.athome.southparkfmc.model.Episode;
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
public class ReadSeason implements Action{
    DaoManager daoManager;
    int selectedSeason;
    
    public ReadSeason(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        SeasonDao dao = daoManager.getSeasonDao();
        
        try {
            int nbSeason = dao.countSeason();
            if (nbSeason < selectedSeason || selectedSeason <= 0) selectedSeason=1;
            List<Episode> episodeList = dao.findEpisodeInSeason(selectedSeason);
            episodeList.sort(new Comparator<Episode>() {
                @Override
                public int compare(Episode o1, Episode o2) {
                    if(o1.getIndexInSeason() > o2.getIndexInSeason()) return 1;
                    if(o1.getIndexInSeason() < o2.getIndexInSeason()) return -1;
                    return 0;
                }
            });
            
            request.setAttribute("episodeList", episodeList);
            request.setAttribute("nbSeason", nbSeason);
            request.setAttribute("selectedSeason", selectedSeason);
        } catch (SQLException ex) {
            Logger.getLogger(ReadSeason.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "/readSeason.jsp";
        
    }
    
    public void gatherParameters(HttpServletRequest request){
        try {
            selectedSeason = Integer.valueOf(request.getParameter("selectedSeason"));
        } catch (Exception e) {
            selectedSeason = 1;
        }
    }
}
