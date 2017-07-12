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
class NLocali extends ParametroRicercaAnnuncio{
    private int nLocali;
    /**
     * ISTANZIA UN OGGETTO DI TIPO NLOCALI CHE È UN PARAMETRO PER LA RICERCA DI UN ANNUNCIO
     * @param stelle INDICA IL NUMERO DI STELLE CHE VENGONO PASSATE NELLA RICERCA, LE STELLE INDICANO QUANTO È IMPORTANTE QUESTO PARAMETRO PER LA RICERCA
     * @param nLocali INDICA IL NUMERO DI LOCALI DESIDERATI
     */
    NLocali(int stelle, int nLocali) {
        super(stelle);
        this.nLocali = nLocali;
    }
    /**
     * CALCOLA L'AFFINITA DEL SINGOLO PARAMETRO NUMERO DI LOCALI
     * @param annuncio ANNUNCIO DELLA CASA IN QUESTIONE
     * @return RITORNA UN NUMERO FLOAT CHE INDICA L'AFFINITA
     */
    @Override
    float calcolaAffinità(AnnuncioCasa annuncio) {
        if(nLocali > annuncio.getnLocali())
            return 0;
        else
            return super.getStelle();
    }
    
    
    
}
