/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.athome.southparkfmc.filters;

import fr.athome.southparkfmc.model.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas
 */
public class AuthFilter implements Filter{
    FilterConfig filterConfig;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        User user = (User)httpRequest.getSession().getAttribute("user");
        if(user == null){
            httpResponse.sendRedirect("/authentication/login");
        }else if(!user.getPrivilege().equals("ADMIN")){
            httpResponse.sendRedirect("/authentication/login");
        }else{
            chain.doFilter(request, response);  
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
    
    

    @Override
    public void destroy() {
    }
    
}
