/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaAnnuncio;

import Casa.AnnuncioCasa;

/**
 *
 * @author Delbo
 */
public class DistanzaCentro extends ParametroRicercaAnnuncio {

    private int distanzaMax;
    /**
     * ISTANZIA UN OGGETTO DI TIPO DISTANZACENTRO CHE È UN PARAMETRO PER LA RICERCA DI UN ANNUNCIO
     * @param stelle INDICA IL NUMERO DI STELLE CHE VENGONO PASSATE NELLA RICERCA, LE STELLE INDICANO QUANTO È IMPORTANTE QUESTO PARAMETRO PER LA RICERCA
     * @param distanzaMax  INDICA LA DISTANZA MASSIMA DAL CENTRO
     */
    public DistanzaCentro(int stelle,int distanzaMax) {
        super(stelle);
        this.distanzaMax=distanzaMax;
    }
    /**
     * CALCOLA L'AFFINITA DEL SINGOLO PARAMETRO DISTANZACENTRO 
     * @param annuncio ANNUNCIO DELLA CASA IN QUESTIONE
     * @return RITORNA UN NUMERO FLOAT CHE INDICA L'AFFINITA
     */
    @Override
    public float calcolaAffinità(AnnuncioCasa annuncio) {
        if(this.distanzaMax<annuncio.getDistanzaCentro()) 
            return 0;
        
        else if(annuncio.getDistanzaCentro()==0)
            return super.getStelle();
        else
            return super.getStelle() - 
                    ( ( (float) super.getStelle()*annuncio.getDistanzaCentro()) / distanzaMax);
        
    }
    
}
