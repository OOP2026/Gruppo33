package implementazioneDao;

import database_connection.ConnessioneDatabase;
import dao.RepartoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Reparto postgres dao.
 */
public class RepartoPostgresDAO implements RepartoDAO {

    private Connection connection;

    /**
     * Instantiates a new Reparto postgres dao.
     */
    public RepartoPostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void leggiRepartiDB(ArrayList<String> idReparti, ArrayList<String> nomiReparti) throws SQLException {
        String sql = "SELECT \"idreparto\", \"nome\" FROM \"reparto\";";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idReparti.add(rs.getString("idreparto"));
                nomiReparti.add(rs.getString("nome"));
            }
            rs.close();
        }
    }
}