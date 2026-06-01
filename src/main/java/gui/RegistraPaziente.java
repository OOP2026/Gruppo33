package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistraPaziente {

    private JTextField txtNome;
    private JTextField txtCognome;
    private JTextField txtCodiceFiscale;
    private JButton btnOK;
    private JButton btnAnnulla;
    private JPanel panel1;
    public JFrame frame;


    public RegistraPaziente() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnAnnulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
