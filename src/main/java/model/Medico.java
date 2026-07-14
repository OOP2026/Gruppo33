package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

/**
 * The type Medico.
 */
public class Medico extends Utente {
    private String nome;
    private String cognome;
    private Reparto reparto;
    private ArrayList<Prestazione> prestazioni = new ArrayList<>();
    private final ArrayList<TurnoLavorativo> turni = new ArrayList<>();

    /**
     * Instantiates a new Medico.
     *
     * @param login    the login
     * @param password the password
     * @param nome     the nome
     * @param cognome  the cognome
     * @param reparto  the reparto
     */
    public Medico(String login, String password, String nome, String cognome, Reparto reparto) {
        super(login, password);
        this.nome = nome;
        this.cognome = cognome;
        this.reparto = reparto;
    }

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Sets nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets cognome.
     *
     * @return the cognome
     */
    public String getCognome() {
        return this.cognome;
    }

    /**
     * Sets cognome.
     *
     * @param cognome the cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Gets reparto.
     *
     * @return the reparto
     */
    public Reparto getReparto() {
        return reparto;
    }

    /**
     * Sets reparto.
     *
     * @param r the r
     */
    public void setReparto(Reparto r) {
        this.reparto = r;

    }

    /**
     * Gets turni.
     *
     * @return the turni
     */
    public List<TurnoLavorativo> getTurni() {
        return turni;
    }



    public void addTurnoLavorativo(TurnoLavorativo t) {
       turni.add(t);
    }

    /**
     * Gets prestazioni.
     *
     * @return the prestazioni
     */
    public List<Prestazione> getPrestazioni() {
        return prestazioni;
    }

    /**
     * Register esito.
     *
     * @param p     the p
     * @param testo the testo
     */
    public void registerEsito(Prestazione p, String testo) {
        p.setEsito(testo);
    }
    private boolean nelTurno(LocalDateTime inizio, LocalDateTime fine){
        for (TurnoLavorativo t : turni) {
            if (t.getGiorno().equals(inizio.getDayOfWeek())
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

    /**
     * Register prestazione.
     *
     * @param prestazione the prestazione
     */
    public void registerPrestazione(Prestazione prestazione){
        if (!nelTurno(prestazione.getOraInizio(), prestazione.getOraFine()))
            throw new IllegalStateException("La prestazione non rientra in un turno lavorativo.");
        if (siAccavalla(prestazione))
            throw new IllegalStateException("Il medico ha già una prestazione in quel periodo.");
        prestazioni.add(prestazione);
    }

    /**
     * Gets agenda.
     *
     * @param data the data
     * @param p    the p
     * @return the agenda
     */
    public List<Prestazione> getAgenda (LocalDate data, Prestazione p) {
        ArrayList<Prestazione> agenda = new ArrayList<>();

        if (p.getOraInizio().toLocalDate().equals(data))
            agenda.add(p);
        return agenda;
    }
}
