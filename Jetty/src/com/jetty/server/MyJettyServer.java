/**
 * 
 */
package com.jetty.server;

import java.io.FileInputStream;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.xml.XmlConfiguration;

/**
 * @author crane.ding
 *
 */
public class MyJettyServer {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		final Server server = new Server();
		
		new XmlConfiguration(new FileInputStream("war/etc/jetty.xml")).configure(server);
		
		server.start();
		
		/*Handler handler=new AbstractHandler()
		{
		    public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch)
		        throws IOException, ServletException
		    {
		        response.setContentType("text/html");
		        response.setStatus(HttpServletResponse.SC_OK);
		        response.getWriter().println("<h1>Hello</h1>");
		        ((Request)request).setHandled(true);
		    }
		};*/

		/*
		Server server = new Server(8080);
		server.setHandler(new WebAppContext("war/webapp", "/"));
		server.start();
		*/

	}

}
