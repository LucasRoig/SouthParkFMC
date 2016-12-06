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
public class AddQuote implements Action{
    DaoManager daoManager;
    int episodeId;
    int characterId;
    String quoteText;
    String quoteNote;

    public AddQuote(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        if(this.validateParameters()){
            QuoteDao dao = daoManager.getQuoteDao();
            if(characterId != -1)
                dao.createWithCharacter(episodeId, characterId, quoteText, quoteNote);
            else
                dao.createWithoutCharacter(episodeId, quoteText, quoteNote);
        }else{
            System.err.println("paramètres non valides");
            request.setAttribute(EpisodeController.PARAM_ERROR, "Paramètres non valides");
        }
        request.setAttribute(EpisodeController.PARAM_SELECTED_EPISODE, episodeId);
        return"/episode/read?episodeId=" + this.episodeId;
    }

    private void gatherParameters(HttpServletRequest request){
        this.episodeId = Integer.valueOf(request.getParameter(EpisodeController.PARAM_EPISODEID));
        try {
            this.characterId = Integer.valueOf(request.getParameter(EpisodeController.PARAM_CHARACTERID));
        } catch (Exception e) {
            //Pas de personnage spécifié.
            this.characterId = -1;
        }

        this.quoteText = request.getParameter(EpisodeController.PARAM_QUOTE_TEXT);
        this.quoteNote = request.getParameter(EpisodeController.PARAM_QUOTE_NOTE);
    }

    private boolean validateParameters(){
        if(quoteText.isEmpty()){
            return false;
        }
        return true;
    }
}
