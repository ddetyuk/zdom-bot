package com.ddetyuk;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddetyuk.actions.LoginAction;
import com.ddetyuk.actions.UserAction;
import com.ddetyuk.actions.models.User;
import com.ddetyuk.connection.Connector;


/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
@SuppressWarnings("serial")
public class ZdomServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(ZdomServlet.class.toString());

	@SuppressWarnings("deprecation")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");

		logger.log(Level.INFO,"Start scheduled task: " + new Date().toGMTString());

		Connector connector = new Connector();
		
		User user = new User("5806946",
				"http://cs304405.vkontakte.ru/u5806946/b_44446400.jpg",
				"http://vkontakte.ru/id5806946", "Дмитрий Детюк", "ru",
				"c4cab838653ea54d776e425e296ff5e5", null, null, null);
		
		LoginAction auth = new LoginAction();
		auth.setUser(user);
		connector.execute(auth);
		
		if(null!=user.getSession()){
			UserAction uAction = new UserAction();
			uAction.setUser(user);
			connector.execute(uAction);
		}
		
		//com.ddetyuk.actions.models.User w = new com.ddetyuk.actions.models.User("1","","","","","",null,null,null);
		//Database.create(w);
		//w.setNick("new");
		//Database.get(w);
		//logger.log(Level.SEVERE,"Start scheduled task: " + w.toString());

	}
}
