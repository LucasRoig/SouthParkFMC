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
 * @author Lucas
 */
public class CreateSeason implements Action{
    DaoManager daoManager;
    int seasonId;
    int diffusionYear;
    
    public CreateSeason(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        if(this.validateParameters()){
            SeasonDao dao = this.daoManager.getSeasonDao();
            dao.create(seasonId, diffusionYear);
            
        }else{
            request.setAttribute(SeasonController.PARAM_ERROR, "Paramètres invalides");
            System.err.println("Parametres invalides");
        }
        request.setAttribute(SeasonController.PARAM_SEASONID, seasonId);
        return "/season/read?seasonId=" + this.seasonId;
    }
    
    private void gatherParameters(HttpServletRequest request){
        this.seasonId = Integer.valueOf(request.getParameter(SeasonController.PARAM_SEASONID));
        this.diffusionYear = Integer.valueOf(request.getParameter(SeasonController.PARAM_DIFFUSION_YEAR));
    }
    
    private boolean validateParameters(){
        if(diffusionYear == 0){
            return false;
        }
        return true;
    }
}
