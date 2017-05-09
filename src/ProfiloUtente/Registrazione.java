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
    public Registrazione(String nome, String cognome, Sesso sesso, String eMail, String password, int giorno, int mese, int anno) throws ParseException, FileNotFoundException, EmailAlreadyExistsException {
        super(nome, cognome, sesso, eMail, password, giorno, mese, anno);
        bm = new BusinessModel();
        /*
        DA RIFARE IN QUANTO IL COLLEGAMENTO AL BM E' STATO GIA MESSO NEL SISTEMA!
        O COMUNQUE BISOGNA RISTRUTTURARLO MEGLIO.
        */
        if(this.controlloDB())
            this.scriviDb();
        else
            throw new EmailAlreadyExistsException("Email già presente sul database! registrarsi con un'altra email.\n");
    }

    public Registrazione(String nome, String cognome, Sesso sesso, String eMail, String password, int giorno, int mese, int anno, String cellulare, Nazionalita nazionalita, Occupazione occupazione, Facolta facolta, boolean fumatore, boolean cuoco, boolean sportivo, String cittaDiRicerca) throws ParseException {
        super(nome, cognome, sesso, eMail, password, giorno, mese, anno, cellulare, nazionalita, occupazione, facolta, fumatore, cuoco, sportivo, cittaDiRicerca);
    }
    private void scriviDb() throws FileNotFoundException{
        bm.scriviFile(this.toStringDB());
    }
    private boolean controlloDB(){
        if(this.bm.getEmailPresenti().contains(this.geteMail()))
            return false;
        else
            return true;
    }

    public BusinessModel getBm() {
        return bm;
    }

        
    
}
