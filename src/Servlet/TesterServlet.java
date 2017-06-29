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
                        handler.addServlet(LoginServlet.class, "/login");
			server.setHandler(handler);
			server.start();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
