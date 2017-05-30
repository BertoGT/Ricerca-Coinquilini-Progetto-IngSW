/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Casa.Citta;
import Exceptions.LoginException;
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
//            System.out.println(s.logIn("berto@gmail.com", "ciao"));
            s.registrazioneUtente("Marco", "La Salvia", Sesso.M, "mrc.lasalvia@gmail.com", "1234", 
                    11, 12, 1995, "340425353", Nazione.ITALIA, Occupazione.STUDENTE, Facolta.INGEGNERIA, 
                    false, false, true, Citta.PAVIA, true);
            System.out.println("c");
        } catch (SQLException ex) {
            Logger.getLogger(TestSistema.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (LoginException ex) {
//            System.out.println(ex.getMessage());
        } catch (RegistrazioneException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(TestSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
