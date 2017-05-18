/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaCoinquilino;

import ProfiloUtente.Utente;

/**
 *
 * @author Niko
 */
public class Fumatore extends ParametroRicercaCoinquilino{
    
    private boolean fumatore;

    public Fumatore(int stelle, boolean fumatore) {
        super(stelle);
        this.fumatore = fumatore;
    }

    @Override
    public float calcolaAffinità(Utente utente) {
        if(fumatore)
            return super.getStelle();
        else {
            if(utente.getDatiUtente().isFumatore())
                return 0;
            else 
                return super.getStelle();
        }
    }
}
