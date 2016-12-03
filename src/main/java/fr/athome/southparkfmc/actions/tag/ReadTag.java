/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.tag;

import fr.athome.southparkfmc.actions.Action;
//import fr.athome.southparkfmc.dataaccess.ActiveTagDao;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.EpisodeDao;
import fr.athome.southparkfmc.dataaccess.TagDao;
import fr.athome.southparkfmc.model.ActiveTag;
import fr.athome.southparkfmc.model.Episode;
import fr.athome.southparkfmc.model.Tag;
import fr.athome.southparkfmc.servlets.TagController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruce
 */
public class ReadTag implements Action{
    
    DaoManager daoManager;
    int tagId;
    
    public ReadTag(DaoManager daoManager) {
        this.daoManager = daoManager;
    }
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        TagDao tagDao = this.daoManager.getTagDao();
        try {
            List<ActiveTag> usesList = tagDao.findUses(tagId);
            request.setAttribute("usesList",usesList);
            return "readTag.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "404";
    }
    
    private void gatherParameters(HttpServletRequest request){
        this.tagId = Integer.valueOf(request.getParameter(TagController.PARAM_TAGID));
    }
    
}
