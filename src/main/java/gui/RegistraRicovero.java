package gui;

import controller.Controller;
import model.Paziente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistraRicovero {
    public static JFrame frame;
    private JPanel panel1;
    private JTextField txtdataDimissioniPrevista;
    private JTextField txtDataInizio;
    private JTextField txtdataDimissioniEffettuata;
    private JButton btnOK;
    private JButton btnAnnulla;
    private JComboBox comboBoxP;
    private JComboBox comboBoxR;
    private JComboBox comboBoxL;

    public RegistraRicovero(Controller controller, JFrame frameChiamante) {
        frame = new JFrame("Registra Ricovero");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnAnnulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                frameChiamante.setVisible(true);
            }
        });
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
    }
}
