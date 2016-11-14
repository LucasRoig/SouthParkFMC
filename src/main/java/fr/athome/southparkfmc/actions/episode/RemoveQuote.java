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
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas
 */
public class RemoveQuote implements Action{
    DaoManager daoManager;
    int quoteId;

    public RemoveQuote(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        QuoteDao dao = daoManager.getQuoteDao();
        boolean result = dao.delete(quoteId);
        
        response.setContentType("application/json");
        String json;
        if(result){
            //Pas d'erreur
            json = "{\"result\":true,\"quoteId\":" + quoteId + "}";
        }else{
            json = "{\"result\":false,\"quoteId\":" + quoteId + "}";
        }
        try {
            PrintWriter out = response.getWriter();
            out.print(json);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "#";
    }
    
    private void gatherParameters(HttpServletRequest request){
        this.quoteId = Integer.valueOf(request.getParameter(EpisodeController.PARAM_QUOTE_ID));
    }
}
