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
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sistema.Sistema;


/**
 *
 * @author Marco La Salvia
 */
public class RegistrationServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String registrazioneHtml = HtmlReader.htmlReader("registrazione.html");
        response.setStatus(200);
        response.getWriter().println(registrazioneHtml);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String nome = req.getParameter("nome");
        String cognome = req.getParameter("cognome");
        String email = req.getParameter("email");
        String sesso = req.getParameter("sesso");
        String dataDiNascita = req.getParameter("datadinascita");
        String nazionalita = req.getParameter("nazionalita");
        String facolta= req.getParameter("facolta");
        String telefono = req.getParameter("cellulare");
        String citta = req.getParameter("cittadiricerca ");
        String occupazione = req.getParameter("occupazione");
        String fumatore = req.getParameter("fumatore");
        String cuoco = req.getParameter("cuoco");
        String sportivo = req.getParameter("sportivo");
        String password = req.getParameter("password");
        
        String[] giornoMeseAnno = dataDiNascita.split("-");
        int giorno = Integer.parseInt(giornoMeseAnno[2]);
        int mese = Integer.parseInt(giornoMeseAnno[1]);
        int anno = Integer.parseInt(giornoMeseAnno[0]);
        
        boolean fumatoreBoolean = true;
        if(fumatore == null && fumatore.equals("null")) 
            fumatoreBoolean = false;
        
        boolean cuocoBoolean = true;
        if(cuoco == null && fumatore.equals("null")) 
            cuocoBoolean = false;
        
        boolean sportivoBoolean = true;
        if(sportivo == null && fumatore.equals("null")) 
            sportivoBoolean = false;

        
        int a=0;
   
        try {
            Sistema sys= new Sistema();
            sys.registrazioneUtente(nome, cognome, Sesso.valueOf(sesso), email,
                        password, giorno, mese, anno, telefono, Nazione.valueOf(nazionalita),
                        Occupazione.valueOf(occupazione), Facolta.valueOf(facolta),
                        fumatoreBoolean, cuocoBoolean, sportivoBoolean, Citta.valueOf(citta), false);
            String registrazioneEffettuataHtml = HtmlReader.htmlReader("registrazioneEffettuata.html");
            resp.setStatus(200);
            resp.getWriter().println(registrazioneEffettuataHtml);
           
        } catch (SQLException | ParseException ex) {
            String erroreHtml = HtmlReader.htmlReader("erroriVari.html");
            resp.setStatus(200);
            resp.getWriter().println(erroreHtml);
        } catch (RegistrazioneException ex) {
           String erroreEmailHtml = HtmlReader.htmlReader("erroreEmailRegistrazione.html");
            resp.setStatus(200);
            resp.getWriter().println(erroreEmailHtml);
        }
        
        
        
        
    }
    
    
    
    
}
