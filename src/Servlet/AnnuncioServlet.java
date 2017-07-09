/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import BusinessModel.BusinessModelAnnuncio;
import Casa.CameraDisponibile;
import Casa.Citta;
import Casa.ElettroDomestico;
import Casa.HouseGenerality;
import Casa.InfoCasa;
import Exceptions.PasswordException;
import ProfiloUtente.Facolta;
import ProfiloUtente.Occupazione;
import ProfiloUtente.ProfileManager;
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
 * @author Marco La Salvia
 */
public class AnnuncioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Cookie cookie = req.getCookies()[0]; 
            if(CookieStorage.getInstance().controllaPresenzaCookie(cookie)){
                // utente gia loggato.
                String pagina = HtmlReader.htmlReader("modificaCreaAnnuncio.html");                
                resp.setStatus(200);
                resp.getWriter().println(pagina);
            } else {
                cookie.setMaxAge(0); // il cookie non è più valido, dunque lo elimino
                resp.addCookie(cookie);
                resp.sendRedirect("/login");
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
    private boolean checkValueBool(String value){
        boolean tmp = true;
        try{
            if(value.equals("null")) 
                tmp = false;
        } catch (NullPointerException ex) {
            tmp = false;
        }
        return tmp;
    }
    private ElettroDomestico checkValueElettrodomestico(String value){
        ElettroDomestico tmp = null;
        try{
            if(value.equals("null")) 
                tmp = null;
            else
                tmp = ElettroDomestico.valueOf(value);
        } catch (NullPointerException ex) {
            tmp = null;
        }
        return tmp;
    }
    private int[][] parseCamereDisponibili(HttpServletRequest req){
        int[][] postiLettoEDisponibili = new int[4][4];
        for(int i=0;i<4;i++){
            String postiLetto = "postiLetto" + i;
            String postiLettoDisponibili = "postiLettoDisponibili"+i;
            if(req.getParameter(postiLetto)!=null)
                postiLettoEDisponibili[i][0] = Integer.parseInt(req.getParameter(postiLetto));
            if(req.getParameter(postiLettoDisponibili)!=null)
                postiLettoEDisponibili[i][1] = Integer.parseInt(req.getParameter(postiLettoDisponibili));  
        }
       return postiLettoEDisponibili; 
    }
    private void effettuaModifica(HttpServletRequest req, int idUtente) throws SQLException, ParseException, PasswordException {
        int metriQuadrati = Integer.parseInt(req.getParameter("metriQuadrati"));
        int distanzaCentro = (int) Math.round(Math.ceil(Float.parseFloat(req.getParameter("distanzaCentro"))));
        int numeroBagni = Integer.parseInt(req.getParameter("numeroBagni"));
        int numeroLocali = Integer.parseInt(req.getParameter("numeroLocali"));
        HouseGenerality sessoCoinquilini = HouseGenerality.valueOf(req.getParameter("sessoCoinquilini"));
        boolean cucinaSeparata = this.checkValueBool(req.getParameter("cucinaSeparata"));
        ElettroDomestico forno = this.checkValueElettrodomestico(req.getParameter("forno"));
        ElettroDomestico lavatrice = this.checkValueElettrodomestico(req.getParameter("lavatrice"));
        ElettroDomestico condizionatore = this.checkValueElettrodomestico(req.getParameter("condizionatore"));
        ElettroDomestico aspirapolvere = this.checkValueElettrodomestico(req.getParameter("aspirapolvere"));
        ElettroDomestico asciugatrice = this.checkValueElettrodomestico(req.getParameter("asciugatrice"));
        ElettroDomestico microonde = this.checkValueElettrodomestico(req.getParameter("microonde"));
        ElettroDomestico lavastoviglie = this.checkValueElettrodomestico(req.getParameter("lavastoviglie"));
        Citta cittaDiRicerca = Citta.valueOf(req.getParameter("cittaDiRicerca"));
        String descrizioneAggiuntiva = req.getParameter("descrizioneAnnuncio");
        int costoMensile = Integer.parseInt(req.getParameter("costoMensile"));
        String nomeCognome = req.getParameter("nomeCognome");
        String cittaIndirizzo = req.getParameter("cittaIndirizzo");
        String cellulare = req.getParameter("cellulare");
        String email = req.getParameter("email");
        int [][] postiLettoEDisponibili = this.parseCamereDisponibili(req);

        BusinessModelAnnuncio bm = BusinessModelAnnuncio.getInstance();
        int idCasa = bm.inserisciInfoCasa(new InfoCasa(metriQuadrati, numeroLocali, numeroBagni, distanzaCentro,
                                                       cucinaSeparata, cittaDiRicerca, cittaIndirizzo, sessoCoinquilini));
        if(bm.inserisciAnnuncioCasa(idCasa, idUtente, descrizioneAggiuntiva, costoMensile)){
            bm.inserisciCamera(idCasa, new CameraDisponibile())
        }
        
        
    }
    
}
