/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Marco La Salvia
 */
public class User extends WebSurfer implements Power{
    private int power;
    private String eMail, password;
    private boolean loggedIn;
    
    public User(int numeroUtente, String eMail, String password) throws FileNotFoundException {
        super(numeroUtente);
        this.eMail = eMail;
        this.password = password;
        this.loggedIn = false;
        this.power = 1;
    }
    public void logIn(String eMail, String password) throws FileNotFoundException{
            Scanner s = new Scanner(new File("loginProva.txt"));
            int i = 0, j = 1;
            while(s.hasNextLine()){
                String riga = s.nextLine();
                String[] userPass = riga.split("\t");
                if(userPass[i].equals(eMail)&& userPass[j].equals(password)){
                    this.loggedIn = true;
                    System.out.println("User Loggato: "+eMail+"," +password+"\n");
                    break;
                }else
                    System.out.println("utente sbagliato\n");
                i++;
                j++;
            }
    }
    @Override
    public int getPower() {
        return this.power;
    }
    
}
