/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ProfiloUtente.DatiUtente;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sistema.Sistema;

/**
 *
 * @author alberto
 */
public class RisultatoCoinquiServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int idUtente = Integer.parseInt(req.getParameter("id"));
            Sistema sys = new Sistema();
            DatiUtente DatiUtente = sys.getDatiUtente(idUtente);
        } catch (SQLException ex) {
            Logger.getLogger(RisultatoCoinquiServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
