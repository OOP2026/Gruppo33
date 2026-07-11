package implementazioneDao;

import dao.PazienteDAO;
import database_connection.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.SQLException;

public class PazientePostgresDAO implements PazienteDAO {

    private Connection connection;

    public PazientePostgresDAO() {

        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            // e.printStackTrace();
        }
    }



}
