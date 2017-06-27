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

    NumeroBagni(int stelle, int numeroBagni) {
        super(stelle);
        this.numeroBagni = numeroBagni;
    }

    @Override
    float calcolaAffinità(AnnuncioCasa annuncio) {
        if(numeroBagni <= annuncio.getNumeroBagni())
            return super.getStelle();
        else
            return 0;
    }
    
}
