/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.character;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.CharacterDao;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.servlets.CharacterController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruce
 */
public class DeleteCharacter implements Action{
    
    DaoManager daoManager;
    int characterId;
    
    public DeleteCharacter(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        CharacterDao dao = daoManager.getCharacterDao();
        dao.delete(characterId);
        return "/character/readAll";
    }
 
    private void gatherParameters(HttpServletRequest request){
        characterId = Integer.valueOf(request.getParameter(CharacterController.PARAM_CHARACTERID));
    }
}
