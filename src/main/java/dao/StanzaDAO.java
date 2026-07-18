package dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The interface Stanza dao.
 */
public interface StanzaDAO {

    /**
     * Leggi stanze db.
     *
     * @param numeriStanza the numeri stanza
     * @param piani        the piani
     * @param idRepartiFK  the id reparti fk
     */
    void leggiStanzeDB(ArrayList<Integer> numeriStanza, ArrayList<Integer> piani,
                       ArrayList<String> idRepartiFK) throws SQLException;

}



