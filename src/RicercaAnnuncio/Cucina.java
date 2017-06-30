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
class Cucina extends ParametroRicercaAnnuncio{
    private boolean cucinaSeparata;

    /**
     * ISTANZIA UN OGGETTO DI TIPO CUCINA CHE È UN PARAMETRO PER LA RICERCA DI UN ANNUNCIO
     * @param stelle INDICA IL NUMERO DI STELLE CHE VENGONO PASSATE NELLA RICERCA, LE STELLE INDICANO QUANTO È IMPORTANTE QUESTO PARAMETRO PER LA RICERCA
     * @param cucinaSeparata INDICA TRUE SE LA CUCINA È SEPARATA DAL SALONE, ALTRIMENTI FALSE
     */
    Cucina(int stelle, boolean cucinaSeparata) {
        super(stelle);
        this.cucinaSeparata = cucinaSeparata;
    }
    
    
    /**
     * CALCOLA L'AFFINITA DEL SINGOLO PARAMETRO A CUCINA 
     * @param annuncio ANNUNCIO DELLA CASA IN QUESTIONE
     * @return RITORNA UN NUMERO FLOAT CHE INDICA L'AFFINITA
     */
    @Override
    float calcolaAffinità(AnnuncioCasa annuncio) {
        if(cucinaSeparata){
            if(annuncio.isCucinaSeparata()) 
                return super.getStelle();
            else 
                return 0;
        } else 
            return super.getStelle();
    }
    
    
}
