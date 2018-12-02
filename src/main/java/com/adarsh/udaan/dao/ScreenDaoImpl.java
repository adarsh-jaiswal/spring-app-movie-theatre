package com.adarsh.udaan.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.adarsh.udaan.entity.Screen;
import com.adarsh.udaan.utils.BusinessException;

@Repository
public class ScreenDaoImpl implements ScreenDao{

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public boolean saveScreen(Screen screen) {
		try {
			em.persist(screen);
			em.flush();
		} catch (PersistenceException ex) {
			throw new BusinessException("Screen Name Already Exists.");
		} catch(Exception ex) {
			throw new BusinessException("System Exception.");
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getScreens() {
		
		Query query = em.createQuery("select name from Screen");		
		return query.getResultList();
	}

}
