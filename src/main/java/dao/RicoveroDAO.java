package dao;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The interface Ricovero dao.
 */
public interface RicoveroDAO {

    /**
     * Inserisci ricovero db int.
     *
     * @param dataInizio             the data inizio
     * @param dataDimissioniPrevista the data dimissioni prevista
     * @param cf                     the cf
     * @param codiceUnivoco          the codice univoco
     * @return the int
     */
    int inserisciRicoveroDB(LocalDateTime dataInizio,
                             LocalDateTime dataDimissioniPrevista, String cf,
                             String codiceUnivoco);

    /**
     * Leggi ricovero db.
     *
     * @param idRicoveri             the id ricoveri
     * @param dateInizio             the date inizio
     * @param dateDimissioniPreviste the date dimissioni previste
     * @param codiciFisc             the codici fisc
     * @param codiciLetto            the codici letto
     */
    void leggiRicoveroDB(ArrayList <Integer> idRicoveri, ArrayList<LocalDateTime> dateInizio,
                         ArrayList<LocalDateTime> dateDimissioniPreviste,
                         ArrayList<String> codiciFisc, ArrayList<String> codiciLetto);


    /**
     * Aggiorna dimissione db.
     *
     * @param idRicovero               the id ricovero
     * @param dataDimissioneEffettuata the data dimissione effettuata
     */
    void aggiornaDimissioneDB(int idRicovero, LocalDateTime dataDimissioneEffettuata);

}
