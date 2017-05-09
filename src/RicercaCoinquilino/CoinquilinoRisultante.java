/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaCoinquilino;

import ProfiloUtente.DatiUtente;

/**
 *
 * @author Delbo
 */
public class CoinquilinoRisultante implements Comparable<CoinquilinoRisultante>{
    
    private final DatiUtente utente;
    private final float punteggio;

    public CoinquilinoRisultante(DatiUtente utente, float punteggio) {
        this.utente = utente;
        this.punteggio = punteggio;
    }

    public DatiUtente getUtente() {
        return utente;
    }

    public float getPunteggio() {
        return punteggio;
    }

    @Override
    public int compareTo(CoinquilinoRisultante t) {
        return (int) (t.getPunteggio() - this.punteggio);
    }

    @Override
    public String toString() {
        return this.utente.toString() +"\t"+  punteggio ;
    }
    
    
    
    
    
}
