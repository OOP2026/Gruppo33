package model;

import java.util.ArrayList;

public class Medico extends Utente {
    private String nome;
    private String cognome;
    private Reparto reparto;
    private ArrayList<Prestazione> prestazioni = new ArrayList<>();
    private ArrayList<TurnoLavorativo> turni = new ArrayList<>();

    public Medico(String login, String password, String nome, String cognome, Reparto reparto) {
        super(login, password);
        this.nome = nome;
        this.cognome = cognome;
        this.reparto = reparto;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Reparto getReparto() {
        return reparto;
    }

    public void setReparto(Reparto r) {
        this.reparto = r;

    }

    public ArrayList<TurnoLavorativo> getTurni() {
        return turni;
    }

    public ArrayList<Prestazione> getPrestazioni() {
        return prestazioni;
    }

    public void registerEsito(Prestazione p, String testo) {
        p.setEsito(testo);

    }

}