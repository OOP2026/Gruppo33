package model;

import java.time.LocalDateTime;

public class Prestazione {
    private String esito;
    private TipoPrestazione tipo;
    private LocalDateTime oraPrestazione;
    private Ricovero ricovero;

    public Prestazione(String esito, TipoPrestazione tipo, LocalDateTime ora){
        this.esito = esito;
        this.tipo = tipo;
        this.oraPrestazione = ora;
    }

    public void setEsito(String esito) {
        this.esito = esito;

    }
    public LocalDateTime getOra(){
        return this.oraPrestazione;
    }


}
