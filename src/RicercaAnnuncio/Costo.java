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
public class Costo extends ParametroRicercaAnnuncio{
    private int costoMax;

    public Costo(int costoMax) {
        super(0);
        this.costoMax = costoMax;
    }
    
    

    @Override
    public float calcolaAffinità(AnnuncioCasa annuncio) {
        // TODO pensare al giusto peso per il costo
        if(costoMax<annuncio.getCosto())
            return -1;
        else
            return 0;        
    }
    
}
