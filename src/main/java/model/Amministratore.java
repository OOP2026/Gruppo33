package model;
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
     * @param paziente the paziente
     */
    public void registerPaziente(Paziente paziente){
        pazienti.add(paziente);

    }

    /**
     * Register ricovero.
     *
     * @param ricovero the ricovero
     */
    public void registerRicovero(Ricovero ricovero){
        ricoveri.add(ricovero);
    }


    /**
     * Get pazienti in scadenza.
     *
     * @param paziente the paziente
     */
    public void getPazientiInScadenza(Paziente paziente){
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
}
