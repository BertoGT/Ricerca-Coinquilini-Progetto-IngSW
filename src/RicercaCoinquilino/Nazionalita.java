/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaCoinquilino;

import ProfiloUtente.Utente;

/**
 *
 * @author Delbo
 */
public class Nazionalita extends ParametroRicercaCoinquilino {

    private ProfiloUtente.Nazionalita nazionalita;
    
    public Nazionalita(int stelle,ProfiloUtente.Nazionalita nazionalita) {
        super(stelle);
        this.nazionalita=nazionalita;
    }

    @Override
    public float calcolaAffinità(Utente utente) {
        if(utente.getDatiUtente().getNazionalita()==this.nazionalita)
            return super.getStelle();
        else
            return 0;
    }
    
}
