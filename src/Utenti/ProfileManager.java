/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;

import Casa.AnnuncioCasa;
import Casa.HouseGender;
import Exceptions.CameraNonInseritaException;
import ProfiloUtente.DatiUtente;
import ProfiloUtente.Sesso;
import java.text.ParseException;

/**
 *
 * @author ilcasa
 */
public class ProfileManager extends DatiUtente{
    
    private AnnuncioCasa annuncioCasa;
    public ProfileManager(String nome, String cognome, Sesso sesso, String eMail, String password, int giorno, int mese, int anno) throws ParseException {
        super(nome, cognome, sesso, eMail, password, giorno, mese, anno);
    }

    public AnnuncioCasa getAnnuncioCasa() {
        return annuncioCasa;
    }
    
    public void creaAnnuncioCasa(String descrizioneAggiuntiva, int idAnnuncio, int costo, String nomeCognomeProprietario, String cellulareProprietario, String emailProprietario) {
        this.annuncioCasa = new AnnuncioCasa(descrizioneAggiuntiva, idAnnuncio, costo, nomeCognomeProprietario, cellulareProprietario, emailProprietario);
    }
    
    public void aggiungiCamera(int idCamera, int postiLetto, int postiLettoDisponibili) throws CameraNonInseritaException{
        this.annuncioCasa.creaCamera(idCamera, postiLetto, postiLettoDisponibili);
    }
    
     public void creaInfo(int metriQuadri, int nLocali, int numeroBagni,int distanzaCentro, boolean cucinaSeparata, String citta, String indirizzo, HouseGender sessoCasa){
         this.annuncioCasa.creaInfo(metriQuadri, nLocali, numeroBagni, distanzaCentro, cucinaSeparata, citta, indirizzo, sessoCasa);
     }
}
