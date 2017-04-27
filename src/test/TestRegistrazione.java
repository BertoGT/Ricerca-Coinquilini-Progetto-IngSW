package test;


import ProfiloUtente.Registrazione;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marco La Salvia
 */
public class TestRegistrazione {
    public static void main(String[] args) {
        try {
            Registrazione r = new Registrazione("marco", "la salvia", "m", "mrc.lasalvia@gmail.com","1234",11,12,1995);
            System.out.println(r.toString());
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestRegistrazione.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
