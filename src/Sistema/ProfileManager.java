/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

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
import ProfiloUtente.DatiUtente;
import ProfiloUtente.Facolta;
import ProfiloUtente.Occupazione;
import ProfiloUtente.Utente;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Marco La Salvia
 */
public class ProfileManager {
    private Utente utente;
    private AnnuncioCasa annuncioCasa;
    /**
     * ISTANZIA UN OGGETTO CHE RAPPRESENTA IL PROFILO UTENTE ALL'INTERNO DEL SISTEMA NEL MOMENTO
     * IN CUI CI SI LOGGA. DAL PROFILO UTENTE SI POSSONO CREARE ANCHE GLI ANNUNCI (SOLO DAL PROFILO E' POSSIBILE).
     * @param utente PROFILO UTENTE IN QUESTIONE CHE E' POSSIBILE MODIFICARE
     * @param annuncioCasa ANNUNCIO CASA CONTENUTO NEL PROFILO, NULL SE L'UTENTE NON NE HA MAI CREATO UNO.
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
     * METODO CHE PERMETTE LA MODIFICA DEL PROFILO ALL'UTENTE. NON E' POSSIBILE MODIFICARE TUTTI I PARAMETRI CON I QUALI L'UTENTE SI E' REGISTRATO.
     * @param facolta FACOLTA UTENTE.
     * @param citta CITTA DI RICERCA UTENTE.
     * @param occupazione OCCUPAZIONE DELL'UTENTE.
     * @param fumatore BOOLEANO CHE INDICA SE L'UTENTE SIA UN FUMATORE ABITUALE.
     * @param sportivo BOOLEANO CHE INDICA SE L'UTENTE SIA UNO SPORTIVO ABITUALE.
     * @param cuoco BOOLEANO CHE INDICA SE L'UTENTE SIA UN CUOCO ABITUALE.
     * @param candidato BOOLEANO CHE INDICA SE L'UTENTE SI CANDIDI COME COINQUILINO O MENO.
     * @param password NUOVA PASSWORD CHE RIMPIAZZA QUELLA VECCHIA.
     * @throws SQLException
     * @throws ParseException
     * @throws PasswordException 
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
     * METODO CHE PERMETTE LA CREAZIONE DI UN ANNUNCIO ALL'UTENTE.
     * @param elettrodomestici LISTA CONTENENTE GLI ELETTRODOMESTICI CHE LA CASA POSSIEDE.
     * @param postiLettoEDisponibili MATRICE CONTENENTE NELLE RIGHE LE CAMERE DISPONIBILI E NELLE COLONNE I POSTI LETTO E I POSTI LETTO DISPONIBILI PER OGNI CAMERA
     * @param idUtente ID DELL'UTENTE UNIVOCO ALL'INTERNO DEL DATABASE.
     * @param costoMensile COSTO MENSILE DELLA CASA.
     * @param metriQuadrati METRI QUADRATI DELLA CASA.
     * @param numeroLocali NUMERO DI LOCALI DELLA CASA.
     * @param numeroBagni NUMERO DI BAGNI DELLA CASA.
     * @param distanzaCentro DISTANZA DAL CENTRO DELLA CASA.
     * @param cucinaSeparata BOOLEANO CHE INDICA SE LA CUCINA E' SEPARATA DAL SALONE.
     * @param cittaDiRicerca CITTA IN CUI LA CASA E' PRESENTE.
     * @param cittaIndirizzo INDIRIZZO DELLA CASA ALL'INTERNO DELLA CITTA'.
     * @param sessoCoinquilini SESSO DEGLI INQUILINI DELL'APPARTAMENTO.
     * @param descrizioneAggiuntiva DESCRIZIONE DELLA CASA.
     * @throws SQLException
     * @throws InserimentoAnnuncioNonRiuscito
     * @throws AnnuncioException 
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
    
    public void modificaAnnuncio(ArrayList<ElettroDomestico> elettrodomestici,int[][] postiLettoEDisponibili, int idUtente, int costoMensile, 
            HouseGenerality sessoCoinquilini, String descrizioneAggiuntiva) throws SQLException, AnnuncioException {
        if(this.annuncioCasa!=null){
            BusinessModelAnnuncio bm = BusinessModelAnnuncio.getInstance();
            int idCasa = annuncioCasa.getIdCasa();
            bm.modificaSessoCasa(idCasa, sessoCoinquilini);
            bm.modificaAnnuncioCasa(annuncioCasa.getIdAnnuncio(), descrizioneAggiuntiva, costoMensile);
            
            bm.eliminaTutteCamere(idCasa);
            for(int i=0;i<postiLettoEDisponibili.length;i++){
                int postiLetto = postiLettoEDisponibili[i][0];
                int postiLettoDisponibili = postiLettoEDisponibili[i][1];
                if(postiLetto!=0 & postiLettoDisponibili!=0)
                    bm.inserisciCamera(idCasa, new CameraDisponibile(idCasa, i, postiLetto, postiLettoDisponibili));
            }
            bm.eliminaTuttiElettrodomestici(idCasa);
            for(ElettroDomestico e: elettrodomestici){
                if(e==null)
                    continue;
                bm.inserisciElettrodomestico(idCasa, e);
            }
        }else{
            throw new AnnuncioException("Non è stato ancora creato nessun annuncio!");
        }
    }
    /**
     * METODO CHE CANCELLA L'ANNUNCIO PRESENTE SUL PROFILO UTENTE.
     * @throws SQLException 
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
