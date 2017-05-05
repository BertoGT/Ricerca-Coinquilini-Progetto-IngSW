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
public class CittaDiRicerca extends ParametroRicercaCoinquilino{

    private String citta;
    
    public CittaDiRicerca(String citta) {
        super(0);
        this.citta = citta;
    }

    @Override
    public float calcolaAffinità(DatiUtente utente) {
        if(this.citta.equals(utente.getCittaDiRicerca()))
            return 0;
        else
            return -1;
    }
    
    
}
