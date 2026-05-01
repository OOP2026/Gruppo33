package model;

import java.util.ArrayList;

public class Paziente {
    private String nome;
    private String cognome;
    private String codiceFiscale;
ArrayList<Ricovero> ricoveri = new ArrayList<>();

public Paziente(String nome, String cognome, String codiceFiscale){
    this.nome = nome;
    this.cognome = cognome;
    this.codiceFiscale = codiceFiscale;

}
public String getNome(){
    return this.nome;
}
public String getCognome(){
    return this.cognome;
}

public String getCodiceFiscale(){

    return this.codiceFiscale;
}

}
