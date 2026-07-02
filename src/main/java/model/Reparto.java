package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Reparto.
 */
public class Reparto {
    private String nome;
    private String idReparto;
    private ArrayList<Stanza> stanze = new ArrayList<>();

    /**
     * Instantiates a new Reparto.
     *
     * @param nome      the nome
     * @param idReparto the id reparto
     */
    public Reparto (String nome, String idReparto){
    this.nome = nome;
    this.idReparto = idReparto;
}

    /**
     * Get nome string.
     *
     * @return the string
     */
    public String getNome(){
    return nome;
}

    /**
     * Set nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome){
    this.nome = nome;
}

    /**
     * Get id reparto string.
     *
     * @return the string
     */
    public String getIdReparto(){
    return idReparto;
}

    /**
     * Set id reparto.
     *
     * @param idReparto the id reparto
     */
    public void setIdReparto(String idReparto){
    this.idReparto = idReparto;
}

    /**
     * Gets stanze.
     *
     * @return the stanze
     */
    public ArrayList<Stanza> getStanze() {
    return stanze;
}

    /**
     * Add stanza.
     *
     * @param stanza the stanza
     */
    public void addStanza(Stanza stanza) {
        stanze.add(stanza);
    }

    /**
     * Gets letti.
     *
     * @return the letti
     */
    public List<Letto> getLetti() {
        ArrayList<Letto> letti = new ArrayList<>();
        for (Stanza s : stanze) letti.addAll(s.getLetti());
        return letti;
    }

    /**
     * Gets letti disponibili.
     *
     * @return the letti disponibili
     */
    public List<Letto> getLettiDisponibili() {
        ArrayList<Letto> disponibili = new ArrayList<>();
        for (Letto l : getLetti()) {
            if (l.getStato() == StatoLetto.DISPONIBILE)
                disponibili.add(l);
        }
        return disponibili;
    }
}
