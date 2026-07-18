package dao;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * The interface Turno dao.
 */
public interface TurnoDAO {


    /**
     * Leggi turni db.
     *
     * @param loginFK   the login fk
     * @param giorni    the giorni
     * @param oreInizio the ore inizio
     * @param oreFine   the ore fine
     */
    void leggiTurniDB(ArrayList<String> loginFK, ArrayList<DayOfWeek> giorni,
                      ArrayList<LocalTime> oreInizio, ArrayList<LocalTime> oreFine) throws SQLException;

}
