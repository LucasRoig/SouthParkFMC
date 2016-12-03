/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.actions.authentication;

import fr.athome.southparkfmc.actions.Action;
import fr.athome.southparkfmc.dataaccess.DaoManager;
import fr.athome.southparkfmc.dataaccess.UserDao;
import fr.athome.southparkfmc.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas
 */
public class LoginAction implements Action {

    private DaoManager daoManager;
    private String userName;
    private String userPassword;

    public LoginAction(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        this.gatherParameters(request);
        if (this.validate()) {
            UserDao userDao = daoManager.getUserDao();
            try {
                User user = userDao.find(userName, userPassword);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                    return "/";
                }
            } catch (Exception e) {
            }
        }
        return "/authentication/login";
    }

    public void gatherParameters(HttpServletRequest request) {
        this.userName = request.getParameter("username");
        this.userPassword = request.getParameter("password");
    }

    public boolean validate() {
        return !this.userName.isEmpty() && !this.userPassword.isEmpty();
    }
}
