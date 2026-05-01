package model;

import java.time.LocalTime;

public class TurnoLavorativo {
    private GiornoSettimana giorno;
    private LocalTime oraInizio;
    private LocalTime oraFine;

    public TurnoLavorativo(GiornoSettimana giorno, LocalTime oraInizio, LocalTime oraFine){
        this.giorno = giorno;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }

}
