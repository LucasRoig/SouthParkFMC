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
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruce
 */
public class CreateCharacter implements Action{
    
    DaoManager daoManager;
    String characterName; 

    public CreateCharacter(DaoManager daoManager) {
        this.daoManager = daoManager;
    }
    
    

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        CharacterDao characterDao = daoManager.getCharacterDao();
        int characterId = characterDao.create(characterName);
        
        response.setContentType("application/json");
        String json;
        
        if(characterId != -1){
            //Pas d'erreur
            json = "{\"result\":true,\"characterId\":" + characterId + "}";
        }else{
            json = "{\"result\":false,\"characterId\":" + characterId + "}";
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
        this.characterName = request.getParameter(CharacterController.PARAM_CHARACTER_NAME);
    }
}
