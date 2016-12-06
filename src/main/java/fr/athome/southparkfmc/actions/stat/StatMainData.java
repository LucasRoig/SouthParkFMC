/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.stat;

import com.google.gson.Gson;
import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.CharacterDao;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.EpisodeDao;
import fr.athome.southparkfmc.dataaccess.SeasonDao;
import fr.athome.southparkfmc.dataaccess.TagDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruce
 */
public class StatMainData implements Action {
    DaoManager daoManager;

    public StatMainData(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/json");
            //Présence Randy Marsh : nbEpisodeRandy, totalEpisode
            //StarWars vs StarTrek : nbTagSW, nbTagST
            //Episodes par saison : nb Saison, nbEpisode
            SeasonDao seasonDao = daoManager.getSeasonDao();
            EpisodeDao episodeDao = daoManager.getEpisodeDao();
            CharacterDao characterDao = daoManager.getCharacterDao();
            TagDao tagDao = daoManager.getTagDao();
            int nbSeason = seasonDao.countSeason();
            int[] nbEpisode = new int[nbSeason];
            int totalEpisode = 0;
            int[][] epParSaison = new int[nbSeason][2];
             for(int i=0; i<nbSeason; i++){
                nbEpisode[i] = seasonDao.findEpisodeInSeason(i+1).size();
                totalEpisode += nbEpisode[i];
                epParSaison[i][0] = i+1;
                epParSaison[i][1] = nbEpisode[i];
            }
            int idRandy = characterDao.findByCharacterName("Randy Marsh").get(0).getCharacterId();
            int nbEpisodeRandy = characterDao.findApparitions(idRandy).size();
            int SWId = tagDao.findByTagName("Star Wars").get(0).getTagId();
            int nbTagSW = tagDao.findUses(SWId).size();
            int STId = tagDao.findByTagName("Star Trek").get(0).getTagId();
            int nbTagST = tagDao.findUses(STId).size();
            Map<String,Object> map = new HashMap<>();
            map.put("totalEpisode", totalEpisode);
            map.put("nbEpisode", nbEpisode);
            map.put("nbRandy", nbEpisodeRandy);
            map.put("nbTagSW", nbTagSW);
            map.put("nbTagST", nbTagST);
            map.put("epParSaison", epParSaison);
            String json = new Gson().toJson(map);
            PrintWriter out = response.getWriter();
            out.print(json);
            out.close();
        } catch (SQLException ex) {
            Logger.getLogger(StatMainData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StatMainData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "#";
    }
    
}
