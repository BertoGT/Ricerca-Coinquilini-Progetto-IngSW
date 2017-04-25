/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import ProfiloUtente.DatiUtente;
import java.text.ParseException;

/**
 *
 * @author Margherita
 */
public class TestUtente {

    public static void main(String args[]) throws ParseException {
        
        
        DatiUtente prova= new DatiUtente("Margherita", "Ricotti", "F", 2, 12, 1996);
        
        System.out.println(prova.getDataDiNascita().getTime());
        
        System.out.println(prova.getEta());
        
         System.out.println(prova.toString());
         
         prova.setDataDiNascita(2, 11, 1995);
         
          System.out.println(prova.getDataDiNascita().getTime());
         
         System.out.println(prova.getEta());
         
    }
    
    
}
