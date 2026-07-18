package dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The interface Reparto dao.
 */
public interface RepartoDAO {

    /**
     * Leggi reparti db.
     *
     * @param idReparti   the id reparti
     * @param nomiReparti the nomi reparti
     */
    void leggiRepartiDB(ArrayList<String> idReparti, ArrayList<String> nomiReparti) throws SQLException;


}
