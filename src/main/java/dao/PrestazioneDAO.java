package dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface PrestazioneDAO {

    void inserisciPrestazioneDB(LocalDateTime dataOraInizio, LocalDateTime dataOraFine,
                                String tipoPrestazione, String esito, int idRicovero) throws SQLException;

    void leggiPrestazioniDB(ArrayList<LocalDateTime> dataOreInizio, ArrayList<LocalDateTime> dateOraFine,
                            ArrayList<String> tipiPrestazione, ArrayList<String> esiti, ArrayList<Integer> idRicoveriFK) throws SQLException;



    void aggiornaEsitoDB (int idRicovero, LocalDateTime oraInizio, String esito);



}
