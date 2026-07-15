package implementazioneDao;
import dao.PrestazioneDAO;
import database_connection.ConnessioneDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PrestazionePostgresDAO implements PrestazioneDAO {

    private Connection connection;

    public PrestazionePostgresDAO() {

        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    @Override
    public void inserisciPrestazioneDB(LocalDateTime dataOraInizio, LocalDateTime dataOraFine, String tipoPrestazione, String esito) {

        String sql = "INSERT INTO prestazione (\"dataoranizio\", \"dataorafine\", \"tipoprestazione\", " +
                "\"idricovero\", " + "VALUES (?, ?, ?, ?);";


        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setTimestamp(1, java.sql.Timestamp.valueOf(dataOraInizio));
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(dataOraFine));
            ps.setString(3, tipoPrestazione);
            ps.setString(4, esito);
            ps.executeUpdate();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void leggiPrestazioniDB(ArrayList<LocalDateTime> dataOreInizio, ArrayList<LocalDateTime> dateOraFine,
                                   ArrayList<String> tipiPrestazione,  ArrayList<String> esiti) {

        String sql = "SELECT \"dataoranizio\", \"dataorafine\", \"tipoprestazione\", \"esito\", FROM \"prestazione\";";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dataOreInizio.add(rs.getTimestamp("dataoranizio").toLocalDateTime());
                dateOraFine.add(rs.getTimestamp("dataorafine").toLocalDateTime());
                tipiPrestazione.add(rs.getString("tipoprestazione"));
                esiti.add(rs.getString("esito"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Errore nell'esecuzione della query");
            e.printStackTrace();
        }


    }
}
