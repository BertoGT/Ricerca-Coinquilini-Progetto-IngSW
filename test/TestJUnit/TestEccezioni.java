/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestJUnit;

import Casa.AnnuncioCasa;
import Casa.Citta;
import Casa.HouseGenerality;
import Exceptions.CameraNonTrovataException;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Delbo
 */
public class TestEccezioni {
    
    @Test(expected=java.lang.Exception.class)
    public void TestEccezioneCamere() throws CameraNonTrovataException{
        AnnuncioCasa a1=new AnnuncioCasa("casa", 0, 0, "nomeCognomeProprietario", "cellulareProprietario", "emailProprietario");
        a1.creaInfo(0, 0, 0, 0, true, Citta.AOSTA, "indirizzo", HouseGenerality.MASCHI);
        a1.rimuoviCamera(0);
   
    }
    
}
