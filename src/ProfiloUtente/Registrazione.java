/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfiloUtente;

import BusinessModelProva.BusinessModel;
import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 *
 * @author Marco La Salvia
 */
public class Registrazione extends DatiUtente {
    
    BusinessModel bm;
    public Registrazione(String nome, String cognome, String sesso, String eMail, String password, int giorno, int mese, int anno) throws ParseException, FileNotFoundException {
        super(nome, cognome, sesso, eMail, password, giorno, mese, anno);
        this.scriviDb();
    }

    public Registrazione(String nome, String cognome, String sesso, String eMail, String password, int giorno, int mese, int anno, String nazionalita, Occupazione occupazione, Facolta facolta, boolean fumatore, boolean cuoco, boolean sportivo) throws ParseException {
        super(nome, cognome, sesso, eMail, password, giorno, mese, anno, nazionalita, occupazione, facolta, fumatore, cuoco, sportivo);
    }
    private void scriviDb() throws FileNotFoundException{
        bm = new BusinessModel();
        bm.scriviFile(this.toStringDB());
    }

        
    
}
