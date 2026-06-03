package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.Controller;

public class AgendaMedico {
    private JComboBox comboBoxDorW;
    private JButton btnIndietro;
    private JButton cercaButton;
    private JTable tabellaAgenda;
    public static JFrame frame;

    public AgendaMedico(Controller controller, JFrame frameChiamante) {
        cercaButton.addActionListener(new ActionListener() {
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
