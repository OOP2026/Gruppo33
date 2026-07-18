package implementazioneDao;

import dao.PazienteDAO;
import database_connection.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Paziente postgres dao.
 */
public class PazientePostgresDAO implements PazienteDAO {

    private Connection connection;

    /**
     * Instantiates a new Paziente postgres dao.
     */
    public PazientePostgresDAO() {

        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (Exception e) {
           e.printStackTrace();
        }
    }


    @Override
    public void inserisciPazienteDB(String nome, String cognome, String codiceFiscale) throws SQLException {

        String sql = "INSERT INTO \"paziente\"(\"cf\", \"nome\", \"cognome\") VALUES (?, ?, ?);";

       try (PreparedStatement inserisciPazientePS = connection.prepareStatement(sql)) {


           if (nome == null || nome.trim().isEmpty() || cognome == null || cognome.trim().isEmpty() || codiceFiscale == null || codiceFiscale.trim().isEmpty()) {
               throw new IllegalArgumentException("Il nome, cognome e codice fiscale del paziente non possono essere vuoti.");
           }
           inserisciPazientePS.setString(1, codiceFiscale.trim());
           inserisciPazientePS.setString(2, nome.trim());
           inserisciPazientePS.setString(3, cognome.trim());
           inserisciPazientePS.executeUpdate();
       }

    }

    @Override
    public void leggiPazientiDB(ArrayList<String> nomi, ArrayList<String> cognomi, ArrayList<String> codiciF) throws SQLException {

        String sql= "Select \"cf\", \"nome\", \"cognome\" from \"paziente\";";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {


            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nomi.add(rs.getString("nome"));
                cognomi.add(rs.getString("cognome"));
                codiciF.add(rs.getString("cf"));

            }
            rs.close();
        }

    }

}
