package model;

import java.util.ArrayList;

/**
 * The type Paziente.
 */
public class Paziente {
    private String nome;
    private String cognome;
    private String codiceFiscale;
    /**
     * The Ricoveri.
     */
    ArrayList<Ricovero> ricoveri = new ArrayList<>();

    /**
     * Instantiates a new Paziente.
     *
     * @param nome          the nome
     * @param cognome       the cognome
     * @param codiceFiscale the codice fiscale
     */
    public Paziente(String nome, String cognome, String codiceFiscale){
    this.nome = nome;
    this.cognome = cognome;
    this.codiceFiscale = codiceFiscale;

}

    /**
     * Get nome string.
     *
     * @return the string
     */
    public String getNome(){
    return this.nome;
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
     * Get cognome string.
     *
     * @return the string
     */
    public String getCognome(){
    return this.cognome;
}

    /**
     * Set cognome.
     *
     * @param cognome the cognome
     */
    public void setCognome(String cognome){
    this.cognome = cognome;
}

    /**
     * Get codice fiscale string.
     *
     * @return the string
     */
    public String getCodiceFiscale(){

    return this.codiceFiscale;
}

    /**
     * Set codice fiscale.
     *
     * @param codiceFiscale the codice fiscale
     */
    public void setCodiceFiscale(String codiceFiscale){
    this.codiceFiscale = codiceFiscale;
}

    public ArrayList<Ricovero> getRicoveri(){
        return ricoveri;

    }

    public void addRicovero(Ricovero ricovero){
        ricoveri.add(ricovero);
    }


}
