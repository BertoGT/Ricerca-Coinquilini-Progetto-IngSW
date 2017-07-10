/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Casa.AnnuncioCasa;
import Exceptions.NessunAnnuncioException;
import HtmlCreators.AnnuncioRisultanteCreator;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Sistema.Sistema;

/**
 *
 * @author Margherita
 */
public class RisultatoAnnuncioServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int idProprietario = Integer.parseInt(req.getParameter("id"));
            Sistema sys = new Sistema();
            AnnuncioCasa annuncio = sys.getAnnuncioProprietario(idProprietario);
            resp.getWriter().println(AnnuncioRisultanteCreator.creazioneAnnuncio(annuncio, req, resp));
            resp.setStatus(200);
        } catch (SQLException ex) {
            Logger.getLogger(RisultatoCoinquiServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NessunAnnuncioException ex) {
            Logger.getLogger(RisultatoAnnuncioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   
}
