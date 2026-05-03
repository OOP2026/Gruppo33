package model;

import java.time.LocalDateTime;

public class Prestazione {
    private String esito;
    private TipoPrestazione tipo;
    private LocalDateTime oraInizio;
    private LocalDateTime oraFine;
    private Ricovero ricovero;

    public Prestazione(String esito, TipoPrestazione tipo, LocalDateTime oraInizio, LocalDateTime oraFine, Ricovero ricovero){
        this.esito = esito;
        this.tipo = tipo;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.ricovero = ricovero;
    }
    public String getEsito(){
        return esito;
    }
    public void setEsito(String esito) {
        this.esito = esito;
    }
    public LocalDateTime getOraInizio(){
        return oraInizio;
    }
    public void setOraInizio(LocalDateTime oraInizio){
        this.oraInizio = oraInizio;
    }
    public LocalDateTime getOraFine(){
        return oraFine;
    }
    public void setOraFine(LocalDateTime oraFine){
        this.oraFine = oraFine;
    }


    public boolean siAccavallaCon(Prestazione altra) {
        return oraInizio.isBefore(altra.getOraFine()) && oraFine.isAfter(altra.getOraInizio());
    }


}
