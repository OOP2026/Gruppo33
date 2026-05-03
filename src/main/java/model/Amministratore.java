package model;
import java.util.ArrayList;

public class Amministratore extends Utente {
    private ArrayList<Paziente> pazienti = new ArrayList<>();
    private ArrayList<Ricovero> ricoveri = new ArrayList<>();


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

    public ArrayList<Letto> getLettiDisponibili(Reparto reparto) {
        return reparto.getLettiDisponibili();
    }
}
