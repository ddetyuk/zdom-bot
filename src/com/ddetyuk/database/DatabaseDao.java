package com.ddetyuk.database;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

/**
 * 
 * @author Dmitriy Detyuk (ddetyuk@gmail.com)
 */
public abstract class DatabaseDao<T> {

	private static Logger logger = Logger.getLogger(DatabaseDao.class.toString());

	public void save(T object) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(object);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getMessage());
		} finally {
			pm.close();
		}
	}
	
	public void saveAll(Collection<T> objects) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Transaction tx = pm.currentTransaction();
			tx.begin();
			for (T object : objects) {
				pm.makePersistent(object);
			}
			tx.commit();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getMessage());
		} finally {
			pm.close();
		}
	}

	public void delete(Class<T> Class, Object id) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			T entity = (T)pm.getObjectById(Class,id);
			pm.deletePersistent(entity);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getMessage());
		} finally {
			pm.close();
		}
	}

	public void create(T object) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(object);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getMessage());
		} finally {
			pm.close();
		}
	}

	public T get(Class<T> Class, String id) {
		T result = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.setDetachAllOnCommit(true);
		try {
			result = (T) pm.getObjectById(Class,id);
		} catch (JDOObjectNotFoundException e) {
			logger.log(Level.INFO, e.getMessage());
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getMessage());
		} finally {
			pm.close();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<T> fetch() {
		Collection <T> result = null;  
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.setDetachAllOnCommit(true);
		try {
			Query query = pm.newQuery();
			result = (Collection<T>) query.execute();
		}catch(JDOObjectNotFoundException e){
			logger.log(Level.INFO,e.getMessage());
 		}catch (Exception ex) {
 			logger.log(Level.SEVERE,ex.getMessage());
		}finally{  
			pm.close();  
		}
		return result;
	}
}
