package dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The interface Paziente dao.
 */
public interface PazienteDAO {


    /**
     * Inserisci paziente db.
     *
     * @param nome          the nome
     * @param cognome       the cognome
     * @param codiceFiscale the codice fiscale
     * @throws SQLException the sql exception
     */
    void inserisciPazienteDB (String nome, String cognome, String codiceFiscale) throws SQLException;

    /**
     * Leggi pazienti db.
     *
     * @param nomi    the nomi
     * @param cognomi the cognomi
     * @param codiciF the codici f
     * @throws SQLException the sql exception
     */
    void leggiPazientiDB(ArrayList<String> nomi, ArrayList<String> cognomi,
                         ArrayList<String> codiciF) throws SQLException;

}
