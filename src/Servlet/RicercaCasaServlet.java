/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import HtmlCreators.ListaAnnunciCreator;
import Casa.Citta;
import Casa.ElettroDomestico;
import Casa.HouseGenerality;
import Exceptions.NessunAnnuncioException;
import RicercaAnnuncio.AnnuncioRisultante;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Sistema.Sistema;

/**
 *
 * @author Margherita
 */
public class RicercaCasaServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            Cookie cookie = req.getCookies()[0]; 
            if(CookieStorage.getInstance().controllaPresenzaCookie(cookie)){
                // utente gia loggato.
                String headerLoggato = HtmlReader.htmlReader("headerLoggato.html");
                String ricercaHtml = HtmlReader.htmlReader("RicercaCasa.html");
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws FileNotFoundException, IOException{ 
        try {
            String risultati = ListaAnnunciCreator.creaListaAnnunci(effettuaRicerca(req), req, resp);
            resp.getWriter().println(risultati);
            resp.setStatus(200);
            
        } catch (SQLException ex) {
            Logger.getLogger(RicercaCasaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NessunAnnuncioException ex) {
            // pagina con avviso nessun annuncio trovato.
            String risultati = HtmlReader.htmlReader("norisultati.html");
            resp.getWriter().println(risultati);          
        }               
    }
    /**
     * METODO CHE EFFETTUA LA RICERCA A SECONDA DEI PARAMETRI INSERITI.
     * @param req OGGETTO SERVLET REQUEST
     * @return RITORNA L'ARRAYLIST DEGLI ANNUNCI RISULTANTI DALLA RICERCA.
     * @throws SQLException ECCEZIONE GENERATA DAL DATABASE.
     * @throws NessunAnnuncioException ECCEZIONE GENERATA DAL MANCATO REPERIMENTO DI ANNUNCI.
     */
    private ArrayList<AnnuncioRisultante> effettuaRicerca(HttpServletRequest req) throws SQLException, NessunAnnuncioException {
        String cittaDiRicerca = req.getParameter("cittadiricerca");
        String costoMax = req.getParameter("costoMax");
        String cucinaSeparata = req.getParameter("cucina");
        String importCucinaSeparata = req.getParameter("rating");
        String distanzaCentro = req.getParameter("distanzacentro");
        String importDistanzaCentro = req.getParameter("ratinga");
        String numeroLocali= req.getParameter("numerolocali");
        String importNumeroLocali = req.getParameter("ratingb");
        String numeroBagni = req.getParameter("numerobagni");
        String importNumeroBagni = req.getParameter("ratingc");
        String sessoCoinquilini = req.getParameter("sessocoinquilini");
        String importSesso = req.getParameter("ratingd");
        String tipoCamera = req.getParameter("tipocamera");
        String importTipoCamera = req.getParameter("ratinge");
        String forno = req.getParameter("forno");
        String importForno = req.getParameter("ratingf");
        String lavatrice = req.getParameter("lavatrice");
        String importLavatrice = req.getParameter("ratingg");
        String lavastoviglie = req.getParameter("lavastoviglie");
        String importLavastoviglie = req.getParameter("ratingn");
        String condizionatore = req.getParameter("condizionatore");
        String importCondizionatore= req.getParameter("ratingh");
        String aspirapolvere = req.getParameter("aspirapolvere");
        String importAspirapolvere = req.getParameter("ratingi");
        String asciugatrice = req.getParameter("asciugatrice");
        String importAsciugatrice = req.getParameter("ratingl");
        String microonde = req.getParameter("microonde");
        String importMicroonde = req.getParameter("ratingm");
        
        Sistema sys= new Sistema();
        sys.iniziaRicercaAnnunci(Citta.valueOf(cittaDiRicerca));
                
        try {
            if(!importCucinaSeparata.equals("null")) {
                boolean cucinaSeparataBoolean = true;
                try{
                    if(cucinaSeparata.equals("null")) 
                        cucinaSeparataBoolean = false;
                } catch (NullPointerException ex) {
                    cucinaSeparataBoolean = false;
                } finally {
                    sys.setParametroCucina(Integer.parseInt(importCucinaSeparata), cucinaSeparataBoolean);
                }
            }
        } catch (NullPointerException ex) {}
        
        try {
            if(!importDistanzaCentro.equals("null")) 
                 sys.setParametroDistCentro(Integer.parseInt(importDistanzaCentro.trim()), Integer.parseInt(distanzaCentro.trim()));
        } catch (NullPointerException | NumberFormatException ex) {}
        
        
        try {
            if(!importNumeroLocali.equals("null")) 
                sys.setParametroNLocali(Integer.parseInt(importNumeroLocali), Integer.parseInt(numeroLocali));
        } catch (NullPointerException ex) {}
        
        try {
            if(!importNumeroBagni.equals("null")) 
                sys.setParametroNBagni(Integer.parseInt(importNumeroBagni), Integer.parseInt(numeroBagni));
        } catch (NullPointerException ex) {}
        
        try {
            if(!importSesso.equals("null")) 
                sys.setParametroSessoCasa(Integer.parseInt(importSesso), HouseGenerality.valueOf(sessoCoinquilini));
        } catch (NullPointerException ex) {}
        
        try {
            if(!importTipoCamera.equals("null")) 
                sys.setParametroTipoCamera(Integer.parseInt(importTipoCamera), Integer.parseInt(tipoCamera));
        } catch (NullPointerException ex) {}
        
        try {
            if(!importForno.equals("null")) {
                if(!forno.equals("null"))  
                    sys.setParametroElettrodomestico(Integer.parseInt(importForno), ElettroDomestico.FORNO);
            }
        } catch (NullPointerException ex) {}
        
        try {
            if(!importLavatrice.equals("null")) {
                if(!lavatrice.equals("null"))  
                    sys.setParametroElettrodomestico(Integer.parseInt(importLavatrice), ElettroDomestico.LAVATRICE);
            }
        } catch (NullPointerException ex) {}
        
        try {
            if(!importLavastoviglie.equals("null")) {
                if(!lavastoviglie.equals("null"))  
                    sys.setParametroElettrodomestico(Integer.parseInt(importLavastoviglie), ElettroDomestico.LAVASTOVIGLIE);
            }
        } catch (NullPointerException ex) {}
        
        try {
            if(!importCondizionatore.equals("null")) {
                if(!condizionatore.equals("null"))  
                    sys.setParametroElettrodomestico(Integer.parseInt(importCondizionatore), ElettroDomestico.CONDIZIONATORE);
            }
        } catch(NullPointerException ex) {}
        
        try{
            if(!importAspirapolvere.equals("null")) {
                if(!aspirapolvere.equals("null"))  
                    sys.setParametroElettrodomestico(Integer.parseInt(importAspirapolvere), ElettroDomestico.ASPIRAPOLVERE);
            }
        } catch (NullPointerException ex) {}
        
        try {
            if(!importAsciugatrice.equals("null")) {
                if(!asciugatrice.equals("null"))  
                    sys.setParametroElettrodomestico(Integer.parseInt(importAsciugatrice), ElettroDomestico.ASCIUGATRICE);
            }
        } catch (NullPointerException ex) {}
        
        try{
            if(!importMicroonde.equals("null")) {
                if(!microonde.equals("null"))  
                    sys.setParametroElettrodomestico(Integer.parseInt(importMicroonde), ElettroDomestico.MICROONDE);
            }
        } catch (NullPointerException ex) {}
        
        try {
            sys.setCostoMax(Integer.parseInt(costoMax));
        } catch (NumberFormatException ex) {}
        
        sys.ricercaAnnuncio();
        
        return sys.getAnnunciRisultanti();
    }
    /**
     * Carico la navbar da non loggato.
     * @param resp Oggetto ServletResponse
     * @throws FileNotFoundException Eccezione gerata dal mancato reperimento del file html.
     * @throws IOException Eccezione di IO
     */
    private void settaNonLoggato(HttpServletResponse resp) throws FileNotFoundException, IOException {
        String headerNonLoggato = HtmlReader.htmlReader("headerNonLoggato.html");
        String ricercaHtml = HtmlReader.htmlReader("RicercaCasa.html");
        resp.setStatus(200);
        resp.getWriter().println(headerNonLoggato + ricercaHtml);
    }
}
