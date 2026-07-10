package controller;

import model.*;

import java.time.LocalDate;
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
		Stanza s2 = new Stanza(2, 1);
		stanze.add(s1);
		s1.addLetto(l1);
		s1.addLetto(l2);
		s1.addLetto(l3);
		s1.addLetto(l4);

		rNeurologia.addStanza(s1);

		Letto l5 = new Letto("005");
		Letto l6 = new Letto("006");
		s2.addLetto(l5);
		s2.addLetto(l6);

		rCardiologia.addStanza(s2);

	}



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

	public List<String> getNomiPazienti(){
		ArrayList<String> nomi = new ArrayList<>();
		for (Paziente p : pazienti)
			nomi.add(p.getNome() + " " + p.getCognome()
					+ " [" + p.getCodiceFiscale() + "]");
		return nomi;

	}

	public List<String> getNomiReparti(){
		ArrayList<String> nomi = new ArrayList<>();
		for (Reparto r : reparti)
			nomi.add(r.getNome() + " (" + r.getIdReparto() + ")");
		return nomi;
	}

	public List<String> getCodiciLettiDisponibili(int indexReparto) {
		ArrayList<String> codici = new ArrayList<>();
		Reparto r = reparti.get(indexReparto);
		for (Letto l : r.getLettiDisponibili())
			codici.add(l.getCodiceUnivoco());
		return codici;
	}


	public List<Letto> getLettiDisp(Reparto reparto){
		return reparto.getLettiDisponibili();
	}

	public List<Letto> getLettiByReparto(Reparto r) {
		return r.getLetti();
	}

	public void registraPaziente(String nome, String cognome, String cf) {
		Paziente p  = new Paziente(nome, cognome, cf);
		pazienti.add(p);

	}



	public  void registraReparto(Reparto r) {
		reparti.add(r);
	}

	public void registraRicovero(int indexPaziente, int indexReparto,
								 int indexLetto, LocalDateTime dataInizio,
								 LocalDateTime dimissioniPreviste){
		Paziente paziente = pazienti.get(indexPaziente);
		Reparto reparto = reparti.get(indexReparto);
		Letto letto = reparto.getLettiDisponibili().get(indexLetto);
		for (Ricovero r: paziente.getRicoveri()){
			if (!dataInizio.isBefore(r.getDataDimissioniPrevista())
					&& !r.getDataInizio().isBefore(dimissioniPreviste)){
				throw new IllegalStateException(
						"Il paziente è già assegnato in un altro letto in questo periodo.");
			}
		}
		Ricovero ricovero = new Ricovero(dataInizio, dimissioniPreviste, null, paziente, letto);
		letto.getRicoveri().add(ricovero);
		letto.setStato(StatoLetto.OCCUPATO);
		ricoveri.add(ricovero);
		paziente.addRicovero(ricovero);

	}

	public List<Ricovero> getRicoveriInScadenza (LocalDate data) {
		List<Ricovero> inScadenza = new ArrayList<>();
		for (Ricovero r: ricoveri){
			if (r.getDataDimissioniPrevista().toLocalDate().equals(data)) {
				inScadenza.add(r);
			}
		}
		return inScadenza;
	}
	public List<Ricovero> getRicoveri() {
		return ricoveri;
	}

}