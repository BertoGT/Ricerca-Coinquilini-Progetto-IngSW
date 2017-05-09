/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaCoinquilino;

import ProfiloUtente.DatiUtente;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Delbo
 */
public class RicercaCoinquilino {
    
    private ArrayList<DatiUtente> utentiTotali;
    private ArrayList<ParametroRicercaCoinquilino> parametriRicerca;
    private ArrayList<CoinquilinoRisultante> coinquiliniRisultanti;

    public RicercaCoinquilino(ArrayList<DatiUtente> utentiTotali, ArrayList<ParametroRicercaCoinquilino> parametriRicerca) {
        this.utentiTotali = utentiTotali;
        this.parametriRicerca = parametriRicerca;
        this.coinquiliniRisultanti= new ArrayList<>();
    }
    
    private void calcolaAffinita() {
        for (DatiUtente dati : utentiTotali) {
            float affinitaTotale = 0;
            int totaleStelle = 0;
            boolean coinquilinoIncompatibile = false;
            
            for (ParametroRicercaCoinquilino parametroRicerca : parametriRicerca) {
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
