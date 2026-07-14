package implementazioneDao;
import database_connection.ConnessioneDatabase;
import dao.RicoveroDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public void inserisciRicoveroDB(String idRicovero, String dataInizio, String dataDimissioniPrevista, String cf, String codiceUnivoco) {


        String sql = "INSERT INTO \"ricovero\" (\"idricovero\", \"datainizio\", \"datadimissioniprevista\", \"cf\", \"codiceunivoco\") " + "VALUES (?, ?, ?, ?, ?);";


        try (PreparedStatement inserisciRicoveroPS = connection.prepareStatement(sql)) {

            if (idRicovero == null || idRicovero.trim().isEmpty()) {
                throw new IllegalArgumentException("L'id del ricovero non può essere vuoto.");
            }

            inserisciRicoveroPS.setString(1, idRicovero.trim());
            inserisciRicoveroPS.setString(2, dataInizio);
            inserisciRicoveroPS.setString(3, dataDimissioniPrevista);
            inserisciRicoveroPS.setString(4, cf.trim());
            inserisciRicoveroPS.setString(5, codiceUnivoco.trim());
            inserisciRicoveroPS.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);


        }

    }

    @Override
    public void leggiRicoveroDB(ArrayList<String> idRicoveri, ArrayList<String> dateInizio,
                                ArrayList<String> dateDimissioniPreviste,
                                ArrayList<String> codiciF, ArrayList<String> codiciLetto) {
        String sql = "SELECT idricovero, datainizio, datadimissioniprevista, " +
                "cf, codiceunivoco FROM ricovero;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idRicoveri.add(rs.getString("idricovero"));
                dateInizio.add(rs.getString("datainizio"));
                dateDimissioniPreviste.add(rs.getString("datadimissioniprevista"));
                codiciF.add(rs.getString("cf"));
                codiciLetto.add(rs.getString("codiceunivoco"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Errore nell'esecuzione della query");
            e.printStackTrace();
        }
    }
}
