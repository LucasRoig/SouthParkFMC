/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.character;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.CharacterDao;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.model.Apparition;
import fr.athome.southparkfmc.servlets.CharacterController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author utilisateur
 */
public class ReadApparition implements Action{
    private int characterId;
    DaoManager daoManager; 
   
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        CharacterDao dao = this.daoManager.getCharacterDao();
        try {
            List<Apparition> apparitionList = dao.findApparitions(characterId);
            request.setAttribute(CharacterController.PARAM_APPARITIONS, apparitionList);
            return "readCharacter.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "404.jsp";
        
    }
    
    private void gatherParameters(HttpServletRequest request){
        this.characterId = Integer.valueOf(request.getParameter(CharacterController.PARAM_CHARACTERID));
    }
}
