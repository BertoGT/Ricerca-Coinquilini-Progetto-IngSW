/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Casa.Citta;
import Exceptions.NessunAnnuncioException;
import ProfiloUtente.Facolta;
import ProfiloUtente.Nazione;
import ProfiloUtente.Occupazione;
import ProfiloUtente.Sesso;
import java.io.FileNotFoundException;
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
        try {
            effettuaRicerca(req);
        } catch (SQLException ex) {
            Logger.getLogger(RicercaCoinquiServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NessunAnnuncioException ex) {
            Logger.getLogger(RicercaCoinquiServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void settaNonLoggato(HttpServletResponse resp) throws FileNotFoundException, IOException {
        String headerNonLoggato = HtmlReader.htmlReader("headerNonLoggato.html");
        String ricercaHtml = HtmlReader.htmlReader("RicercaCoinquilini.html");
        resp.setStatus(200);
        resp.getWriter().println(headerNonLoggato + ricercaHtml);
    }
    
    private void effettuaRicerca(HttpServletRequest req) throws SQLException, NessunAnnuncioException {
        String citta = req.getParameter("cittadiricerca");
        String sesso = req.getParameter("sesso");
        String eta = req.getParameter("eta");
        String impoEta = req.getParameter("ratinga");
        String facolta = req.getParameter("facolta");
        String impoFacolta = req.getParameter("ratingb");
        String cuoco = req.getParameter("cuoco");
        String impoCuoco = req.getParameter("ratingc");
        String fumatore = req.getParameter("fumatore");
        String impoFumatore = req.getParameter("ratingf");
        String sportivo = req.getParameter("sportivo");
        String impoSportivo = req.getParameter("ratinge");
        String occupazione = req.getParameter("occupazione");
        String impoOccupazione = req.getParameter("ratingg");
        String nazionalita = req.getParameter("nazionalita");
        String impoNazionalita = req.getParameter("ratingh");
        
        Sistema sys = new Sistema();
        sys.iniziaRicercaCoinquilini(Citta.valueOf(citta));
        sys.setSesso(Sesso.valueOf(sesso));
        
        try {
            if(!impoEta.equals("null")) 
                 sys.setParametroDistCentro(Integer.parseInt(impoEta), Integer.parseInt(eta.trim()));           
        } catch (NullPointerException | NumberFormatException ex) {}
        
        try {
            if(!impoFacolta.equals("null")) 
                sys.setParametroFacolta(Integer.parseInt(impoFacolta), Facolta.valueOf(facolta));
        } catch (NullPointerException ex) {}
          
        try {
            if(!impoCuoco.equals("null")) {
                boolean cuocoBoolean = true;
                try{
                    if(cuoco.equals("null")) 
                        cuocoBoolean = false;
                } catch (NullPointerException ex) {
                    cuocoBoolean = false;
                } finally {
                    sys.setParametroCucina(Integer.parseInt(impoCuoco), cuocoBoolean);
                }
            }
        } catch (NullPointerException ex) {}
        
        try {
            if(!impoFumatore.equals("null")) {
                boolean fumatoreBoolean = true;
                try{
                    if(fumatore.equals("null")) 
                        fumatoreBoolean = false;
                } catch (NullPointerException ex) {
                    fumatoreBoolean = false;
                } finally {
                    sys.setParametroFumatore(Integer.parseInt(impoFumatore), fumatoreBoolean);
                }
            }
        } catch (NullPointerException ex) {}
        
        try {
            if(!impoSportivo.equals("null")) {
                boolean sportivoBoolean = true;
                try{
                    if(sportivo.equals("null")) 
                        sportivoBoolean = false;
                } catch (NullPointerException ex) {
                    sportivoBoolean = false;
                } finally {
                    sys.setParametroSportivo(Integer.parseInt(impoSportivo), sportivoBoolean);
                }
            }
        } catch (NullPointerException ex) {}
        
        try {
            if(!impoOccupazione.equals("null")) 
                sys.setParametroOccupazione(Integer.parseInt(impoOccupazione), Occupazione.valueOf(occupazione));
        } catch (NullPointerException ex) {}
        
        try {
            if(!impoNazionalita.equals("null")) 
                sys.setParametroNazionalita(Integer.parseInt(impoNazionalita), Nazione.valueOf(nazionalita));
        } catch (NullPointerException ex) {}
        
        sys.ricercaCoinquilino();
        int a = 0;
    }
    

    
    
    
    
}
