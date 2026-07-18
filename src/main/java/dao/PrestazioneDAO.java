package dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The interface Prestazione dao.
 */
public interface PrestazioneDAO {

    /**
     * Inserisci prestazione db.
     *
     * @param dataOraInizio   the data ora inizio
     * @param dataOraFine     the data ora fine
     * @param tipoPrestazione the tipo prestazione
     * @param esito           the esito
     * @param idRicovero      the id ricovero
     * @throws SQLException the sql exception
     */
    void inserisciPrestazioneDB(LocalDateTime dataOraInizio, LocalDateTime dataOraFine,
                                String tipoPrestazione, String esito, int idRicovero) throws SQLException;

    /**
     * Leggi prestazioni db.
     *
     * @param dataOreInizio   the data ore inizio
     * @param dateOraFine     the date ora fine
     * @param tipiPrestazione the tipi prestazione
     * @param esiti           the esiti
     * @param idRicoveriFK    the id ricoveri fk
     * @throws SQLException the sql exception
     */
    void leggiPrestazioniDB(ArrayList<LocalDateTime> dataOreInizio, ArrayList<LocalDateTime> dateOraFine,
                            ArrayList<String> tipiPrestazione, ArrayList<String> esiti, ArrayList<Integer> idRicoveriFK) throws SQLException;


    /**
     * Aggiorna esito db.
     *
     * @param idRicovero the id ricovero
     * @param oraInizio  the ora inizio
     * @param esito      the esito
     */
    void aggiornaEsitoDB (int idRicovero, LocalDateTime oraInizio, String esito);



}
