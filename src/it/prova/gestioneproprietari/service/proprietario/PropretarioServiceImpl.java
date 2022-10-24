package it.prova.gestioneproprietari.service.proprietario;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneproprietari.dao.EntityManagerUtil;
import it.prova.gestioneproprietari.dao.proprietario.ProprietarioDAO;
import it.prova.gestioneproprietari.model.Proprietario;

public class PropretarioServiceImpl implements ProprietarioService{

	private ProprietarioDAO proprietarioDAO;
	
	public ProprietarioDAO getProprietarioDAO() {
		return proprietarioDAO;
	}

	@Override
	public void setProprietarioDAO(ProprietarioDAO proprietarioDAO) {
		this.proprietarioDAO = proprietarioDAO;
	}

	@Override
	public List<Proprietario> listAllProprietari() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		try {
			
			proprietarioDAO.setEntityManager(entityManager);
			
			return proprietarioDAO.list();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Proprietario caricaSingoloProprietario(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			
			proprietarioDAO.setEntityManager(entityManager);
			
			return proprietarioDAO.get(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Proprietario proprietarioInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			
			proprietarioDAO.setEntityManager(entityManager);
			
			proprietarioDAO.update(proprietarioInstance);
			
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
	public void inserisciNuovo(Proprietario proprietarioInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			
			proprietarioDAO.setEntityManager(entityManager);
			
			proprietarioDAO.insert(proprietarioInstance);
			
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
	public void rimuovi(Long idProprietarioInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		
		try {
			
			entityManager.getTransaction().begin();
			
			proprietarioDAO.setEntityManager(entityManager);
			
			proprietarioDAO.delete(proprietarioDAO.get(idProprietarioInstance));
			
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
	public TypedQuery<Proprietario> contaQuantiProprietarisiedonoAutomobiliImmatricolateDopo(Date annoImmatricolazione) throws Exception{
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
	
		try {

			proprietarioDAO.setEntityManager(entityManager);
		
			return proprietarioDAO.findAllProprietarisiedonoAutomobiliImmatricolateDopo(annoImmatricolazione);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

}
