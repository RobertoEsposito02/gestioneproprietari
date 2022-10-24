package it.prova.gestioneproprietari.dao.proprietario;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioneproprietari.model.Proprietario;

public class ProprietarioDAOImpl implements ProprietarioDAO {

	private EntityManager entityManager;
	
	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<Proprietario> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Proprietario get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Proprietario o) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Proprietario o) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Proprietario o) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
