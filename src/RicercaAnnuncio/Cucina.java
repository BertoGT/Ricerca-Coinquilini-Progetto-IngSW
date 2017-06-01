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

    Cucina(int stelle, boolean cucinaSeparata) {
        super(stelle);
        this.cucinaSeparata = cucinaSeparata;
    }
    
    

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
