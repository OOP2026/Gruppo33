package implementazioneDao;

import dao.LettoDAO;
import database_connection.ConnessioneDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LettoPostgresDAO implements LettoDAO {
    private Connection connection;
    public LettoPostgresDAO() {

            try {
                connection = ConnessioneDatabase.getInstance().connection;
            } catch (SQLException e) {
                // e.printStackTrace();
            }
        }

    @Override
    public void leggiLettiDB(ArrayList<String> codiciunivoci, ArrayList<String> stati, ArrayList<Integer> numerostanze) {


        String sql = "Select \"codiceunivoco\", \"stato\", \"numerostanza\" from \"letto\";";

        try (PreparedStatement ps = connection.prepareStatement(sql)){

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                codiciunivoci.add(rs.getString("codiceunivoco"));
                stati.add(rs.getString("stato"));
                numerostanze.add(rs.getInt("numerostanza"));

            }
            rs.close();
        }

        catch (SQLException e){
            System.err.println("Errore nell'esecuzione della query");
            e.printStackTrace();
        }
    }
}




