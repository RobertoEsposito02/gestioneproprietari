package it.prova.gestioneproprietari.dao.proprietario;

import java.util.Date;

import it.prova.gestioneproprietari.dao.IBaseDAO;
import it.prova.gestioneproprietari.model.Proprietario;

public interface ProprietarioDAO extends IBaseDAO<Proprietario> {
	public int findAllProprietarisiedonoAutomobiliImmatricolateDopo(Date annoImmatricolazione) throws Exception;
}
