/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaAnnuncio;

import Casa.AnnuncioCasa;
import Casa.ElettroDomestico;

/**
 *
 * @author cl428444
 */
public class Elettrodomestico extends ParametroRicercaAnnuncio{
    private ElettroDomestico elettrodomestico;

    public Elettrodomestico(int stelle, ElettroDomestico elettrodomestico) {
        super(stelle);
        this.elettrodomestico = elettrodomestico;
    }

    @Override
    public float calcolaAffinità(AnnuncioCasa annuncio) {

        boolean elettroPresente = false;
        for (ElettroDomestico elettro : annuncio.getElettroDomestici()) {
            if(elettro == elettrodomestico) {
                elettroPresente = true;
                break;
            }
        } 
        if(elettroPresente)
            return super.getStelle();      
        else 
            return 0;
    }
    
}
