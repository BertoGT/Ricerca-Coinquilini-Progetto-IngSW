/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Exceptions.LoginException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
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
public class LoginServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginHtml = HtmlReader.htmlReader("login.html");
        resp.setStatus(200);
        resp.getWriter().println(loginHtml);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        try {
            int idUtente = new Sistema().logIn(email, password);
            Cookie cookie = generaCookie(idUtente);
            CookieStorage cookieStorage = CookieStorage.getInstance();
            cookieStorage.salvaCookie(cookie);
            resp.addCookie(cookie);
            resp.sendRedirect("/homepage");
        } catch (SQLException | LoginException ex) {
            // aprire pagina in cui si dice che i dati di login sono sbagliati.
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Cookie generaCookie(int idUtente) {
        Cookie cookie = new Cookie(String.valueOf(idUtente), generaStringa());
        cookie.setMaxAge(60*60*24*2); // dura due giorni
        return cookie;
    }
    
    private String generaStringa() {
        Random rnd = new Random ();
        char[] arr = new char[16];
        for (int i=0; i<16; i++) {
            int n = rnd.nextInt (36);
            arr[i] = (char) (n < 10 ? '0'+n : 'a'+n-10);
        }
        return new String (arr);
    }
    
    
    
}
