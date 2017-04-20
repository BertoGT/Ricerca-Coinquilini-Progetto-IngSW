/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Casa.AnnuncioCasa;
import Lettori.LeggiAnnunci;
import java.util.ArrayList;

/**
 *
 * @author Niko
 */
public class TestFile {
    public static void main(String[] args) {
        ArrayList<AnnuncioCasa> ann = new ArrayList<>();
        LeggiAnnunci l = new LeggiAnnunci();
        l.leggiFile();
        ann = l.getAnnunci();
        System.out.println(l.stampaAnnunci());
    }
}
