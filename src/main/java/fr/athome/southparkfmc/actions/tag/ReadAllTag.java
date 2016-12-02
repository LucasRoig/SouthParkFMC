/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.tag;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.TagDao;
import fr.athome.southparkfmc.model.Tag;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruce
 */
public class ReadAllTag implements Action{
    
    DaoManager daoManager;
    int selectedTag;

    public ReadAllTag(DaoManager daoManager) {
        this.daoManager=daoManager;
    }
 
     @Override
     public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        TagDao dao = daoManager.getTagDao();
        try {
            List<Tag> tagList = dao.findAll();
            request.setAttribute("tagList", tagList);
        } catch (SQLException ex) {
            Logger.getLogger(ReadTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "readAllTags.jsp";
    }
    
    public void gatherParameters(HttpServletRequest request){
        try {
            selectedTag = Integer.valueOf(request.getParameter("selectedTag"));
        } catch (Exception e) {
            selectedTag = 1;
        }
     }
     
 }
