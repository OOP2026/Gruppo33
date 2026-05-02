package model;

import java.util.ArrayList;

public class Letto {
    private String codiceUnivoco;
    private StatoLetto stato;
    ArrayList<Ricovero> ricoveri = new ArrayList<>();

public Letto(String codiceUnivoco){
    this.codiceUnivoco = codiceUnivoco;
    this.stato = StatoLetto.DISPONIBILE;
}
    public String getCodiceUnivoco() {
        return codiceUnivoco;
    }

    public void setCodiceUnivoco(String codiceUnivoco){
            this.codiceUnivoco = codiceUnivoco;
    }
    public StatoLetto getStato(){
        return stato;
    }
    public void setStato(StatoLetto stato) {
        this.stato = stato;
    }
    public ArrayList<Ricovero> getRicoveri(){
        return ricoveri;
    }
}
