package implementazioneDao;

import dao.LettoDAO;
import database_connection.ConnessioneDatabase;
import java.sql.Connection;
import java.sql.SQLException;

public class LettoPostgresDAO implements LettoDAO {
    private Connection connection;
    public LettoPostgresDAO() {

            try {
                connection = ConnessioneDatabase.getInstance().connection;
            } catch (SQLException e) {
                // e.printStackTrace();
            }
        }

    }




