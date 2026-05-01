package model;

public class Letto {
    private String codiceUnivoco;
    private StatoLetto stato;

public Letto(String codiceUnivoco){
    this.codiceUnivoco = codiceUnivoco;
    this.stato = StatoLetto.DISPONIBILE;
}

    public String getCodiceUnivoco() {
        return codiceUnivoco;
    }
    public void setStato(StatoLetto stato) {
        this.stato = stato
    }
}
