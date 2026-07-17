package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PazienteDAO {


    void inserisciPazienteDB (String nome, String cognome, String codiceFiscale) throws SQLException;

    void leggiPazientiDB(ArrayList<String> nomi, ArrayList<String> cognomi,
                         ArrayList<String> codiciF) throws SQLException;

}
