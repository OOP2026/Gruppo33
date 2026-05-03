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
    public GiornoSettimana getGiorno() {
        return giorno;
    }
    public void setGiorno (GiornoSettimana giorno)   {
        this.giorno = giorno;
    }
    public LocalTime getOraInizio() {
        return oraInizio;
    }
    public void setOraInizio(LocalTime oraInizio) {
        this.oraInizio = oraInizio;
    }
    public LocalTime getOraFine() {
        return oraFine;
    }
    public void setOraFine(LocalTime oraFine) {
        this.oraFine = oraFine;
    }
    public boolean copre(LocalTime inizio, LocalTime fine) {
        return !inizio.isBefore(oraInizio) && !fine.isAfter(oraFine);
    }
}
