package controller;

import dao.*;
import implementazioneDao.*;
import model.*;


import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Controller.
 */
public class Controller {

	// Dati dell'utente autenticato
	private Utente utenteCorrente;
    private final Amministratore amministratore;
	private final Medico medico;

	private final ArrayList<Utente> utenti = new ArrayList<>();
	private final ArrayList<Reparto> reparti = new ArrayList<>();
	private final ArrayList<Ricovero> ricoveri = new ArrayList<>();
	private final ArrayList<Letto> letti = new ArrayList<>();
	private final ArrayList<Stanza> stanze = new ArrayList<>();

	/**
	 * Instantiates a new Controller.
	 */
	public Controller() {
		// dati per testare
		Amministratore admin = new Amministratore("admin1", "admin123");
		utenti.add(admin);
		this.amministratore = admin;

		//Caricamento reparti dal database
		ArrayList<String> idReparti = new ArrayList<>();
		ArrayList<String> nomiReparti = new ArrayList<>();
		try {
		RepartoDAO repdao = new RepartoPostgresDAO();
		repdao.leggiRepartiDB(idReparti, nomiReparti);
		for (int i=0; i<idReparti.size(); i++) {
			Reparto reparto = new Reparto(nomiReparti.get(i), idReparti.get(i));
			reparti.add(reparto);
		}

		} catch (SQLException se1) {
			System.err.println("Errore nel caricamento del database (Reparti)");
		}


		Reparto rNeurologia = getRepartoDaId("REP01");
		Medico medico = new Medico("medico1", "medico123", "Mario", "Rossi", rNeurologia);
		utenti.add(medico);
		this.medico = medico;


		//Caricamento turni dal database

		ArrayList<String> loginTurni = new ArrayList<>();
		ArrayList<DayOfWeek> giorniTurni = new ArrayList<>();
		ArrayList<LocalTime> oreInizioTurni = new ArrayList<>();
		ArrayList<LocalTime> oreFineTurni = new ArrayList<>();
		try {
			TurnoDAO tdao = new TurnoPostgresDAO();
			tdao.leggiTurniDB(loginTurni, giorniTurni, oreInizioTurni, oreFineTurni);

			for (int i = 0; i < loginTurni.size(); i++) {
				if (medico.getLogin().equals(loginTurni.get(i))) {
					TurnoLavorativo t = new TurnoLavorativo(giorniTurni.get(i), oreInizioTurni.get(i), oreFineTurni.get(i));
					medico.addTurnoLavorativo(t);
				}
			}

		} catch (SQLException se4) {
			System.err.println("Errore nel caricamento del database (Turni lavorativi)");

		}
		//Caricamento stanze dal database

		ArrayList<Integer> numeriStanza = new ArrayList<>();
		ArrayList<Integer> piani = new ArrayList<>();
		ArrayList<String> idRepartiFK = new ArrayList<>();
		try {
			StanzaDAO stanzaDao = new StanzaPostgresDAO();
			stanzaDao.leggiStanzeDB(numeriStanza, piani, idRepartiFK);
			for (int i = 0; i < numeriStanza.size(); i++) {
				Stanza s = new Stanza(numeriStanza.get(i), piani.get(i));
				stanze.add(s);
				Reparto repartoDiAppartenenza = getRepartoDaId(idRepartiFK.get(i));
				if (repartoDiAppartenenza != null) {
					repartoDiAppartenenza.addStanza(s);
				}
			}
		} catch  (SQLException se2) {
			System.err.println("Errore nel caricamento del database (stanze)");
		}

		//Caricamento letti dal database

		ArrayList<String> codiciLetto = new ArrayList<>();
		ArrayList<String> statiLetto = new ArrayList<>();
		ArrayList<Integer> numeriStanzaFK = new ArrayList<>();
		try {
			LettoDAO lettoDao = new LettoPostgresDAO();
			lettoDao.leggiLettiDB(codiciLetto, statiLetto, numeriStanzaFK);
			for (int i = 0; i < codiciLetto.size(); i++) {
				Letto l = new Letto(codiciLetto.get(i));
				l.setStato(StatoLetto.valueOf(statiLetto.get(i)));
				letti.add(l);
				Stanza stanzaDiAppartenenza = getStanzaDaNumero(numeriStanzaFK.get(i));
				if (stanzaDiAppartenenza != null) {
					stanzaDiAppartenenza.addLetto(l);
				}
			}
		} catch (SQLException se3) {
			System.err.println("Errore nel caricamento del database (letti)");
		}

		//Caricamento pazienti dal database

		ArrayList<String> nomi = new ArrayList<>();
		ArrayList<String> cognomi = new ArrayList<>();
		ArrayList<String> codiciF = new ArrayList<>();
		PazienteDAO pdao = new PazientePostgresDAO();
		try {
			pdao.leggiPazientiDB(nomi, cognomi, codiciF);
			for (int i = 0; i < nomi.size(); i++) {
				amministratore.registerPaziente(nomi.get(i), cognomi.get(i), codiciF.get(i));
			}
		} catch (SQLException ex) {
			System.err.println("Errore nel caricamento del database (Pazienti registrati)");
		}


		//Caricamento ricoveri dal database
		ArrayList<Integer> idRicoveri = new ArrayList<>();
		ArrayList<LocalDateTime> dateInizio  = new ArrayList<>();
		ArrayList<LocalDateTime> dateDimissioniPreviste = new ArrayList<>();
		ArrayList<String> codiciFisc = new ArrayList<>();
		ArrayList<String> codiciLett = new ArrayList<>();
		RicoveroDAO rdao = new RicoveroPostgresDAO();
		rdao.leggiRicoveroDB(idRicoveri, dateInizio, dateDimissioniPreviste, codiciFisc, codiciLett);
		for (int i=0; i<idRicoveri.size(); i++) {
			Paziente paz = getPazienteDaCf(codiciFisc.get(i));
			Letto lett = getLettoDaCodice(codiciLett.get(i));
			if (paz != null && lett != null) {
				amministratore.registerRicovero(paz, lett, dateInizio.get(i), dateDimissioniPreviste.get(i));
				 Ricovero r = amministratore.getRicoveri().get(amministratore.getRicoveri().size()-1);
				 r.setIdRicovero(idRicoveri.get(i));

			}

		}

		//Caricamento prestazioni dal database

		ArrayList<LocalDateTime> dataOreInizio = new ArrayList<>();
		ArrayList<LocalDateTime> dateOraFine = new ArrayList<>();
		ArrayList<String> tipiPrestazione  = new ArrayList<>();
		ArrayList<String> esiti = new  ArrayList<>();
		ArrayList<Integer> idRicoveriDellePrestazioni = new ArrayList<>();
		PrestazioneDAO prestazioneDAO = new PrestazionePostgresDAO();
		try {
			prestazioneDAO.leggiPrestazioniDB(dataOreInizio, dateOraFine, tipiPrestazione, esiti, idRicoveriDellePrestazioni);

			for (int i = 0; i < dataOreInizio.size(); i++) {
				int idRicoveroCorrente = idRicoveriDellePrestazioni.get(i);
				Ricovero ricovero = getRicoveroDaId(idRicoveroCorrente);
				if (ricovero != null) {
					TipoPrestazione tipo = TipoPrestazione.valueOf(tipiPrestazione.get(i));
					Prestazione p = new Prestazione(esiti.get(i), tipo, dataOreInizio.get(i), dateOraFine.get(i), ricovero);
					medico.registerPrestazione(p);
				}
			}
		} catch  (SQLException ex2) {
			System.err.println("Errore nel caricamento del database (Prestazioni registrate)");
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

	/**
	 * Gets nomi pazienti ricoverati.
	 *
	 * @return the nomi pazienti ricoverati
	 */
	public List<String> getNomiPazientiRicoverati() {
		ArrayList<String> nomi = new ArrayList<>();
		for (Ricovero r : amministratore.getRicoveri()) {

			Letto letto = r.getLetto();
			boolean nelReparto = medico.getReparto().getLetti().contains(letto);
			if (nelReparto) {
				Paziente paziente = r.getPaziente();
				nomi.add(paziente.getNome() + " " + paziente.getCognome()
						+ " [" + paziente.getCodiceFiscale() + "]");
			}
		} return nomi;
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
			if (r.getDataDimissioniEffettuata() != null) {
				continue;
			}
			Paziente p = r.getPaziente();
			String dimissioneEffettuata = (r.getDataDimissioniEffettuata() != null) ? r.getDataDimissioniEffettuata().toString() : "-";
			risultato.add(new String[]{
					String.valueOf(r.getIdRicovero()),
					p.getNome(),
					p.getCognome(),
					p.getCodiceFiscale(),
					r.getDataDimissioniPrevista().toString(),
					dimissioneEffettuata
			});
		}
		return risultato;
	}


	/**
	 * Dimetti paziente.
	 *
	 * @param idRicovero the id ricovero
	 * @param data       the data
	 */
	public void dimettiPaziente(int idRicovero, LocalDateTime data) {
		Ricovero ricovero = getRicoveroDaId(idRicovero);
		if (ricovero == null) {
			throw new IllegalArgumentException("Ricovero non trovato");
		}
		ricovero.setDataDimissioniPrevista(data);
		RicoveroDAO ridao = new RicoveroPostgresDAO();
		ridao.aggiornaDimissioneDB(idRicovero, data);


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
	 * @throws SQLIntegrityConstraintViolationException the sql integrity constraint violation exception
	 */
	public void registraPaziente(String nome, String cognome, String cf) throws SQLIntegrityConstraintViolationException {
		PazienteDAO pdao = new PazientePostgresDAO();

		try {
			amministratore.registerPaziente(nome, cognome, cf);
			pdao.inserisciPazienteDB(nome, cognome, cf);

		} catch (SQLException e) {
			throw new SQLIntegrityConstraintViolationException ("Paziente già registrato in sistema");
		}
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
								 LocalDateTime dimissioniPreviste) {
		Paziente paziente = amministratore.getPazienti().get(indexPaziente);
		Reparto reparto = reparti.get(indexReparto);
		Letto letto = reparto.getLettiDisponibili().get(indexLetto);
		amministratore.registerRicovero(paziente, letto,
				dataInizio, dimissioniPreviste);

		RicoveroDAO rdao = new RicoveroPostgresDAO();
		int idGenerato = rdao.inserisciRicoveroDB(
				dataInizio,
				dimissioniPreviste,
				paziente.getCodiceFiscale(),
				letto.getCodiceUnivoco()
		);

		Ricovero ricoveroAppenaCreato = getRicoveroNonPersistito(paziente, letto, dataInizio);
		if (ricoveroAppenaCreato != null) {
			ricoveroAppenaCreato.setIdRicovero(idGenerato);
		}


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
	 * @throws Exception the exception
	 */
	public void registraPrestazione(int indexRicovero, int indexTipo,
									LocalDateTime oraInizio, LocalDateTime oraFine) throws Exception {
		Ricovero ricovero = amministratore.getRicoveri().get(indexRicovero);
		TipoPrestazione tipo = TipoPrestazione.values()[indexTipo];
		Prestazione p = new Prestazione(null, tipo, oraInizio, oraFine, ricovero);
		medico.registerPrestazione(p);

		PrestazioneDAO pdao = new PrestazionePostgresDAO();
		try {
			pdao.inserisciPrestazioneDB(oraInizio, oraFine, tipo.toString(), p.getEsito(), ricovero.getIdRicovero());
		} catch (SQLException e) {
			throw new Exception ("Errore nell'esecuzione della query");

		}
	}


	/**
	 * Gets prestazioni giornaliere.
	 *
	 * @param data the data
	 * @return the prestazioni giornaliere
	 */
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


	/**
	 * Gets prestazioni settimanali.
	 *
	 * @param data the data
	 * @return the prestazioni settimanali
	 */
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


	/**
	 * Modifica esito.
	 *
	 * @param indexPrestazione the index prestazione
	 * @param esito            the esito
	 */
	public void modificaEsito(int indexPrestazione, String esito) {
		Prestazione prestazione = medico.getPrestazioni().get(indexPrestazione);
		prestazione.setEsito(esito);
		PrestazioneDAO p2dao = new PrestazionePostgresDAO();
		p2dao.aggiornaEsitoDB(prestazione.getRicovero().getIdRicovero(), prestazione.getOraInizio(), esito);


	}

	/**
	 * Gets paziente da cf.
	 *
	 * @param cf the cf
	 * @return the paziente da cf
	 */
	public Paziente getPazienteDaCf (String cf) {
		for (Paziente p : amministratore.getPazienti()){
			if  (p.getCodiceFiscale().equals(cf)){
				return p;
			}

		}

		return null;
	}

	/**
	 * Gets letto da codice.
	 *
	 * @param codiceunivoco the codiceunivoco
	 * @return the letto da codice
	 */
	public Letto getLettoDaCodice (String codiceunivoco) {
		for (Reparto r : reparti)
			for (Letto l : r.getLetti())
				if (l.getCodiceUnivoco().equals(codiceunivoco))
					return l;
		return null;
	}


	private Ricovero getRicoveroDaId(int idRicovero){
		for (Ricovero r : amministratore.getRicoveri())
			if (r.getIdRicovero() == idRicovero)
				return r;
		return null;
	}


	/**
	 * Get reparto da id reparto.
	 *
	 * @param idReparto the id reparto
	 * @return the reparto
	 */
	public Reparto getRepartoDaId (String  idReparto){
		for (Reparto rep : reparti) {
			if (rep.getIdReparto().equals(idReparto))
				return rep;
		}
		return null;
	}

	private Stanza getStanzaDaNumero(int numeroStanza) {
		for (Stanza s : stanze)
			if (s.getNumeroStanza() == numeroStanza)
				return s;
		return null;
	}

	private Ricovero getRicoveroNonPersistito(Paziente paziente, Letto letto, LocalDateTime dataInizio) {
		for (Ricovero r : paziente.getRicoveri()) {
			if (r.getIdRicovero() == -1
					&& r.getLetto().equals(letto)
					&& r.getDataInizio().equals(dataInizio)) {
				return r;
			}
		}
		return null;
	}



}