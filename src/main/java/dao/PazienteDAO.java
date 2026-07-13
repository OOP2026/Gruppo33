package dao;

import java.util.ArrayList;

public interface PazienteDAO {


    void inserisciPazienteDB (String nome, String cognome, String codiceFiscale);

    void leggiPazientiDB(ArrayList<String> nomi, ArrayList<String> cognomi,
                         ArrayList<String> codiciF);

}
