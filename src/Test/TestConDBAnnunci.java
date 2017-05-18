/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import BusinessModel.BusinnessModelAnnuncio;
import Casa.Citta;
import Casa.HouseGenerality;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alberto
 */
public class TestConDBAnnunci {
    public static void main(String[] args) {
        try {
            BusinnessModelAnnuncio bm = new BusinnessModelAnnuncio();
            
            int idCasa = bm.inserisciInfoCasa(50, 2, 1, 1000, HouseGenerality.MASCHI, true, Citta.PAVIA, "indirizzo");
            System.out.println(bm.inserisciAnnuncioCasa(idCasa, 1, "descrizione", 300));
        } catch (SQLException ex) {
            Logger.getLogger(TestConDBAnnunci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
