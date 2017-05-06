/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModelProva;

import java.io.*;
import java.util.*;

/**
 *
 * @author Marco La Salvia
 */
public class BusinessModel {
    private String nomeFile;
    private PrintWriter writer;
    private Scanner reader;
    private ArrayList<String> emailPresenti;
    private HashMap<String, String> utentiRegistrati;

    public BusinessModel() throws FileNotFoundException{
        this.nomeFile = "file/registrazioni.txt";
        this.emailPresenti = new ArrayList<>();
        this.utentiRegistrati = new HashMap<>();
        this.caricaEmail();
        this.caricaUtentiRegistrati();
    }
    private void apriFileWriter() throws FileNotFoundException{
        this.writer = new PrintWriter(new FileOutputStream(nomeFile, true));
    }
    private void chiudiFileWriter(){
        this.writer.close();
    }
    public void scriviFile(String s) throws FileNotFoundException{
        this.apriFileWriter();
        this.writer.println(s);
        this.chiudiFileWriter();
    } 

    public String getNomeFile() {
        return nomeFile;
    }

    public void setNomeFile(String nomeFile) {
        this.nomeFile = nomeFile;
    }
    
    private void apriFileReader() throws FileNotFoundException{
        this.reader = new Scanner(new File(this.nomeFile));
    }
    
    private void chiudiFileReader(){
        this.reader.close();
    }
    
    private void caricaEmail() throws FileNotFoundException{
        ArrayList<String> eMails = new ArrayList<>();
        this.apriFileReader();
        while(this.reader.hasNextLine()){
            String riga = this.reader.nextLine();
            String[] elem = riga.split("\t");
            eMails.add(elem[0]);           
        }
        this.emailPresenti = eMails;
        this.chiudiFileReader();
    }
    
    private void caricaUtentiRegistrati() throws FileNotFoundException{
        this.apriFileReader();
        while(this.reader.hasNextLine()){
            String riga = this.reader.nextLine();
            String[] elem = riga.split("\t");
            this.utentiRegistrati.put(elem[0], elem[1]);
        }
        this.chiudiFileReader();
    }

    public HashMap<String, String> getUtentiRegistrati() {
        return utentiRegistrati;
    }

    public ArrayList<String> getEmailPresenti() {
        return emailPresenti;
    }
    
}
