/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaCoinquilino;

import Casa.Citta;
import ProfiloUtente.DatiUtente;
import ProfiloUtente.Utente;

/**
 *
 * @author Niko
 */
public class CittaDiRicerca extends ParametroRicercaCoinquilino{

    private Citta
            citta;
    
    public CittaDiRicerca(Citta citta) {
        super(0);
        this.citta = citta;
    }

    @Override
    public float calcolaAffinità(Utente utente) {
        if(this.citta.equals(utente.getDatiUtente().getCittaDiRicerca()))
            return 0;
        else
            return -1;
    }
    
    
}
