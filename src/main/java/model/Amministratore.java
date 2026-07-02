package model;
import java.util.ArrayList;
import java.util.List;

public class Amministratore extends Utente {
    private final ArrayList<Paziente> pazienti = new ArrayList<>();
    private final ArrayList<Ricovero> ricoveri = new ArrayList<>();


    public Amministratore(String login, String password) {
        super(login, password);
    }

    public void registerPaziente(Paziente paziente){
        pazienti.add(paziente);

    }

    public void registerRicovero(Ricovero ricovero){
        ricoveri.add(ricovero);
    }


    public void getPazientiInScadenza(Paziente paziente){
    }

    public List<Letto> getLettiDisponibili(Reparto reparto) {
        return reparto.getLettiDisponibili();
    }
}
