/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ilcasa
 */
public class TestConnessioneDb {
    
    
    public static void main(String[] args)  {
        String user = "rooming";
        String pass = "facoltativo";
        //su altervista da problemi, dobbiamo cambiare il sito di hosting
        try{    
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://rooming.altervista.org:3306/my_rooming",user, pass);
            Statement myStatement = (Statement) conn.createStatement();
            ResultSet myResult = myStatement.executeQuery("SELECT * FROM UTENTI");
            while (myResult.next()){
                System.out.println(myResult.getString("Nome"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestConnessioneDb.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
}

