package it.prova.gestioneproprietari.dao.proprietario;

import java.util.Date;

import javax.persistence.TypedQuery;

import it.prova.gestioneproprietari.dao.IBaseDAO;
import it.prova.gestioneproprietari.model.Proprietario;

public interface ProprietarioDAO extends IBaseDAO<Proprietario>{
	public TypedQuery<Proprietario> findAllProprietarisiedonoAutomobiliImmatricolateDopo(Date annoImmatricolazione) throws Exception;
}
