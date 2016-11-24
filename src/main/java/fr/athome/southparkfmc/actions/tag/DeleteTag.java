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
public class DeleteTag implements Action{
    
    private DaoManager daoManager;
    private int tagId;
    public DeleteTag(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        TagDao dao = daoManager.getTagDao();
        dao.delete(tagId);
        return "/tag/readAll";
    }
 
    private void gatherParameters(HttpServletRequest request){
        tagId = Integer.valueOf(request.getParameter(TagController.PARAM_TAGID));
    }
    
}
