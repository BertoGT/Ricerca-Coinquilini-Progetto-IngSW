/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import BusinessModel.BusinessModelAnnuncio;
import Casa.CameraDisponibile;
import Casa.Citta;
import Casa.ElettroDomestico;
import Casa.HouseGenerality;
import Casa.InfoCasa;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alberto
 */
public class TestConDBAnnunci {
    public static void main(String[] args) throws SQLException {
        
            BusinessModelAnnuncio bm = BusinessModelAnnuncio.getInstance();
            
//            int idProprietario = 10;
//            int idCasa = bm.inserisciInfoCasa(new InfoCasa(70, 2, 1, 1200, true, Citta.PAVIA, "via mia 7", HouseGenerality.MASCHI));
//            System.out.println(bm.inserisciAnnuncioCasa(idCasa ,idProprietario, "casa nico", 390));
            System.out.println(bm.eliminaAnnuncioCasa(9, 10));
//            System.out.println(bm.eliminaCamera(3));
//            System.out.println(bm.eliminaTuttiElettrodomestici(9));
////////            System.out.println(bm.modificaAnnuncioCasa(1, "modifica", 200));
////////            System.out.println(bm.modificaInfoCasa(1, 70, 3, 1, 300, HouseGenerality.MASCHI, true, Citta.AOSTA, "ciao"));
//            System.out.println(bm.inserisciCamera(idCasa, new CameraDisponibile(1, 2, 0)));
////            System.out.println(bm.inserisciCamera(idCasa, new CameraDisponibile(2, 1, 0)));
////////            System.out.println(bm.modificaCamera(1, new CameraDisponibile(1, 2, 0)));
//            System.out.println(bm.inserisciElettrodomestico(idCasa, ElettroDomestico.LAVATRICE));  
////            System.out.println(bm.inserisciElettrodomestico(idCasa, ElettroDomestico.FORNO)); 
////            System.out.println(bm.inserisciElettrodomestico(idCasa, ElettroDomestico.MICROONDE)); 
////            System.out.println(bm.eliminaElettrodomestico(1, ElettroDomestico.LAVATRICE));
    }
}
