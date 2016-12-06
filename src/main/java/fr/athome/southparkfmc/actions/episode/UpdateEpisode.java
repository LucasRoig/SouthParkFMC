/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.episode;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.EpisodeDao;
import fr.athome.southparkfmc.servlets.EpisodeController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas
 */
public class UpdateEpisode implements Action{
    DaoManager daoManager;
    int episodeId;
    int productionCode;
    int seasonId;
    String nameVo;
    String nameVf;
    String plot;
    int indexInSeason;

    public UpdateEpisode(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        if(this.validateParameters()){
            EpisodeDao dao = this.daoManager.getEpisodeDao();
            dao.update(episodeId, productionCode, seasonId, nameVo, nameVf, plot, indexInSeason);
            
        }else{
            request.setAttribute(EpisodeController.PARAM_ERROR, "Paramètres invalides");
            System.err.println("Parametres invalides");
        }
        request.setAttribute(EpisodeController.PARAM_EPISODEID, episodeId);
        return "/episode/read?episodeId=" + this.episodeId;
    }
    
    private void gatherParameters(HttpServletRequest request){
        this.episodeId = Integer.valueOf(request.getParameter(EpisodeController.PARAM_EPISODEID));
        this.productionCode = Integer.valueOf(request.getParameter(EpisodeController.PARAM_PRODUCTION_CODE));
        this.seasonId = Integer.valueOf(request.getParameter(EpisodeController.PARAM_SEASON_ID));
        this.nameVo = request.getParameter(EpisodeController.PARAM_NAME_VO);
        this.nameVf = request.getParameter(EpisodeController.PARAM_NAME_VF);
        this.plot = request.getParameter(EpisodeController.PARAM_PLOT);
        this.indexInSeason = Integer.parseInt(request.getParameter(EpisodeController.PARAM_INDEX_IN_SEASON));
    }
    
    private boolean validateParameters(){
        if(nameVo.isEmpty()){
            return false;
        }
        return true;
    }
    
}
