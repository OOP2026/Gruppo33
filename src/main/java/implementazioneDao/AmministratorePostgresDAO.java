package implementazioneDao;

import dao.AmministratoreDAO;
import database_connection.ConnessioneDatabase;
import model.Amministratore;

import java.sql.Connection;
import java.sql.SQLException;

public class AmministratorePostgresDAO implements AmministratoreDAO {

    private Connection connection;

    public AmministratorePostgresDAO(){
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registraPaziente(String nome, String cognome, String cf) {


    }

}
