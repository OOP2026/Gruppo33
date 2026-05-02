package model;

import java.time.LocalDateTime;

public class TestModel {

	public static void main(String[] args) {
		Utente u = new Utente("topolino","minni");
		System.out.println(u.login("pippo","pluto"));
		System.out.println(u.login("topolino","minni"));

		Ricovero ricovero1 = new Ricovero();
		Prestazione p = new Prestazione("Positivo", TipoPrestazione.VISITA,LocalDateTime.now(), ricovero1);
		Reparto r = new Reparto("Neurologia", "2kfdokf");
		Medico med1 = new Medico("topolino", "minni", "Franco", "Rossi", r);

		med1.registerEsito(p, "La visita non ha riscontrato nuove diagnosi");
		System.out.println(p.getEsito());
	}

}
