package implementazioneDao;
import database_connection.ConnessioneDatabase;
import dao.RicoveroDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class RicoveroPostgresDAO implements RicoveroDAO {

    private Connection connection;

    public RicoveroPostgresDAO() {

        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
