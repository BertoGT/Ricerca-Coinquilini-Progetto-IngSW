/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import RicercaCoinquilino.CoinquilinoRisultante;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alberto
 */
public class ListaCoinquiCreator {
    public static String creaLista(ArrayList<CoinquilinoRisultante> risultati, 
            HttpServletRequest req, HttpServletResponse resp) {
        String risposta;
        try {
            Cookie cookie = req.getCookies()[0]; 
            if(CookieStorage.getInstance().controllaPresenzaCookie(cookie)){
                // utente gia loggato.
                
            } else {
                cookie.setMaxAge(0); // il cookie non è più valido, dunque lo elimino
                resp.addCookie(cookie);
            }
        } catch (NullPointerException ex) {

        }
        return risposta;
    }
    
    private static String rispostaLoggato(ArrayList<CoinquilinoRisultante> risultati) throws FileNotFoundException {
         String headerLoggato = HtmlReader.htmlReader("headerLoggato.html");
         
    }
}
