/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel;

import Casa.AnnuncioCasa;
import Casa.Citta;
import Casa.ElettroDomestico;
import Casa.HouseGenerality;
import Database.Database;
import Exceptions.LoginException;
import Exceptions.NessunAnnuncioException;
import Exceptions.PasswordException;
import Exceptions.RegistrazioneException;
import ProfiloUtente.DatiUtente;
import ProfiloUtente.Facolta;
import ProfiloUtente.Nazione;
import ProfiloUtente.Occupazione;
import ProfiloUtente.Sesso;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Niko
 */
public class BusinessModelUtente {
    private Database db;
    private static BusinessModelUtente instance = null;
    /**
     * Istanzia un oggetto BusinessModelUtente
     * @throws SQLException 
     */
    BusinessModelUtente() throws SQLException {
        db = Database.getInstance();
    }
    /**
     * 
     * @return Restituisce l'istanza creata BusinessModelUtente
     */
    public static BusinessModelUtente getInstance() {
      if(instance == null) {
          try {
              instance = new BusinessModelUtente();
          } catch (SQLException ex) {
              Logger.getLogger(BusinessModelUtente.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      return instance;
   }
    /**
     * Registra un utente nel database e da la possibilità di candidarsi come coinquilino 
     * @param email email dell'utente che vuole registrarsi
     * @param password password dell'utente che vuole registrarsi
     * @param candidato indica se l'utente vuole candidarsi o meno come potenziale coinquilino
     * @return Restituisce un intero che indica l'id dell'utente
     * @throws SQLException
     * @throws RegistrazioneException lancia un'eccezione se la regisgtrazione non avviene con successo (es.email già in uso)
     */
    
    public int registrazione(String email, String password, boolean candidato) throws SQLException, RegistrazioneException {
        db.apriConnesione();
        ResultSet rs;
        rs = db.registrazione(email, password, 1, candidato);
        if(rs != null && rs.next()) {
            int result = rs.getInt(1);
            db.chiudiConnessione();
            return result;
        }
        else {
            db.chiudiConnessione();
            throw new RegistrazioneException("Email gia in uso");
        }
    }
    /**
     * Verifica se l'utente è registrato e in caso positivo lo logga 
     * @param email email dell'utente registrato
     * @param password password dell'utente registrato
     * @returnRestituisce un intero che indica l'id dell'utente
     * @throws SQLException
     * @throws LoginException lancia un'eccezione se il login non avviene con successo (es.email o password errate)
     */
    public int login(String email, String password) throws SQLException, LoginException {
        db.apriConnesione();
        ResultSet rs;
        rs = db.login(email, password);
        if(rs.next()) {
            int result = rs.getInt(1);
            db.chiudiConnessione();
            return result;
        }else {
            db.chiudiConnessione();
            throw new LoginException("Email o password errata"); // dati sbagliati
        }          
    }
    
    /**
     * Permette all'utente di modificare la propria password
     * @param idUtente id dell'utente registrato
     * @param vecchiaPassword password corrente dell'utente registrato
     * @param nuovaPassword nuova password dell'utente registrato
     * @throws SQLException
     * @throws PasswordException lancia un'eccezione se la modifica della password non avviene con successo (es.vecchia password errate)
     */
    public void modificaPassword(int idUtente, String vecchiaPassword, String nuovaPassword) throws SQLException, PasswordException {
        db.apriConnesione();
        int result = db.modificaPassword(idUtente, vecchiaPassword, nuovaPassword);
        db.chiudiConnessione();
        if(result == 0) 
            throw new PasswordException("Vecchia password errata"); // modifica non avvenuta, vecchia password errata
    }
    /**
     * Permette all'utente di potersi o meno candidare come coinquilino
     * @param idUtente id dell'utente registrato
     * @param candidatura indica se l'utente vuole candidarsi o meno come potenziale coinquilino
     * @return Ritorna true se l'operazione è avvenuta con successo, viceversa false
     * @throws SQLException 
     */
    public boolean setCandidatura(int idUtente, boolean candidatura) throws SQLException {
        db.apriConnesione();
        int result = db.setCandidatura(idUtente, candidatura);
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    /**
     * Permette all'utente di inserire i propri dati anagrafici
     * @param idUtente id dell'utente registrato
     * @param dati dati dell'utente registrato
     * @return Ritorna true se l'operazione è avvenuta con successo, viceversa false
     * @throws SQLException 
     */
    public boolean inserisciAnagraficaUtente(int idUtente, DatiUtente dati) throws SQLException {
        db.apriConnesione();
        Calendar c = Calendar.getInstance();
        c.set(dati.getDataDiNascita().getAnno(), dati.getDataDiNascita().getMese()-1, dati.getDataDiNascita().getGiorno());
        Date data = new Date(c.getTimeInMillis());
        int result = db.setDatiAnagrafici(idUtente, dati.getNome(), dati.getCognome(), data, dati.getSesso().name(),
                dati.getNazionalita().name(), dati.getCittaDiRicerca().name(), dati.getNumeroDiTelefono());
        db.chiudiConnessione();
        if(result == 0)
            return false;
        else
            return true;
    }
    /**
     * Permette all'utente di modificaren la propria citta di ricerca
     * @param idUtente id dell'utente registrato
     * @param nuovaCitta nuova citta di ricerca in cui l'utente vuole essere ricercato
     * @return Ritorna true se l'operazione è avvenuta con successo, viceversa false
     * @throws SQLException 
     */
    public boolean modificaCittaDiRicerca(int idUtente, Citta nuovaCitta) throws SQLException {
        db.apriConnesione();
        int result = db.modificaCitaDiRicerca(idUtente, nuovaCitta.name());
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    /**
     * Permette all'utente di inserire i propri dati caratteriali
     * @param idUtente id dell'utente registrato
     * @param dati dell'utente registrato
     * @return Ritorna true se l'operazione è avvenuta con successo, viceversa false
     * @throws SQLException 
     */
    public boolean inserisciInfoUtente(int idUtente, DatiUtente dati) throws SQLException {
        db.apriConnesione();
        
        String fac;
        if(dati.getOccupazione() == Occupazione.STUDENTE)
            fac = dati.getFacolta().name();
        else 
            fac = null;
        
        int result = db.setInfoUtente(idUtente, dati.isFumatore(), dati.isCuoco(), dati.isSportivo(), 
                dati.getOccupazione().name(), fac);
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    /**
     * Permette all'utente di modificare i propri dati caratteriali
     * @param idUtente id dell'utente registrato
     * @param dati dati dell'utente registrato
     * @return Ritorna true se l'operazione è avvenuta con successo, viceversa false
     * @throws SQLException 
     */
    public boolean modificaInfoUtente(int idUtente, DatiUtente dati) throws SQLException {
        db.apriConnesione();
        
        String fac;
        if(dati.getOccupazione() == Occupazione.STUDENTE)
            fac = dati.getFacolta().name();
        else 
            fac = null;
        
        int result = db.modificaInfoUtente(idUtente, dati.isFumatore(), dati.isCuoco(), dati.isSportivo(), 
                dati.getOccupazione().name(), fac);
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    /**
     * Permette all'utente di visualizzare l'annuncio da lui creato
     * @param idUtenteProprietario id dell'utente proprietario dell'annuncio
     * @return  Ritorna l'annuncio creato dall'utente
     * @throws SQLException
     * @throws NessunAnnuncioException lancia un'eccezione nel caso in cui si voglia visualizzare il proprio annuncio ma non è stato creato
     */
    public AnnuncioCasa getAnnuncioUtente(int idUtenteProprietario) throws SQLException, NessunAnnuncioException {
        
        db.apriConnesione();
        ResultSet rs = db.getAnnuncioUtente(idUtenteProprietario);
        if(rs.next()) {
            int idCasa = rs.getInt(1);
            AnnuncioCasa annuncio = new AnnuncioCasa(rs.getString(4), rs.getInt(2), rs.getInt(5),
                    rs.getString(16) +" "+rs.getString(17), rs.getString(18), rs.getString(15));
            annuncio.creaInfo(rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getBoolean(11),
                    Citta.valueOf(rs.getString(12)), rs.getString(13), HouseGenerality.valueOf(rs.getString(10)));
            ResultSet rCamere = db.getCamere(idCasa);
            while(rCamere.next()) {
                annuncio.creaCamera(rCamere.getInt(3), rCamere.getInt(4));
            }
            ResultSet rElettro = db.getElettrodomestici(idCasa);
            while(rElettro.next()) {
                annuncio.creaElettrodomestico(ElettroDomestico.valueOf(rElettro.getString(1)));
            }
            return annuncio;
        } else
            throw new NessunAnnuncioException("Nessun annuncio creato");
    }
    /**
     * Permette all'utente di visualizzare i propri dati inseriti
     * @param idUtente id dell'utente registrato
     * @return Ritorna i dati dell'utente
     * @throws SQLException 
     */
    public DatiUtente getDatiUtente(int idUtente) throws SQLException {
        try {
            db.apriConnesione();
            ResultSet rs = db.getDatiUtente(idUtente);
            rs.next();
            String[] data = rs.getString(3).split("-");
            DatiUtente dati = new DatiUtente(rs.getString(1), rs.getString(2), Sesso.valueOf(rs.getString(4)),
                    rs.getString(13), null, Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]), rs.getString(7), Nazione.valueOf(rs.getString(5)),
                    Occupazione.valueOf(rs.getString(11)), Facolta.valueOf(rs.getString(12)),rs.getBoolean(8), rs.getBoolean(9),
                    rs.getBoolean(10), Citta.valueOf(rs.getString(6)), rs.getBoolean(14));
            return dati;
        } catch (ParseException ex) {
            Logger.getLogger(BusinessModelUtente.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

       
    }
    
    /**
     * VERIFICA SE UN UTENTE HA ALMENO UN ANNUNCIO
     * @param idUtenteProprietario È L'ID DELL'UTENTE IL CUI SI VUOLE VERIFICARE SE HA O MENO ANNUNCI
     * @return TORNA TRUE SE L'UTENTE HA ALMENO UN ANNUNICO, FALSE ALTRIMENTI
     * @throws SQLException 
     */
    public boolean verificaPresenzaAnnuncio(int idUtenteProprietario) throws SQLException {
        db.apriConnesione();
        ResultSet rs = db.verificaPresenzaAnnuncio(idUtenteProprietario);
        if(rs.next())
            return true;
        else
            return false;
                    
    }
}
