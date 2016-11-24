/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.quote;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.QuoteDao;
import fr.athome.southparkfmc.servlets.QuoteController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruce
 */
public class UpdateQuote implements Action{
    
    DaoManager daoManager;
    int quoteId;
    int episodeId;
    int characterId;
    String quoteText;
    String quoteNote;

    public UpdateQuote(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        if(this.validateParameters()){
            QuoteDao dao = this.daoManager.getQuoteDao();
            dao.update(quoteId, episodeId, characterId, quoteText, quoteNote);
            
        }else{
            request.setAttribute(QuoteController.PARAM_ERROR, "Paramètres invalides");
            System.err.println("Parametres invalides");
        }
        request.setAttribute(QuoteController.PARAM_QUOTEID, quoteId);
        return "read?quoteId=" + this.quoteId;
    }
    
    private void gatherParameters(HttpServletRequest request){
        this.quoteId = Integer.valueOf(request.getParameter(QuoteController.PARAM_QUOTEID));
        this.episodeId = Integer.valueOf(request.getParameter(QuoteController.PARAM_EPISODEID));
        this.characterId = Integer.valueOf(request.getParameter(QuoteController.PARAM_CHARACTERID));
        this.quoteText = request.getParameter(QuoteController.PARAM_QUOTE_TEXT);
        this.quoteNote = request.getParameter(QuoteController.PARAM_QUOTE_NOTE);
    }
    
    private boolean validateParameters(){
        if(quoteText.isEmpty()){
            return false;
        }
        return true;
    }
    
}
