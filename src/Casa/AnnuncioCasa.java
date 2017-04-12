/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casa;

import Exceptions.CameraNonInseritaException;
import Exceptions.CameraNonTrovataException;
import java.util.ArrayList;

/**
 *
 * @author alberto
 */
public class AnnuncioCasa {
    private InfoCasa casa;
    private String descrizioneAggiuntiva;
    private int idAnnuncio;

    public AnnuncioCasa(String descrizioneAggiuntiva, int idAnnuncio) {
        this.descrizioneAggiuntiva = descrizioneAggiuntiva;
        this.idAnnuncio = idAnnuncio;
    }
    
    public void creaCamera(int idCamera, int postiLetto, int postiLettoDisponibili) throws CameraNonInseritaException{
        casa.creaCamera(idCamera, postiLetto, postiLettoDisponibili);
    }
    
    public void rimuoviCamera(int idCamera) throws CameraNonTrovataException{
        casa.rimuoviCamera(idAnnuncio);
    }
    
    public void creaInfo(int m2, int nLocali, int nBagni, boolean cucinaSeparata){
        casa = new InfoCasa(m2, nLocali, nBagni, cucinaSeparata);
    }

    public InfoCasa getCasa() {
        return casa;
    }

    public String getDescrizioneAggiuntiva() {
        return descrizioneAggiuntiva;
    }

    public int getIdAnnuncio() {
        return idAnnuncio;
    }

    public void setDescrizioneAggiuntiva(String descrizioneAggiuntiva) {
        this.descrizioneAggiuntiva = descrizioneAggiuntiva;
    }
}
