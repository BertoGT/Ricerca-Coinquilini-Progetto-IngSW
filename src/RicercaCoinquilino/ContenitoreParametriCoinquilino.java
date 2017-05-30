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

    public void setSesso(Sesso sesso) {
        this.sesso = sesso;
    }
      
    
    public void setParametroCuoco(int stelle, Boolean isCuoco){
        this.parametri.add(new Cuoco(stelle, isCuoco));
    }
    
    public void setParametroEta(int stelle,int etaMin,int etaMax){
        this.parametri.add(new Eta(stelle,etaMin,etaMax));
    }
    
    public void setParametroFacolta(int stelle,ProfiloUtente.Facolta facolta){
        this.parametri.add(new Facolta(stelle, facolta));
    }
    
    public void setParametroFumatore(int stelle,boolean isFumatore){
        this.parametri.add(new Fumatore(stelle, isFumatore));
    }
    
    public void setParametroNazionalita(int stelle,ProfiloUtente.Nazione nazionalita){
        this.parametri.add(new Nazionalita(stelle, nazionalita));
    }
    
    public void setParametroOccupazione(int stelle,ProfiloUtente.Occupazione occupazione){
        this.parametri.add(new Occupazione(stelle, occupazione));
    } 
    
    public void setParametroSportivo(int stelle,boolean sportivo){
        this.parametri.add(new Sportivo(stelle, sportivo));
    }

    public ArrayList<ParametroRicercaCoinquilino> getParametri() {
        return parametri;
    }
    
    
    

}
