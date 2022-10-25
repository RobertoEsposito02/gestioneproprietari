package it.prova.gestioneproprietari.dao.automobile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestioneproprietari.model.Automobile;

public class AutomobileDAOImpl implements AutomobileDAO{

	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;	
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<Automobile> list() throws Exception {
		return entityManager.createQuery("from Automobile", Automobile.class).getResultList();
	}

	@Override
	public Automobile get(Long id) throws Exception {
		return entityManager.find(Automobile.class, id);
	}

	@Override
	public void update(Automobile automobileInstance) throws Exception {
		if(automobileInstance == null)
			throw new Exception("input non valido");
		
		automobileInstance = entityManager.merge(automobileInstance);
		
	}

	@Override
	public void insert(Automobile automobileInstance) throws Exception {
		if (automobileInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(automobileInstance);
		
	}

	@Override
	public void delete(Automobile automobileInstance) throws Exception {
		if (automobileInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.remove(entityManager.merge(automobileInstance));
	}

	@Override
	public List<Automobile> findAllAutomobiliConProprietarioConCodiceFiscaleCheIniziaCon(String iniziale)
			throws Exception {
		TypedQuery<Automobile> query = entityManager.createQuery("select distinct a from Automobile a left join fetch a.proprietario where codicefiscale like ?1", Automobile.class);
		return query.setParameter(1, iniziale +"%").getResultList();
	}

	@Override
	public List<Automobile> findAllAutomobiliErroriDiMinorenni() throws Exception{
		String dataString = "2005-01-01";
		Date date = new SimpleDateFormat("yyyy/MM/dd").parse("2005/01/01");
		TypedQuery<Automobile> query = entityManager.createQuery("from Automobile a inner join fetch a.proprietario where datadinascita > ?1", Automobile.class);
		return query.setParameter(1,date).getResultList();
	}
}
