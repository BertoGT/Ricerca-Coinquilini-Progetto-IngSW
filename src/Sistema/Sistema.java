/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import BusinessModelProva.BusinessModel;
import Exceptions.EmailAlreadyExistsException;
import Exceptions.UserNotFoundException;
import ProfiloUtente.Registrazione;
import ProfiloUtente.Sesso;
import RicercaAnnuncio.AnnuncioRisultante;
import RicercaAnnuncio.ContenitoreParametriAnnuncio;
import RicercaAnnuncio.RicercaAnnuncio;
import Utenti.Guest;
import Utenti.IdTemporanei;
import Utenti.User;
import Utenti.WebSurfer;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;;

/**
 *
 * @author Delbo
 */
public class Sistema {
    private WebSurfer webSurfer;
    private Guest guest;
    private User user;
    private boolean init;       //FINCHE QUESTO FLAG NON E' TRUE LE ALTRE AZIONI DI SISTEMA NON SARANNO VISIBILI/EFFETTUABILI
    private IdTemporanei contatore;
    private BusinessModel bm;

    public Sistema() throws FileNotFoundException {
        this.contatore = new IdTemporanei();
        this.bm = new BusinessModel();
        this.webSurfer = new WebSurfer(this.contatore.getIdCreati());        //VA CREATO UN METODO PRIVATO CHE ATTRIBUISCA L'ID LEGGENDO DA FILE L'ULTIMO NUMERO E SUCCESSIVAMENTE RISCRIVENDO DOVE SI E' ARRIVATI
        this.contatore.incrementaId();
        this.init = false; 
    }
    
    public void procediComeGuest(){
        this.guest = new Guest(this.webSurfer.getNumeroUtente());
        this.init = true;
    }
    public int registrati(String nome, String cognome, Sesso sesso, String email,
        String password, int giorno, int mese, int anno) 
            throws ParseException, FileNotFoundException, EmailAlreadyExistsException{
        
        return new Registrazione(nome, cognome, sesso, email, password, giorno, mese, anno).scriviDb();
    }

    public int logIn(String eMail, String password) throws UserNotFoundException{
        // TODO richiama il metodo del mainModel che esegue il login
        if(this.bm.getUtentiRegistrati().containsKey(eMail)){
            if(this.bm.getUtentiRegistrati().get(eMail).equals(password)){
                this.user = new User(this.webSurfer.getNumeroUtente(), null);
                this.user.setLoggedIn(true);
                this.init = true;
                return 0; // codice login effettuato
            }
            return 1; // codice password sbagliata
        }else {
            throw new UserNotFoundException("eMail non trovata all'interno del database: Utente inesistente.");
        }
    }
    
    public ArrayList<AnnuncioRisultante> eseguiRicercaCasa(ContenitoreParametriAnnuncio parametriRicerca) {
        return new RicercaAnnuncio(parametriRicerca).eseguiRicerca();
    }
}
