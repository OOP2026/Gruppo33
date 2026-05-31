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

	private static JFrame frame;

	private Controller controller;

	public Home() {

		controller = new Controller();

		btnRegistraPaziente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {


			}
		});

		btnRegistraRicovero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

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
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {

		frame = new JFrame("Sistema Ospedaliero");

		frame.setContentPane(new Home().panel1);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.pack();

		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}
}
