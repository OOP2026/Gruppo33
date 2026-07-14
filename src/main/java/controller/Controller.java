package controller;

import dao.PazienteDAO;
import implementazioneDao.PazientePostgresDAO;
import model.*;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Controller.
 */
public class Controller {

	// Dati dell'utente autenticato
	private Utente utenteCorrente;
    private Amministratore amministratore;
	private Medico medico;

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
		Amministratore admin = new Amministratore("admin1", "admin123");
		utenti.add(admin);
		this.amministratore = admin;

		Reparto rNeurologia = new Reparto("Neurologia", "REP01");
		Reparto rCardiologia = new Reparto("Cardiologia", "REP02");
		reparti.add(rNeurologia);
		reparti.add(rCardiologia);

		Medico medico = new Medico("medico1", "medico123", "Mario", "Rossi", rNeurologia);
		utenti.add(medico);
		this.medico = medico;

		//turno lavorativo
		TurnoLavorativo t1 = new TurnoLavorativo(DayOfWeek.MONDAY, LocalTime.of(14, 0), LocalTime.of(23, 0));
		TurnoLavorativo t2 = new TurnoLavorativo(DayOfWeek.TUESDAY, LocalTime.of(14, 0), LocalTime.of(23, 0));
		TurnoLavorativo t3 = new TurnoLavorativo(DayOfWeek.WEDNESDAY, LocalTime.of(14, 0), LocalTime.of(23, 0));
		TurnoLavorativo t4 = new TurnoLavorativo(DayOfWeek.THURSDAY, LocalTime.of(14, 0), LocalTime.of(23, 0));
		TurnoLavorativo t5 = new TurnoLavorativo(DayOfWeek.FRIDAY, LocalTime.of(14, 0), LocalTime.of(23, 0));
		TurnoLavorativo t6 = new TurnoLavorativo(DayOfWeek.SATURDAY, LocalTime.of(14, 0), LocalTime.of(23, 0));
		TurnoLavorativo t7 = new TurnoLavorativo(DayOfWeek.SUNDAY, LocalTime.of(14, 0), LocalTime.of(23, 0));

		medico.addTurnoLavorativo(t1);
		medico.addTurnoLavorativo(t2);
		medico.addTurnoLavorativo(t3);
		medico.addTurnoLavorativo(t4);
		medico.addTurnoLavorativo(t5);
		medico.addTurnoLavorativo(t6);
		medico.addTurnoLavorativo(t7);



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

		ArrayList<String> nomi = new ArrayList<>();
		ArrayList<String> cognomi = new ArrayList<>();
		ArrayList<String> codiciF = new ArrayList<>();
		PazienteDAO pdao = new PazientePostgresDAO();
		pdao.leggiPazientiDB(nomi, cognomi, codiciF);
		for (int i = 0; i < nomi.size(); i++) {
			Paziente p = new Paziente(nomi.get(i), cognomi.get(i), codiciF.get(i));
			amministratore.registerPaziente(nomi.get(i), cognomi.get(i), codiciF.get(i));
		}


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
	 * Gets nomi pazienti.
	 *
	 * @return the nomi pazienti
	 */
	public List<String> getNomiPazienti() {
		ArrayList<String> nomi = new ArrayList<>();
		for (Paziente p : amministratore.getPazienti())
			nomi.add(p.getNome() + " " + p.getCognome()
					+ " [" + p.getCodiceFiscale() + "]");
		return nomi;
	}

	public List<String> getNomiPazientiRicoverati() {
		ArrayList<String> nomi = new ArrayList<>();
		for (Ricovero r : amministratore.getRicoveri()) {
			Paziente paziente = r.getPaziente();
			nomi.add(paziente.getNome() + " " + paziente.getCognome()
					+ " [" + paziente.getCodiceFiscale() + "]");
		}
		return nomi;
	}


	/**
	 * Gets pazienti in scadenza.
	 *
	 * @param data the data
	 * @return the pazienti in scadenza
	 */
	public List<String[]> getPazientiInScadenza(LocalDate data) {
		List<String[]> risultato = new ArrayList<>();
		for (Ricovero r : amministratore.getPazientiInScadenza(data)) {
			Paziente p = r.getPaziente();
			risultato.add(new String[]{
					p.getNome(),
					p.getCognome(),
					p.getCodiceFiscale(),
					r.getDataDimissioniPrevista().toString()
			});
		}
		return risultato;
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
	PazienteDAO pdao = new PazientePostgresDAO();
        amministratore.registerPaziente(nome, cognome, cf);
		pdao.inserisciPazienteDB(nome, cognome, cf);

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
		Paziente paziente = amministratore.getPazienti().get(indexPaziente);
		Reparto reparto = reparti.get(indexReparto);
		Letto letto = reparto.getLettiDisponibili().get(indexLetto);
		amministratore.registerRicovero(paziente, letto,
				dataInizio, dimissioniPreviste);

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
		Ricovero ricovero = amministratore.getRicoveri().get(indexRicovero);
		TipoPrestazione tipo = TipoPrestazione.values()[indexTipo];
		Prestazione p = new Prestazione(null, tipo, oraInizio, oraFine, ricovero);
		medico.registerPrestazione(p);
	}



	public List<String[]> getPrestazioniGiornaliere(LocalDate data) {
		ArrayList<String[]> prestazioniGiornaliere = new ArrayList<>();
		for (Prestazione p : medico.getPrestazioni()) {
			if (p.getOraInizio().toLocalDate().equals(data)) {
				prestazioniGiornaliere.add(new String[]{
						p.getOraInizio().toString(),
						p.getOraFine().toString(),
						p.getTipo().toString(),
						p.getRicovero().getPaziente().getNome() +
						p.getRicovero().getPaziente().getCognome(),
						p.getEsito()

				});
			}

		}
		return prestazioniGiornaliere;

	}


	public List <String[]> getPrestazioniSettimanali(LocalDate data) {
		ArrayList<String[]> prestazioniSettimanali = new ArrayList<>();
		LocalDate lunedi = data.with(DayOfWeek.MONDAY);
		LocalDate domenica = lunedi.plusDays(6);
		for (Prestazione p : medico.getPrestazioni()) {
			if (p.getOraInizio().toLocalDate().isAfter(lunedi)
				&& p.getOraFine().toLocalDate().isBefore(domenica)) {
				prestazioniSettimanali.add(new String[]{
						p.getOraInizio().toString(),
						p.getOraFine().toString(),
						p.getTipo().toString(),
						p.getRicovero().getPaziente().getNome() + p.getRicovero().getPaziente().getCognome(),
						p.getEsito()
				});

			}

		}

		return  prestazioniSettimanali;
	}


	public void modificaEsito(int indexPrestazione, String esito) {
		medico.getPrestazioni().get(indexPrestazione).setEsito(esito);

	}

}