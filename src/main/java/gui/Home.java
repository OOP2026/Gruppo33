package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {

	private JPanel panel1;

	private JButton btnRegistraPaziente;
	private JButton btnRegistraRicovero;
	private JButton btnLettiDisponibili;
	private JButton btnPazientiScadenza;
	private JButton btnAgendaMedico;
	private JButton btnGestionePrestazioni;
	private JButton btnLogout;
	private JLabel lblWelc; // label vuoto per dare il messaggio di benvenuto
	private Controller controller;
	private JFrame frame;

	public Home(Controller controller, JFrame frameChiamante, String ruolo) {
		this.controller = controller;


		frame = new JFrame("Home - Sistema Ospedaliero");
		frame.setContentPane(panel1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);

		// Visibilità pulsanti in base al ruolo
		boolean isAdmin = "AMMINISTRATORE".equals(ruolo);
		boolean isMedico = "MEDICO".equals(ruolo);

		// Solo amministratore
		btnRegistraPaziente.setVisible(isAdmin);
		btnRegistraRicovero.setVisible(isAdmin);
		btnLettiDisponibili.setVisible(isAdmin);
		btnPazientiScadenza.setVisible(isAdmin);

		// Solo medico
		btnAgendaMedico.setVisible(isMedico);
		btnGestionePrestazioni.setVisible(isMedico);

		if (isAdmin) {
			lblWelc.setText("Benvenuto amministratore");
		} else if (isMedico) {
			lblWelc.setText("Benvenuto medico");
		}

		btnRegistraPaziente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistraPaziente registraPaziente = new RegistraPaziente(controller, frame);
				registraPaziente.frame.setVisible(true);
				frame.setVisible(false);

			}
		});

		btnRegistraRicovero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistraRicovero registraRicovero = new RegistraRicovero(controller, frame);
				registraRicovero.frame.setVisible(true);
				frame.setVisible(false);
			}
		});

		btnLettiDisponibili.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LettiDisponibili lettiDisponibili = new LettiDisponibili(controller, frame);
				LettiDisponibili.frame.setVisible(true);
				frame.setVisible(false);
			}
		});

		btnPazientiScadenza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PazientiScadenza pazientiScadenza = new PazientiScadenza(controller, frame);
				PazientiScadenza.frame.setVisible(true);
				frame.setVisible(false);
			}
		});

		btnAgendaMedico.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AgendaMedico agendaMedico = new AgendaMedico(controller, frame);
				AgendaMedico.frame.setVisible(true);
				frame.setVisible(false);
			}
		});

		btnGestionePrestazioni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GestionePrestazioni gestionePrestazioni = new GestionePrestazioni(controller, frame);
				GestionePrestazioni.frame.setVisible(true);
				frame.setVisible(false);
			}
		});

		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frameChiamante.setVisible(true);
			}
		});
	}


	public JFrame getFrame() {
		return frame;
	}

}

