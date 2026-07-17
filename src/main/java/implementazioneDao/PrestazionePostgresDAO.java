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
    public void inserisciPrestazioneDB(LocalDateTime dataOraInizio, LocalDateTime dataOraFine, String tipoPrestazione, String esito, int idRicovero) throws  SQLException {



        String sql = "INSERT INTO prestazione (\"dataorainizio\", \"dataorafine\", \"tipoprestazione\", \"esito\", \"idricovero\") " + "VALUES (?, ?, ?, ?, ?);";


        try { PreparedStatement ps = connection.prepareStatement(sql);

            ps.setTimestamp(1, java.sql.Timestamp.valueOf(dataOraInizio));
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(dataOraFine));
            ps.setString(3, tipoPrestazione);
            ps.setString(4, esito);
            ps.setInt(5, idRicovero);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
        System.err.println(ex.getMessage());}
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
        } catch (SQLException e) {
            System.err.println("Errore nell'esecuzione della query");
            e.printStackTrace();
        }


    }
}
