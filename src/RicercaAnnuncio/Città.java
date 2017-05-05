/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RicercaAnnuncio;

import Casa.AnnuncioCasa;

/**
 *
 * @author Delbo
 */
public class Città extends ParametroRicercaAnnuncio{

    private String nome;
    
    public Città(String nome) {
        super(0);
        this.nome=nome;
        
    }
    
    
    
    @Override
    public float calcolaAffinità(AnnuncioCasa annuncio) {
        if(this.nome.equals(annuncio.getCitta()))
            return 0;
        else
            return -1;
    }
    
}
