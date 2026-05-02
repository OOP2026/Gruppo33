package model;

import java.time.LocalDateTime;

public class Prestazione {
    private String esito;
    private TipoPrestazione tipo;
    private LocalDateTime oraPrestazione;
    private Ricovero ricovero;

    public Prestazione(String esito, TipoPrestazione tipo, LocalDateTime ora, Ricovero ricovero){
        this.esito = esito;
        this.tipo = tipo;
        this.oraPrestazione = ora;
        this.ricovero = ricovero;
    }

    public String getEsito(){
        return esito;
    }
    public void setEsito(String esito) {
        this.esito = esito;
    }
    public LocalDateTime getOra(){
        return this.oraPrestazione;
    }


}
