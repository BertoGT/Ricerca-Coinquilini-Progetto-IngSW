/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Marco La Salvia
 */
public class HtmlReader {

    public static String htmlReader(String nomeFile) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("html/"+nomeFile));
        StringBuilder ssb = new StringBuilder();
        while(scanner.hasNextLine()){
            ssb.append(scanner.nextLine());
        }
        return ssb.toString();
    }
}
