/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaCoinquilino;

import ProfiloUtente.DatiUtente;
import ProfiloUtente.Utente;

/**
 *
 * @author Niko
 */
abstract class ParametroRicercaCoinquilino {
    
    private int stelle;

    ParametroRicercaCoinquilino(int stelle) {
        this.stelle = stelle;
    }

    
    public int getStelle() {
        return stelle;
    }
    
    abstract float calcolaAffinità(Utente utente);
}
