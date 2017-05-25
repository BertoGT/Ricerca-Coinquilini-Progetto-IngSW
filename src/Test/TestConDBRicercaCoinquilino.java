/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import BusinessModel.BusinessModelAnnuncio;
import Casa.Citta;
import Exceptions.NessunAnnuncioException;
import ProfiloUtente.Sesso;
import ProfiloUtente.Utente;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cl428444
 */
public class TestConDBRicercaCoinquilino {
    public static void main(String[] args) throws ParseException {
        try {
            BusinessModelAnnuncio bm = new BusinessModelAnnuncio();
            ArrayList<Utente> annunci = bm.getAnnunciCoinquilini(Citta.PAVIA, Sesso.M);
            System.out.println("ciao");
        } catch (SQLException ex) {
            Logger.getLogger(TestConDBRicercaCoinquilino.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NessunAnnuncioException ex) {
            Logger.getLogger(TestConDBRicercaCoinquilino.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
