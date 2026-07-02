package model;

import java.time.LocalDateTime;

/**
 * The type Ricovero.
 */
public class Ricovero {

    private LocalDateTime dataInizio;
    private LocalDateTime dataDimissioniPrevista;
    private LocalDateTime dataDimissioniEffettuata;
    private Paziente paziente;


    /**
     * Instantiates a new Ricovero.
     *
     * @param dataInizio               the data inizio
     * @param dataDimissioniPrevista   the data dimissioni prevista
     * @param dataDimissioniEffettuata the data dimissioni effettuata
     * @param paziente                 the paziente
     */
    public Ricovero(LocalDateTime dataInizio, LocalDateTime dataDimissioniPrevista, LocalDateTime dataDimissioniEffettuata, Paziente paziente){
        this.dataInizio = dataInizio;
        this.dataDimissioniPrevista = dataDimissioniPrevista;
        this.dataDimissioniEffettuata = dataDimissioniEffettuata;
        this.paziente = paziente;
    }

    /**
     * Get data inizio local date time.
     *
     * @return the local date time
     */
    public LocalDateTime getDataInizio(){
        return dataInizio;
    }

    /**
     * Set data inizio.
     *
     * @param dataInizio the data inizio
     */
    public void setDataInizio(LocalDateTime dataInizio){
                this.dataInizio = dataInizio;
    }

    /**
     * Get data dimissioni prevista local date time.
     *
     * @return the local date time
     */
    public LocalDateTime getDataDimissioniPrevista(){
        return dataDimissioniPrevista;
    }

    /**
     * Set data dimissioni prevista.
     *
     * @param dataDimissioniPrevista the data dimissioni prevista
     */
    public void setDataDimissioniPrevista(LocalDateTime dataDimissioniPrevista){
        this.dataDimissioniPrevista = dataDimissioniPrevista;
    }

    /**
     * Get data dimissioni effettuata local date time.
     *
     * @return the local date time
     */
    public LocalDateTime getDataDimissioniEffettuata(){
        return dataDimissioniEffettuata;
    }

    /**
     * Set data dimissioni effettuata.
     *
     * @param dataDimissioniEffettuata the data dimissioni effettuata
     */
    public void setDataDimissioniEffettuata(LocalDateTime dataDimissioniEffettuata){
        this.dataDimissioniEffettuata = dataDimissioniEffettuata;
    }
}
