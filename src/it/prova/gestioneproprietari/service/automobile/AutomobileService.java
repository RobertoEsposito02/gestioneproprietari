package it.prova.gestioneproprietari.service.automobile;

import java.util.List;

import it.prova.gestioneproprietari.dao.automobile.AutomobileDAO;
import it.prova.gestioneproprietari.model.Automobile;

public interface AutomobileService {
	public void setAutomobileDAO(AutomobileDAO automobileDAO);
	
	public List<Automobile> listAllAutomobili() throws Exception;

	public Automobile caricaSingoloAutomobile(Long id) throws Exception;

	public void aggiorna(Automobile abitanteInstance) throws Exception;

	public void inserisciNuovo(Automobile abitanteInstance) throws Exception;

	public void rimuovi(Long idAbitanteInstance) throws Exception;
}
