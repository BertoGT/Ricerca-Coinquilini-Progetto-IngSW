/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import BusinessModel.BusinnessModelUtente;
import ProfiloUtente.Facolta;
import ProfiloUtente.Nazionalita;
import ProfiloUtente.Occupazione;
import ProfiloUtente.Sesso;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Niko
 */
public class TestConDb {
    public static void main(String[] args) {
        try {
            BusinnessModelUtente bm = new BusinnessModelUtente();       
            //System.out.println(bm.registrazione("nico@gmail.com", "ciao", 1, true));
            //System.out.println(bm.login("nico@gmail.com", "sucamelo"));
            //System.out.println(bm.modificaPassword(4, "ciao", "bobo"));
            //System.out.println(bm.setCandidatura(1, false));
//            System.out.println(bm.inserisciAnagraficaUtente(1, "nicholas", "farina", 10, 3, 1995, Sesso.M, Nazionalita.Italiana, "Pavia"));
//            System.out.println(bm.modificaCittaDiRicerca(1, "Milano"));

            System.out.println(bm.inserisciInfoUtente(1, false, false, true, Occupazione.STUDENTE, Facolta.TecnicoScientifico));
            System.out.println(bm.modificaInfoUtente(1, true, true, true, Occupazione.LAVORATORE, Facolta.Altro));
            
        } catch (SQLException ex) {
            Logger.getLogger(TestConDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
