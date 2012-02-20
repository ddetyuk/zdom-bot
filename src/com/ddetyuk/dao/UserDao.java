package com.ddetyuk.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.ddetyuk.actions.models.User;
import com.ddetyuk.database.DatabaseDao;
import com.ddetyuk.database.PMF;

public class UserDao extends DatabaseDao<User> {

	private static Logger logger = Logger.getLogger(UserDao.class.toString());
	
	@SuppressWarnings("unchecked")
	public User getByUserId(String id) {
		User user = null;  
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.setDetachAllOnCommit(true);
		try {
			Query query = pm.newQuery(User.class);
			query.setFilter("uid=='"+id+"'");
			List <User> users = (List<User>) query.execute();
			user = users.get(0);
		}catch(JDOObjectNotFoundException e){
			logger.log(Level.INFO,e.getMessage());
 		}catch (Exception ex) {
 			logger.log(Level.SEVERE,ex.toString());
		}finally{  
			pm.close();  
		}
		return user;
	}
	
}
