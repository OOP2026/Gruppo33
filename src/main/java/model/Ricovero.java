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

    private LocalDateTime getDataInizio(){
        return dataInizio;
    }

    private void setDataInizio(LocalDateTime dataInizio){
                this.dataInizio = dataInizio;
    }

    private LocalDateTime getDataDimissioniPrevista(){
        return dataDimissioniPrevista;
    }

    private void setDataDimissioniPrevista(LocalDateTime dataDimissioniPrevista){
        this.dataDimissioniPrevista = dataDimissioniPrevista;
    }

    private LocalDateTime getDataDimissioniEffettuata(){
        return dataDimissioniEffettuata;
    }

    private void setDataDimissioniEffettuata(LocalDateTime dataDimissioniEffettuata){
        this.dataDimissioniEffettuata = dataDimissioniEffettuata;
    }
}
