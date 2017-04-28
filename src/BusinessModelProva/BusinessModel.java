/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModelProva;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Marco La Salvia
 */
public class BusinessModel {
    private String nomeFile;
    private PrintWriter buffer;

    public BusinessModel(){
        this.nomeFile = "file/registrazioni.txt";
    }
    private void apriFile() throws FileNotFoundException{
        this.buffer = new PrintWriter(new FileOutputStream(nomeFile, true));
    }
    private void chiudiFile(){
        this.buffer.close();
    }
    public void scriviFile(String s) throws FileNotFoundException{
        this.apriFile();
        this.buffer.println(s);
        this.chiudiFile();
    } 

    public String getNomeFile() {
        return nomeFile;
    }

    public void setNomeFile(String nomeFile) {
        this.nomeFile = nomeFile;
    }
}
