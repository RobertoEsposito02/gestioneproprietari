package it.prova.gestioneproprietari.service.proprietario;

import it.prova.gestioneproprietari.dao.proprietario.ProprietarioDAO;

public class PropretarioServiceImpl implements ProprietarioService{

	private ProprietarioDAO proprietarioDAO;
	
	public ProprietarioDAO getProprietarioDAO() {
		return proprietarioDAO;
	}

	@Override
	public void setProprietarioDAO(ProprietarioDAO proprietarioDAO) {
		this.proprietarioDAO = proprietarioDAO;
	}

}
