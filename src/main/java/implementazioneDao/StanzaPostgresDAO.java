package implementazioneDao;

import database_connection.ConnessioneDatabase;
import dao.StanzaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StanzaPostgresDAO implements StanzaDAO {

    private Connection connection;

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
                              ArrayList<String> idRepartiFK) {
        String sql = "SELECT \"numerostanza\", \"piano\", \"idreparto\" FROM \"stanza\";";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                numeriStanza.add(rs.getInt("numerostanza"));
                piani.add(rs.getInt("piano"));
                idRepartiFK.add(rs.getString("idreparto"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Errore nell'esecuzione della query");
            e.printStackTrace();
        }
    }
}