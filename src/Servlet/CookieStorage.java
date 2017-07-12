/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.util.ArrayList;
import javax.servlet.http.Cookie;

/**
 *
 * @author alberto
 */
public class CookieStorage {
    private static CookieStorage instance;
    private ArrayList<Cookie> cookies;
    
    private CookieStorage() {
        cookies = new ArrayList<>();
    }
    /**
     * 
     * @return RITORNA UN'ISTANZA DELL'OGGETTO COOKIE STORAGE.
     */
    public static CookieStorage getInstance() {
        if(instance == null)
            instance = new CookieStorage();
        return instance;
    }
    /**
     * 
     * @param cookie COOKIE PASSATO COME PARAMETRO
     * @return TRUE SE IL COOKIE E' GIA' PRESENTE NEL SISTEMA.
     */
    public boolean controllaPresenzaCookie(Cookie cookie) {
        for (Cookie c : cookies) {
            if(c.getName().equals(cookie.getName()) && c.getValue().equals(cookie.getValue()))
                return true;          
        }
        return false;
    }
    /**
     * 
     * @param cookie COOKIE DA SALVARE NEL SISTEMA.
     */
    public void salvaCookie(Cookie cookie) {
        cookies.add(cookie);
    }
    /**
     * 
     * @param cookie COOKIE DA ELIMINARE DAL SISTEMA.
     */
    public void eliminaCookie(Cookie cookie) {
        for (int i = 0; i<cookies.size(); i++) {
            if(cookies.get(i).getName().equals(cookie.getName()) && cookies.get(i).getValue().equals(cookie.getValue())) {
                cookies.remove(i);
                break;
            }
        }
    }
    
}
