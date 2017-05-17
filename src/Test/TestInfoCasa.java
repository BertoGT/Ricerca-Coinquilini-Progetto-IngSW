/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Casa.ElettroDomestico;
import Casa.HouseGender;
import Casa.InfoCasa;

/**
 *
 * @author Delbo
 */
public class TestInfoCasa {
    public static void main(String[] args) {
        InfoCasa info=new InfoCasa(10,2,1,100,true,"Pavia","Via dal cazzo 7",HouseGender.MISTA);
        
        info.addElettroDomestico(ElettroDomestico.LAVATRICE);
        info.addElettroDomestico(ElettroDomestico.LAVATRICE);
        info.addElettroDomestico(ElettroDomestico.MICROONDE);
        
        System.out.print(info.getElettroDomestici());
        System.out.println("\n");
        
        info.rimuoviElettroDomestico(ElettroDomestico.LAVATRICE);
        info.rimuoviElettroDomestico(ElettroDomestico.ASPIRAPOLVERE);
        
        System.out.print(info.getElettroDomestici());
    }
          
}
