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
class CookieStorage {
    private static CookieStorage instance;
    private ArrayList<Cookie> cookies;
    
    private CookieStorage() {
        cookies = new ArrayList<>();
    }
    
    static CookieStorage getInstance() {
        if(instance == null)
            instance = new CookieStorage();
        return instance;
    }
    
    boolean controllaPresenzaCookie(Cookie cookie) {
        for (Cookie c : cookies) {
            if(c.getName().equals(cookie.getName()) && c.getValue().equals(cookie.getValue()))
                return true;          
        }
        return false;
    }
    
    void salvaCookie(Cookie cookie) {
        cookies.add(cookie);
    }
    
}
