package implementazioneDao;

import database_connection.ConnessioneDatabase;
import dao.TurnoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * The type Turno postgres dao.
 */
public class TurnoPostgresDAO implements TurnoDAO {

    private Connection connection;

    /**
     * Instantiates a new Turno postgres dao.
     */
    public TurnoPostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void leggiTurniDB(ArrayList<String> loginFK,
                             ArrayList<DayOfWeek> giorni,
                             ArrayList<LocalTime> oreInizio,
                             ArrayList<LocalTime> oreFine) throws SQLException {
        String sql = "SELECT login, giornosettimana, orainizio, orafine FROM turnolavorativo;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                loginFK.add(rs.getString("login"));
                giorni.add(DayOfWeek.valueOf(rs.getString("giornosettimana")));
                oreInizio.add(rs.getTime("orainizio").toLocalTime());
                oreFine.add(rs.getTime("orafine").toLocalTime());
            }
            rs.close();
        }
    }
}
