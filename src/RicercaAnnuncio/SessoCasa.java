/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaAnnuncio;

import Casa.AnnuncioCasa;
import Casa.HouseGenerality;

/**
 *
 * @author alberto
 */
class SessoCasa extends ParametroRicercaAnnuncio{
    private HouseGenerality sesso;
    /**
     * ISTANZIA UN OGGETTO DI TIPO SESSO CASA( HOUSE GENERALITY) CHE È UN PARAMETRO PER LA RICERCA DI UN ANNUNCIO
     * @param stelle INDICA IL NUMERO DI STELLE CHE VENGONO PASSATE NELLA RICERCA, LE STELLE INDICANO QUANTO È IMPORTANTE QUESTO PARAMETRO PER LA RICERCA
     * @param nLocali INDICA IL NUMERO DI LOCALI DESIDERATI
     */
    SessoCasa(int stelle, HouseGenerality sesso) {
        super(stelle);
        this.sesso = sesso;
    }
    /**
     * CALCOLA L'AFFINITA DEL SINGOLO PARAMETRO HOUSE GENERALITY
     * @param annuncio ANNUNCIO DELLA CASA IN QUESTIONE
     * @return RITORNA UN NUMERO FLOAT CHE INDICA L'AFFINITA
     */
    @Override
    float calcolaAffinità(AnnuncioCasa annuncio) {
        if(sesso == HouseGenerality.MISTA) {
            if(sesso == annuncio.getSessoCasa())
                return super.getStelle();
            else 
                return ((float)super.getStelle() / 2);
        } else {
            if(sesso == annuncio.getSessoCasa())
                return super.getStelle();
            else if(annuncio.getSessoCasa() == HouseGenerality.MISTA)
                return ((float)super.getStelle() / 2);
            else 
                return 0;
        } 
    }
    
}
