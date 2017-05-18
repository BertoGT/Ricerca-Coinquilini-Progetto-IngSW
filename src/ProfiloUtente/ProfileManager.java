/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfiloUtente;

import Casa.AnnuncioCasa;

/**
 *
 * @author Marco La Salvia
 */
public class ProfileManager {
    private Utente utente;
    private String descrizioneAggiuntiva;
    private AnnuncioCasa annuncioCasa;

    public ProfileManager(Utente utente, String descrizioneAggiuntiva, AnnuncioCasa annuncioCasa) {
        this.utente = utente;
        this.descrizioneAggiuntiva = descrizioneAggiuntiva;
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

    public String getDescrizioneAggiuntiva() {
        return descrizioneAggiuntiva;
    }

    public void setDescrizioneAggiuntiva(String descrizioneAggiuntiva) {
        this.descrizioneAggiuntiva = descrizioneAggiuntiva;
    }

    @Override
    public String toString() {
        return "\nPROFILO UTENTE\n" + this.utente.toString() +"\nDESCRIZIONE PERSONALE: "+this.descrizioneAggiuntiva+"\n";
    }
    
    
    
    
}
