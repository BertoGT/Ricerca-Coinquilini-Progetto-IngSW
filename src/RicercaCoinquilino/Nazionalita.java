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
class Nazionalita extends ParametroRicercaCoinquilino {

    private ProfiloUtente.Nazione nazione;
    
    Nazionalita(int stelle,ProfiloUtente.Nazione nazionalita) {
        super(stelle);
        this.nazione=nazionalita;
    }

    @Override
    float calcolaAffinità(Utente utente) {
        if(utente.getDatiUtente().getNazionalita()==this.nazione)
            return super.getStelle();
        else
            return 0;
    }
    
}
