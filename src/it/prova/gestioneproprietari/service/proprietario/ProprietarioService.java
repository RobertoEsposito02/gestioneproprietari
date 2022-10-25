package it.prova.gestioneproprietari.service.proprietario;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import it.prova.gestioneproprietari.dao.proprietario.ProprietarioDAO;
import it.prova.gestioneproprietari.model.Proprietario;

public interface ProprietarioService {
	public void setProprietarioDAO(ProprietarioDAO proprietarioDAO);
	
	public List<Proprietario> listAllProprietari() throws Exception;

	public Proprietario caricaSingoloProprietario(Long id) throws Exception;

	public void aggiorna(Proprietario proprietarioInstance) throws Exception;

	public void inserisciNuovo(Proprietario proprietarioInstance) throws Exception;

	public void rimuovi(Long idProprietarioInstance) throws Exception;
	
	public int contaQuantiProprietarisiedonoAutomobiliImmatricolateDopo(Date annoImmatricolazione) throws Exception;
}
