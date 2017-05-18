/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfiloUtente;

/**
 *
 * @author Marco La Salvia
 */
public class ProfileManager {
    private Utente utente;
    private String descrizioneAggiuntiva;

    public ProfileManager(Utente utente, String descrizioneAggiuntiva) {
        this.utente = utente;
        this.descrizioneAggiuntiva = descrizioneAggiuntiva;
    }

    public Utente getUtente() {
        return utente;
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
