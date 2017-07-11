/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HtmlCreators;

import ProfiloUtente.DatiUtente;
import RicercaCoinquilino.CoinquilinoRisultante;
import Servlet.CookieStorage;
import Servlet.HtmlReader;
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
    /**
     * 
     * @param risultati Lista contentente le info dei coinquilini risultanti da una ricerca.
     * @param req
     * @param resp
     * @return Stringa che prepara la lista passata in input per l'html.
     * @throws FileNotFoundException 
     */
    public static String creaLista(ArrayList<CoinquilinoRisultante> risultati, 
            HttpServletRequest req, HttpServletResponse resp) throws FileNotFoundException {
        String risposta;
        try {
            Cookie cookie = req.getCookies()[0]; 
            if(CookieStorage.getInstance().controllaPresenzaCookie(cookie)){
                // utente gia loggato.
                risposta = rispostaLoggato(risultati);   
                
            } else {
                cookie.setMaxAge(0); // il cookie non è più valido, dunque lo elimino
                resp.addCookie(cookie);
                risposta = rispostaNonLoggato(risultati);
            }
        } catch (NullPointerException ex) {
            risposta = rispostaNonLoggato(risultati);
        }
        return risposta;
    }
    
    private static String rispostaLoggato(ArrayList<CoinquilinoRisultante> risultati) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(HtmlReader.htmlReader("headerLoggato.html"));
        sb.append(HtmlReader.htmlReader("risultatiRicerca.html"));
        for (CoinquilinoRisultante co : risultati) {
            DatiUtente utente = co.getUtente().getDatiUtente();
            sb.append("<a href=\"http://localhost:8080/risultatoCoinquilino?id=");
            sb.append(co.getUtente().getIdUtente()).append("\">");
            sb.append("<div class=\"w3-main w3-white\" style=\"margin:2% 10%; padding: 1% 2% 2% 2%; width:auto; text-align:left; opacity:0.95;\">");
            sb.append("<div class=\"w3-light-grey\">\n" +
                      "<div class=\"w3-container w3-blue w3-center\" style=\"width:"+co.getPunteggio()+"%\">Affinita' : "+co.getPunteggio()+"%</div>\n" +
                      "</div>");
            //sb.append("<div style=\"text-align:center;float:right; font-weight:bold; font-size:20;\">Affinita'<br>");
            //sb.append(co.getPunteggio()).append("%</div>");
            sb.append("<h4><strong>UTENTE: ").append(utente.getNome()).append(" ").append(utente.getCognome()).append("</strong></h4>");
            sb.append("<p class=\"w3-large w3-text-theme\"><i class=\"fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal\"></i>");
            sb.append(utente.getDataDiNascita().toString()).append("</p>");
            sb.append("<p class=\"w3-large\"><i class=\"fa fa-venus-mars fa-fw w3-margin-right w3-large w3-text-teal\"></i>Sesso: ");
            sb.append(utente.getSesso().name()).append("</p>");  
            sb.append("<p class=\"w3-large\"><i class=\"fa fa-briefcase fa-fw w3-margin-right w3-large w3-text-teal\"></i>Occupazione: ");
            sb.append(utente.getOccupazione().name()).append("</p>");
            sb.append("<p class=\"w3-large\"><i class=\"fa fa-graduation-cap fa-fw w3-margin-right w3-large w3-text-teal\"></i>Facolta': ");
            sb.append(utente.getFacolta().name()).append("</p>");
            sb.append("<p class=\"w3-large\"><i class=\"fa fa-envelope fa-fw w3-margin-right w3-large w3-text-teal\"></i>Email: ");
            sb.append(utente.geteMail()).append("</p>");
            sb.append("<p class=\"w3-large\"><i class=\"fa fa-phone fa-fw w3-margin-right w3-large w3-text-teal\"></i>Telefono: ");
            sb.append(utente.getNumeroDiTelefono()).append("</p></div></a>");          
        }
        sb.append("</table></body><div id=\"navfooter\"></div></html>");
        return sb.toString();
    }
    
    private static String rispostaNonLoggato(ArrayList<CoinquilinoRisultante> risultati) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(HtmlReader.htmlReader("headerNonLoggato.html"));
        sb.append(HtmlReader.htmlReader("risultatiRicerca.html"));
        for (CoinquilinoRisultante co : risultati) {
            DatiUtente utente = co.getUtente().getDatiUtente();
            sb.append("<a href=\"http://localhost:8080/risultatoCoinquilino?id=");
            sb.append(co.getUtente().getIdUtente()).append("\">");
            sb.append("<div class=\"w3-main w3-white\" style=\"margin:2% 10%; padding: 1% 2% 2% 2%; width:auto; text-align:left; opacity:0.95;\">");
            sb.append("<div class=\"w3-light-grey\">\n" +
                      "<div class=\"w3-container w3-blue w3-center\" style=\"width:"+co.getPunteggio()+"%\">Affinita' : "+co.getPunteggio()+"%</div>\n" +
                      "</div>");
            //sb.append("<div style=\"text-align:center;float:right; font-weight:bold; font-size:20;\">Affinita'<br>");
            //sb.append(co.getPunteggio()).append("%</div>");
            sb.append("<h4><strong>UTENTE: ").append(utente.getNome()).append(" ").append(utente.getCognome()).append("</strong></h4>");
            sb.append("<p class=\"w3-large w3-text-theme\"><i class=\"fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal\"></i>");
            sb.append(utente.getDataDiNascita().toString()).append("</p>");
            sb.append("<p class=\"w3-large\"><i class=\"fa fa-venus-mars fa-fw w3-margin-right w3-large w3-text-teal\"></i>Sesso: ");
            sb.append(utente.getSesso().name()).append("</p>");  
            sb.append("<p class=\"w3-large\"><i class=\"fa fa-briefcase fa-fw w3-margin-right w3-large w3-text-teal\"></i>Occupazione: ");
            sb.append(utente.getOccupazione().name()).append("</p>");
            sb.append("<p class=\"w3-large\"><i class=\"fa fa-graduation-cap fa-fw w3-margin-right w3-large w3-text-teal\"></i>Facolta': ");
            sb.append(utente.getFacolta().name()).append("</p></div></a>");         
        }
        sb.append("</table></body><div id=\"navfooter\"></div></html>");
        return sb.toString();
    }
}
