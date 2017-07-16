/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HtmlCreators;

import Casa.AnnuncioCasa;
import Casa.CameraDisponibile;
import Casa.ElettroDomestico;
import Servlet.CookieStorage;
import Servlet.HtmlReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author margherita
 */
public class AnnuncioRisultanteCreator {
    /**
     * Metodo che crea una stringa contenente l'html contenente il risultato della ricerca di annunci, sia per l'utente loggato che non loggato.
     * Gli utenti non loggati non vedranno i contatti (email e numero di telefono). 
     * NB! lavora sul singolo annuncio, la composizione finale è eseguita nella classe ListaAnnunciCreator
     * @param annuncio Oggetto AnnuncioCasa contenente le informazioni dell'annuncio.
     * @param req Oggetto Servlet Request.
     * @param resp Oggetto Servelt Response.
     * @return Stringa contenente l'html per restituire i risultati della ricerca annunci all'utente, sia che esso sia loggato che non.
     * @throws FileNotFoundException ECCEZIONE GENERATA DAL MANCATO REPERIMENTO DEL FILE.
     */
     public static String creazioneAnnuncio(AnnuncioCasa annuncio, HttpServletRequest req, 
            HttpServletResponse resp) throws FileNotFoundException {
        String risposta;
        try {
            Cookie cookie = req.getCookies()[0]; 
            if(CookieStorage.getInstance().controllaPresenzaCookie(cookie)){
                // utente gia loggato.
                risposta = annuncioLoggato(annuncio);   
                
            } else {
                cookie.setMaxAge(0); // il cookie non è più valido, dunque lo elimino
                resp.addCookie(cookie);
                risposta = annuncioNonLoggato(annuncio);   
            }
        } catch (NullPointerException ex) {
            risposta = annuncioNonLoggato(annuncio);   
        }
        return risposta;
    }
    /**
     * Metodo che sostituisce i campi all'interno dell'annuncio che si vuole visualizzare.
     * (VERSIONE LOGGATO : VEDO I CONTATTI).
     * @param annuncio Oggetto AnnuncioCasa contenente i campi che devono essere sostituiti.
     * @return Ritorna la stringa contenente l'html con i campi correttamente sostituiti.
     * @throws FileNotFoundException  Eccezzione generata dal mancato reperimento del file html.
     */
      private static String annuncioLoggato(AnnuncioCasa annuncio) throws FileNotFoundException{
  
        StringBuilder sb = new StringBuilder();
        sb.append(HtmlReader.htmlReader("headerLoggato.html"));
        String body = HtmlReader.htmlReader("formAnnuncio.html");
        ArrayList<String> elementiDaModificareDinamici = new ArrayList<>();
        ArrayList<String> elementiDaModificareHtml = new ArrayList<>();
        
        elementiDaModificareDinamici.add(String.valueOf(annuncio.getMetriQuadri()));
        elementiDaModificareDinamici.add(String.valueOf(annuncio.getDistanzaCentro()));
        elementiDaModificareDinamici.add(String.valueOf(annuncio.getNumeroBagni()));
        elementiDaModificareDinamici.add(String.valueOf(annuncio.getnLocali()));
        elementiDaModificareDinamici.add(annuncio.getSessoCasa().toString()); 
        boolean cucinaSeparata = annuncio.isCucinaSeparata();
        if(cucinaSeparata==true)
            elementiDaModificareDinamici.add("SI");
        else
            elementiDaModificareDinamici.add("NO");
        elementiDaModificareDinamici.add(annuncio.getDescrizioneAggiuntiva());
        elementiDaModificareDinamici.add(String.valueOf(annuncio.getCosto()));
        elementiDaModificareDinamici.add(annuncio.getNomeCognomeProprietario());
        elementiDaModificareDinamici.add(annuncio.getCitta() +" - "+annuncio.getIndirizzoCasa());
        elementiDaModificareDinamici.add(annuncio.getCellulareProprietario());
        elementiDaModificareDinamici.add(annuncio.getEmailProprietario());
        elementiDaModificareDinamici.add(annuncio.getDataCreazioneAnnuncio());
        
        elementiDaModificareHtml.add("metriQuadrati");
        elementiDaModificareHtml.add("distanzaDalCentro");
        elementiDaModificareHtml.add("numeroBagni");
        elementiDaModificareHtml.add("numeroLocali");
        elementiDaModificareHtml.add("sessoCasa");
        elementiDaModificareHtml.add("cucinaSeparata");
        elementiDaModificareHtml.add("descrizioneAggiuntivaAnnuncio");
        elementiDaModificareHtml.add("costoAnnuncioInEuro");
        elementiDaModificareHtml.add("nomeCognomeProprietario");
        elementiDaModificareHtml.add("indirizzoCasa");
        elementiDaModificareHtml.add("numeroDiTelefonoProprietario");
        elementiDaModificareHtml.add("emailProprietario");
        elementiDaModificareHtml.add("dataCreazioneAnnuncio");
        
        
        for (int i = 0; i < elementiDaModificareDinamici.size(); i++) {
            String tmp = body.replaceAll(elementiDaModificareHtml.get(i), elementiDaModificareDinamici.get(i));
            body = tmp;
        }
        
        ArrayList<ElettroDomestico> elettrodomestici = annuncio.getElettroDomestici();
        ArrayList<CameraDisponibile> camereDisponibili = annuncio.getCamere();
        
        String tmp;
        StringBuilder elettrodomestico = new StringBuilder();
        if(elettrodomestici.isEmpty()==true){
            body = body.replaceAll("<!-- ELETTRODOMESTICI DA AGGIUNGERE DINAMICAMENTE QUI-->", "Nessun Elettrodomestico presente nell'annuncio!");
        }else{
            for(ElettroDomestico e: elettrodomestici){
                if(e==null)
                    break;
                String elettrodomesticoAnnuncio = HtmlReader.htmlReader("elettrodomesticiDinamico.html");
                elettrodomesticoAnnuncio = elettrodomesticoAnnuncio.replaceAll("nomeElettrodomestico", e.toString());
                elettrodomestico.append(elettrodomesticoAnnuncio);
            }
             body = body.replaceAll("<!-- ELETTRODOMESTICI DA AGGIUNGERE DINAMICAMENTE QUI-->", elettrodomestico.toString());
        }
        StringBuilder cameraDisponibile = new StringBuilder();
        if(camereDisponibili.isEmpty()){
            body = body.replaceAll("<!-- CAMERE DISPONIBILI DA AGGIUNGERE DINAMICAMENTE QUI-->", "Nessuna camera disponibile!");
        }else{
            for(CameraDisponibile c: camereDisponibili){
                if(c==null)
                    break;
                String cameraDisponibileAnnuncio = HtmlReader.htmlReader("cameraDisponibileDinamico.html");
                tmp = cameraDisponibileAnnuncio.replaceAll("numeroPostiLetto", String.valueOf(c.getPostiLetto()));
                cameraDisponibileAnnuncio = tmp;
                tmp = cameraDisponibileAnnuncio.replaceAll("disponibiliReplace", String.valueOf(c.getPostiLettoDisponibili()));
                cameraDisponibileAnnuncio = tmp;
                cameraDisponibile.append(cameraDisponibileAnnuncio);
            }
            tmp = body.replaceAll("<!-- CAMERE DISPONIBILI DA AGGIUNGERE DINAMICAMENTE QUI-->", cameraDisponibile.toString());
            sb.append(tmp);
        }
        return sb.toString();
    }
    /**
     * Metodo che sostituisce i campi all'interno dell'annuncio che si vuole visualizzare.
     * (VERSIONE NON LOGGATO : NON VEDO I CONTATTI).
     * @param annuncio Oggetto AnnuncioCasa contenente i campi che devono essere sostituiti.
     * @return Ritorna la stringa contenente l'html con i campi correttamente sostituiti.
     * @throws FileNotFoundException  Eccezzione generata dal mancato reperimento del file html.
     */
     private static String annuncioNonLoggato(AnnuncioCasa annuncio) throws FileNotFoundException{
  
        StringBuilder sb = new StringBuilder();
        sb.append(HtmlReader.htmlReader("headerNonLoggato.html"));
        String body = HtmlReader.htmlReader("formAnnuncioNonLoggato.html");
        ArrayList<String> elementiDaModificareDinamici = new ArrayList<>();
        ArrayList<String> elementiDaModificareHtml = new ArrayList<>();
        
        elementiDaModificareDinamici.add(String.valueOf(annuncio.getMetriQuadri()));
        elementiDaModificareDinamici.add(String.valueOf(annuncio.getDistanzaCentro()));
        elementiDaModificareDinamici.add(String.valueOf(annuncio.getNumeroBagni()));
        elementiDaModificareDinamici.add(String.valueOf(annuncio.getnLocali()));
        elementiDaModificareDinamici.add(annuncio.getSessoCasa().toString()); 
        boolean cucinaSeparata = annuncio.isCucinaSeparata();
        if(cucinaSeparata==true)
            elementiDaModificareDinamici.add("SI");
        else
            elementiDaModificareDinamici.add("NO");
        elementiDaModificareDinamici.add(annuncio.getDescrizioneAggiuntiva());
        elementiDaModificareDinamici.add(String.valueOf(annuncio.getCosto()));
        elementiDaModificareDinamici.add(annuncio.getNomeCognomeProprietario());
        elementiDaModificareDinamici.add(annuncio.getCitta() +" - "+annuncio.getIndirizzoCasa());

        elementiDaModificareDinamici.add(annuncio.getDataCreazioneAnnuncio());
        
        elementiDaModificareHtml.add("metriQuadrati");
        elementiDaModificareHtml.add("distanzaDalCentro");
        elementiDaModificareHtml.add("numeroBagni");
        elementiDaModificareHtml.add("numeroLocali");
        elementiDaModificareHtml.add("sessoCasa");
        elementiDaModificareHtml.add("cucinaSeparata");
        elementiDaModificareHtml.add("descrizioneAggiuntivaAnnuncio");
        elementiDaModificareHtml.add("costoAnnuncioInEuro");
        elementiDaModificareHtml.add("nomeCognomeProprietario");
        elementiDaModificareHtml.add("indirizzoCasa");
        elementiDaModificareHtml.add("dataCreazioneAnnuncio");
        
        
        for (int i = 0; i < elementiDaModificareDinamici.size(); i++) {
            String tmp = body.replaceAll(elementiDaModificareHtml.get(i), elementiDaModificareDinamici.get(i));
            body = tmp;
        }
        
        ArrayList<ElettroDomestico> elettrodomestici = annuncio.getElettroDomestici();
        ArrayList<CameraDisponibile> camereDisponibili = annuncio.getCamere();
        
        String tmp;
        StringBuilder elettrodomestico = new StringBuilder();
        if(elettrodomestici.isEmpty()==true){
            body = body.replaceAll("<!-- ELETTRODOMESTICI DA AGGIUNGERE DINAMICAMENTE QUI-->", "Nessun Elettrodomestico presente nell'annuncio!");
        }else{
            for(ElettroDomestico e: elettrodomestici){
                if(e==null)
                    break;
                String elettrodomesticoAnnuncio = HtmlReader.htmlReader("elettrodomesticiDinamico.html");
                elettrodomesticoAnnuncio = elettrodomesticoAnnuncio.replaceAll("nomeElettrodomestico", e.toString());
                elettrodomestico.append(elettrodomesticoAnnuncio);
            }
             body = body.replaceAll("<!-- ELETTRODOMESTICI DA AGGIUNGERE DINAMICAMENTE QUI-->", elettrodomestico.toString());
        }
        StringBuilder cameraDisponibile = new StringBuilder();
        if(camereDisponibili.isEmpty()){
            body = body.replaceAll("<!-- CAMERE DISPONIBILI DA AGGIUNGERE DINAMICAMENTE QUI-->", "Nessuna camera disponibile!");
        }else{
            for(CameraDisponibile c: camereDisponibili){
                if(c==null)
                    break;
                String cameraDisponibileAnnuncio = HtmlReader.htmlReader("cameraDisponibileDinamico.html");
                tmp = cameraDisponibileAnnuncio.replaceAll("numeroPostiLetto", String.valueOf(c.getPostiLetto()));
                cameraDisponibileAnnuncio = tmp;
                tmp = cameraDisponibileAnnuncio.replaceAll("numeroPostiLettoDisponibili", String.valueOf(c.getPostiLettoDisponibili()));
                cameraDisponibileAnnuncio = tmp;
                cameraDisponibile.append(cameraDisponibileAnnuncio);
            }
            tmp = body.replaceAll("<!-- CAMERE DISPONIBILI DA AGGIUNGERE DINAMICAMENTE QUI-->", cameraDisponibile.toString());
            sb.append(tmp);
        }
        return sb.toString();
    }
   
}
