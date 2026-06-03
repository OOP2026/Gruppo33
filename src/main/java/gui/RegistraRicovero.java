package gui;

import controller.Controller;
import model.Paziente;
import model.Reparto;
import model.Letto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class RegistraRicovero {
    public static JFrame frame;
    private JPanel panel1;
    private JTextField txtdataDimissioniPrevista;
    private JTextField txtDataInizio;
    private JTextField txtdataDimissioniEffettuata;
    private JButton btnOK;
    private JButton btnAnnulla;
    private JComboBox comboBoxL;
    private JComboBox comboBoxR;
    private JComboBox comboBoxP;
    private JButton aggiornaButton;

    public RegistraRicovero(Controller controller, JFrame frameChiamante) {
        frame = new JFrame("Registra Ricovero");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ArrayList<Paziente> pazienti = controller.getPazienti();
        for (Paziente p : pazienti) comboBoxP.addItem(p.getNome() + " " + p.getCognome() + " " + "["+p.getCodiceFiscale()+"]");

        ArrayList<Reparto> reparti = controller.getReparti();
        for (Reparto r : reparti) comboBoxR.addItem(r.getNome() + " " + "("+r.getIdReparto()+")");

        ArrayList<Letto> letti = controller.getLetti();
        for (Letto l : letti) comboBoxL.addItem("Letto"+l.getCodiceUnivoco());

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnAnnulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }
}
