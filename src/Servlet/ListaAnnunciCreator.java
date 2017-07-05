/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Casa.AnnuncioCasa;
import RicercaAnnuncio.AnnuncioRisultante;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Margherita
 */
public class ListaAnnunciCreator extends HttpServlet {
    
    public static String creaListaAnnunci(ArrayList<AnnuncioRisultante> annunci, HttpServletRequest req, HttpServletResponse resp) throws FileNotFoundException {
        String risultati = null;
         try {
            Cookie cookie = req.getCookies()[0]; 
            if(CookieStorage.getInstance().controllaPresenzaCookie(cookie)){
                // utente gia loggato.
                risultati = annunciRisultantiLoggato(annunci);
            } else {
                cookie.setMaxAge(0); // il cookie non è più valido, dunque lo elimino
                resp.addCookie(cookie);
               
            }
        } catch (NullPointerException ex) {
           
        }
        
         return risultati;
    }
    
    private static String annunciRisultantiLoggato(ArrayList<AnnuncioRisultante> annunci) throws FileNotFoundException {
        
        StringBuilder sb= new StringBuilder();
        sb.append(HtmlReader.htmlReader("headerLoggato.html"));
        sb.append(HtmlReader.htmlReader("risultatiRicerca.html"));
        
        for(AnnuncioRisultante a: annunci) {
            
            AnnuncioCasa annuncio = a.getAnnuncio();
            
            sb.append("<div class=\"w3-main w3-white\" style=\"margin-left:10%; margin-right:10%;width:auto;text-align:left\"");
            sb.append("<h4><strong> ID ANNUNCIO:");
            sb.append(annuncio.getIdAnnuncio());
            sb.append("</strong></h4>");
            sb.append("<p><i class=\"fa fa-fw fa-id-card-o fa-fw w3-margin-right w3-large w3-text-teal\" style=\"width:30px\"></i>");
            sb.append(annuncio.getNomeCognomeProprietario());
            sb.append("</p>");
            sb.append("<p><i class=\"fa fa-fw fa-map-marker fa-fw w3-margin-right w3-large w3-text-teal\" style=\"width:30px\"></i>");
            sb.append(annuncio.getCitta());
            sb.append(annuncio.getIndirizzoCasa());
            sb.append("</p><p><i class=\"fa fa-fw fa-phone fa-fw w3-margin-right w3-large w3-text-teal\" style=\"width:30px\"></i>");
            sb.append(annuncio.getCellulareProprietario());
            sb.append("</p><p><i class=\"fa fa-fw fa-envelope fa-fw w3-margin-right w3-large w3-text-teal\" style=\"width:30px\"> </i>"); 
            sb.append(annuncio.getEmailProprietario());
            sb.append("</p><p><i class=\"fa fa-fw fa-calendar fa-fw w3-margin-right w3-large w3-text-teal\" style=\"width:30px\"></i>"); 
            sb.append(annuncio.getDataCreazioneAnnuncio());
            sb.append("</p><p><i class=\"fa fa-fw fa-eur fa-fw w3-margin-right w3-large w3-text-teal\" style=\"width:30px\"></i>");
            sb.append(annuncio.getCosto());
            sb.append("</p></div>");
        }        
              
        sb.append("</table></div></body><div id=\"navfooter\"></div></html>");
        return sb.toString();
    }
    
    
    
}
