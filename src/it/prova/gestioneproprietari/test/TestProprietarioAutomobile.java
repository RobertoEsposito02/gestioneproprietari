package it.prova.gestioneproprietari.test;

import java.text.SimpleDateFormat;

import it.prova.gestioneproprietari.dao.EntityManagerUtil;
import it.prova.gestioneproprietari.model.Automobile;
import it.prova.gestioneproprietari.service.MyServiceFactory;
import it.prova.gestioneproprietari.service.automobile.AutomobileService;
import it.prova.gestioneproprietari.service.proprietario.ProprietarioService;

public class TestProprietarioAutomobile {
	public static void main(String[] args) {
		ProprietarioService proprietarioService = MyServiceFactory.getProprietarioServiceInstance();
		AutomobileService automobileService = MyServiceFactory.getAutomobileServiceInstance();
		
		try {
			
			testListAllAutomobile(automobileService);
			System.out.println("in tabella ci sono: " + automobileService.listAllAutomobili().size() + " elementi");
			
			testInserisciUnAutomobile(automobileService);
			System.out.println("in tabella ci sono: " + automobileService.listAllAutomobili().size() + " elementi");
			
			testRimuoviAutomobile(automobileService);
			System.out.println("in tabella ci sono: " + automobileService.listAllAutomobili().size() + " elementi");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerUtil.shutdown();
		}
	}
	
	private static void testListAllAutomobile(AutomobileService automobileService) throws Exception{
		System.out.println(".......testListAllAutomobile inizio.............");
		
		automobileService.listAllAutomobili();
		
		System.out.println(".......testListAllAutomobile fine: PASSED.............");
	}
	
	private static void testInserisciUnAutomobile(AutomobileService automobileService) throws Exception{
		System.out.println(".......testInserisciUnAutomobile inizio.............");
		
		Automobile nuovaAutomobile = new Automobile("BMW","E36","FF564GH",new SimpleDateFormat("yyyy/MM/dd").parse("1997/05/03"));
		if(nuovaAutomobile.getId() != null)
			throw new RuntimeException("testInserisciUnAutomobile: FALLITO record gia presente");
		
		automobileService.inserisciNuovo(nuovaAutomobile);
		if(nuovaAutomobile.getId() == null)
			throw new RuntimeException("testInserisciUnAutomobile: FALLITO inserimento non riuscito");
		
		System.out.println(".......testInserisciUnAutomobile fine: PASSED.............");
	}
	
	/* nel testRimuoviAutomobile viene provato anche il funzionamento del metodo caricaSingolaAutomobile, ho pensato non ci fosse bisogno di fare un test a parte */
	private static void testRimuoviAutomobile(AutomobileService automobileService) throws Exception{
		System.out.println(".......testRimuoviAutomobile inizio.............");
		
		if(automobileService.listAllAutomobili().isEmpty())
			throw new RuntimeException("testRimuoviAutomobile: FALLITO non c'e nulla da eliminare");
		
		Automobile nuovaAutomobile = new Automobile("BMW","E36","FF564GH",new SimpleDateFormat("yyyy/MM/dd").parse("1997/05/03"));
		if(nuovaAutomobile.getId() != null)
			throw new RuntimeException("testInserisciUnAutomobile: FALLITO record gia presente");
		
		automobileService.inserisciNuovo(nuovaAutomobile);
		if(nuovaAutomobile.getId() == null)
			throw new RuntimeException("testInserisciUnAutomobile: FALLITO inserimento non riuscito");
		
		Long idDaEliminare = nuovaAutomobile.getId();
		automobileService.rimuovi(idDaEliminare);
		if(automobileService.caricaSingoloAutomobile(idDaEliminare) != null)
			throw new RuntimeException("testRimuoviAutomobile: FALLITO rimozione non avvenuta");
		
		
		System.out.println(".......testRimuoviAutomobile fine: PASSED.............");
	}
	
	
}
