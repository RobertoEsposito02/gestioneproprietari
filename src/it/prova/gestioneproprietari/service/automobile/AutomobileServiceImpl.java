package it.prova.gestioneproprietari.service.automobile;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneproprietari.dao.EntityManagerUtil;
import it.prova.gestioneproprietari.dao.automobile.AutomobileDAO;
import it.prova.gestioneproprietari.model.Automobile;

public class AutomobileServiceImpl implements AutomobileService{
	private AutomobileDAO automobileDAO;

	public AutomobileDAO getAutomobileDAO() {
		return automobileDAO;
	}

	public void setAutomobileDAO(AutomobileDAO automobileDAO) {
		this.automobileDAO = automobileDAO;
	}

	@Override
	public List<Automobile> listAllAutomobili() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			
			automobileDAO.setEntityManager(entityManager);
			
			return automobileDAO.list();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Automobile caricaSingoloAutomobile(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			
			automobileDAO.setEntityManager(entityManager);
			
			return automobileDAO.get(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Automobile automobileInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			
			automobileDAO.setEntityManager(entityManager);
			
			automobileDAO.update(automobileInstance);
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Automobile automobileInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			
			automobileDAO.setEntityManager(entityManager);
			
			automobileDAO.insert(automobileInstance);
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public void rimuovi(Long idAutomobileInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			
			entityManager.getTransaction().begin();
			
			automobileDAO.setEntityManager(entityManager);
			
			automobileDAO.delete(automobileDAO.get(idAutomobileInstance));
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Automobile> cercaTutteAutomobiliConProprietarioConCodiceFiscaleCheIniziaCon(String iniziale)
			throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
		
			automobileDAO.setEntityManager(entityManager);
			
			return automobileDAO.findAllAutomobiliConProprietarioConCodiceFiscaleCheIniziaCon(iniziale);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public List<Automobile> cercaTutteAutomobiliErroriDiMinorenni() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
		
			automobileDAO.setEntityManager(entityManager);
			
			return automobileDAO.findAllAutomobiliErroriDiMinorenni();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
	
	
}
