package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.DayOfWeek;

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
    private GiornoSettimana toGiornoSettimana(DayOfWeek dow) {
        switch (dow) {
            case MONDAY:
                return GiornoSettimana.LUNEDI;
            case TUESDAY:
                return GiornoSettimana.MARTEDI;
            case WEDNESDAY:
                return GiornoSettimana.MERCOLEDI;
            case THURSDAY:
                return GiornoSettimana.GIOVEDI;
            case FRIDAY:
                return GiornoSettimana.VENERDI;
            case SATURDAY:
                return GiornoSettimana.SABATO;
            default:
                return GiornoSettimana.DOMENICA;
        }
    }

    private boolean nelTurno(LocalDateTime inizio, LocalDateTime fine){
        GiornoSettimana giornoRichiesto = toGiornoSettimana(inizio.getDayOfWeek());
        for (TurnoLavorativo t : turni) {
            if (t.getGiorno() == giornoRichiesto
                    && t.copre(inizio.toLocalTime(), fine.toLocalTime())) {
                return true;
            }
        }
        return false;
    }


    private boolean siAccavalla(Prestazione nuova) {
        for (Prestazione p : prestazioni) {
            if (nuova.siAccavallaCon(p))
                return true;
        }
        return false;
    }

    public void registerPrestazione(Prestazione prestazione){
        if (!nelTurno(prestazione.getOraInizio(), prestazione.getOraFine()))
            throw new IllegalStateException("La prestazione non rientra in un turno lavorativo.");
        if (siAccavalla(prestazione))
            throw new IllegalStateException("Il medico ha già una prestazione in quel periodo.");
        prestazioni.add(prestazione);
    }

    public ArrayList<Prestazione> getAgendaGiornaliera (LocalDate data, Prestazione p) {
        ArrayList<Prestazione> agenda = new ArrayList<>();

        if (p.getOraInizio().toLocalDate().equals(data))
            agenda.add(p);
        return agenda;
    }
}
