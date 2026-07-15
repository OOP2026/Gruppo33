package dao;

import java.util.ArrayList;

public interface StanzaDAO {

    void leggiStanzeDB(ArrayList<Integer> numeriStanza, ArrayList<Integer> piani,
                       ArrayList<String> idRepartiFK);

}



