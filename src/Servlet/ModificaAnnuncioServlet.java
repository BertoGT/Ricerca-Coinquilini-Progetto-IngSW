/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Casa.Citta;
import Casa.ElettroDomestico;
import Casa.HouseGenerality;
import Exceptions.AnnuncioException;
import Exceptions.InserimentoAnnuncioNonRiuscito;
import Exceptions.PasswordException;
import Sistema.Sistema;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Niko
 */
public class ModificaAnnuncioServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Cookie cookie = req.getCookies()[0]; 
            if(CookieStorage.getInstance().controllaPresenzaCookie(cookie)){
                // utente gia loggato.
                String pagina = HtmlReader.htmlReader("modificaAnnuncio.html");          
                PrintWriter out = new PrintWriter(new FileOutputStream(new File("fileModificaCreaAnnuncio.txt"),false));
                out.append(pagina);
                out.close();
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
                modificaAnnuncio(req, idUtente);
                resp.setStatus(200);
                resp.sendRedirect("/profiloUtente");
            } else {
                cookie.setMaxAge(0); // il cookie non è più valido, dunque lo elimino
                resp.addCookie(cookie);
                resp.sendRedirect("/login");
            }
        } catch (NullPointerException ex) {
            resp.sendRedirect("/homepage");
        } catch (SQLException | ParseException | PasswordException | InserimentoAnnuncioNonRiuscito | AnnuncioException ex) {
            String errorePagina = HtmlReader.htmlReader("erroriVari.html");
            resp.setStatus(200);
            resp.getWriter().println(errorePagina); 
        }
    }
    /**
     * 
     * @param value Stringa passata come parametro.
     * @return  True se la stringa non è nulla, altrimenti false.
     */
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
    /**
     * 
     * @param value Stringa contenente l'elettrodomestico
     * @return Il valore Enum relativo all'elettrodomestico.
     */
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
    /**
     * 
     * @param req Oggetto Servlet Request.
     * @return Matrice contenente posti e posti disponibili per ogni camera disponibile in ogni riga.
     */
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
    /**
     * METODO CHE MODIFICA UN ANNUNCIO DAL PROFILO UTENTE.
     * @param req OGGETTO SERVLET REQUEST.
     * @param idUtente Id dell'utente univoco all'interno del database
     * @throws SQLException ECCEZIONE GENERATA DAL DATABASE.
     * @throws ParseException ECCEZIONE GENERATA DAL PARSING ERRATO
     * @throws PasswordException ECCEZIONE GENERATA DALL'ERRATO INSERIMENTO DELLA PASSWORD.
     */
    private void modificaAnnuncio(HttpServletRequest req, int idUtente) throws SQLException, ParseException, PasswordException, InserimentoAnnuncioNonRiuscito, AnnuncioException {
        ArrayList<ElettroDomestico> elettrodomestici = new ArrayList<>();
        HouseGenerality sessoCoinquilini = HouseGenerality.valueOf(req.getParameter("sessoCoinquilini"));
        elettrodomestici.add(this.checkValueElettrodomestico(req.getParameter("forno")));
        elettrodomestici.add(this.checkValueElettrodomestico(req.getParameter("lavatrice")));
        elettrodomestici.add(this.checkValueElettrodomestico(req.getParameter("condizionatore")));
        elettrodomestici.add(this.checkValueElettrodomestico(req.getParameter("aspirapolvere")));
        elettrodomestici.add(this.checkValueElettrodomestico(req.getParameter("asciugatrice")));
        elettrodomestici.add(this.checkValueElettrodomestico(req.getParameter("microonde")));
        elettrodomestici.add(this.checkValueElettrodomestico(req.getParameter("lavastoviglie")));
        String descrizioneAggiuntiva = req.getParameter("descrizioneAnnuncio");
        int costoMensile = Integer.parseInt(req.getParameter("costoMensile"));
        int [][] postiLettoEDisponibili = this.parseCamereDisponibili(req);

        Sistema system = new Sistema();
        system.settaLoggato(idUtente);
        system.getUser().getProfileManager().modificaAnnuncio(elettrodomestici, 
                postiLettoEDisponibili, idUtente, costoMensile, sessoCoinquilini, descrizioneAggiuntiva);
    }
}
