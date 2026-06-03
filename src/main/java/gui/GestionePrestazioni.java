package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.Controller;

public class GestionePrestazioni {

    private JComboBox comboBoxPaziente;
    private JPanel panel1;
    private JComboBox comboBoxTipo; //si riferisce agli enum visita o intervento chirurgico
    private JTextField txtOraInizio;
    private JTextField txtOraFine;
    private JButton btnRegistra;
    private JButton btnIndietro;
    public static JFrame frame;

    public GestionePrestazioni(Controller controller, JFrame frameChiamante) {
        btnRegistra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
    }
}
