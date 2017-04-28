/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfiloUtente;

import Exceptions.EmailAlreadyExistsException;
import BusinessModelProva.BusinessModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author Marco La Salvia
 */
public class Registrazione extends DatiUtente {
    
    private BusinessModel bm;
    public Registrazione(String nome, String cognome, String sesso, String eMail, String password, int giorno, int mese, int anno) throws ParseException, FileNotFoundException, EmailAlreadyExistsException {
        super(nome, cognome, sesso, eMail, password, giorno, mese, anno);
        if(this.controlloDB())
            this.scriviDb();
        else
            throw new EmailAlreadyExistsException("Email già presente sul database! registrarsi con un'altra email.\n");
    }

    public Registrazione(String nome, String cognome, String sesso, String eMail, String password, int giorno, int mese, int anno, String nazionalita, Occupazione occupazione, Facolta facolta, boolean fumatore, boolean cuoco, boolean sportivo) throws ParseException {
        super(nome, cognome, sesso, eMail, password, giorno, mese, anno, nazionalita, occupazione, facolta, fumatore, cuoco, sportivo);
    }
    private void scriviDb() throws FileNotFoundException{
        bm = new BusinessModel();
        bm.scriviFile(this.toStringDB());
    }
    private boolean controlloDB() throws FileNotFoundException{
        Scanner s = new Scanner(new File("file/registrazioni.txt"));
        while(s.hasNextLine()){
            String riga = s.nextLine();
            String[] elem = riga.split("\t");
            if(elem[0].equals(this.geteMail()))
                return false;
        }
        return true;
    }

    public BusinessModel getBm() {
        return bm;
    }

        
    
}
