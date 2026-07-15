package model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Amministratore.
 */
public class Amministratore extends Utente {
    private final ArrayList<Paziente> pazienti = new ArrayList<>();
    private final ArrayList<Ricovero> ricoveri = new ArrayList<>();


    /**
     * Instantiates a new Amministratore.
     *
     * @param login    the login
     * @param password the password
     */
    public Amministratore(String login, String password) {
        super(login, password);
    }

    /**
     * Register paziente.
     *
     * @param nome    the nome
     * @param cognome the cognome
     * @param cf      the cf
     */
    public void registerPaziente(String nome, String cognome, String cf){
        Paziente p = new Paziente(nome, cognome, cf);
        pazienti.add(p);

    }

    /**
     * Register ricovero.
     *
     * @param paziente           the paziente
     * @param letto              the letto
     * @param dataInizio         the data inizio
     * @param dimissioniPreviste the dimissioni previste
     */
    public void registerRicovero(Paziente paziente, Letto letto,
                                 LocalDateTime dataInizio,
                                 LocalDateTime dimissioniPreviste){
        for (Ricovero r : paziente.getRicoveri()) {
            if (!dataInizio.isBefore(r.getDataDimissioniPrevista())
                    && !r.getDataInizio().isBefore(dimissioniPreviste)) {
                throw new IllegalStateException(
                        "Il paziente è già assegnato in un altro letto in questo periodo.");
            }
        }

        Ricovero ricovero = new Ricovero(dataInizio, dimissioniPreviste, paziente, letto);
        letto.getRicoveri().add(ricovero);
        letto.setStato(StatoLetto.OCCUPATO);
        paziente.addRicovero(ricovero);
        ricoveri.add(ricovero);

    }


    /**
     * Get pazienti in scadenza.
     *
     * @return the list
     */
    public List<Paziente> getPazienti(){
        return pazienti;
    }


    /**
     * Gets pazienti in scadenza.
     *
     * @param data the data
     * @return the pazienti in scadenza
     */
    public List<Ricovero> getPazientiInScadenza(LocalDate data) {
        List<Ricovero> inScadenza = new ArrayList<>();
        for (Ricovero r : ricoveri) {
            if (r.getDataDimissioniPrevista().toLocalDate().equals(data)) {
                inScadenza.add(r);
            }
        }
        return inScadenza;
    }

    /**
     * Gets letti disponibili.
     *
     * @param reparto the reparto
     * @return the letti disponibili
     */
    public List<Letto> getLettiDisponibili(Reparto reparto) {
        return reparto.getLettiDisponibili();
    }

    /**
     * Gets ricoveri.
     *
     * @return the ricoveri
     */
    public List<Ricovero> getRicoveri() {
        return ricoveri;
    }

}
