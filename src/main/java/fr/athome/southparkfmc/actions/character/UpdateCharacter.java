/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.character;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.CharacterDao;
import fr.athome.southparkfmc.servlets.CharacterController;
import fr.athome.southparkfmc.servlets.EpisodeController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruce
 */
public class UpdateCharacter implements Action{
    
    DaoManager daoManager;
    int characterId;
    String characterName;
    String background;
    
    public UpdateCharacter (DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        if(this.validateParameters()){
            CharacterDao dao = this.daoManager.getCharacterDao();
            dao.update(characterId, characterName, background);
            
        }else{
            request.setAttribute(EpisodeController.PARAM_ERROR, "Paramètres invalides");
            System.err.println("Parametres invalides");
        }
        request.setAttribute(CharacterController.PARAM_CHARACTERID, characterId);
        return "read?characterId=" + this.characterId;
    }
    
    private void gatherParameters(HttpServletRequest request){
        this.characterId = Integer.valueOf(request.getParameter(CharacterController.PARAM_CHARACTERID));
        this.characterName = request.getParameter(CharacterController.PARAM_CHARACTER_NAME);
        this.background = request.getParameter(CharacterController.PARAM_BACKGROUND);
    }
    
    private boolean validateParameters(){
        if(characterName.isEmpty()){
            return false;
        }
        return true;
    }
}
