/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.character;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.CharacterDao;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.RoleDao;
import fr.athome.southparkfmc.model.Apparition;
import fr.athome.southparkfmc.model.CharacterEntity;
import fr.athome.southparkfmc.model.Role;
import fr.athome.southparkfmc.servlets.CharacterController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruce
 */
public class ReadCharacter implements Action{
    
    DaoManager daoManager;
    int characterId;
    
    public ReadCharacter (DaoManager daoManager) {
        this.daoManager = daoManager;
    }
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        CharacterDao characterDao = this.daoManager.getCharacterDao();
        RoleDao roleDao= this.daoManager.getRoleDao();
        
        try {
            CharacterEntity selectedCharacter = characterDao.find(characterId);
            List<Apparition> apparitionList = characterDao.findApparitions(characterId);
            List<Role> allRolesList = roleDao.findAll();
            request.setAttribute("allRolesList", allRolesList);
            request.setAttribute(CharacterController.PARAM_APPARITIONS, apparitionList);
            request.setAttribute(CharacterController.PARAM_SELECTED_CHARACTER, selectedCharacter);
            return "readCharacter.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "404";
    }
    
    private void gatherParameters(HttpServletRequest request){
        this.characterId = Integer.valueOf(request.getParameter(CharacterController.PARAM_CHARACTERID));
    }
    
}
