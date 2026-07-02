package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Stanza.
 */
public class Stanza {
    private int numeroStanza;
    private int piano;
    private final ArrayList<Letto> letti = new ArrayList<>();

    /**
     * Instantiates a new Stanza.
     *
     * @param numeroStanza the numero stanza
     * @param piano        the piano
     */
    public Stanza(int numeroStanza, int piano){
        this.numeroStanza = numeroStanza;
        this.piano = piano;
    }

    /**
     * Set numero stanza.
     *
     * @param numeroStanza the numero stanza
     */
    public void setNumeroStanza(int numeroStanza){
        this.numeroStanza = numeroStanza;
}

    /**
     * Get numero stanza int.
     *
     * @return the int
     */
    public int getNumeroStanza(){
        return numeroStanza;
}

    /**
     * Set piano.
     *
     * @param piano the piano
     */
    public void setPiano(int piano){
        this.piano = piano;
}

    /**
     * Get piano int.
     *
     * @return the int
     */
    public int getPiano(){
        return piano;
}

    /**
     * Get letti list.
     *
     * @return the list
     */
    public List<Letto> getLetti(){
        return letti;
}

    /**
     * Add letto.
     *
     * @param l the l
     */
    public void addLetto(Letto l){
        letti.add(l);
}

}
