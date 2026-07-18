package implementazioneDao;

import database_connection.ConnessioneDatabase;
import dao.StanzaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Stanza postgres dao.
 */
public class StanzaPostgresDAO implements StanzaDAO {

    private Connection connection;

    /**
     * Instantiates a new Stanza postgres dao.
     */
    public StanzaPostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void leggiStanzeDB(ArrayList<Integer> numeriStanza,
                              ArrayList<Integer> piani,
                              ArrayList<String> idRepartiFK) throws SQLException {
        String sql = "SELECT \"numerostanza\", \"piano\", \"idreparto\" FROM \"stanza\";";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                numeriStanza.add(rs.getInt("numerostanza"));
                piani.add(rs.getInt("piano"));
                idRepartiFK.add(rs.getString("idreparto"));
            }
            rs.close();
        }
    }
}