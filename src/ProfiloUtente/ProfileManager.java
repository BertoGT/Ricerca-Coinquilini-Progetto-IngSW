/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfiloUtente;

import BusinessModel.BusinessModelUtente;
import Exceptions.AnnuncioException;
import Casa.AnnuncioCasa;
import Casa.Citta;
import Exceptions.PasswordException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Marco La Salvia
 */
public class ProfileManager {
    private Utente utente;
    private AnnuncioCasa annuncioCasa;

    public ProfileManager(Utente utente, AnnuncioCasa annuncioCasa) {
        this.utente = utente;
        this.annuncioCasa = annuncioCasa;
    }

    public Utente getUtente() {
        return utente;
    }

    public AnnuncioCasa getAnnuncioCasa() {
        return annuncioCasa;
    }

    public void setAnnuncioCasa(AnnuncioCasa annuncioCasa) {
        this.annuncioCasa = annuncioCasa;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
    
    public void modificaProfilo(Facolta facolta, Citta citta, Occupazione occupazione, 
            boolean fumatore, boolean sportivo, boolean cuoco, boolean candidato, String password) 
            throws SQLException, ParseException, PasswordException {
        
        BusinessModelUtente bm = BusinessModelUtente.getInstance();
        int idUtente = utente.getIdUtente();
        bm.modificaCittaDiRicerca(idUtente, citta);
        bm.setCandidatura(idUtente, candidato);
        bm.modificaInfoUtente(idUtente, new DatiUtente(null, null, null, null, 
                null, 0, 0, 0, null, null, occupazione, facolta, fumatore, cuoco,
                sportivo, null, false));
        
        if(!password.equals(""))
            bm.modificaPassword(idUtente, password, password);
    }


    @Override
    public String toString() {
        return "\nPROFILO UTENTE\n" + this.utente.toString()+"\n";
    }
    
    public void creaAnnuncio(String descrizioneAggiuntiva, int idAnnuncio, int costo, String nomeCognomeProprietario, String cellulareProprietario, String emailProprietario) throws AnnuncioException{
        if(this.annuncioCasa==null) {
            this.annuncioCasa = new AnnuncioCasa(utente.getIdUtente(), descrizioneAggiuntiva, 0, costo, 
                    nomeCognomeProprietario, cellulareProprietario, emailProprietario, new Date(System.currentTimeMillis()));
            
        } else
            throw new AnnuncioException("Cancellare annuncio precedente prima di crearne uno nuovo!");
    }  
    
}
