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
    private static CookieStorage instance = new CookieStorage();
    private static ArrayList<Cookie> cookies = new ArrayList<>();
    
    private CookieStorage() {}
    
    static CookieStorage getInstance() {
        return instance;
    }
    
    static boolean controllaPresenzaCookie(Cookie cookie) {
        for (Cookie c : cookies) {
            if(c.getName().equals(cookie.getName()) && c.getValue().equals(cookie.getValue()))
                return true;          
        }
        return false;
    }
    
    static void salvaCookie(Cookie cookie) {
        cookies.add(cookie);
    }
    
}
