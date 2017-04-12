/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Lettori.LeggiAnnunci;

/**
 *
 * @author Niko
 */
public class NewClass {
    public static void main(String[] args) {
        LeggiAnnunci l = new LeggiAnnunci();
        l.leggiCase();
        System.out.println(l.stampaAnnunci());
    }
}
