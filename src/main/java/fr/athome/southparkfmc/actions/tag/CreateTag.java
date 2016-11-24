/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.tag;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.EpisodeDao;
import fr.athome.southparkfmc.dataaccess.TagDao;
import fr.athome.southparkfmc.servlets.EpisodeController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas
 */
public class CreateTag  implements Action {
    DaoManager daoManager;
    String tagName;

    public CreateTag(DaoManager daoManager){
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        TagDao dao = this.daoManager.getTagDao();
        int tagId = dao.create(tagName);
        response.setContentType("application/json");
        String json;
        if(tagId != -1){
            //Pas d'erreur
            json = "{\"result\":true,\"tagId\":" + tagId + "}";
        }else{
            json = "{\"result\":false,\"tagId\":" + tagId + "}";
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
        this.tagName=request.getParameter("tagName");
    }
}
