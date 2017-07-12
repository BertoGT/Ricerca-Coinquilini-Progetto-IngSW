/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfiloUtente;

import BusinessModel.BusinessModelAnnuncio;
import BusinessModel.BusinessModelUtente;
import Exceptions.AnnuncioException;
import Casa.AnnuncioCasa;
import Casa.CameraDisponibile;
import Casa.Citta;
import Casa.ElettroDomestico;
import Casa.HouseGenerality;
import Casa.InfoCasa;
import Exceptions.InserimentoAnnuncioNonRiuscito;
import Exceptions.PasswordException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Marco La Salvia
 */
public class ProfileManager {
    private Utente utente;
    private AnnuncioCasa annuncioCasa;
    /**
     * Crea un'istanza del profilo dell'utente.
     * @param utente Oggetto utente contenente informazioni di base
     * @param annuncioCasa  Oggetto AnnuncioCasa contenente le informazioni dell'annuncio.
     */
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
    /**
     * Metodo che permette la modifica del profilo utente.
     * 
     * @param facolta Facolta frequentata dall'utente, "NESSUNA" se l'utente non è studente.
     * @param citta Città di ricerca dell'utente.
     * @param occupazione Occupazione dell'utente.
     * @param fumatore Indica se l'utente è fumatore.
     * @param sportivo Indica se l'utente è sportivo.
     * @param cuoco Indica se l'utente è un cuoco.
     * @param candidato Indica se l'utente si candida o meno come coinquilino.
     * @param password Password scelta dall'utente, se non scelta si utilizza quella precedente.
     * @throws SQLException ECCEZIONE GENERATA DAL DATABASE.
     * @throws ParseException ECCEZIONE GENERATA DALLA CREAZIONE DELLA DATA DI NASCITA. 
     * @throws PasswordException ECCEZIONE GENERATA DA UN ERRATO INSERIMENTO DELLA PASSWORD.
     */
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
    /**
     * Metodo che permette la creazione di un annuncio all'utente. 
     * 
     * @param elettrodomestici Lista degli elettrodomestici da aggiungere all'annuncio.
     * @param postiLettoEDisponibili Posti letto e posti letto disponibili per ogni camera disponibile creata dall'utente tramite interfaccia web.
     * @param idUtente IdUtente univoco all'interno del database.
     * @param costoMensile Costo mensile della casa.
     * @param metriQuadrati Metri quadrati della casa.
     * @param numeroLocali Numero di locali della casa.
     * @param numeroBagni Numero di bagni della casa.
     * @param distanzaCentro Distanza dal centro della casa.
     * @param cucinaSeparata Indica se la casa ha una cucina separata o meno.
     * @param cittaDiRicerca Citta in cui la casa è presente.
     * @param cittaIndirizzo Indirizzo della casa.
     * @param sessoCoinquilini Sesso presente all'interno della casa. 
     * @param descrizioneAggiuntiva Descrizione aggiuntiva dell'annuncio.
     * @throws SQLException ECCEZIONE GENERATA DAL DATABASE.
     * @throws InserimentoAnnuncioNonRiuscito ECCEZIONE GENERATA NEL MOMENTO IN CUI NON SI RIESCE AD INSERIRE L'ANNUNCIO CORRETTAMENTE SUL DATABASE.
     * @throws AnnuncioException ECCEZIONE CHE NON PERMETTE LA CREAZIONE DELL'ANNUNCIO ALL'UTENTE IN QUANTO NE POSSIEDE GIA' UNO SUL PROPRIO PROFILO.
     */
    public void creaAnnuncio(ArrayList<ElettroDomestico> elettrodomestici,int[][] postiLettoEDisponibili, int idUtente, int costoMensile, 
                             int metriQuadrati, int numeroLocali, int numeroBagni, int distanzaCentro, boolean cucinaSeparata,
                             Citta cittaDiRicerca, String cittaIndirizzo, HouseGenerality sessoCoinquilini, String descrizioneAggiuntiva) throws SQLException, InserimentoAnnuncioNonRiuscito, AnnuncioException{
        if(this.annuncioCasa==null){
            BusinessModelAnnuncio bm = BusinessModelAnnuncio.getInstance();
            int idCasa = bm.inserisciInfoCasa(new InfoCasa(0,metriQuadrati, numeroLocali, numeroBagni, distanzaCentro,
                                                           cucinaSeparata, cittaDiRicerca, cittaIndirizzo, sessoCoinquilini));
            if(bm.inserisciAnnuncioCasa(idCasa, idUtente, descrizioneAggiuntiva, costoMensile)){
                for(int i=0;i<postiLettoEDisponibili.length;i++){
                    int postiLetto = postiLettoEDisponibili[i][0];
                    int postiLettoDisponibili = postiLettoEDisponibili[i][1];
                    if(postiLetto!=0 & postiLettoDisponibili!=0)
                        bm.inserisciCamera(idCasa, new CameraDisponibile(idCasa, i, postiLetto, postiLettoDisponibili));
                }
                for(ElettroDomestico e: elettrodomestici){
                    if(e==null)
                        continue;
                    bm.inserisciElettrodomestico(idCasa, e);
                }
            }else{
                throw new InserimentoAnnuncioNonRiuscito("Inserimento dell'annuncio nel db non riuscito!");
            }
        }else{
            throw new AnnuncioException("Cancellare annuncio prima di crearne uno nuovo!");
        }
    }
    /**
     * Metodo che cancella l'annuncio dell'utente.
     * @throws SQLException ECCEZIONE GENERATA DAL DATABASE.
     */
    public void cancellaAnnuncio() throws SQLException{
        BusinessModelAnnuncio bm = BusinessModelAnnuncio.getInstance();
        int idCasa = this.annuncioCasa.getIdCasa();
        
        bm.eliminaAnnuncioCasa(idCasa);
    }
    
    @Override
    public String toString() {
        return "\nPROFILO UTENTE\n" + this.utente.toString()+"\n";
    }
}
