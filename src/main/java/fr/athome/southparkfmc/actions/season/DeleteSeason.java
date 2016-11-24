/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.season;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.SeasonDao;
import fr.athome.southparkfmc.servlets.SeasonController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruce
 */
public class DeleteSeason implements Action{
    
    private DaoManager daoManager;
    int seasonId;
    
    public DeleteSeason(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        SeasonDao dao = daoManager.getSeasonDao();
        dao.delete(seasonId);
        return "/season/readAll";
    }
    
    private void gatherParameters(HttpServletRequest request){
        seasonId = Integer.valueOf(request.getParameter(SeasonController.PARAM_SEASONID));
    }
    
}
