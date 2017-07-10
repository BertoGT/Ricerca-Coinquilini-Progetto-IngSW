/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Sistema.Sistema;

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
                String headerLoggato = HtmlReader.htmlReader("headerLoggato.html");
                String homepageHtml = HtmlReader.htmlReader("homepage.html");
                resp.setStatus(200);
                resp.getWriter().println(headerLoggato+homepageHtml);
            } else {
                cookie.setMaxAge(0); // il cookie non è più valido, dunque lo elimino
                resp.addCookie(cookie);
                settaNonLoggato(resp);
            }
        } catch (SQLException ex) {
            String errorePagina = HtmlReader.htmlReader("erroriVari.html");
            resp.setStatus(200);
            resp.getWriter().println(errorePagina);  
        } catch (NullPointerException ex) {
            settaNonLoggato(resp);
        } 
    }
    
    private void settaNonLoggato(HttpServletResponse resp) throws FileNotFoundException, IOException {
        String headerNonLoggato = HtmlReader.htmlReader("headerNonLoggato.html");
        String homepageHtml = HtmlReader.htmlReader("homepage.html");
        resp.setStatus(200);
        resp.getWriter().println(headerNonLoggato+homepageHtml);
    }   
    
}
