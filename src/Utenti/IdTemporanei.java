/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;

/**
 *
 * @author Marco La Salvia
 */
public class IdTemporanei {
    int idCreati;

    public IdTemporanei() {
        this.idCreati = 0;
    }

    public int getIdCreati() {
        return idCreati;
    }
    public void incrementaId(){
        this.idCreati++;
    }
    
    
}
