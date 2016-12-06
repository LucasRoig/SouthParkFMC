/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.stat;

import fr.athome.southparkfmc.actions.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruce
 */
public class StatPage implements Action {

    public StatPage() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "stat.jsp";
    }
    
}
