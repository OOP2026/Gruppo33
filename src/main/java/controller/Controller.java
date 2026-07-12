package controller;

import model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Controller.
 */
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

	/**
	 * Instantiates a new Controller.
	 */
	public Controller() {
		// dati per testare
		utenti.add(new Amministratore("admin1", "admin123"));

		Reparto rNeurologia = new Reparto("Neurologia", "REP01");
		Reparto rCardiologia = new Reparto("Cardiologia", "REP02");
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


	/**
	 * Login string.
	 *
	 * @param login    the login
	 * @param password the password
	 * @return the string
	 */
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

	/**
	 * Gets utente corrente.
	 *
	 * @return the utente corrente
	 */
	public Utente getUtenteCorrente() {
		return utenteCorrente;
	}

	/**
	 * Gets medico corrente.
	 *
	 * @return the medico corrente
	 */
	public Medico getMedicoCorrente() {
		if (utenteCorrente instanceof Medico) return (Medico) utenteCorrente;
		return null;
	}

	/**
	 * Gets amministratore corrente.
	 *
	 * @return the amministratore corrente
	 */
	public Amministratore getAmministratoreCorrente() {
		if (utenteCorrente instanceof Amministratore) return (Amministratore) utenteCorrente;
		return null;
	}

	/**
	 * Gets pazienti in scadenza.
	 *
	 * @param data the data
	 * @return the pazienti in scadenza
	 */
	public List<String[]> getPazientiInScadenza(LocalDate data) {

		List<String[]> risultato = new ArrayList<>();
		for (Ricovero r : ricoveri) {
			if (r.getDataDimissioniPrevista().toLocalDate().equals(data)) {
				Paziente p = r.getPaziente();
				risultato.add(new String[]{
						p.getNome(),
						p.getCognome(),
						p.getCodiceFiscale(),
						r.getDataDimissioniPrevista().toString()
				});

			}
		}
		return risultato;
	}

	/**
	 * Gets nomi pazienti.
	 *
	 * @return the nomi pazienti
	 */
	public List<String> getNomiPazienti() {
		Amministratore amministratore = getAmministratoreCorrente();
		List<Paziente> p = amministratore.getPazienti();
		ArrayList<String> nomi = new ArrayList<>();
		for (Paziente paziente : p)
			nomi.add(paziente.getNome() + " " + paziente.getCognome()
					+ " [" + paziente.getCodiceFiscale() + "]");
		return nomi;

	}

	/**
	 * Gets nomi reparti.
	 *
	 * @return the nomi reparti
	 */
	public List<String> getNomiReparti() {
		ArrayList<String> nomi = new ArrayList<>();
		for (Reparto r : reparti)
			nomi.add(r.getNome() + " (" + r.getIdReparto() + ")");
		return nomi;
	}

	/**
	 * Gets codici letti disponibili.
	 *
	 * @param indexReparto the index reparto
	 * @return the codici letti disponibili
	 */
	public List<String> getCodiciLettiDisponibili(int indexReparto) {
		ArrayList<String> codici = new ArrayList<>();
		Reparto r = reparti.get(indexReparto);
		for (Letto l : r.getLettiDisponibili())
			codici.add(l.getCodiceUnivoco());
		return codici;
	}


	/**
	 * Gets letti disp.
	 *
	 * @param reparto the reparto
	 * @return the letti disp
	 */
	public List<Letto> getLettiDisp(Reparto reparto) {
		return reparto.getLettiDisponibili();
	}

	/**
	 * Gets letti by reparto.
	 *
	 * @param r the r
	 * @return the letti by reparto
	 */
	public List<Letto> getLettiByReparto(Reparto r) {
		return r.getLetti();
	}

	/**
	 * Registra paziente.
	 *
	 * @param nome    the nome
	 * @param cognome the cognome
	 * @param cf      the cf
	 */
	public void registraPaziente(String nome, String cognome, String cf) {
		Amministratore amministratore = getAmministratoreCorrente();
		amministratore.registerPaziente(nome, cognome, cf);

	}

	/**
	 * Gets codici letti by reparto.
	 *
	 * @param indexReparto the index reparto
	 * @return the codici letti by reparto
	 */
	public List<String> getCodiciLettiByReparto(int indexReparto) {
		List<String> codici = new ArrayList<>();
		Reparto r = reparti.get(indexReparto);
		for (Letto l : r.getLetti())
			codici.add(l.getCodiceUnivoco() + " - " + l.getStato().toString());
		return codici;

	}

	/**
	 * Registra ricovero.
	 *
	 * @param indexPaziente      the index paziente
	 * @param indexReparto       the index reparto
	 * @param indexLetto         the index letto
	 * @param dataInizio         the data inizio
	 * @param dimissioniPreviste the dimissioni previste
	 */
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

	/**
	 * Gets ricoveri in scadenza.
	 *
	 * @param data the data
	 * @return the ricoveri in scadenza
	 */
	public List<Ricovero> getRicoveriInScadenza (LocalDate data) {
		List<Ricovero> inScadenza = new ArrayList<>();
		for (Ricovero r: ricoveri){
			if (r.getDataDimissioniPrevista().toLocalDate().equals(data)) {
				inScadenza.add(r);
			}
		}
		return inScadenza;
	}


	/**
	 * Gets tipi prestazione.
	 *
	 * @return the tipi prestazione
	 */
	public List<String> getTipiPrestazione() {
		ArrayList<String> tipi = new ArrayList<>();
		for (TipoPrestazione t : TipoPrestazione.values())
			tipi.add(t.toString());
		return tipi;

	}

	/**
	 * Registra prestazione.
	 *
	 * @param indexRicovero the index ricovero
	 * @param indexTipo     the index tipo
	 * @param oraInizio     the ora inizio
	 * @param oraFine       the ora fine
	 */
	public void registraPrestazione(int indexRicovero, int indexTipo,
									LocalDateTime oraInizio, LocalDateTime oraFine) {
		Medico medico = getMedicoCorrente();
		Ricovero ricovero = ricoveri.get(indexRicovero);
		TipoPrestazione tipo = TipoPrestazione.values()[indexTipo];
		Prestazione p = new Prestazione(null, tipo, oraInizio, oraFine, ricovero);
		medico.registerPrestazione(p);
	}


}