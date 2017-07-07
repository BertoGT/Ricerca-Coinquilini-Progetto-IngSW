/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Casa.Citta;
import Exceptions.PasswordException;
import ProfiloUtente.Facolta;
import ProfiloUtente.Nazione;
import ProfiloUtente.Occupazione;
import ProfiloUtente.ProfileManager;
import ProfiloUtente.Sesso;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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
public class ModificaProfiloServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Cookie cookie = req.getCookies()[0]; 
            if(CookieStorage.getInstance().controllaPresenzaCookie(cookie)){
                // utente gia loggato.
                String pagina = HtmlReader.htmlReader("modificaProfilo.html");                
                resp.setStatus(200);
                resp.getWriter().println(pagina);
            } else {
                cookie.setMaxAge(0); // il cookie non è più valido, dunque lo elimino
                resp.addCookie(cookie);
                resp.sendRedirect("/homepage");
            }
        } catch (NullPointerException ex) {
            resp.sendRedirect("/homepage");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Cookie cookie = req.getCookies()[0]; 
            if(CookieStorage.getInstance().controllaPresenzaCookie(cookie)){
                // utente gia loggato.
                int idUtente = Integer.parseInt(cookie.getName());
                effettuaModifica(req, idUtente);
                resp.setStatus(200);
                resp.sendRedirect("/profiloUtente");
            } else {
                cookie.setMaxAge(0); // il cookie non è più valido, dunque lo elimino
                resp.addCookie(cookie);
                resp.sendRedirect("/homepage");
            }
        } catch (NullPointerException ex) {
            resp.sendRedirect("/homepage");
        } catch (SQLException | ParseException | PasswordException ex) {
            Logger.getLogger(ModificaProfiloServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void effettuaModifica(HttpServletRequest req, int idUtente) throws SQLException, ParseException, PasswordException {
        String facolta= req.getParameter("facolta");
        String citta = req.getParameter("cittadiricerca");
        String occupazione = req.getParameter("occupazione");
        String fumatore = req.getParameter("fumatore");
        String candidato = req.getParameter("candidatocoinquilino");
        String cuoco = req.getParameter("cuoco");
        String sportivo = req.getParameter("sportivo");
        String password = req.getParameter("password");


        boolean candidatoBoolean = true;
        try{
            if(candidato.equals("null")) 
                candidatoBoolean = false;
        } catch (NullPointerException ex) {
            candidatoBoolean = false;
        }

        
        boolean fumatoreBoolean = true;
        try{
            if(fumatore.equals("null")) 
                fumatoreBoolean = false;
        } catch (NullPointerException ex) {
            fumatoreBoolean = false;
        }

        boolean cuocoBoolean = true;
        try{
            if(cuoco.equals("null")) 
                cuocoBoolean = false;
        } catch (NullPointerException ex) {
            cuocoBoolean = false;
        }

        boolean sportivoBoolean = true;
        try{
            if(sportivo.equals("null")) 
                sportivoBoolean = false;
        } catch (NullPointerException ex) {
            sportivoBoolean = false;
        }

        Sistema sys= new Sistema();
        sys.settaLoggato(idUtente);
        ProfileManager pm = sys.getUser().getProfileManager();
        pm.modificaProfilo(Facolta.valueOf(facolta),  Citta.valueOf(citta), 
                Occupazione.valueOf(occupazione), fumatoreBoolean, sportivoBoolean, 
                cuocoBoolean, candidatoBoolean, password);
    }

    
}
