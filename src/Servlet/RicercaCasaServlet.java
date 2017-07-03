/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Casa.Citta;
import Casa.ElettroDomestico;
import Casa.HouseGenerality;
import Exceptions.RegistrazioneException;
import ProfiloUtente.Facolta;
import ProfiloUtente.Nazione;
import ProfiloUtente.Occupazione;
import ProfiloUtente.Sesso;
import java.io.FileNotFoundException;
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
 * @author Margherita
 */
public class RicercaCasaServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
            Cookie cookie = request.getCookies()[0]; 
            if(CookieStorage.getInstance().controllaPresenzaCookie(cookie)){
                // utente gia loggato.
                String registrazioneGiaLoggato = HtmlReader.htmlReader("RicercaCasaLoggato.html");
                response.setStatus(200);
                response.getWriter().println(registrazioneGiaLoggato);
            } else {
                String registrazioneHtml = HtmlReader.htmlReader("RicercaCasaNonLoggato.html");
                response.setStatus(200);
                response.getWriter().println(registrazioneHtml);
            }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws FileNotFoundException, IOException{
     
       
        try {
            effettuaRicerca(req);
        } catch (SQLException ex) {
            Logger.getLogger(RicercaCasaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RegistrazioneException ex) {
            Logger.getLogger(RicercaCasaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RicercaCasaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            String ricercaEffettuataHtml = HtmlReader.htmlReader("registrazioneEffettuata.html");
            resp.setStatus(200);
            resp.getWriter().println(ricercaEffettuataHtml);

        
    }
    
    private void effettuaRicerca(HttpServletRequest req) throws SQLException, RegistrazioneException, ParseException {
        String cittaDiRicerca = req.getParameter("cittadiricerca");
        String costoMax = req.getParameter("costoMax");
        String cucinaSeparata = req.getParameter("cucina");
        String importCucinaSeparata = req.getParameter("importanzacucinaseparata");
        String distanzaCentro = req.getParameter("distanzacentro");
        String importDistanzaCentro = req.getParameter("importanzadistanzacentro");
        String numeroLocali= req.getParameter("numerolocali");
        String importNumeroLocali = req.getParameter("importanzanumerolocali");
        String numeroBagni = req.getParameter("numerobagni");
        String importNumeroBagni = req.getParameter("importanzanumerobagni");
        String sessoCoinquilini = req.getParameter("sessocoinquilini");
        String importSesso = req.getParameter("importanzasesso");
        String tipoCamera = req.getParameter("tipocamera");
        String importTipoCamera = req.getParameter("importanzatipocamera");
        String forno = req.getParameter("forno");
        String importForno = req.getParameter("importanzaforno");
        String lavatrice = req.getParameter("lavatrice");
        String importLavatrice = req.getParameter("importanzalavatrice");
        String lavastoviglie = req.getParameter("lavastoviglie");
        String importLavastoviglie = req.getParameter("importanzalavastoviglie");
        String condizionatore = req.getParameter("condizionatore");
        String importCondizionatore= req.getParameter("importanzacondizionatore");
        String aspirapolvere = req.getParameter("aspirapolvere");
        String importAspirapolvere = req.getParameter("importanzaaspirapolvere");
        String asciugatrice = req.getParameter("asciugatrice");
        String importAsciugatrice = req.getParameter("importanzaasciugatrice");
        String microonde = req.getParameter("microonde");
        String importMicroonde = req.getParameter("importanzamicroonde");
        
         
        boolean cucinaSeparataBoolean = true;
        try{
            if(cucinaSeparata.equals("null")) 
                cucinaSeparataBoolean = false;
        } catch (NullPointerException ex) {
            cucinaSeparataBoolean = false;
        }
        
        Sistema sys= new Sistema();
        sys.iniziaRicercaAnnunci(Citta.valueOf(cittaDiRicerca));
        sys.setCostoMax(Integer.parseInt(costoMax));
        sys.setParametroCucina(Integer.parseInt(importCucinaSeparata), cucinaSeparataBoolean);
        sys.setParametroDistCentro(Integer.parseInt(importDistanzaCentro), Integer.parseInt(distanzaCentro));
        sys.setParametroNLocali(Integer.parseInt(importNumeroLocali), Integer.parseInt(numeroLocali));
        sys.setParametroNBagni(Integer.parseInt(importNumeroBagni), Integer.parseInt(numeroBagni));
        sys.setParametroSessoCasa(Integer.parseInt(importSesso), HouseGenerality.valueOf(sessoCoinquilini));
        sys.setParametroTipoCamera(Integer.parseInt(importTipoCamera), Integer.parseInt(tipoCamera));
        sys.setParametroElettrodomestico(Integer.parseInt(importForno), ElettroDomestico.valueOf(forno));
        sys.setParametroElettrodomestico(Integer.parseInt(importLavatrice), ElettroDomestico.valueOf(lavatrice));
        sys.setParametroElettrodomestico(Integer.parseInt(importLavastoviglie), ElettroDomestico.valueOf(lavastoviglie));
        sys.setParametroElettrodomestico(Integer.parseInt(importCondizionatore), ElettroDomestico.valueOf(condizionatore));
        sys.setParametroElettrodomestico(Integer.parseInt(importAsciugatrice), ElettroDomestico.valueOf(asciugatrice));
        sys.setParametroElettrodomestico(Integer.parseInt(importAspirapolvere), ElettroDomestico.valueOf(aspirapolvere));
        sys.setParametroElettrodomestico(Integer.parseInt(importMicroonde), ElettroDomestico.valueOf(microonde));
        
    }
    
    
    
    
}
