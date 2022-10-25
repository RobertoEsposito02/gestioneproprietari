package it.prova.gestioneproprietari.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import it.prova.gestioneproprietari.dao.EntityManagerUtil;
import it.prova.gestioneproprietari.model.Automobile;
import it.prova.gestioneproprietari.model.Proprietario;
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
			
			testInserisciUnProprietario(proprietarioService);
			/* in questo modo controllo anche il funzionamento del metodo listAllProprietari */
			System.out.println("in tabella ci sono: " + proprietarioService.listAllProprietari().size() + " elementi");
			
			testRimuoviProprietario(proprietarioService);
			System.out.println("in tabella ci sono: " + proprietarioService.listAllProprietari().size() + " elementi");
			
			testContaQuantiProprietarisiedonoAutomobiliImmatricolateDopo(proprietarioService, automobileService);
			
			testCercaTutteAutomobiliConProprietarioConCodiceFiscaleCheIniziaCon(proprietarioService, automobileService);
			
			testCercaTutteAutomobiliErroriDiMinorenni(proprietarioService, automobileService);
			
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
	
	private static void testInserisciUnProprietario(ProprietarioService proprietarioService) throws Exception{
		System.out.println(".......testInserisciUnProprietario inizio.............");
		
		Proprietario nuovoProprietario = new Proprietario("Marco","Rossi","codiceFiscaleMarcoRossi", new SimpleDateFormat("yyyy/MM/dd").parse("1980/01/01"));
		if(nuovoProprietario.getId() != null)
			throw new RuntimeException("testInserisciUnProprietario: FALLITO record gia presente");
		
		proprietarioService.inserisciNuovo(nuovoProprietario);
		if(nuovoProprietario.getId() == null)
			throw new RuntimeException("testInserisciUnProprietario: FALLITO inserimento non riuscito");
		
		System.out.println(".......testInserisciUnProprietario fine: PASSED.............");
	}
	
	/* nel testRimuoviProprietario viene provato anche il funzionamento del metodo caricaSingolaProprietario, ho pensato non ci fosse bisogno di fare un test a parte */
	private static void testRimuoviProprietario(ProprietarioService proprietarioService) throws Exception{
		System.out.println(".......testRimuoviProprietario inizio.............");
		
		if(proprietarioService.listAllProprietari().isEmpty())
			throw new RuntimeException("testRimuoviProprietario: FALLITO non c'e nulla da eliminare");
		
		Proprietario nuovoProprietario = new Proprietario("Luigi","Bianchi","codiceFiscaleLuigiBianchi", new SimpleDateFormat("yyyy/MM/dd").parse("1990/04/12"));
		if(nuovoProprietario.getId() != null)
			throw new RuntimeException("testRimuoviProprietario: FALLITO record gia presente");
		
		proprietarioService.inserisciNuovo(nuovoProprietario);
		if(nuovoProprietario.getId() == null)
			throw new RuntimeException("testRimuoviProprietario: FALLITO inserimento non riuscito");
		
		if(!nuovoProprietario.getAutomobili().isEmpty())
			throw new RuntimeException("testRimuoviProprietario: FALLITO ques'elemento ha dei figli");
		
		Long idDaEliminare = nuovoProprietario.getId();
		proprietarioService.rimuovi(idDaEliminare);
		if(proprietarioService.caricaSingoloProprietario(idDaEliminare) != null)
			throw new RuntimeException("testRimuoviProprietario: FALLITO rimozione non avvenuta");
		
		
		System.out.println(".......testRimuoviProprietario fine: PASSED.............");
	}
	
	private static void testContaQuantiProprietarisiedonoAutomobiliImmatricolateDopo(ProprietarioService proprietarioService, AutomobileService automobileService) throws Exception{
		System.out.println(".......testContaQuantiProprietarisiedonoAutomobiliImmatricolateDopo inizio.............");
		
		Proprietario nuovoProprietario = new Proprietario("Luigi","Bianchi","codiceFiscaleLuigiBianchi", new SimpleDateFormat("yyyy/MM/dd").parse("1990/04/12"));
		if(nuovoProprietario.getId() != null)
			throw new RuntimeException("testRimuoviProprietario: FALLITO record gia presente");
		proprietarioService.inserisciNuovo(nuovoProprietario);
		if(nuovoProprietario.getId() == null)
			throw new RuntimeException("testRimuoviProprietario: FALLITO inserimento non riuscito");
		
		Automobile nuovaAutomobile = new Automobile("BMW","E36","FF564GH",new SimpleDateFormat("yyyy/MM/dd").parse("1997/05/03"));
		if(nuovaAutomobile.getId() != null)
			throw new RuntimeException("testInserisciUnAutomobile: FALLITO record gia presente");
		
		nuovaAutomobile.setProprietario(nuovoProprietario);
		
		automobileService.inserisciNuovo(nuovaAutomobile);
		if(nuovaAutomobile.getId() == null)
			throw new RuntimeException("testInserisciUnAutomobile: FALLITO inserimento non riuscito");
		
		Date annoImmatricolazione = new SimpleDateFormat("yyyy/MM/dd").parse("1800/01/01");
		
		proprietarioService.contaQuantiProprietarisiedonoAutomobiliImmatricolateDopo(annoImmatricolazione);
		
		
		System.out.println(".......testContaQuantiProprietarisiedonoAutomobiliImmatricolateDopo fine: PASSED.............");
	}
	
	private static void testCercaTutteAutomobiliConProprietarioConCodiceFiscaleCheIniziaCon(ProprietarioService proprietarioService,AutomobileService automobileService) throws Exception{
		System.out.println(".......testCercaTutteAutomobiliConProprietarioConCodiceFiscaleCheIniziaCon inizio.............");
		
		Automobile nuovaAutomobile = new Automobile("Fiat","punto","FF564GH",new SimpleDateFormat("yyyy/MM/dd").parse("1997/05/03"));
		Automobile nuovaAutomobile2 = new Automobile("Fiat","panda","FF564GH",new SimpleDateFormat("yyyy/MM/dd").parse("1997/05/03"));
		Proprietario nuovoProprietario = new Proprietario("Gigi","Marrone","gigiMarroneCF", new SimpleDateFormat("yyyy/MM/dd").parse("1980/01/01"));
		
		nuovaAutomobile.setProprietario(nuovoProprietario);
		nuovaAutomobile2.setProprietario(nuovoProprietario);
		
		proprietarioService.inserisciNuovo(nuovoProprietario);
		automobileService.inserisciNuovo(nuovaAutomobile);
		automobileService.inserisciNuovo(nuovaAutomobile2);
		
		String iniziale = "g";
		automobileService.cercaTutteAutomobiliConProprietarioConCodiceFiscaleCheIniziaCon(iniziale);
		
		automobileService.rimuovi(nuovaAutomobile.getId());
		automobileService.rimuovi(nuovaAutomobile2.getId());
		proprietarioService.rimuovi(nuovoProprietario.getId());
		
		System.out.println(".......testCercaTutteAutomobiliConProprietarioConCodiceFiscaleCheIniziaCon fine: PASSED.............");
	}
	
	private static void testCercaTutteAutomobiliErroriDiMinorenni(ProprietarioService proprietarioService, AutomobileService automobileService) throws Exception{
		System.out.println(".......testCercaTutteAutomobiliErroriDiMinorenni inizio.............");
		
		Automobile nuovaAutomobile = new Automobile("Fiat","punto","FF564GH",new SimpleDateFormat("yyyy/MM/dd").parse("1997/05/03"));
		Automobile nuovaAutomobile2 = new Automobile("Fiat","panda","FF564GH",new SimpleDateFormat("yyyy/MM/dd").parse("1997/05/03"));
		Proprietario nuovoProprietario = new Proprietario("Gigi","Marrone","gigiMarroneCF", new SimpleDateFormat("yyyy/MM/dd").parse("2010/01/01"));
		Proprietario nuovoProprietario2 = new Proprietario("Gigi","Marrone","gigiMarroneCF", new SimpleDateFormat("yyyy/MM/dd").parse("2010/01/01"));
		
		nuovaAutomobile.setProprietario(nuovoProprietario);
		nuovaAutomobile2.setProprietario(nuovoProprietario2);
		
		proprietarioService.inserisciNuovo(nuovoProprietario);
		proprietarioService.inserisciNuovo(nuovoProprietario2);
		automobileService.inserisciNuovo(nuovaAutomobile);
		automobileService.inserisciNuovo(nuovaAutomobile2);
	
		automobileService.cercaTutteAutomobiliErroriDiMinorenni();

		automobileService.rimuovi(nuovaAutomobile.getId());
		automobileService.rimuovi(nuovaAutomobile2.getId());
		proprietarioService.rimuovi(nuovoProprietario.getId());
		proprietarioService.rimuovi(nuovoProprietario2.getId());
		
		System.out.println(".......testCercaTutteAutomobiliErroriDiMinorenni fine: PASSED.............");
	}
}
