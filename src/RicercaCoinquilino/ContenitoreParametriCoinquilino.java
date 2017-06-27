/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaCoinquilino;

import Casa.Citta;
import ProfiloUtente.Sesso;
import java.util.ArrayList;

/**
 *
 * @author Niko
 */
public class ContenitoreParametriCoinquilino {
    
    private ArrayList<ParametroRicercaCoinquilino> parametri;
    private Sesso sesso;
    private Citta cittaDiRicerca;

    /**
     * Istanzia un oggetto che contiene i parametri di ricerca del potenziale coinquilino
     * @param cittaDiRicerca citta in cui cercare il coinquilino
     */
    public ContenitoreParametriCoinquilino(Citta cittaDiRicerca) {
        this.parametri = new ArrayList<>();
        this.cittaDiRicerca = cittaDiRicerca;
    }

    public Sesso getSesso() {
        return sesso;
    }

    public Citta getCittaDiRicerca() {
        return cittaDiRicerca;
    }

    /**
     * Imposta il sesso del potenziale coinquilino
     * @param sesso sesso da applicare alla ricerca (se null trascura il parametro)
     */
    public void setSesso(Sesso sesso) {
        this.sesso = sesso;
    }
      
    /**
     * Imposta se il potenziale coinquilino deve essere cuoco o meno
     * @param stelle peso che viene assegnato al parametro
     * @param isCuoco 
     */
    public void setParametroCuoco(int stelle, Boolean isCuoco){
        this.parametri.add(new Cuoco(stelle, isCuoco));
    }
    
    /**
     * Imposta eta minima e massima del poteziale coinquilino
     * @param stelle peso che viene assegnato al parametro
     * @param etaMin
     * @param etaMax 
     */
    public void setParametroEta(int stelle,int etaMin,int etaMax){
        this.parametri.add(new Eta(stelle,etaMin,etaMax));
    }
    
    /**
     * Imposta la facolta del potenziale coinquilino
     * @param stelle peso che viene assegnato al parametro
     * @param facolta enum della facolta frequentata
     */
    public void setParametroFacolta(int stelle,ProfiloUtente.Facolta facolta){
        this.parametri.add(new Facolta(stelle, facolta));
    }
    
    /**
     * Imposta se il coinquilino può essre fumatore o meno
     * @param stelle peso che viene assegnato al parametro
     * @param isFumatore 
     */
    public void setParametroFumatore(int stelle,boolean isFumatore){
        this.parametri.add(new Fumatore(stelle, isFumatore));
    }
    
    /**
     * Imposta la nazionalita del coinquilino
     * @param stelle peso che viene assegnato al parametro
     * @param nazione enum della nazione di nascita del coinquilino
     */
    public void setParametroNazionalita(int stelle,ProfiloUtente.Nazione nazione){
        this.parametri.add(new Nazionalita(stelle, nazione));
    }
    
    /**
     * Imposta l'occupazione
     * @param stelle peso che viene assegnato al parametro
     * @param occupazione 
     */
    public void setParametroOccupazione(int stelle,ProfiloUtente.Occupazione occupazione){
        this.parametri.add(new Occupazione(stelle, occupazione));
    } 
    
    /**
     * Imposta se il potenziale coinquilino deve essere sportivo o meno
     * @param stelle peso che viene assegnato al parametro
     * @param sportivo 
     */
    public void setParametroSportivo(int stelle,boolean sportivo){
        this.parametri.add(new Sportivo(stelle, sportivo));
    }

    /**
     * Ritorna i parametri inseriti per la ricerca del coinquilino
     * @return lista dei parametri di ricerca inseriti
     */
    public ArrayList<ParametroRicercaCoinquilino> getParametri() {
        return parametri;
    }
    
    
    

}
