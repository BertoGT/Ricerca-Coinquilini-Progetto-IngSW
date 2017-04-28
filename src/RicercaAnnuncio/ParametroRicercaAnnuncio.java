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
public abstract class ParametroRicercaAnnuncio {
    
    private int stelle;
    protected TipoParametro type;

    public ParametroRicercaAnnuncio(int stelle) {
        this.stelle = stelle;
    }

    
    public int getStelle() {
        return stelle;
    }

    public TipoParametro getType() {
        return type;
    }
    
    public abstract float calcolaAffinità(AnnuncioCasa annuncio);
    
    
    
}
