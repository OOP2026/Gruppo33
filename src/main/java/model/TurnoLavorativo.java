package model;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * The type Turno lavorativo.
 */
public class TurnoLavorativo {
    private GiornoSettimana giorno;
    private LocalDateTime oraInizio;
    private LocalDateTime oraFine;

    /**
     * Instantiates a new Turno lavorativo.
     *
     * @param giorno    the giorno
     * @param oraInizio the ora inizio
     * @param oraFine   the ora fine
     */
    public TurnoLavorativo(GiornoSettimana giorno, LocalDateTime oraInizio, LocalDateTime oraFine){
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
    public LocalDateTime getOraInizio() {
        return oraInizio;
    }

    /**
     * Sets ora inizio.
     *
     * @param oraInizio the ora inizio
     */
    public void setOraInizio(LocalDateTime oraInizio) {
        this.oraInizio = oraInizio;
    }

    /**
     * Gets ora fine.
     *
     * @return the ora fine
     */
    public LocalDateTime getOraFine() {
        return oraFine;
    }

    /**
     * Sets ora fine.
     *
     * @param oraFine the ora fine
     */
    public void setOraFine(LocalDateTime oraFine) {
        this.oraFine = oraFine;
    }

    /**
     * Copre boolean.
     *
     * @param inizio the inizio
     * @param fine   the fine
     * @return the boolean
     */
    public boolean copre(LocalDateTime inizio, LocalDateTime fine) {
        return !inizio.isBefore(oraInizio) && !fine.isAfter(oraFine);
    }
}
