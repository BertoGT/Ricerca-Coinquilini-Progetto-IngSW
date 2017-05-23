/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import BusinessModel.BusinnessModelUtente;
import Casa.Citta;
import ProfiloUtente.DatiUtente;
import ProfiloUtente.Facolta;
import ProfiloUtente.Nazionalita;
import ProfiloUtente.Occupazione;
import ProfiloUtente.Sesso;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Niko
 */
public class TestConDb {
    public static void main(String[] args) throws ParseException {
        try {
            BusinnessModelUtente bm = new BusinnessModelUtente();       
//            int idUtente = bm.registrazione("ricoz@gmail.com", "marghe", true);
//            //System.out.println(bm.login("nico@gmail.com", "sucamelo"));
//            //System.out.println(bm.modificaPassword(4, "ciao", "bobo"));
//            //System.out.println(bm.setCandidatura(1, false));
//            DatiUtente dati = new DatiUtente("Margherita", "Ricotti", Sesso.F, "ricoz@gmail.com",
//                    "marghe", 20, 12, 1996, "3348930567", Nazionalita.Italiana, Occupazione.STUDENTE, Facolta.INGEGNERIA, 
//                    false, false, false, Citta.PAVIA);
//            
//            System.out.println(bm.inserisciAnagraficaUtente(idUtente,dati));
////            System.out.println(bm.modificaCittaDiRicerca(1, "Milano"));
//
//            System.out.println(bm.inserisciInfoUtente(idUtente, dati));
////            System.out.println(bm.modificaInfoUtente(1, true, true, true, Occupazione.LAVORATORE, Facolta.ALTRO));

            
        } catch (SQLException ex) {
            Logger.getLogger(TestConDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
