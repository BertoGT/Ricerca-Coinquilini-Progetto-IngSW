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
