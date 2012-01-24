package com.ddetyuk.database;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
public class Database {

	private static Logger logger = Logger.getLogger(Database.class.toString());

	public static void save(Object object) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(object);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.toString());
		} finally {
			pm.close();
		}
	}

	public static void delete(Object object) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.deletePersistent(object);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.toString());
		} finally {
			pm.close();
		}
	}

	public static void create(Object object) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(object);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.toString());
		} finally {
			pm.close();
		}
	}

	public static void get(Object object) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.setDetachAllOnCommit(true);
		try {
			object = pm.getObjectById(object);
		} catch (JDOObjectNotFoundException e) {
			logger.log(Level.INFO, e.getMessage());
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.toString());
		} finally {
			pm.close();
		}
	}
}
