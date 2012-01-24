package com.ddetyuk;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddetyuk.actions.AuthAction;
import com.ddetyuk.connection.Action;
import com.ddetyuk.connection.Connector;
import com.ddetyuk.database.Database;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
@SuppressWarnings("serial")
public class ZdomServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(ZdomServlet.class.toString());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		if (user == null) {
			resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
		}
		
		 //if( !user.getNickname().equals("ddetyuk@gmail.com") ){
		if (!user.getNickname().equals("test@example.com")) {
			resp.setContentType("text/plain");
			resp.getWriter().println("If no mistake have you made, yet losing you are ... a different game you should play.");
			return;
		}

		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");

		logger.log(Level.INFO,"Start scheduled task: " + new Date().toGMTString());

		Connector connector = new Connector();
		Action auth = new AuthAction();
		connector.execute(auth);
		
		//com.ddetyuk.actions.models.User w = new com.ddetyuk.actions.models.User("1","","","","","",null,null,null);
		//Database.create(w);
		//w.setNick("new");
		//Database.get(w);
		//logger.log(Level.SEVERE,"Start scheduled task: " + w.toString());

	}
}
