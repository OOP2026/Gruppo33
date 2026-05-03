package model;

import java.util.ArrayList;

public class Reparto {
    private String nome;
    private String idReparto;
    private ArrayList<Stanza> stanze = new ArrayList<>();

public Reparto (String nome, String idReparto){
    this.nome = nome;
    this.idReparto = idReparto;
}
public String getNome(){
    return nome;
}

public void setNome(String nome){
    this.nome = nome;
}

public String getIdReparto(){
    return idReparto;
}

public void setIdReparto(String idReparto){
    this.idReparto = idReparto;
}
    public ArrayList<Stanza> getStanze() {
    return stanze;
}
    public void addStanza(Stanza stanza) {
        stanze.add(stanza);
    }

    public ArrayList<Letto> getLetti() {
        ArrayList<Letto> letti = new ArrayList<>();
        for (Stanza s : stanze) letti.addAll(s.getLetti());
        return letti;
    }

    public ArrayList<Letto> getLettiDisponibili() {
        ArrayList<Letto> disponibili = new ArrayList<>();
        for (Letto l : getLetti()) {
            if (l.getStato() == StatoLetto.DISPONIBILE)
                disponibili.add(l);
        }
        return disponibili;
    }
}
