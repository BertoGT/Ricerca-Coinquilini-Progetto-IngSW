package test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Exceptions.EmailAlreadyExistsException;
import Exceptions.UserNotFoundException;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistema.Sistema;

/**
 *
 * @author Marco La Salvia
 */
public class TestSystem {
    public static void main(String[] args) {
        try {
            Sistema s = new Sistema();
            s.procediComeGuest();
            s.registrati();
            s.logIn();
        } catch (FileNotFoundException | ParseException | EmailAlreadyExistsException | UserNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
