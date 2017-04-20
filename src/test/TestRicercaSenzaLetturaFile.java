/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Casa.AnnuncioCasa;
import Exceptions.CameraNonInseritaException;
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
    public static void main(String[] args) {
        ArrayList<AnnuncioCasa> annunci = new ArrayList<>();
        AnnuncioCasa a1 = new AnnuncioCasa("prova", 1, 250);
        
        a1.creaInfo(100, 2, 1, true, "Pavia", "Via Ferrini, 77");
        try {
            a1.creaCamera(1, 2, 1);
        } catch (CameraNonInseritaException ex) {
            Logger.getLogger(TestRicercaSenzaLetturaFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        annunci.add(a1);
        
        AnnuncioCasa a2 = new AnnuncioCasa("prova", 1, 350);
        a2.creaInfo(100, 2, 1, true, "Pavia", "Via Ferrini, 77");
        try {
            a2.creaCamera(1, 2, 1);
        } catch (CameraNonInseritaException ex) {
            Logger.getLogger(TestRicercaSenzaLetturaFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        annunci.add(a2);
        
        AnnuncioCasa a3 = new AnnuncioCasa("prova", 1, 250);
        a3.creaInfo(100, 2, 1, true, "Pavia", "Via Ferrini, 77");
        try {
            a3.creaCamera(1, 2, 1);
        } catch (CameraNonInseritaException ex) {
            Logger.getLogger(TestRicercaSenzaLetturaFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        annunci.add(a3);
        
        ArrayList<AnnuncioCasa> annunciRisultati;
        annunciRisultati = new RicercaAnnuncio(annunci, "Pavia", 0, 0, 0, 0, 0, 0, 300).eseguiRicerca();
        System.out.println("prova");
    }
    
}
