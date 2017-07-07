/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Casa.AnnuncioCasa;
import Casa.CameraDisponibile;
import Casa.Citta;
import Casa.ElettroDomestico;
import Casa.HouseGenerality;
import Exceptions.RegistrazioneException;
import ProfiloUtente.DataDiNascita;
import ProfiloUtente.Facolta;
import ProfiloUtente.Nazione;
import ProfiloUtente.Occupazione;
import ProfiloUtente.ProfileManager;
import ProfiloUtente.Sesso;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
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
public class ProfileServlet extends HttpServlet {
    private Sistema system; 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.system = new Sistema();
            Cookie cookie = request.getCookies()[0]; 
            if(CookieStorage.getInstance().controllaPresenzaCookie(cookie)){
                // utente gia loggato.
                int idUtente = Integer.parseInt(cookie.getName());
                system.settaLoggato(idUtente);
                ProfileManager pM = this.system.getUser().getProfileManager();
                
                String headerLoggato = HtmlReader.htmlReader("headerLoggato.html");
                
                String profileManager = HtmlReader.htmlReader("profileManager.html");
                profileManager = this.sostituisciCampiProfilo(profileManager, pM);
                profileManager = this.caricaAnnuncioSePresente(profileManager, pM);
                response.setStatus(200);
                response.getWriter().println(headerLoggato+profileManager);
            } else {
                cookie.setMaxAge(0); // il cookie non è più valido, dunque lo elimino
                /*
                il cookie non è presente, perciò devo rimandare l'utente ad un'altra pagina!
                */
                response.addCookie(cookie);
                settaNonLoggato(response);
            }
        } catch (NullPointerException ex) {
            settaNonLoggato(response);
            /*
            non sono presenti cookie, l'utente non può essere loggato!
            lo rimando alla pagina del login!
            */
        } catch (SQLException ex) {
            String errorePagina = HtmlReader.htmlReader("erroriVari.html");
            response.setStatus(200);
            response.getWriter().println(errorePagina);     
        }
    }
    
    private void settaNonLoggato(HttpServletResponse response) throws FileNotFoundException, IOException {
        String loginHtml = HtmlReader.htmlReader("login.html");
        response.setStatus(200);
        response.getWriter().println(loginHtml);
    }
    
    private String sostituisciCampiProfilo(String profileManager, ProfileManager pM){
        ArrayList<String> elementiDaModificareDinamici = new ArrayList<>();
        ArrayList<String> elementiDaModificareHtml = new ArrayList<>();
        
        elementiDaModificareDinamici.add(pM.getUtente().getDatiUtente().getDataDiNascita().toString());
        elementiDaModificareDinamici.add(pM.getUtente().getDatiUtente().getOccupazione().toString());
        elementiDaModificareDinamici.add(pM.getUtente().getDatiUtente().getFacolta().toString());
        elementiDaModificareDinamici.add(pM.getUtente().getDatiUtente().getCittaDiRicerca().toString());
        elementiDaModificareDinamici.add(pM.getUtente().getDatiUtente().geteMail());
        elementiDaModificareDinamici.add(pM.getUtente().getDatiUtente().getNumeroDiTelefono());
        elementiDaModificareDinamici.add(pM.getUtente().getDatiUtente().getSesso().toString());
        elementiDaModificareDinamici.add(pM.getUtente().getDatiUtente().getNazionalita().toString());
        boolean utenteSportivo = pM.getUtente().getDatiUtente().isSportivo();
        if(utenteSportivo==true)
            elementiDaModificareDinamici.add("SI");
        else
            elementiDaModificareDinamici.add("NO");
        boolean utenteFumatore = pM.getUtente().getDatiUtente().isFumatore();
        if(utenteFumatore==true)
            elementiDaModificareDinamici.add("SI");
        else
            elementiDaModificareDinamici.add("NO");
        boolean utenteCuoco = pM.getUtente().getDatiUtente().isCuoco();
        if(utenteCuoco==true)
            elementiDaModificareDinamici.add("SI");
        else
            elementiDaModificareDinamici.add("NO");
        boolean candidatoCoinquilino = pM.getUtente().getDatiUtente().isPotenzialeCoinquilino();
        if(candidatoCoinquilino==true)
            elementiDaModificareDinamici.add("SI");
        else
            elementiDaModificareDinamici.add("NO");
        String nomeCognomeUtente = pM.getUtente().getDatiUtente().getCognome()+ " " + pM.getUtente().getDatiUtente().getNome() ;
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
            String tmp = profileManager.replaceAll(elementiDaModificareHtml.get(i), elementiDaModificareDinamici.get(i));
            profileManager = tmp;
        }
        return profileManager;
    }
    
    private String caricaAnnuncioSePresente(String profileManager, ProfileManager pM){
        try{
           if(pM.getAnnuncioCasa()== null){
               String nessunAnnuncioPerIlProfilo = HtmlReader.htmlReader("nessunAnnuncioProfiloDinamico.html");
               String tmp = profileManager.replaceAll("<!--testoAnnuncioSostituzioneDinamica-->", nessunAnnuncioPerIlProfilo);
               profileManager = tmp;
           }
           if(pM.getAnnuncioCasa()!=null){
               AnnuncioCasa annuncio = pM.getAnnuncioCasa();
               String annuncioPerIlProfilo = HtmlReader.htmlReader("annuncioDinamicoProfileManager.html");
               String buttonPerAnnuncio = HtmlReader.htmlReader("risultatoCasa/buttonAnnuncio.html");
               String tmp = annuncioPerIlProfilo.replaceAll("<!-- BUTTON ANNUNCIO-->", buttonPerAnnuncio);
               annuncioPerIlProfilo = tmp;
               
               annuncioPerIlProfilo = this.sostituisciCampiAnnuncio(annuncioPerIlProfilo, annuncio);
               tmp = profileManager.replaceAll("<!--testoAnnuncioSostituzioneDinamica-->", annuncioPerIlProfilo);
               profileManager = tmp;
               
           }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return profileManager;
    }
    private String sostituisciCampiAnnuncio(String annuncioPerIlProfilo, AnnuncioCasa annuncio) throws FileNotFoundException{
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
            String tmp = annuncioPerIlProfilo.replaceAll(elementiDaModificareHtml.get(i), elementiDaModificareDinamici.get(i));
            annuncioPerIlProfilo = tmp;
        }
        
        ArrayList<ElettroDomestico> elettrodomestici = annuncio.getElettroDomestici();
        ArrayList<CameraDisponibile> camereDisponibili = annuncio.getCamere();
        
        String tmp;
        StringBuilder elettrodomestico = new StringBuilder();
        if(elettrodomestici.isEmpty()==true){
            annuncioPerIlProfilo = annuncioPerIlProfilo.replaceAll("<!-- ELETTRODOMESTICI DA AGGIUNGERE DINAMICAMENTE QUI-->", "Nessun Elettrodomestico presente nell'annuncio!");
        }else{
            for(ElettroDomestico e: elettrodomestici){
                if(e==null)
                    break;
                String elettrodomesticoAnnuncio = HtmlReader.htmlReader("risultatoCasa/elettrodomesticiDinamico.html");
                elettrodomesticoAnnuncio = elettrodomesticoAnnuncio.replaceAll("nomeElettrodomestico", e.toString());
                elettrodomestico.append(elettrodomesticoAnnuncio);
            }
             annuncioPerIlProfilo = annuncioPerIlProfilo.replaceAll("<!-- ELETTRODOMESTICI DA AGGIUNGERE DINAMICAMENTE QUI-->", elettrodomestico.toString());
        }
        StringBuilder cameraDisponibile = new StringBuilder();
        if(camereDisponibili.isEmpty()==true){
            annuncioPerIlProfilo = annuncioPerIlProfilo.replaceAll("<!-- CAMERE DISPONIBILI DA AGGIUNGERE DINAMICAMENTE QUI-->", "Nessuna camera disponibile!");
        }else{
            for(CameraDisponibile c: camereDisponibili){
                if(c==null)
                    break;
                String cameraDisponibileAnnuncio = HtmlReader.htmlReader("risultatoCasa/cameraDisponibileDinamico.html");
                tmp = cameraDisponibileAnnuncio.replaceAll("numeroPostiLetto", String.valueOf(c.getPostiLetto()));
                cameraDisponibileAnnuncio = tmp;
                tmp = cameraDisponibileAnnuncio.replaceAll("numeroPostiLettoDisponibili", String.valueOf(c.getPostiLettoDisponibili()));
                cameraDisponibileAnnuncio = tmp;
                cameraDisponibile.append(cameraDisponibileAnnuncio);
            }
            annuncioPerIlProfilo = annuncioPerIlProfilo.replaceAll("<!-- ELETTRODOMESTICI DA AGGIUNGERE DINAMICAMENTE QUI-->", cameraDisponibile.toString());
        }
        return annuncioPerIlProfilo;
    } 
/*
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idAnnuncio = this.system.getUser().getProfileManager().;
    }
    */
}
