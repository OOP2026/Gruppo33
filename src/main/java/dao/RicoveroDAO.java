package dao;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface RicoveroDAO {

    void inserisciRicoveroDB(LocalDateTime dataInizio,
                             LocalDateTime dataDimissioniPrevista, String cf,
                             String codiceUnivoco);

    void leggiRicoveroDB(ArrayList<LocalDateTime> dateInizio,
                         ArrayList<LocalDateTime> dateDimissioniPreviste,
                         ArrayList<String> codiciFisc, ArrayList<String> codiciLetto);




}
