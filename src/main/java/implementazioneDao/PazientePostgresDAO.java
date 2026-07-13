package implementazioneDao;

import dao.PazienteDAO;
import database_connection.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PazientePostgresDAO implements PazienteDAO {

    private Connection connection;

    public PazientePostgresDAO() {

        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }


    @Override
    public void inserisciPazienteDB(String nome, String cognome, String codiceFiscale) {

        String sql = "INSERT INTO \"paziente\"(\"cf\", \"nome\", \"cognome\") VALUES (?, ?, ?);";

        try (PreparedStatement inserisciPazienteDB = connection.prepareStatement(sql)) {

            if (nome == null || nome.trim().isEmpty() || cognome == null || cognome.trim().isEmpty() || codiceFiscale == null || codiceFiscale.trim().isEmpty()) {
                throw new IllegalArgumentException("Il nome, cognome e codice fiscale del paziente non possono essere vuoti.");
            }

            inserisciPazienteDB.setString(2, nome.trim());
            inserisciPazienteDB.setString(3, cognome.trim());
            inserisciPazienteDB.setString(1, codiceFiscale.trim());
            inserisciPazienteDB.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void leggiPazientiDB(ArrayList<String> nomi, ArrayList<String> cognomi, ArrayList<String> codiciF) {

        String sql= "Select \"cf\", \"nome\", \"cognome\" from \"paziente\";";

        try (PreparedStatement ps = connection.prepareStatement(sql)){


            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nomi.add(rs.getString("nome"));
                cognomi.add(rs.getString("cognome"));
                codiciF.add(rs.getString("cf"));

            }
            rs.close();
        }

        catch (SQLException e){
            System.err.println("Errore nell'esecuzione della query");
            e.printStackTrace();
        }


    }


}
