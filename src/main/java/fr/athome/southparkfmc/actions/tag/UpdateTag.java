/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.tag;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.TagDao;
import fr.athome.southparkfmc.servlets.TagController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruce
 */
public class UpdateTag implements Action{
    DaoManager daoManager;
    int tagId;
    String tagName;

    public UpdateTag(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        if(this.validateParameters()){
            TagDao dao = this.daoManager.getTagDao();
            dao.update(tagId, tagName);
            
        }else{
            request.setAttribute(TagController.PARAM_ERROR, "Paramètres invalides");
            System.err.println("Parametres invalides");
        }
        request.setAttribute(TagController.PARAM_TAGID, tagId);
        return "read?tagId=" + this.tagId;
    }
    
    private void gatherParameters(HttpServletRequest request){
        this.tagId = Integer.valueOf(request.getParameter(TagController.PARAM_TAGID));
        this.tagName = request.getParameter(TagController.PARAM_TAG_NAME);
    }
    
    private boolean validateParameters(){
        if(tagName.isEmpty()){
            return false;
        }
        return true;
    }
    
}
