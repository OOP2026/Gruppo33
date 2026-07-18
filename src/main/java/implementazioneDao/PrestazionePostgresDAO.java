package implementazioneDao;
import dao.PrestazioneDAO;
import database_connection.ConnessioneDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The type Prestazione postgres dao.
 */
public class PrestazionePostgresDAO implements PrestazioneDAO {

    private Connection connection;

    /**
     * Instantiates a new Prestazione postgres dao.
     */
    public PrestazionePostgresDAO() {

        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    @Override
    public void inserisciPrestazioneDB(LocalDateTime dataOraInizio, LocalDateTime dataOraFine, String tipoPrestazione, String esito, int idRicovero) throws  SQLException {



        String sql = "INSERT INTO prestazione (\"dataorainizio\", \"dataorafine\", \"tipoprestazione\", \"esito\", \"idricovero\") " + "VALUES (?, ?, ?, ?, ?);";



          try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setTimestamp(1, java.sql.Timestamp.valueOf(dataOraInizio));
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(dataOraFine));
            ps.setString(3, tipoPrestazione);
            ps.setString(4, esito);
            ps.setInt(5, idRicovero);
            ps.executeUpdate();
        }
    }



    @Override
    public void leggiPrestazioniDB(ArrayList<LocalDateTime> dataOreInizio, ArrayList<LocalDateTime> dateOraFine,
                                   ArrayList<String> tipiPrestazione,  ArrayList<String> esiti, ArrayList<Integer> idRicoveriFK) throws SQLException {

        String sql = "SELECT \"dataorainizio\", \"dataorafine\", \"tipoprestazione\", \"esito\", \"idricovero\" FROM \"prestazione\";";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dataOreInizio.add(rs.getTimestamp("dataorainizio").toLocalDateTime());
                dateOraFine.add(rs.getTimestamp("dataorafine").toLocalDateTime());
                tipiPrestazione.add(rs.getString("tipoprestazione"));
                esiti.add(rs.getString("esito"));
                idRicoveriFK.add(rs.getInt("idricovero"));
            }
            rs.close();
        }

    }

    public void aggiornaEsitoDB(int idRicovero, LocalDateTime oraInizio, String esito) {

    String sql = "UPDATE Prestazione SET esito = ? WHERE idricovero = ? AND dataorainizio = ?;";
         try (PreparedStatement ps = connection.prepareStatement(sql)) {

        ps.setString(1, esito);
        ps.setInt(2, idRicovero);
        ps.setTimestamp(3, java.sql.Timestamp.valueOf(oraInizio));
        int righe = ps.executeUpdate();
        if (righe == 0) {
            throw new RuntimeException("Nessuna prestazione aggiornata per ricovero " + idRicovero + " e ora inizio " + oraInizio);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}


}
