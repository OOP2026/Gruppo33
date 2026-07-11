package implementazioneDao;
import database_connection.ConnessioneDatabase;
import dao.MedicoDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class MedicoPostgresDAO implements MedicoDAO {

private Connection connection;

public MedicoPostgresDAO() {

        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
           // e.printStackTrace();
        }
    }

}


