package model;

import java.time.LocalDateTime;

/**
 * The type Prestazione.
 */
public class Prestazione {
    private String esito;
    private TipoPrestazione tipo;
    private LocalDateTime oraInizio;
    private LocalDateTime oraFine;
    private Ricovero ricovero;

    /**
     * Instantiates a new Prestazione.
     *
     * @param esito     the esito
     * @param tipo      the tipo
     * @param oraInizio the ora inizio
     * @param oraFine   the ora fine
     * @param ricovero  the ricovero
     */
    public Prestazione(String esito, TipoPrestazione tipo, LocalDateTime oraInizio, LocalDateTime oraFine, Ricovero ricovero){
        this.esito = esito;
        this.tipo = tipo;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.ricovero = ricovero;
    }

    /**
     * Get esito string.
     *
     * @return the string
     */
    public String getEsito(){
        return esito;
    }

    /**
     * Sets esito.
     *
     * @param esito the esito
     */
    public void setEsito(String esito) {
        this.esito = esito;
    }

    /**
     * Get ora inizio local date time.
     *
     * @return the local date time
     */
    public LocalDateTime getOraInizio(){
        return oraInizio;
    }

    /**
     * Set ora inizio.
     *
     * @param oraInizio the ora inizio
     */
    public void setOraInizio(LocalDateTime oraInizio){
        this.oraInizio = oraInizio;
    }

    /**
     * Get ora fine local date time.
     *
     * @return the local date time
     */
    public LocalDateTime getOraFine(){
        return oraFine;
    }

    /**
     * Set ora fine.
     *
     * @param oraFine the ora fine
     */
    public void setOraFine(LocalDateTime oraFine){
        this.oraFine = oraFine;
    }


    public TipoPrestazione getTipo () {
        return tipo;

    }

    public Ricovero getRicovero() {
        return ricovero;
    }


    /**
     * Si accavalla con boolean.
     *
     * @param altra the altra
     * @return the boolean
     */
    public boolean siAccavallaCon(Prestazione altra) {
        return oraInizio.isBefore(altra.getOraFine()) && oraFine.isAfter(altra.getOraInizio());
    }


}