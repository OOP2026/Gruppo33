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
		boolean isAdmin  = "AMMINISTRATORE".equals(ruolo);
		boolean isMedico = "MEDICO".equals(ruolo);

		// Solo amministratore
		btnRegistraPaziente.setVisible(isAdmin);
		btnRegistraRicovero.setVisible(isAdmin);
		btnLettiDisponibili.setVisible(isAdmin);
		btnPazientiScadenza.setVisible(isAdmin);

		// Solo medico
		btnAgendaMedico.setVisible(isMedico);
		btnGestionePrestazioni.setVisible(isMedico);


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

			}
		});

		btnPazientiScadenza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		btnAgendaMedico.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		btnGestionePrestazioni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Torna al login
				frame.dispose();
				frameChiamante.setVisible(true);
			}
		});
	}


	public JFrame getFrame() {
		return frame;
	}
}

