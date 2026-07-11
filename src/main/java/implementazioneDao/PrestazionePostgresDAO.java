package implementazioneDao;
import dao.PrestazioneDAO;
import database_connection.ConnessioneDatabase;

import java.sql.Connection;
import java.sql.SQLException;

public class PrestazionePostgresDAO implements PrestazioneDAO {

    private Connection connection;

    public PrestazionePostgresDAO() {

        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
