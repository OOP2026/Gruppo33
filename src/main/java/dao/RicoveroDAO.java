package dao;

import java.util.ArrayList;

public interface RicoveroDAO {

    void inserisciRicoveroDB(String idRicovero, String dataInizio,
                             String dataDimissioniPrevista, String cf,
                             String codiceUnivoco);

    void leggiRicoveroDB(ArrayList<String> idRicoveri, ArrayList<String> dateInizio,
                         ArrayList<String> dateDimissioniPreviste,
                         ArrayList<String> codiciF, ArrayList<String> codiciLetto);




}
