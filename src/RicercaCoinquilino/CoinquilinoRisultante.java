/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaCoinquilino;

import ProfiloUtente.Utente;

/**
 *
 * @author Delbo
 */
public class CoinquilinoRisultante implements Comparable<CoinquilinoRisultante>{
    
    private final Utente utente;
    private final float punteggio;

    public CoinquilinoRisultante(Utente utente, float punteggio) {
        this.utente = utente;
        this.punteggio = punteggio;
    }

    public Utente getUtente() {
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
