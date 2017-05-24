/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaCoinquilino;

import ProfiloUtente.Utente;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Delbo
 */
public class RicercaCoinquilino {
    
    private ArrayList<Utente> utentiTotali;
    private ContenitoreParametriCoinquilino parametriRicerca;
    private ArrayList<CoinquilinoRisultante> coinquiliniRisultanti;

    public RicercaCoinquilino(ContenitoreParametriCoinquilino parametriRicerca) {
        // TODO carica gli utenti che si candidano da DB
        this.parametriRicerca = parametriRicerca;
        this.coinquiliniRisultanti= new ArrayList<>();
    }
    
    private void calcolaAffinita() {
        for (Utente dati : utentiTotali) {
            if(dati==null)
                break;
            float affinitaTotale = 0;
            int totaleStelle = 0;
            boolean coinquilinoIncompatibile = false;
            
            for (ParametroRicercaCoinquilino parametroRicerca : parametriRicerca.getParametri()) {
                if(parametroRicerca==null)
                    break;
                totaleStelle += parametroRicerca.getStelle();
                float affinita = parametroRicerca.calcolaAffinità(dati);
                if(affinita == -1) {
                    // coinquilino da escludere ai risultati
                    coinquilinoIncompatibile = true;
                    break;                      
                } else 
                    affinitaTotale += affinita;
            }
            if(!coinquilinoIncompatibile) {
                float punteggio = affinitaTotale * 100 / totaleStelle;
                this.coinquiliniRisultanti.add(new CoinquilinoRisultante(dati, punteggio));
            }      
        }
    }
    
    public ArrayList<CoinquilinoRisultante> eseguiRicerca() {
        calcolaAffinita();
        Collections.sort(coinquiliniRisultanti);
        return coinquiliniRisultanti;
    } 
    
    
    
}
