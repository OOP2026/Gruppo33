package controller;

import model.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

	// Dati dell'utente autenticato
	private Utente utenteCorrente;

	// Dati di sistema
	private ArrayList<Utente> utenti = new ArrayList<>();
	private ArrayList<Reparto> reparti = new ArrayList<>();
	private ArrayList<Paziente> pazienti = new ArrayList<>();
	private ArrayList<Ricovero> ricoveri = new ArrayList<>();

	public Controller() {
		// dati per testare il sistema login
		utenti.add(new Amministratore("admin1", "admin123"));

		Reparto rNeurologia = new Reparto("Neurologia", "2kfdokf");
		reparti.add(rNeurologia);

		utenti.add(new Medico("medico1", "medico123", "Mario", "Rossi", rNeurologia));
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

	public void registraPaziente(Paziente p) {
		pazienti.add(p);
	}
}