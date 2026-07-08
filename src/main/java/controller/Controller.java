package controller;

import model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Controller {

	// Dati dell'utente autenticato
	private Utente utenteCorrente;

	// Dati di sistema
	private ArrayList<Utente> utenti = new ArrayList<>();
	private ArrayList<Reparto> reparti = new ArrayList<>();
	private ArrayList<Paziente> pazienti = new ArrayList<>();
	private ArrayList<Ricovero> ricoveri = new ArrayList<>();
	private ArrayList<Letto> letti = new ArrayList<>();
	private ArrayList<Stanza> stanze = new ArrayList<>();

	public Controller() {
		// dati per testare
		utenti.add(new Amministratore("admin1", "admin123"));

		Reparto rNeurologia = new Reparto("Neurologia", "REP01");
		Reparto rCardiologia = new  Reparto("Cardiologia", "REP02");
		reparti.add(rNeurologia);
		reparti.add(rCardiologia);

		utenti.add(new Medico("medico1", "medico123", "Mario", "Rossi", rNeurologia));

		Letto l1 = new Letto("001");
		Letto l2 = new Letto("002");
		Letto l3 = new Letto("003");
		Letto l4 = new Letto("004");
		letti.add(l1);
		letti.add(l2);
		letti.add(l3);
		letti.add(l4);

		Stanza s1 = new Stanza(1, 1);
		stanze.add(s1);
		s1.addLetto(l1);

		rNeurologia.addStanza(s1);


	}

	// In base alle credenziali restituisce un ruolo, null se sono errate.

	public String login(String login, String password) {
		for (Utente u : utenti) {
			if (u.login(login, password)) {
				utenteCorrente = u;
				if (u instanceof Amministratore) return "AMMINISTRATORE";
				if (u instanceof Medico) return "MEDICO";
			}
		}
		return null;
	}

	public Utente getUtenteCorrente() {
		return utenteCorrente;
	}

	public Medico getMedicoCorrente() {
		if (utenteCorrente instanceof Medico) return (Medico) utenteCorrente;
		return null;
	}

	public Amministratore getAmministratoreCorrente() {
		if (utenteCorrente instanceof Amministratore) return (Amministratore) utenteCorrente;
		return null;
	}
	public List<Reparto> getReparti() {
		return reparti;
	}
	public List<Paziente> getPazienti() {
		return pazienti;
	}

	public List<Letto> getLettiDisp(Reparto reparto){
		return reparto.getLettiDisponibili();
	}

	public void registraPaziente(Paziente p) {
		pazienti.add(p);
	}
	public  void registraReparto(Reparto r) {
		reparti.add(r);
	}

	public void registraRicovero(Paziente paziente, Letto letto, LocalDateTime dataInizio, LocalDateTime dimissioniPreviste ){
		for (Ricovero r: letto.getRicoveri()){
			boolean Sovrapposto = dataInizio.isBefore(r.getDataDimissioniPrevista()) && dimissioniPreviste.isAfter(r.getDataInizio());
			if (Sovrapposto){
				throw new IllegalStateException(
						"Il letto è già occupato in questo periodo.");

			}

		}
		Ricovero ricovero = new Ricovero(dataInizio, dimissioniPreviste, null, paziente);
		letto.getRicoveri().add(ricovero);
		letto.setStato(StatoLetto.OCCUPATO);
		ricoveri.add(ricovero);

	}


}