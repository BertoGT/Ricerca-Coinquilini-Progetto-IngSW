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
public class AnnuncioRisultante implements Comparable<AnnuncioRisultante>{
    private final float punteggio;
    private final AnnuncioCasa annuncio;

    /**
     * ISTANZIA UN OGGETTO CHE INDICA L'ANNUNCIO RISULTANTE
     * @param annuncio ANNUNCIO IN QUESTIONE
     * @param punteggio PUNTEGGIO DI CIASCUN ANNUNCIO
     */
    AnnuncioRisultante(AnnuncioCasa annuncio, float punteggio) {
        this.punteggio = punteggio;
        this.annuncio = annuncio;
    }

    public float getPunteggio() {
        return punteggio;
    }

    public AnnuncioCasa getAnnuncio() {
        return annuncio;
    }

    /**
     * METODO PER COMPARARE GLI ANNUNCI 
     * @param o
     * @return UN NUMERO MAGGIORE DI 0 SE IL PRIMO ELEMENTO È MAGGIORE DEL SECONDO MINORE DI 0
     */
    @Override
    public int compareTo(AnnuncioRisultante o) {
        int diff = (int) (o.getPunteggio() - this.punteggio);
        if(diff == 0) 
            return o.getAnnuncio().getCosto() - this.annuncio.getCosto();
        else
            return diff;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.punteggio).append(" ").append(this.annuncio.getIdAnnuncio())
                .append(" ").append(this.annuncio.getDescrizioneAggiuntiva());
        return s.toString();
    }

    
    
    
}
