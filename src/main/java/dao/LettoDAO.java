package dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The interface Letto dao.
 */
public interface LettoDAO {

    /**
     * Leggi letti db.
     *
     * @param codiciunivoci the codiciunivoci
     * @param stati         the stati
     * @param numerostanze  the numerostanze
     */
    void leggiLettiDB (ArrayList<String> codiciunivoci, ArrayList<String> stati, ArrayList<Integer> numerostanze) throws SQLException;


}
