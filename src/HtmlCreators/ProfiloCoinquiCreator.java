/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HtmlCreators;

import ProfiloUtente.DatiUtente;
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
public class ProfiloCoinquiCreator {
    /**
     * Metodo che crea la pagina html di un utente che si è candidato come coinquilino.
     * 
     * @param datiUtente Oggetto contenente le informazioni dell'utente.
     * @param req Oggetto Servlet Request. 
     * @param resp Oggetto Servlet Response.
     * @return Ritorna la stringa formattata e pronta per il caricamento dinamico Html.
     * @throws FileNotFoundException Eccezione generata dal mancato reperimento del file html.
     */
    public static String creaPagina(DatiUtente datiUtente, HttpServletRequest req, 
            HttpServletResponse resp) throws FileNotFoundException {
        String risposta;
        try {
            Cookie cookie = req.getCookies()[0]; 
            if(CookieStorage.getInstance().controllaPresenzaCookie(cookie)){
                // utente gia loggato.
                risposta = rispostaLoggato(datiUtente);   
                
            } else {
                cookie.setMaxAge(0); // il cookie non è più valido, dunque lo elimino
                resp.addCookie(cookie);
                risposta = rispostaNonLoggato(datiUtente);
            }
        } catch (NullPointerException ex) {
            risposta = rispostaNonLoggato(datiUtente);
        }
        return risposta;
    }
    /**
     * Metodo che sostituisce i campi all'interno dell'profilo utente che si vuole visualizzare.
     * (VERSIONE LOGGATO : VEDO I CONTATTI).
     * @param datiUtente Oggetto DatiUtente contenente i campi che devono essere sostituiti.
     * @return Ritorna la stringa contenente l'html con i campi correttamente sostituiti.
     * @throws FileNotFoundException  Eccezzione generata dal mancato reperimento del file html.
     */
    private static String rispostaLoggato(DatiUtente datiUtente) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(HtmlReader.htmlReader("headerLoggato.html"));
        String body = HtmlReader.htmlReader("formCoinquiLoggato.html");
        ArrayList<String> elementiDaModificareDinamici = new ArrayList<>();
        ArrayList<String> elementiDaModificareHtml = new ArrayList<>();
        
        elementiDaModificareDinamici.add(datiUtente.getDataDiNascita().toString());
        elementiDaModificareDinamici.add(datiUtente.getOccupazione().name());
        elementiDaModificareDinamici.add(datiUtente.getFacolta().name());
        elementiDaModificareDinamici.add(datiUtente.getCittaDiRicerca().name());
        elementiDaModificareDinamici.add(datiUtente.geteMail());
        elementiDaModificareDinamici.add(datiUtente.getNumeroDiTelefono());
        elementiDaModificareDinamici.add(datiUtente.getSesso().name());
        elementiDaModificareDinamici.add(datiUtente.getNazionalita().name());
        boolean utenteSportivo = datiUtente.isSportivo();
        if(utenteSportivo==true)
            elementiDaModificareDinamici.add("SI");
        else
            elementiDaModificareDinamici.add("NO");
        boolean utenteFumatore = datiUtente.isFumatore();
        if(utenteFumatore==true)
            elementiDaModificareDinamici.add("SI");
        else
            elementiDaModificareDinamici.add("NO");
        boolean utenteCuoco = datiUtente.isCuoco();
        if(utenteCuoco==true)
            elementiDaModificareDinamici.add("SI");
        else
            elementiDaModificareDinamici.add("NO");
        boolean candidatoCoinquilino = datiUtente.isPotenzialeCoinquilino();
        if(candidatoCoinquilino==true)
            elementiDaModificareDinamici.add("SI");
        else
            elementiDaModificareDinamici.add("NO");
        String nomeCognomeUtente = datiUtente.getNome() + " " + datiUtente.getCognome() ;
        elementiDaModificareDinamici.add(nomeCognomeUtente);
        
        elementiDaModificareHtml.add("dataDiNascitaUtente");
        elementiDaModificareHtml.add("occupazioneUtente");
        elementiDaModificareHtml.add("facoltaUtente");
        elementiDaModificareHtml.add("cittaDiRicerca");
        elementiDaModificareHtml.add("emailUtente");
        elementiDaModificareHtml.add("numeroDiTelefonoUtente");
        elementiDaModificareHtml.add("sessoUtente");
        elementiDaModificareHtml.add("nazionalitaUtente");
        elementiDaModificareHtml.add("utenteSportivo");
        elementiDaModificareHtml.add("utenteCuoco");
        elementiDaModificareHtml.add("utenteFumatore");
        elementiDaModificareHtml.add("candidatoCoinquilino");
        elementiDaModificareHtml.add("nomeCognomeUtente");
        
        for (int i = 0; i < elementiDaModificareDinamici.size(); i++) {
            String tmp = body.replaceAll(elementiDaModificareHtml.get(i), elementiDaModificareDinamici.get(i));
            body = tmp;
        }
        return sb.append(body).toString();
    }
    /**
     * Metodo che sostituisce i campi all'interno dell'profilo utente che si vuole visualizzare.
     * (VERSIONE NON LOGGATO : NON VEDO I CONTATTI).
     * @param datiUtente Oggetto DatiUtente contenente i campi che devono essere sostituiti.
     * @return Ritorna la stringa contenente l'html con i campi correttamente sostituiti.
     * @throws FileNotFoundException  Eccezzione generata dal mancato reperimento del file html.
     */
    private static String rispostaNonLoggato(DatiUtente datiUtente) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(HtmlReader.htmlReader("headerNonLoggato.html"));
        String body = HtmlReader.htmlReader("formCoinquiNonLoggato.html");
        ArrayList<String> elementiDaModificareDinamici = new ArrayList<>();
        ArrayList<String> elementiDaModificareHtml = new ArrayList<>();
        
        elementiDaModificareDinamici.add(datiUtente.getDataDiNascita().toString());
        elementiDaModificareDinamici.add(datiUtente.getOccupazione().name());
        elementiDaModificareDinamici.add(datiUtente.getFacolta().name());
        elementiDaModificareDinamici.add(datiUtente.getCittaDiRicerca().name());
        elementiDaModificareDinamici.add(datiUtente.getSesso().name());
        elementiDaModificareDinamici.add(datiUtente.getNazionalita().name());
        boolean utenteSportivo = datiUtente.isSportivo();
        if(utenteSportivo==true)
            elementiDaModificareDinamici.add("SI");
        else
            elementiDaModificareDinamici.add("NO");
        boolean utenteFumatore = datiUtente.isFumatore();
        if(utenteFumatore==true)
            elementiDaModificareDinamici.add("SI");
        else
            elementiDaModificareDinamici.add("NO");
        boolean utenteCuoco = datiUtente.isCuoco();
        if(utenteCuoco==true)
            elementiDaModificareDinamici.add("SI");
        else
            elementiDaModificareDinamici.add("NO");
        boolean candidatoCoinquilino = datiUtente.isPotenzialeCoinquilino();
        if(candidatoCoinquilino==true)
            elementiDaModificareDinamici.add("SI");
        else
            elementiDaModificareDinamici.add("NO");
        String nomeCognomeUtente = datiUtente.getNome() + " " + datiUtente.getCognome() ;
        elementiDaModificareDinamici.add(nomeCognomeUtente);
        
        elementiDaModificareHtml.add("dataDiNascitaUtente");
        elementiDaModificareHtml.add("occupazioneUtente");
        elementiDaModificareHtml.add("facoltaUtente");
        elementiDaModificareHtml.add("cittaDiRicerca");
        elementiDaModificareHtml.add("sessoUtente");
        elementiDaModificareHtml.add("nazionalitaUtente");
        elementiDaModificareHtml.add("utenteSportivo");
        elementiDaModificareHtml.add("utenteCuoco");
        elementiDaModificareHtml.add("utenteFumatore");
        elementiDaModificareHtml.add("candidatoCoinquilino");
        elementiDaModificareHtml.add("nomeCognomeUtente");
        
        for (int i = 0; i < elementiDaModificareDinamici.size(); i++) {
            String tmp = body.replaceAll(elementiDaModificareHtml.get(i), elementiDaModificareDinamici.get(i));
            body = tmp;
        }
        return sb.append(body).toString();
    }   
}
