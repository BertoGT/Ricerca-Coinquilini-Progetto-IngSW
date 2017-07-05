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
                String profileManager = HtmlReader.htmlReader("profileManager.html");
                profileManager = this.sostituisciCampiProfilo(profileManager, pM);
                profileManager = this.caricaAnnuncioSePresente(profileManager, pM);
                response.setStatus(200);
                response.getWriter().println(profileManager);
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
        String newProfileManager = profileManager;
        
        DataDiNascita dataDiNascitaUtente = pM.getUtente().getDatiUtente().getDataDiNascita();
        Occupazione occupazioneUtente = pM.getUtente().getDatiUtente().getOccupazione();
        Facolta facoltaUtente = pM.getUtente().getDatiUtente().getFacolta();
        Citta cittaDiRicerca = pM.getUtente().getDatiUtente().getCittaDiRicerca();
        String emailUtente = pM.getUtente().getDatiUtente().geteMail();
        String numeroDiTelefonoUtente = pM.getUtente().getDatiUtente().getNumeroDiTelefono();
        Sesso sessoUtente = pM.getUtente().getDatiUtente().getSesso();
        Nazione nazionalitaUtente = pM.getUtente().getDatiUtente().getNazionalita();
        boolean utenteSportivo = pM.getUtente().getDatiUtente().isSportivo();
        boolean utenteFumatore = pM.getUtente().getDatiUtente().isFumatore();
        boolean utenteCuoco = pM.getUtente().getDatiUtente().isCuoco();
        boolean candidatoCoinquilino = pM.getUtente().getDatiUtente().isPotenzialeCoinquilino();
        
        profileManager.replaceAll("dataDiNascitaUtente", dataDiNascitaUtente.toString());
        profileManager.replaceAll("occupazioneUtente", occupazioneUtente.toString());
        profileManager.replaceAll("facoltaUtente", facoltaUtente.toString());
        profileManager.replaceAll("cittaDiRicerca", cittaDiRicerca.toString());
        profileManager.replaceAll("emailUtente", emailUtente);
        profileManager.replaceAll("numeroDiTelefonoUtente", numeroDiTelefonoUtente);
        profileManager.replaceAll("sessoUtente", sessoUtente.toString());
        profileManager.replaceAll("nazionalitaUtente", nazionalitaUtente.toString());
        if(utenteSportivo==true)
            profileManager.replaceAll("utenteSportivo", "SI");
        else
            profileManager.replaceAll("utenteSportivo", "NO");
        
        if(utenteCuoco==true)
            profileManager.replaceAll("utenteCuoco", "SI");
        else
            profileManager.replaceAll("utenteCuoco", "NO");
        
        if(utenteFumatore==true)
            profileManager.replaceAll("utenteFumatore", "SI");
        else
            profileManager.replaceAll("utenteFumatore", "NO");
        
        if(candidatoCoinquilino==true)
            profileManager.replaceAll("candidatoCoinquilino", "SI");
        else
            profileManager.replaceAll("candidatoCoinquilino", "NO");
        return newProfileManager;
    }
    private String caricaAnnuncioSePresente(String profileManager, ProfileManager pM){
        String newProfileManager = profileManager;
        try{
           if(pM.getAnnuncioCasa()== null){
               String nessunAnnuncioPerIlProfilo = HtmlReader.htmlReader("nessunAnnuncioProfiloDinamico.html");
               profileManager.replaceAll("<!--testoAnnuncioSostituzioneDinamica-->", nessunAnnuncioPerIlProfilo);
           }else{
               AnnuncioCasa annuncio = pM.getAnnuncioCasa();
               String annuncioPerIlProfilo = HtmlReader.htmlReader("annuncioDinamicoProfileManager.html");
               String buttonPerAnnuncio = HtmlReader.htmlReader("risultatoCasa/buttonAnnuncio.html");
               annuncioPerIlProfilo.replaceAll("<!-- BUTTON ANNUNCIO-->", buttonPerAnnuncio);
               annuncioPerIlProfilo = this.sostituisciCampiAnnuncio(annuncioPerIlProfilo, annuncio);
               profileManager.replaceAll("<!--testoAnnuncioSostituzioneDinamica-->", annuncioPerIlProfilo);
               
           }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return newProfileManager;
    }
    private String sostituisciCampiAnnuncio(String annuncioPerIlProfilo, AnnuncioCasa annuncio) throws FileNotFoundException{
        String newAnnuncio = annuncioPerIlProfilo;
        int metriQuadrati = annuncio.getMetriQuadri();
        int distanzaDalCentro = annuncio.getDistanzaCentro();
        int numeroBagni = annuncio.getNumeroBagni();
        int numeroLocali = annuncio.getnLocali();
        HouseGenerality sessoCasa = annuncio.getSessoCasa();
        boolean cucinaSeparata = annuncio.isCucinaSeparata();
        ArrayList<ElettroDomestico> elettrodomestici = annuncio.getElettroDomestici();
        ArrayList<CameraDisponibile> camereDisponibili = annuncio.getCamere();
        String descrizioneAggiuntivaAnnuncio = annuncio.getDescrizioneAggiuntiva();
        int costoAnnuncioInEuro = annuncio.getCosto();
        String nomeCognomeProprietario = annuncio.getNomeCognomeProprietario();
        String indirizzoCasa = annuncio.getCitta() +" - "+annuncio.getIndirizzoCasa();
        String numeroDiTelefonoProprietario = annuncio.getCellulareProprietario();
        String emailProprietario = annuncio.getEmailProprietario();
        String dataCreazioneAnnuncio = annuncio.getDataCreazioneAnnuncio();
        
        annuncioPerIlProfilo.replaceAll("metriQuadrati", String.valueOf(metriQuadrati));
        annuncioPerIlProfilo.replaceAll("distanzaDalCentro", String.valueOf(distanzaDalCentro));
        annuncioPerIlProfilo.replaceAll("numeroBagni", String.valueOf(numeroBagni));
        annuncioPerIlProfilo.replaceAll("numeroLocali", String.valueOf(numeroLocali));
        annuncioPerIlProfilo.replaceAll("costoAnnuncioInEuro", String.valueOf(costoAnnuncioInEuro));
        annuncioPerIlProfilo.replaceAll("sessoCasa", sessoCasa.toString());
        annuncioPerIlProfilo.replaceAll("descrizioneAggiuntivaAnnuncio", descrizioneAggiuntivaAnnuncio);
        annuncioPerIlProfilo.replaceAll("nomeCognomeProprietario", nomeCognomeProprietario);
        annuncioPerIlProfilo.replaceAll("indirizzoCasa", indirizzoCasa);
        annuncioPerIlProfilo.replaceAll("numeroDiTelefonoProprietario", numeroDiTelefonoProprietario);
        annuncioPerIlProfilo.replaceAll("emailProprietario", emailProprietario);
        annuncioPerIlProfilo.replaceAll("dataCreazioneAnnuncio", dataCreazioneAnnuncio);
        
        if(cucinaSeparata==true)
            annuncioPerIlProfilo.replaceAll("cucinaSeparata", "SI");
        else
            annuncioPerIlProfilo.replaceAll("cucinaSeparata", "NO");
        
        StringBuilder elettrodomestico = new StringBuilder();
        if(elettrodomestici.isEmpty()==true){
            annuncioPerIlProfilo.replaceAll("<!-- ELETTRODOMESTICI DA AGGIUNGERE DINAMICAMENTE QUI-->", "Nessun Elettrodomestico presente nell'annuncio!");
        }else{
            for(ElettroDomestico e: elettrodomestici){
                if(e==null)
                    break;
                String elettrodomesticoAnnuncio = HtmlReader.htmlReader("elettrodomesticiDinamico.html");
                elettrodomesticoAnnuncio.replaceAll("nomeElettrodomestico", e.toString());
                elettrodomestico.append(elettrodomesticoAnnuncio);
            }
            annuncioPerIlProfilo.replaceAll("<!-- ELETTRODOMESTICI DA AGGIUNGERE DINAMICAMENTE QUI-->", elettrodomestico.toString());
        }
        
        StringBuilder cameraDisponibile = new StringBuilder();
        if(camereDisponibili.isEmpty()==true){
            annuncioPerIlProfilo.replaceAll("<!-- CAMERE DISPONIBILI DA AGGIUNGERE DINAMICAMENTE QUI-->", "Nessuna camera disponibile!");
        }else{
            for(CameraDisponibile c: camereDisponibili){
                if(c==null)
                    break;
                String cameraDisponibileAnnuncio = HtmlReader.htmlReader("cameraDisponibileDinamico.html");
                cameraDisponibileAnnuncio.replaceAll("numeroPostiLetto", String.valueOf(c.getPostiLetto()));
                cameraDisponibileAnnuncio.replaceAll("numeroPostiLettoDisponibili", String.valueOf(c.getPostiLettoDisponibili()));
                cameraDisponibile.append(cameraDisponibileAnnuncio);
            }
            annuncioPerIlProfilo.replaceAll("<!-- ELETTRODOMESTICI DA AGGIUNGERE DINAMICAMENTE QUI-->", cameraDisponibile.toString());
        }
        return newAnnuncio;
    }
    
}
