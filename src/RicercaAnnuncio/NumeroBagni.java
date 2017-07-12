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
class NumeroBagni extends ParametroRicercaAnnuncio{
    private int numeroBagni;
    /**
     * ISTANZIA UN OGGETTO DI TIPO NUMEROBAGNI CHE È UN PARAMETRO PER LA RICERCA DI UN ANNUNCIO
     * @param stelle INDICA IL NUMERO DI STELLE CHE VENGONO PASSATE NELLA RICERCA, LE STELLE INDICANO QUANTO È IMPORTANTE QUESTO PARAMETRO PER LA RICERCA
     * @param nLocali INDICA IL NUMERO DI LOCALI DESIDERATI
     */
    NumeroBagni(int stelle, int numeroBagni) {
        super(stelle);
        this.numeroBagni = numeroBagni;
    }
    /**
     * CALCOLA L'AFFINITA DEL SINGOLO PARAMETRO NUMERO DI BAGNI
     * @param annuncio ANNUNCIO DELLA CASA IN QUESTIONE
     * @return RITORNA UN NUMERO FLOAT CHE INDICA L'AFFINITA
     */
    @Override
    float calcolaAffinità(AnnuncioCasa annuncio) {
        if(numeroBagni <= annuncio.getNumeroBagni())
            return super.getStelle();
        else
            return 0;
    }
    
}
