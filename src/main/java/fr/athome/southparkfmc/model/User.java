/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.model;

/**
 *
 * @author Lucas
 */
public class User {
    private String userLogin;
    private String userPassword;
    private String privilege;

    public User(String userLogin, String userPassword, String privilege) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.privilege = privilege;
    }

    /**
     * @return the userLogin
     */
    public String getUserLogin() {
        return userLogin;
    }

    /**
     * @return the userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @return the privilege
     */
    public String getPrivilege() {
        return privilege;
    }
    
    
}
