/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Casa.AnnuncioCasa;
import Casa.HouseGender;
import Casa.InfoCasa;
import Exceptions.CameraNonInseritaException;
import RicercaAnnuncio.Costo;
import RicercaAnnuncio.SessoCasa;
import RicercaAnnuncio.TipoCamera;

/**
 *
 * @author alberto
 */
public class TestParametriRicercaAnnuncio {
    public static void main(String[] args) throws CameraNonInseritaException {
        /*
        Test parametro sesso casa
        */
        AnnuncioCasa a = new AnnuncioCasa("", 0, 200);
        a.creaInfo(0, 0, 0, 0, true, "", "", HouseGender.FEMMINE);
        SessoCasa s = new SessoCasa(5, HouseGender.MASCHI);
//        System.out.println(s.calcolaAffinità(a));
        
        /*
        Test tipo camera
        */
        a.creaCamera(2, 1, 1);
        a.creaCamera(1, 2, 1);
        TipoCamera t = new TipoCamera(4, 2);
        //System.out.println(t.calcolaAffinità(a));
        
        /*
        Test costo
        */
        Costo c = new Costo(500);
        System.out.println(c.calcolaAffinità(a));
    }
}
