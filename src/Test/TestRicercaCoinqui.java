/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import ProfiloUtente.DatiUtente;
import ProfiloUtente.Facolta;
import ProfiloUtente.Nazionalita;
import ProfiloUtente.Occupazione;
import ProfiloUtente.Sesso;
import RicercaCoinquilino.ContenitoreParametriCoinquilino;
import RicercaCoinquilino.RicercaCoinquilino;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Delbo
 */
public class TestRicercaCoinqui {/*
    
    public static void main(String[] args) throws ParseException {
        ArrayList<DatiUtente> dati=new ArrayList<>();
        DatiUtente dati1=new DatiUtente("Piero", "Chiatante", Sesso.M, "pieromail", "password", 10, 7, 1993, "cellulare", Nazionalita.Asiatica, Occupazione.Lavoratore, Facolta.Altro, true, true, true, "Milano");
        DatiUtente dati2= new DatiUtente("Mario", "Shehu", Sesso.M, "cazzaromail", "password", 10, 7, 1995, "cellulare", Nazionalita.Balcanica, Occupazione.Studente, Facolta.TecnicoScientifico, true, true, true,"Pavia");
        DatiUtente dati3= new DatiUtente("Margherita", "Ricotti", Sesso.F, "marghemail", "password", 10, 12, 1996, "cellulare", Nazionalita.Italiana, Occupazione.Studente, Facolta.TecnicoScientifico, false, false, false, "Pavia");
        DatiUtente dati4= new DatiUtente("lorenzo", "Bonati", Sesso.M, "loremail", "password", 2, 2, 1995, "cellulare", Nazionalita.Italiana, Occupazione.Altro, Facolta.Altro, true, false, true, "Pavia");
        
        dati.add(dati1);
        dati.add(dati2);
        dati.add(dati3);
        dati.add(dati4);
        
        ContenitoreParametriCoinquilino c=new ContenitoreParametriCoinquilino();
        c.setParametroCittaDiRicerca("Pavia");
        c.setParametroCuoco(3, Boolean.TRUE);
        c.setParametroEta(5, 18, 22);
        c.setParametroFacolta(3, Facolta.TecnicoScientifico);
        c.setParametroOccupazione(3, Occupazione.Lavoratore);
        c.setParametroFumatore(0, true);
        c.setParametroSesso(Sesso.M);
        c.setParametroSportivo(1, true);
        c.setParametroNazionalita(4, Nazionalita.Italiana);
        
        RicercaCoinquilino r=new RicercaCoinquilino(c);
        System.out.print(r.eseguiRicerca());
    }*/
}
