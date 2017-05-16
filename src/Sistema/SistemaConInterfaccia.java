/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import BusinessModelProva.BusinessModelProva;
import Exceptions.EmailAlreadyExistsException;
import Exceptions.PasswordNonInseritaException;
import Exceptions.UserNotFoundException;
import ProfiloUtente.Registrazione;
import ProfiloUtente.Sesso;
import Utenti.*;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Marco La Salvia
 */
public class SistemaConInterfaccia {
    private WebSurfer webSurfer;
    private Guest guest;
    private User user;
    private boolean init;       //FINCHE QUESTO FLAG NON E' TRUE LE ALTRE AZIONI DI SISTEMA NON SARANNO VISIBILI/EFFETTUABILI
    private IdTemporanei contatore;
    private BusinessModelProva bm;

    public SistemaConInterfaccia() throws FileNotFoundException {
        this.contatore = new IdTemporanei();
        this.bm = new BusinessModelProva();
        this.webSurfer = new WebSurfer(this.contatore.getIdCreati());        //VA CREATO UN METODO PRIVATO CHE ATTRIBUISCA L'ID LEGGENDO DA FILE L'ULTIMO NUMERO E SUCCESSIVAMENTE RISCRIVENDO DOVE SI E' ARRIVATI
        this.contatore.incrementaId();
        this.init = false; 
    }
    
    public void procediComeGuest(){
        this.guest = new Guest(this.webSurfer.getNumeroUtente());
        this.init = true;
    }
    public void registrati() throws ParseException, FileNotFoundException, EmailAlreadyExistsException{
        String nome = JOptionPane.showInputDialog("INTRODURRE NOME E PREMERE 'OK' : ");
        String cognome = JOptionPane.showInputDialog("INTRODURRE COGNOME E PREMERE 'OK' : ");
        String sesso = JOptionPane.showInputDialog("INTRODURRE M OPPURE F E PREMERE 'OK' : ");
        String eMail = JOptionPane.showInputDialog("INTRODURRE E-MAIL E PREMERE 'OK' : ");
        String password = this.checkPassword(this.introdurrePassword());        //ANDRA MESSO UN LOOP PER IL CONTROLLO CHE SIA INSERITA UNA PASSWORD(EVENTUALMENTE CON REGOLE)
        int giorno = Integer.parseInt(JOptionPane.showInputDialog("INTRODURRE GIORNO DI NASCITA E PREMERE 'OK' : "));
        int mese = Integer.parseInt(JOptionPane.showInputDialog("INTRODURRE MESE DI NASCITA E PREMERE 'OK' : "));
        int anno = Integer.parseInt(JOptionPane.showInputDialog("INTRODURRE ANNO DI NASCITA E PREMERE 'OK' : "));
        Registrazione nuovaRegistrazione = new Registrazione(nome, cognome, Sesso.valueOf(sesso), eMail, password, giorno, mese, anno);
        JOptionPane.showMessageDialog(null, "UNA VOLTA EFFETTUATO IL LOGIN SARA' POSSIBILE COMPLETARE IL PROFILO UTENTE, QUESTI ERANO I DATI OBBLIGATORI", "INFO MESSAGE", JOptionPane.INFORMATION_MESSAGE);
    }
    private String introdurrePassword(){
        JPanel panel = new JPanel();
        JLabel label = new JLabel("INTRODURRE PASSWORD PER REGISTRARSI E PREMERE 'OK' :");
        JPasswordField pass = new JPasswordField(15);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"OK", "CANCEL"};
        int option = JOptionPane.showOptionDialog(null, panel, "INTRODURRE PASSWORD",
                                 JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                 null, options, options[1]);
        if(option == 0){
            // UTENTE HA SCHIACCIATO "OK"
            char[] password = pass.getPassword();
            return String.valueOf(password);
        }
        return null;
    }
    private String checkPassword(String s){
        if(s==null){
            while(s==null){
                s = this.introdurrePassword();
            }
        }
        return s;
    }
    public void logIn() throws UserNotFoundException{
        String eMail = JOptionPane.showInputDialog("INTRODURRE E-MAIL E PREMERE 'OK' : ");
        String password = this.checkPassword(this.introdurrePassword());
        if(this.bm.getUtentiRegistrati().containsKey(eMail)){
            if(this.bm.getUtentiRegistrati().get(eMail).equals(password)){
                this.user = new User(this.webSurfer.getNumeroUtente(), null);
                this.user.setLoggedIn(true);
                this.init = true;
            }
        }else
            throw new UserNotFoundException("eMail non trovata all'interno del database: Utente inesistente.");
    }
    /*
    VA AGGIUNTO UN QUALCOSA CHE PERMETTA DI PROCEDERE COME GUEST/REGISTRARSI/EFFETTUARE IL LOGIN SOLO IN UN CERTO ORDINE O COMUNQUE SE LOGGATO NON SI PUO' EFFETTUARE REGISTRAZIONE
    FINCHE NON SI FA LOGOUT ECC..
    */
    
    
}
