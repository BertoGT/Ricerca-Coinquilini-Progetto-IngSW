/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Exceptions.LoginException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistema.Sistema;

/**
 *
 * @author Marco La Salvia
 */
public class TestSistema {
    public static void main(String[] args) {
        try {
            Sistema s = new Sistema();
            s.logIn("berto@gmail.com", "ciao");
        } catch (SQLException ex) {
            Logger.getLogger(TestSistema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LoginException ex) {
            Logger.getLogger(TestSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
