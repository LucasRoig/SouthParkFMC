/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.episode;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.QuoteDao;
import fr.athome.southparkfmc.servlets.EpisodeController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas
 */
public class RemoveQuote implements Action{
    DaoManager daoManager;
    int quoteId;
    int episodeId;

    public RemoveQuote(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        QuoteDao dao = daoManager.getQuoteDao();
        dao.delete(quoteId);
        request.setAttribute(EpisodeController.PARAM_EPISODEID, episodeId);
        return "read";
    }
    
    private void gatherParameters(HttpServletRequest request){
        this.quoteId = Integer.valueOf(request.getParameter(EpisodeController.PARAM_QUOTE_ID));
    }
}
