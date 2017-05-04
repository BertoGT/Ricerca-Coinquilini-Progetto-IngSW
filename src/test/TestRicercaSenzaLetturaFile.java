/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Casa.AnnuncioCasa;
import Casa.HouseGender;
import Exceptions.CameraNonInseritaException;
import RicercaAnnuncio.AnnuncioRisultante;
import RicercaAnnuncio.ContenitoreParametriAnnuncio;
import RicercaAnnuncio.RicercaAnnuncio;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicholas
 */
public class TestRicercaSenzaLetturaFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CameraNonInseritaException {
        ArrayList<AnnuncioCasa> annunci = new ArrayList<>();
        AnnuncioCasa a1 = new AnnuncioCasa("prova", 1, 250);
        
        a1.creaInfo(70, 2, 1, 1000, true, "Pavia", "prova", HouseGender.MASCHI);
        a1.creaCamera(1, 2, 1);
        a1.creaCamera(2,1,1);
        annunci.add(a1);
        
        AnnuncioCasa a2 = new AnnuncioCasa("prova", 2, 500);
        a2.creaInfo(100, 3, 2, 200, false, "Pavia", "Via Ferrini, 77", HouseGender.FEMMINE);
        a2.creaCamera(1, 1, 1);
        a2.creaCamera(2, 1, 0);
        annunci.add(a2);
        
        AnnuncioCasa a3 = new AnnuncioCasa("prova", 3, 250);
        a3.creaInfo(60, 2, 1, 3000, true, "Pavia", "Via Ferrini, 77",HouseGender.MISTA);
        a3.creaCamera(1, 3, 1);
        a3.creaCamera(2, 2, 1);
        annunci.add(a3);
        
        ContenitoreParametriAnnuncio c = new ContenitoreParametriAnnuncio();
        c.setParametroCitta("Pavia");
        c.setParametroCosto(300);
        c.setParametroCucina(2, true);
        c.setParametroDistCentro(3, 2000);
        c.setParametroNBagni(4, 2);
        c.setParametroNLocali(2, 2);
        c.setParametroSessoCasa(3, HouseGender.MISTA);
        c.setParametroTipoCamera(4, 1);
        
        RicercaAnnuncio r = new RicercaAnnuncio(annunci, c.getParametriRicerca());
        
        ArrayList<AnnuncioRisultante> a = r.eseguiRicerca();   
        System.out.println(a);
    }
    
}
