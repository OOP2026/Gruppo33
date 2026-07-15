package dao;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface PrestazioneDAO {

    void inserisciPrestazioneDB(LocalDateTime dataOraInizio, LocalDateTime dataOraFine,
                                String tipoPrestazione, String esito);

    void leggiPrestazioniDB(ArrayList<LocalDateTime> dataOreInizio, ArrayList<LocalDateTime> dateOraFine,
                            ArrayList<String> tipiPrestazione, ArrayList<String> esiti);



}
