package model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;

import static model.GiornoSettimana.LUNEDI;

public class TestModel {

	public static void main(String[] args) {
		Utente u = new Utente("topolino","minni");
		System.out.println(u.login("pippo","pluto"));
		System.out.println(u.login("topolino","minni"));

		Paziente pa = new Paziente("Pasquale", "Rossi", "RSSPQL80A01F839I");
		Ricovero ricovero1 = new Ricovero(LocalDateTime.of(2026, 5, 2, 16, 23, 0 ), LocalDateTime.now(), LocalDateTime.now(), pa);
		Prestazione p = new Prestazione("Positivo", TipoPrestazione.VISITA,LocalDateTime.now(), LocalDateTime.now(), ricovero1);
		Reparto r = new Reparto("Neurologia", "2kfdokf");
		TurnoLavorativo turno = new TurnoLavorativo (LUNEDI, LocalTime.of(16, 0, 0), LocalTime.of(23,0, 0));
		Stanza s = new Stanza(1, 1);
		Letto l = new Letto("12jnn");
		Medico med1 = new Medico("topolino", "minni", "Franco", "Rossi", r);

		med1.registerEsito(p, "La visita non ha riscontrato nuove diagnosi");
		System.out.println(p.getEsito());
		System.out.println(turno.copre( LocalTime.of(16, 0, 0), LocalTime.of(23,0, 0)));
		System.out.println(turno.copre( LocalTime.of(13, 0, 0), LocalTime.of(15,0, 0)));
		s.addLetto(l);

	}

}
