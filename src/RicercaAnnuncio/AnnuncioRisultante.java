/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaAnnuncio;

import Casa.AnnuncioCasa;

/**
 *
 * @author alberto
 */
public class AnnuncioRisultante {
    private final float punteggio;
    private final AnnuncioCasa annuncio;

    public AnnuncioRisultante(AnnuncioCasa annuncio, float punteggio) {
        this.punteggio = punteggio;
        this.annuncio = annuncio;
    }

    public float getPunteggio() {
        return punteggio;
    }

    public AnnuncioCasa getAnnuncio() {
        return annuncio;
    }
    
    
}
