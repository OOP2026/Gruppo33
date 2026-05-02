package model;

import java.util.ArrayList;

public class Stanza {
    private int numeroStanza;
    private int piano;
    private ArrayList<Letto> letti = new ArrayList<>();

    public Stanza(int numeroStanza, int piano){
        this.numeroStanza = numeroStanza;
        this.piano = piano;
    }

public void setNumeroStanza(int numeroStanza){
        this.numeroStanza = numeroStanza;
}
public int getNumeroStanza(){
        return numeroStanza;
}
public void setPiano(int piano){
        this.piano = piano;
}

public int getPiano(){
        return piano;
}

public ArrayList<Letto> getLetti(){
        return letti;
}

}
