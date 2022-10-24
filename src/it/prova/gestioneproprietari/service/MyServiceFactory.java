package it.prova.gestioneproprietari.service;

import it.prova.gestioneproprietari.dao.MyDaoFactory;
import it.prova.gestioneproprietari.service.automobile.AutomobileService;
import it.prova.gestioneproprietari.service.automobile.AutomobileServiceImpl;
import it.prova.gestioneproprietari.service.proprietario.PropretarioServiceImpl;
import it.prova.gestioneproprietari.service.proprietario.ProprietarioService;

public class MyServiceFactory {

	private static AutomobileService automobileServiceInstance = null;
	private static ProprietarioService proprietarioServiceInstance = null;

	public static AutomobileService getAbitanteServiceInstance() {
		if (automobileServiceInstance == null) {
			automobileServiceInstance = new AutomobileServiceImpl();
			automobileServiceInstance.setAutomobileDAO(MyDaoFactory.getAutomobileDAOInstance());
		}
		return automobileServiceInstance;
	}

	public static ProprietarioService getMunicipioServiceInstance() {
		if (proprietarioServiceInstance == null) {
			proprietarioServiceInstance = new PropretarioServiceImpl();
			proprietarioServiceInstance.setProprietarioDAO(MyDaoFactory.getProprietarioDAOInstance());;
		}
		return proprietarioServiceInstance;
	}

}
