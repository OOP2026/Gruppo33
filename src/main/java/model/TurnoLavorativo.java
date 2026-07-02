package model;

import java.time.LocalTime;

/**
 * The type Turno lavorativo.
 */
public class TurnoLavorativo {
    private GiornoSettimana giorno;
    private LocalTime oraInizio;
    private LocalTime oraFine;

    /**
     * Instantiates a new Turno lavorativo.
     *
     * @param giorno    the giorno
     * @param oraInizio the ora inizio
     * @param oraFine   the ora fine
     */
    public TurnoLavorativo(GiornoSettimana giorno, LocalTime oraInizio, LocalTime oraFine){
        this.giorno = giorno;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }

    /**
     * Gets giorno.
     *
     * @return the giorno
     */
    public GiornoSettimana getGiorno() {
        return giorno;
    }

    /**
     * Sets giorno.
     *
     * @param giorno the giorno
     */
    public void setGiorno (GiornoSettimana giorno)   {
        this.giorno = giorno;
    }

    /**
     * Gets ora inizio.
     *
     * @return the ora inizio
     */
    public LocalTime getOraInizio() {
        return oraInizio;
    }

    /**
     * Sets ora inizio.
     *
     * @param oraInizio the ora inizio
     */
    public void setOraInizio(LocalTime oraInizio) {
        this.oraInizio = oraInizio;
    }

    /**
     * Gets ora fine.
     *
     * @return the ora fine
     */
    public LocalTime getOraFine() {
        return oraFine;
    }

    /**
     * Sets ora fine.
     *
     * @param oraFine the ora fine
     */
    public void setOraFine(LocalTime oraFine) {
        this.oraFine = oraFine;
    }

    /**
     * Copre boolean.
     *
     * @param inizio the inizio
     * @param fine   the fine
     * @return the boolean
     */
    public boolean copre(LocalTime inizio, LocalTime fine) {
        return !inizio.isBefore(oraInizio) && !fine.isAfter(oraFine);
    }
}
