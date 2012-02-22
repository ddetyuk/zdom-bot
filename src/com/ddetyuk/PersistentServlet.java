package com.ddetyuk;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddetyuk.actions.LoginAction;
import com.ddetyuk.actions.PersistentAction;
import com.ddetyuk.actions.models.Session;
import com.ddetyuk.actions.models.User;
import com.ddetyuk.connection.ActionException;
import com.ddetyuk.connection.Connector;
import com.ddetyuk.dao.SessionDao;
import com.ddetyuk.dao.UserDao;

/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
@SuppressWarnings("serial")
public class PersistentServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(PersistentServlet.class.toString());

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try{
			resp.setContentType("text/plain");
			
			Connector connector = new Connector();
			
			//get user
			UserDao userdao = new UserDao();
			User user = userdao.getByUserId("5806946");
	
			if(null!=user){
				//get session
				if(null!=user.getSession()){
					try{
						//get user info
						PersistentAction pAction = new PersistentAction();
						pAction.setUser(user);
						connector.execute(pAction);
					}catch(ActionException e){
						logger.log(Level.SEVERE,"Persistent action error: " + e.getMessage());
						//remove old session
						SessionDao sessiondao = new SessionDao();
						sessiondao.delete(Session.class, user.getSession().getId());
					}
				}else{
					try{
						//login action
						LoginAction lAction = new LoginAction();
						lAction.setUser(user);
						connector.execute(lAction);
						//save session
						userdao.save(user);
					}catch(ActionException e){
						logger.log(Level.SEVERE,"Login action error: " + e.getMessage());
					}
				}
			}
		}catch(com.google.apphosting.api.DeadlineExceededException ex){
			logger.log(Level.SEVERE,"NO enough time to complete task!");	
		}
	}
}
