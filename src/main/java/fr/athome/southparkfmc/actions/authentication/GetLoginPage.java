/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.authentication;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas
 */
public class GetLoginPage implements Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            if(user != null){
                return "/";
            }else{
                return "/login.jsp";
            }
        } catch (Exception e) {
            return "/login.jsp";
        }
    }
    
}
