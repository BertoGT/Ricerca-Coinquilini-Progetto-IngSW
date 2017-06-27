/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;



/**
 *
 * @author Marco La Salvia
 */
public class TesterServlet {
    public static void main(String[] args) {
        Server server = new Server(8080);
		try {
			ServletContextHandler handler = new ServletContextHandler();
			handler.addServlet(RegistrationServlet.class, "/registrazione");
			server.setHandler(handler);
			server.start();
			/**
			 * Le classi che generalmente gestiscono le richiete http si chiamano servlet
			 * useremo un contesto di una servlet, ossia MyServlet che andremo a scrivere noi
			 * questa gestirà tutte le richieste "chiamate" dalla URL cioè "servi tutto con questa nuova servlet" 
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
