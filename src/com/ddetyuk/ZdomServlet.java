package com.ddetyuk;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddetyuk.actions.LoginAction;
import com.ddetyuk.actions.RoomFinAction;
import com.ddetyuk.actions.RoomStartAction;
import com.ddetyuk.actions.UserAction;
import com.ddetyuk.actions.UserRoomsAction;
import com.ddetyuk.actions.models.Session;
import com.ddetyuk.actions.models.User;
import com.ddetyuk.actions.models.UserRoom;
import com.ddetyuk.connection.ActionException;
import com.ddetyuk.connection.Connector;
import com.ddetyuk.dao.SessionDao;
import com.ddetyuk.dao.UserDao;
import com.ddetyuk.dao.UserRoomDao;

/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
@SuppressWarnings("serial")
public class ZdomServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(ZdomServlet.class.toString());

	@SuppressWarnings("deprecation")
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try{
			resp.setContentType("text/plain");
			resp.getWriter().println("Hello, world");
	
			logger.log(Level.INFO,"Start scheduled task: " + new Date().toGMTString());
	
			Connector connector = new Connector();
	
			//get user
			UserDao userdao = new UserDao();
			
			User user = userdao.getByUserId("5806946");
			
			logger.log(Level.INFO,"user: " + user);
			//logger.log(Level.INFO,"user: " + user.getUserRooms());
			
	
			if(null!=user){
				//get session
				if(null!=user.getSession()){
					
					try{
						//get user info
						UserAction uAction = new UserAction();
						uAction.setUser(user);
						connector.execute(uAction);
	
						//get user rooms
						UserRoomsAction rAction = new UserRoomsAction();
						rAction.setUser(user);
						connector.execute(rAction);
						
						//save user rooms
						UserRoomDao userRoomDao = new UserRoomDao();
						userRoomDao.saveAll(user.getUserRooms());
						
						//save user
						userdao.save(user);
						
					}catch(ActionException e){
						logger.log(Level.SEVERE,"User action error: " + e.getMessage());
						//remove old session
						SessionDao sessiondao = new SessionDao();
						sessiondao.delete(Session.class, user.getSession().getId());
					}


					List<UserRoom> rooms = user.getUserRooms();
					
					//get random room
					Collections.shuffle(rooms);
					UserRoom room = rooms.get(0);
					
					//get room with Phenomenon
					for (UserRoom userRoom : rooms) {
						if(userRoom.getPhenomenonId()>0L){
							room = userRoom;
						}
					}
					
					
					//check energy
					if( user.getEnergy() >= room.getNeedfullEnergy() ){
						
						try{
							//start room search
							RoomStartAction sAction = new RoomStartAction();
							sAction.setUser(user);
							sAction.setRoom(room);
							connector.execute(sAction);
							
							//end room search
							RoomFinAction eAction = new RoomFinAction();
							eAction.setUser(user);
							eAction.setRoom(room);
							connector.execute(eAction);

						}catch(ActionException e){
							logger.log(Level.SEVERE,"Room action error: " + e.getMessage());
						}

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
		}catch(IOException e){
			logger.log(Level.SEVERE,"Connection timeout:"+e.getMessage());
		}
	}
}
