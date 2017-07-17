/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HtmlCreators;

import Casa.AnnuncioCasa;
import RicercaAnnuncio.AnnuncioRisultante;
import Servlet.CookieStorage;
import Servlet.HtmlReader;
import java.io.FileNotFoundException;
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
    /**
     * Metodo che restituisce l'html del risultato della ricerca all'utente. Ottenuto tramite composizione di stringhe tramite la classe AnnunciRisultantiCreator.
     * @param annunci Lista contenente tutti gli annunci risultanti da una ricerca
     * @param req Oggetto Servlet Request.
     * @param resp Oggetto Servlet Response.
     * @return Ritorna la lista sotto forma di stringa di tutti gli annunci risultanti formattati sotto forma di html.
     * @throws FileNotFoundException Eccezione generata dal mancato reperimento del file html.
     */
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
                risultati= annunciRisultantiNonLoggato(annunci);
                resp.setStatus(200);
            }
        } catch (NullPointerException ex) {
            risultati= annunciRisultantiNonLoggato(annunci);
            resp.setStatus(200);
        }
        
         return risultati;
    }
    /**
     * Metodo che genera la lista di annunci in html reperiti tramite la ricerca del sistema.
     * (VERSIONE LOGGATO: VEDO I CONTATTI)
     * @param annunci Lista degli annunci reperita grazie alla ricerca.
     * @return Stringa contenente l'html in cui abbiamo inserito la lista degli annunci.
     * @throws FileNotFoundException Eccezzione generata dal mancato reperimento dei file html.
     */
    private static String annunciRisultantiLoggato(ArrayList<AnnuncioRisultante> annunci) throws FileNotFoundException {
        
        StringBuilder sb= new StringBuilder();
        sb.append(HtmlReader.htmlReader("headerLoggato.html"));
        sb.append(HtmlReader.htmlReader("risultatiRicerca.html"));
        
        for(AnnuncioRisultante a: annunci) {
            
            AnnuncioCasa annuncio = a.getAnnuncio();
            
            sb.append("<a href=\"http://localhost:8080/risultatoCasa?id=");
            sb.append(annuncio.getIdProprietario()).append("\">");
            sb.append("<div class=\"w3-main w3-white\" style=\"margin:2% 10%; padding: 1% 2% 2% 2%; width:auto; text-align:left; opacity:0.95;\">");
            sb.append("<div class=\"w3-light-grey\">\n" +
                      "<div class=\"w3-container w3-blue w3-center\" style=\"width:100%\">Affinita' : "+String.format("%.1f",a.getPunteggio())+"%</div>\n" +
                      "</div>");
           // sb.append("<div style=\"text-align:center;float:right; font-weight:bold; font-size:20;\">Affinita'<br>");
            //sb.append(a.getPunteggio()).append("%</div>");
            sb.append("<h4><strong> ");
            sb.append(annuncio.getCitta()).append(" - ");
            sb.append(annuncio.getIndirizzoCasa());
            sb.append(" </strong></h4>");
            sb.append("<p class=\"w3-large\"><i class=\"fa fa-fw fa-id-card-o fa-fw w3-margin-right w3-large w3-text-teal\" style=\"width:30px\"></i>Creato da: ");
            sb.append(annuncio.getNomeCognomeProprietario());
            sb.append("</p><p class=\"w3-large\"><i class=\"fa fa-fw fa-phone fa-fw w3-margin-right w3-large w3-text-teal\" style=\"width:30px\"></i>Telefono: ");
            sb.append(annuncio.getCellulareProprietario());
            sb.append("</p><p class=\"w3-large\"><i class=\"fa fa-fw fa-envelope fa-fw w3-margin-right w3-large w3-text-teal\" style=\"width:30px\"> </i>Email: "); 
            sb.append(annuncio.getEmailProprietario());
            sb.append("</p><p class=\"w3-large\"><i class=\"fa fa-fw fa-calendar fa-fw w3-margin-right w3-large w3-text-teal\" style=\"width:30px\"></i>Data creazione: "); 
            sb.append(annuncio.getDataCreazioneAnnuncio());
            sb.append("</p><p class=\"w3-large\"><i class=\"fa fa-fw fa-eur fa-fw w3-margin-right w3-large w3-text-teal\" style=\"width:30px\"></i>Costo mensile: ");
            sb.append(annuncio.getCosto());
            sb.append("</p></div></a>");
        }        
              
        sb.append("</table></div></body></html>");
        return sb.toString();
    }
    
    /**
     * Metodo che genera la lista di annunci in html reperiti tramite la ricerca del sistema.
     * (VERSIONE NON LOGGATO: NON VEDO I CONTATTI)
     * @param annunci Lista degli annunci reperita grazie alla ricerca.
     * @return Stringa contenente l'html in cui abbiamo inserito la lista degli annunci.
     * @throws FileNotFoundException Eccezzione generata dal mancato reperimento dei file html.
     */
    private static String annunciRisultantiNonLoggato(ArrayList<AnnuncioRisultante> annunci) throws FileNotFoundException {
        
        StringBuilder sb= new StringBuilder();
        sb.append(HtmlReader.htmlReader("headerNonLoggato.html"));
        sb.append(HtmlReader.htmlReader("risultatiRicerca.html"));
        
        for(AnnuncioRisultante a: annunci) {
            
            AnnuncioCasa annuncio = a.getAnnuncio();
            
            sb.append("<a href=\"http://localhost:8080/risultatoCasa?id=");
            sb.append(annuncio.getIdProprietario()).append("\">");
            sb.append("<div class=\"w3-main w3-white\" style=\"margin:2% 10%; padding: 1% 2% 2% 2%; width:auto; text-align:left; opacity:0.95;\">");
            sb.append("<div class=\"w3-light-grey\">\n" +
                      "<div class=\"w3-container w3-blue w3-center\" style=\"width:"+a.getPunteggio()+"%\">Affinita' : "+String.format("%.1f",a.getPunteggio())+"%</div>\n" +
                      "</div>");
            //sb.append("<div style=\"text-align:center;float:right; font-weight:bold; font-size:20;\">Affinita'<br>");
            //sb.append(a.getPunteggio()).append("%</div>");
            sb.append("<h4><strong> ");
            sb.append(annuncio.getCitta()).append(" - ");
            sb.append(annuncio.getIndirizzoCasa());
            sb.append(" </strong></h4>");
            sb.append("<p class=\"w3-large\"><i class=\"fa fa-fw fa-id-card-o fa-fw w3-margin-right w3-large w3-text-teal\" style=\"width:30px\"></i>Creato da: ");
            sb.append(annuncio.getNomeCognomeProprietario());
            sb.append("</p><p class=\"w3-large\"><i class=\"fa fa-fw fa-calendar fa-fw w3-margin-right w3-large w3-text-teal\" style=\"width:30px\"></i>Data creazione: "); 
            sb.append(annuncio.getDataCreazioneAnnuncio());
            sb.append("</p><p class=\"w3-large\"><i class=\"fa fa-fw fa-eur fa-fw w3-margin-right w3-large w3-text-teal\" style=\"width:30px\"></i>Costo mensile: ");
            sb.append(annuncio.getCosto());
            sb.append("</p></div></a>");
        }        
              
        sb.append("</table></div></body><div id=\"navfooter\"></div></html>");
        return sb.toString();
    }
 
}
