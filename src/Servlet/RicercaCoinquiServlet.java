/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alberto
 */
public class RicercaCoinquiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            Cookie cookie = req.getCookies()[0]; 
            if(CookieStorage.getInstance().controllaPresenzaCookie(cookie)){
                // utente gia loggato.
                String headerLoggato = HtmlReader.htmlReader("headerLoggato.html");
                String ricercaHtml = HtmlReader.htmlReader("RicercaCoinquilini.html");
                resp.setStatus(200);
                resp.getWriter().println(headerLoggato + ricercaHtml);
            } else {
                cookie.setMaxAge(0); // il cookie non è più valido, dunque lo elimino
                resp.addCookie(cookie);
                settaNonLoggato(resp);
            }
        } catch (NullPointerException ex) {
            settaNonLoggato(resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
    private void settaNonLoggato(HttpServletResponse resp) throws FileNotFoundException, IOException {
        String headerNonLoggato = HtmlReader.htmlReader("headerNonLoggato.html");
        String ricercaHtml = HtmlReader.htmlReader("RicercaCoinquilini.html");
        resp.setStatus(200);
        resp.getWriter().println(headerNonLoggato + ricercaHtml);
    }
    
    
    
}
