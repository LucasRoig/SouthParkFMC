/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.character;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.CharacterDao;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.model.CharacterEntity;
import fr.athome.southparkfmc.servlets.CharacterController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruce
 */
public class ReadAllCharacter implements Action{
    
    DaoManager daoManager;
    
    public ReadAllCharacter(DaoManager daoManager) {
        this.daoManager = daoManager;
    }
    
     public String execute(HttpServletRequest request, HttpServletResponse response) {
        CharacterDao dao = this.daoManager.getCharacterDao();
        try {
            List<CharacterEntity> characters = dao.findAll();
            request.setAttribute(CharacterController.PARAM_CHARACTERS, characters);
            return "readAllCharacters.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "404";
    }
}
