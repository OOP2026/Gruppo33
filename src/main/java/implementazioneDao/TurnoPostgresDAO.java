package implementazioneDao;

import dao.TurnoDAO;
import database_connection.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.SQLException;

public class TurnoPostgresDAO implements TurnoDAO {
    private Connection connection;


    public TurnoPostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            // e.printStackTrace();
        }

    }

}
