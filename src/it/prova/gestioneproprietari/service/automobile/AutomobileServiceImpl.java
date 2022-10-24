package it.prova.gestioneproprietari.service.automobile;

import it.prova.gestioneproprietari.dao.automobile.AutomobileDAO;

public class AutomobileServiceImpl implements AutomobileService{
	private AutomobileDAO automobileDAO;

	public AutomobileDAO getAutomobileDAO() {
		return automobileDAO;
	}

	public void setAutomobileDAO(AutomobileDAO automobileDAO) {
		this.automobileDAO = automobileDAO;
	}
	
	
}
