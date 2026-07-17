package implementazioneDao;
import database_connection.ConnessioneDatabase;
import dao.RicoveroDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RicoveroPostgresDAO implements RicoveroDAO {

    private Connection connection;

    public RicoveroPostgresDAO() {

        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    @Override
    public int inserisciRicoveroDB(LocalDateTime dataInizio, LocalDateTime dataDimissioniPrevista, String cf, String codiceUnivoco) {


        String sql = "INSERT INTO \"ricovero\" (\"datainizio\", \"datadimissioniprevista\", \"cf\", \"codiceunivoco\") " + "VALUES (?, ?, ?, ?) " +
                "RETURNING \"idricovero\";";


        try (PreparedStatement inserisciRicoveroPS = connection.prepareStatement(sql)) {

            inserisciRicoveroPS.setTimestamp(1, java.sql.Timestamp.valueOf(dataInizio));
            inserisciRicoveroPS.setTimestamp(2, java.sql.Timestamp.valueOf(dataDimissioniPrevista));
            inserisciRicoveroPS.setString(3, cf.trim());
            inserisciRicoveroPS.setString(4, codiceUnivoco.trim());
            try (ResultSet rs = inserisciRicoveroPS.executeQuery()) {


                if (rs.next()) {
                    rs.getInt("idricovero");
                    return rs.getInt("idricovero");
                }
                throw new RuntimeException("Inserimento ricovero fallito: nessun id restituito");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);


        }

    }

    @Override
    public void leggiRicoveroDB(ArrayList<Integer> idRicoveri, ArrayList<LocalDateTime> dateInizio,
                                ArrayList<LocalDateTime> dateDimissioniPreviste,
                                ArrayList<String> codiciFisc, ArrayList<String> codiciLetto) {
        String sql = "SELECT \"idricovero\",\"datainizio\", \"datadimissioniprevista\", " +
                "\"cf\", \"codiceunivoco\" FROM \"ricovero\";";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idRicoveri.add(rs.getInt("idricovero"));
                dateInizio.add(rs.getTimestamp("datainizio").toLocalDateTime());
                dateDimissioniPreviste.add(rs.getTimestamp("datadimissioniprevista").toLocalDateTime());
                codiciFisc.add(rs.getString("cf"));
                codiciLetto.add(rs.getString("codiceunivoco"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Errore nell'esecuzione della query");
            e.printStackTrace();
        }
    }
}
