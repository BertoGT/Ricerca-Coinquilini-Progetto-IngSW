/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;


import Casa.Citta;
import Exceptions.RegistrazioneException;
import ProfiloUtente.Facolta;
import ProfiloUtente.Nazione;
import ProfiloUtente.Occupazione;
import ProfiloUtente.Sesso;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Sistema.Sistema;


/**
 *
 * @author Marco La Salvia
 */
public class RegistrationServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cookie cookie = request.getCookies()[0]; 
            if(CookieStorage.getInstance().controllaPresenzaCookie(cookie)){
                // utente gia loggato.
                String registrazioneGiaLoggato = HtmlReader.htmlReader("registrazioneGiaLoggato.html");
                response.setStatus(200);
                response.getWriter().println(registrazioneGiaLoggato);
            } else {
                cookie.setMaxAge(0); // il cookie non è più valido, dunque lo elimino
                response.addCookie(cookie);
                settaNonLoggato(response);
            }
        } catch (NullPointerException ex) {
            settaNonLoggato(response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            effettuaRegistrazione(req);
            String registrazioneEffettuataHtml = HtmlReader.htmlReader("registrazioneEffettuata.html");
            resp.setStatus(200);
            resp.getWriter().println(registrazioneEffettuataHtml);

        } catch (SQLException | ParseException e) {
            String erroreHtml = HtmlReader.htmlReader("erroriVari.html");
            resp.setStatus(200);
            resp.getWriter().println(erroreHtml);
        } catch (RegistrazioneException e) {
            String erroreEmailHtml = HtmlReader.htmlReader("erroreEmailRegistrazione.html");
            resp.setStatus(200);
            resp.getWriter().println(erroreEmailHtml);
        }       
    }
    
    private void effettuaRegistrazione(HttpServletRequest req) throws SQLException, RegistrazioneException, ParseException {
        String nome = req.getParameter("nome");
        String cognome = req.getParameter("cognome");
        String email = req.getParameter("email");
        String sesso = req.getParameter("sesso");
        String dataDiNascita = req.getParameter("datadinascita");
        String nazionalita = req.getParameter("nazionalita");
        String facolta= req.getParameter("facolta");
        String telefono = req.getParameter("cellulare");
        String citta = req.getParameter("cittadiricerca");
        String occupazione = req.getParameter("occupazione");
        String fumatore = req.getParameter("fumatore");
        String cuoco = req.getParameter("cuoco");
        String sportivo = req.getParameter("sportivo");
        String password = req.getParameter("password");
        
        String[] giornoMeseAnno = dataDiNascita.split("-");
        int giorno = Integer.parseInt(giornoMeseAnno[2]);
        int mese = Integer.parseInt(giornoMeseAnno[1]);
        int anno = Integer.parseInt(giornoMeseAnno[0]);
        
        boolean fumatoreBoolean = checkValueBool(fumatore);
        boolean cuocoBoolean = checkValueBool(cuoco);   
        boolean sportivoBoolean = checkValueBool(sportivo);
        
        Sistema sys= new Sistema();
        sys.registrazioneUtente(nome, cognome, Sesso.valueOf(sesso), email,
            password, giorno, mese, anno, telefono, Nazione.valueOf(nazionalita),
            Occupazione.valueOf(occupazione), Facolta.valueOf(facolta),
            fumatoreBoolean, cuocoBoolean, sportivoBoolean, Citta.valueOf(citta), false);
    }
    
    private void settaNonLoggato(HttpServletResponse response) throws FileNotFoundException, IOException {
        String registrazioneHtml = HtmlReader.htmlReader("registrazione.html");
        response.setStatus(200);
        response.getWriter().println(registrazioneHtml);
    }
    
    private boolean checkValueBool(String value){
        boolean tmp = true;
        try{
            if(value.equals("null")) 
                tmp = false;
        } catch (NullPointerException ex) {
            tmp = false;
        } finally {
            return tmp;
        }
    }
    
    
    
    
}
