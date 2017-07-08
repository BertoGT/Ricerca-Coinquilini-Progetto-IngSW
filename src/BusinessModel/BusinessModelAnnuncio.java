/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel;

import Casa.AnnuncioCasa;
import Casa.CameraDisponibile;
import Casa.Citta;
import Casa.ElettroDomestico;
import Casa.HouseGenerality;
import Casa.InfoCasa;
import Database.Database;
import Exceptions.NessunAnnuncioException;
import ProfiloUtente.DatiUtente;
import ProfiloUtente.Facolta;
import ProfiloUtente.Nazione;
import ProfiloUtente.Occupazione;
import ProfiloUtente.Sesso;
import ProfiloUtente.Utente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author alberto
 */
public class BusinessModelAnnuncio {
    private Database db;
    private static BusinessModelAnnuncio instance;

    private BusinessModelAnnuncio() throws SQLException {
        db = Database.getInstance();
    }
    
    /**
     * Restituisce l'istanza della classe. Se non è ancora stata creata
     * viene istanziata.
     * @return 
     */
    public static BusinessModelAnnuncio getInstance() {
      if(instance == null) {
          try {
              instance = new BusinessModelAnnuncio();
          } catch (SQLException ex) {
              Logger.getLogger(BusinessModelAnnuncio.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      return instance;
    }
    
    /**
     * Inserisci sul database l'annuncio della casa. Deve essere gia stata creata
     * e inserita la casa a cui fa riferimento l'annuncio
     * @param idCasa id della casa a cui fa riferimento l'annuncio
     * @param idUtenteProprietario id dell'utente che crea l'annuncio
     * @param descrizione descrizione aggiuntiva della casa 
     * @param costo costo di affitto
     * @return true se avviene l'inserimento, false altrimenti
     * @throws SQLException 
     */
    public boolean inserisciAnnuncioCasa(int idCasa, int idUtenteProprietario, 
            String descrizione, int costo) throws SQLException {
        
        db.apriConnesione();
        int result = db.setAnnuncioCasa(idCasa, idUtenteProprietario, descrizione, costo);
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    
    /**
     * Modifica i campi descrizione e costo di un annuncio gia creato
     * @param idAnnuncio idAnnuncio da modificare
     * @param descrizione nuova descrizione
     * @param costo nuovo costo
     * @return true se avviene la modifica, false altrimenti
     * @throws SQLException 
     */
    public boolean modificaAnnuncioCasa(int idAnnuncio, String descrizione, int costo) throws SQLException {
        db.apriConnesione();
        int result = db.modificaAnnuncioCasa(idAnnuncio, descrizione, costo);
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    /**
     * Elimina l'annuncio dell'utente
     * @param idAnnuncio idAnnuncio da eliminare
     * @return true se avviene l'eliminazione, false atrimenti
     * @throws SQLException 
     */
    public boolean eliminaAnnuncioCasa(int idAnnuncio, int idCasa, int idUtenteProprietario) throws SQLException {
        db.apriConnesione();
        int result = db.eliminaAnnuncioCasa(idAnnuncio, idCasa, idUtenteProprietario);
        eliminaCamera(idCasa);
        eliminaTuttiElettrodomestici(idCasa);
        db.chiudiConnessione();
        if(result == 0)
            return false;
        else 
            return true;
    }
    
    /**
     * Inserisce su DB le informazioni che descrivono la casa 
     * @param info oggetto che descrive la casa da inserire
     * @return l'idCasa che rappresenta l'identificatore univoco della casa inserita
     * su DB
     * @throws SQLException 
     */
    public int inserisciInfoCasa(InfoCasa info) throws SQLException {
        // se crea l'infoCasa restituisce il suo id AI
        db.apriConnesione();
        ResultSet rs;
        rs = db.setInfoCasa(info.getMetriQuadri(), info.getnLocali(), info.getNumeroBagni(),
                info.getDistanzaCentro(), info.getSessoCasa().name(), info.isCucinaSeparata(),
                info.getCitta().name(), info.getIndirizzo());
        if(rs != null && rs.next()) {
            int result = rs.getInt(1);
            db.chiudiConnessione();
            return result;
        }
        else {
            db.chiudiConnessione();
            return -1;
        }
    }
    
    /**
     * Modifica una casa precedentemente creata
     * @param idCasa id univoco della casa da modificare
     * @param info contenitore dei nuovi dati da sovrascrivere
     * @return true se avviene la modifica, false altrimenti
     * @throws SQLException 
     */
    public boolean modificaInfoCasa(int idCasa, InfoCasa info) throws SQLException {
        
        db.apriConnesione();
        int result = db.modificaInfoCasa(idCasa, info.getMetriQuadri(), info.getnLocali(), info.getNumeroBagni(),
                info.getDistanzaCentro(), info.getSessoCasa().name(), info.isCucinaSeparata(),
                info.getCitta().name(), info.getIndirizzo());
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    /**
     * Inserisci su DB una camera alla casa specificata tramite idCasa
     * @param idCasa id univoco della casa a cui aggiungere la camera
     * @param camera oggetto che rappresenta la cemera da inserire
     * @return true se viene caricata correttamente, false altrimenti
     * @throws SQLException 
     */
    public boolean inserisciCamera(int idCasa, CameraDisponibile camera) throws SQLException {
        
        db.apriConnesione();
        int result = db.setCamera(idCasa, camera.getIdCamera(), camera.getPostiLetto(), camera.getPostiLettoDisponibili());
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    /**
     * Modifica una camera presente su DB
     * @param idCasa id univoco della casa a cui aggiungere la camera
     * @param camera oggetto che rappresenta la cemera da inserire
     * @return true se avviene la modifica, false altrimenti
     * @throws SQLException 
     */
    public boolean modificaCamera(int idCasa, CameraDisponibile camera) throws SQLException {
        
        db.apriConnesione();
        int result = db.modificaCamera(idCasa, camera.getIdCamera(), camera.getPostiLetto(), camera.getPostiLettoDisponibili());
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    /**
     * Elimina una camera presente nel DB
     * @param idCasa id univoco della casa a cui aggiungere la camera
     * @return true se avviene la modifica, false altrimenti
     * @throws SQLException 
     */
    public boolean eliminaCamera(int idCasa) throws SQLException {
        db.apriConnesione();
        int result = db.eliminaCamere(idCasa);
        db.chiudiConnessione();
        if(result == 0)
            return false;
        else
            return true;
    }
    
    /**
     * Inserisci su DB un elettrodomestico alla casa specificata
     * @param idCasa id univoco della casa
     * @param elettrodomestico tipo dell'elettrodomestico da inserire
     * @return true se viene caricata correttamente, false altrimenti
     * @throws SQLException 
     */
    public boolean inserisciElettrodomestico(int idCasa, ElettroDomestico elettrodomestico) throws SQLException {
        
        db.apriConnesione();
        int result = db.setElettrodomestico(idCasa, elettrodomestico.name());
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    /**
     * Elimina su DB l'elettrodomestico dalla casa specificata
     * @param idCasa id univoco della casa
     * @param elettrodomestico tipo dell'elettrodomestico da eliminare
     * @return true se viene eliminato, false altrimenti
     * @throws SQLException 
     */
    public boolean eliminaElettrodomestico(int idCasa, ElettroDomestico elettrodomestico) throws SQLException {
        
        db.apriConnesione();
        int result = db.eliminaElettrodomestico(idCasa, elettrodomestico.name());
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    public boolean eliminaTuttiElettrodomestici(int idCasa) throws SQLException {
        
        db.apriConnesione();
        int result = db.eliminaTuttiElettrodomestici(idCasa);
        db.chiudiConnessione();
        if(result == 0) 
            return false;
        else 
            return true;
    }
    
    /* SEZIONE LETTURA ANNUNCI */
    
    
    /**
     * Restituisce un ArrayList contenente gli annunci salvati su DB che soddisfano i
     * requisiti di citta e costo (se specificato)
     * @param citta Citta in cui si vuole cercare la casa
     * @param costo Costo massimo della casa. Se nullo viene trascurato questo criterio di ricerca
     * @return Lista annunci compatibili con citta e costo (se inserito)
     * @throws SQLException
     * @throws NessunAnnuncioException se nessun annuncio è compatibile con i criteri inseriti
     * (citta e/o costo)
     */
    public ArrayList<AnnuncioCasa> getAnnunciCase(Citta citta, int costo) throws SQLException, NessunAnnuncioException {
        
        ArrayList<AnnuncioCasa> annunci = new ArrayList<>();
        
        db.apriConnesione();
        int costoConTolleranza;
        if(costo == 0)
            costoConTolleranza = 0;
        else 
            costoConTolleranza = costo + 50;
        ResultSet rs = db.getAnnunciInfoCasa(citta.name(), costoConTolleranza);
        while(rs.next()) {
            int idCasa = rs.getInt(1);
            AnnuncioCasa annuncio = new AnnuncioCasa(rs.getInt(1), rs.getString(4), rs.getInt(2), rs.getInt(5),
                    rs.getString(17) +" "+rs.getString(18), rs.getString(19), rs.getString(16), rs.getDate(6));
            annuncio.creaInfo(rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getBoolean(12),
                    Citta.valueOf(rs.getString(13)), rs.getString(14), HouseGenerality.valueOf(rs.getString(11)));
            ResultSet rCamere = db.getCamere(idCasa);
            while(rCamere.next()) {
                annuncio.creaCamera(rCamere.getInt(3), rCamere.getInt(4));
                
            }
            ResultSet rElettro = db.getElettrodomestici(idCasa);
            while(rElettro.next()) {
                annuncio.creaElettrodomestico(ElettroDomestico.valueOf(rElettro.getString(1)));
            }
            annunci.add(annuncio);
        }
        if(annunci.isEmpty())
            throw new NessunAnnuncioException("Nessun annuncio soddisfa i criteri di ricerca inseriti");
        
        return annunci;
    }
    
    /**
     * Restituisce un ArrayList di Utenti che sono in cerca di una casa nella citta inserita.
     * Può essere specificato anche il sesso
     * @param citta Citta in cui si cerca un coinquilino
     * @param sesso Sesso che deve avere il coinquilino
     * @return Lista dei potenziali coninquilini
     * @throws SQLException
     * @throws NessunAnnuncioException nessun annuncio è compatibile con i criteri inseriti
     * (citta e/o sesso)
     */
    public ArrayList<Utente> getAnnunciCoinquilini(Citta citta, ProfiloUtente.Sesso sesso) throws SQLException, NessunAnnuncioException {
        try {
            ArrayList<Utente> annunciUtenti = new ArrayList<>();

            db.apriConnesione();
            String sessoString = null;
            if(sesso != null)
                sessoString = sesso.name();
            ResultSet rs = db.getAnnunciCoinquilini(citta.name(), sessoString);
            while(rs.next()) {
                int idCasa = rs.getInt(1);
                String[] data = rs.getString(4).split("-");
                DatiUtente dati = new DatiUtente(rs.getString(2), rs.getString(3), Sesso.valueOf(rs.getString(5)),
                        rs.getString(14), null, Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]), rs.getString(8), Nazione.valueOf(rs.getString(6)),
                        Occupazione.valueOf(rs.getString(12)), Facolta.valueOf(rs.getString(13)),rs.getBoolean(9), rs.getBoolean(10),
                        rs.getBoolean(11), Citta.valueOf(rs.getString(7)), true);
                Utente annuncio = new Utente(rs.getInt(1), dati);
                annunciUtenti.add(annuncio);
            }
            if(annunciUtenti.isEmpty())
                throw new NessunAnnuncioException("Nessun annuncio soddisfa i criteri di ricerca inseriti");

            return annunciUtenti;
        } catch (ParseException ex) {
            return null;
        }
    }
}
