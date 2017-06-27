/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Casa.Citta;
import Casa.HouseGenerality;
import Exceptions.LoginException;
import Exceptions.NessunAnnuncioException;
import Exceptions.RegistrazioneException;
import ProfiloUtente.Facolta;
import ProfiloUtente.Nazione;
import ProfiloUtente.Occupazione;
import ProfiloUtente.Sesso;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistema.Sistema;

/**
 *
 * @author Marco La Salvia
 */
public class TestSistema {
    public static void main(String[] args) {
        try {
            Sistema s = new Sistema();
//            System.out.println(s.logIn("mario.shehu.07@gmail.com", "proteine"));
            s.registrazioneUtente("Antonio", "Licchello", Sesso.M, "antonio.licchello@gmail.com", "cicciofranco", 
                    10, 11, 1995, "36547474", Nazione.ITALIA, Occupazione.STUDENTE, Facolta.INGEGNERIA, 
                    false, true, true, Citta.PAVIA, true);
//            s.iniziaRicercaAnnunci(Citta.PAVIA);
//            s.setCostoMax(270);
//            s.setParametroNBagni(4, 1);
//            s.setParametroSessoCasa(3, HouseGenerality.MASCHI);
//            s.setParametroDistCentro(5, 1000);
//            s.ricercaAnnuncio();
//            System.out.println("c");
        } catch (Exception ex) {
            Logger.getLogger(TestSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
