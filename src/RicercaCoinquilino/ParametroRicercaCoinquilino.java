/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaCoinquilino;

import ProfiloUtente.DatiUtente;

/**
 *
 * @author Niko
 */
public abstract class ParametroRicercaCoinquilino {
    
    private int stelle;

    public ParametroRicercaCoinquilino(int stelle) {
        this.stelle = stelle;
    }

    
    public int getStelle() {
        return stelle;
    }
    
    public abstract float calcolaAffinità(DatiUtente utente);
}
