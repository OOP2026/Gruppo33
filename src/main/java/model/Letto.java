package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Letto.
 */
public class Letto {
    private String codiceUnivoco;
    private StatoLetto stato;
    /**
     * The Ricoveri.
     */
    ArrayList<Ricovero> ricoveri = new ArrayList<>();

    /**
     * Instantiates a new Letto.
     *
     * @param codiceUnivoco the codice univoco
     */
    public Letto(String codiceUnivoco) {
        this.codiceUnivoco = codiceUnivoco;
        this.stato = StatoLetto.DISPONIBILE;
    }

    /**
     * Gets codice univoco.
     *
     * @return the codice univoco
     */
    public String getCodiceUnivoco() {
        return codiceUnivoco;
    }

    /**
     * Sets codice univoco.
     *
     * @param codiceUnivoco the codice univoco
     */
    public void setCodiceUnivoco(String codiceUnivoco) {
        this.codiceUnivoco = codiceUnivoco;
    }

    /**
     * Gets stato.
     *
     * @return the stato
     */
    public StatoLetto getStato() {
        return stato;
    }

    /**
     * Sets stato.
     *
     * @param stato the stato
     */
    public void setStato(StatoLetto stato) {
        this.stato = stato;
    }

    /**
     * Gets ricoveri.
     *
     * @return the ricoveri
     */
    public List<Ricovero> getRicoveri() {
        return ricoveri;

    }

}





