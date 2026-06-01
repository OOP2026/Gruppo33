package model;

import java.time.LocalDateTime;

public class Ricovero {

    private LocalDateTime dataInizio;
    private LocalDateTime dataDimissioniPrevista;
    private LocalDateTime dataDimissioniEffettuata;
    private Paziente paziente;


    public Ricovero(LocalDateTime dataInizio, LocalDateTime dataDimissioniPrevista, LocalDateTime dataDimissioniEffettuata, Paziente paziente){
        this.dataInizio = dataInizio;
        this.dataDimissioniPrevista = dataDimissioniPrevista;
        this.dataDimissioniEffettuata = dataDimissioniEffettuata;
        this.paziente = paziente;
    }

    public LocalDateTime getDataInizio(){
        return dataInizio;
    }

    public void setDataInizio(LocalDateTime dataInizio){
                this.dataInizio = dataInizio;
    }

    public LocalDateTime getDataDimissioniPrevista(){
        return dataDimissioniPrevista;
    }

    public void setDataDimissioniPrevista(LocalDateTime dataDimissioniPrevista){
        this.dataDimissioniPrevista = dataDimissioniPrevista;
    }

    public LocalDateTime getDataDimissioniEffettuata(){
        return dataDimissioniEffettuata;
    }

    public void setDataDimissioniEffettuata(LocalDateTime dataDimissioniEffettuata){
        this.dataDimissioniEffettuata = dataDimissioniEffettuata;
    }
}
