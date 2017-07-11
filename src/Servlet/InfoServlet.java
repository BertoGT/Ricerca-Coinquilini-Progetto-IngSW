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
 * @author cl426653
 */
public class InfoServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Cookie cookie = req.getCookies()[0]; 
            if(CookieStorage.getInstance().controllaPresenzaCookie(cookie)){
                // utente loggato
                String header = HtmlReader.htmlReader("headerLoggato.html");
                String body = HtmlReader.htmlReader("chisiamo.html");
                resp.setStatus(200);
                resp.getWriter().println(header + body);
            } else {
                cookie.setMaxAge(0); // il cookie non è più valido, dunque lo elimino
                resp.addCookie(cookie);
                utenteNonLoggato(resp);
            }
        } catch (NullPointerException ex) {
            utenteNonLoggato(resp);
        } 
    }
    
    private void utenteNonLoggato(HttpServletResponse resp) throws FileNotFoundException, IOException{
        String header = HtmlReader.htmlReader("headerNonLoggato.html");
        String body = HtmlReader.htmlReader("chisiamo.html");
        resp.setStatus(200);
        resp.getWriter().println(header + body);
    }
    
}
