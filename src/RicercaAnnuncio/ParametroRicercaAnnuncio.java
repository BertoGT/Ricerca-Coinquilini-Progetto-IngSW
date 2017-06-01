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
abstract class ParametroRicercaAnnuncio {
    
    private int stelle;

    ParametroRicercaAnnuncio(int stelle) {
        this.stelle = stelle;
    }

    int getStelle() {
        return stelle;
    }
    
    abstract float calcolaAffinità(AnnuncioCasa annuncio);
    
    
    
}
