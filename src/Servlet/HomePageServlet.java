/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sistema.Sistema;

/**
 *
 * @author alberto
 */
public class HomePageServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Sistema s = new Sistema();
            Cookie cookie = req.getCookies()[0]; 
            int idUtente = Integer.parseInt(cookie.getName());        
            if(CookieStorage.getInstance().controllaPresenzaCookie(cookie)){
                s.settaLoggato(idUtente);
            } else {
                cookie.setMaxAge(0); // il cookie non è più valido, dunque lo elimino
                resp.addCookie(cookie);
                settaNonLoggato(resp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomePageServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            settaNonLoggato(resp);
        } 
    }
    
    private void settaNonLoggato(HttpServletResponse response) {
        // risposta da utente non loggato
    }
    
}
