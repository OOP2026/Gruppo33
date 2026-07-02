package gui;

import controller.Controller;
import model.Paziente;
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
    private Controller controller;

    public RegistraPaziente(Controller controller, JFrame frameChiamante) {
        this.controller = controller;

        frame = new JFrame("Registra Paziente");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(frameChiamante);

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String cognome = txtCognome.getText();
                String cf = txtCodiceFiscale.getText();


                if (nome.isEmpty() || cognome.isEmpty() || cf.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Compilare i campi.", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Paziente p = new Paziente(nome, cognome, cf);
                controller.registraPaziente(p);

                JOptionPane.showMessageDialog(frame, "Paziente registrato con successo.", "Successo", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                frameChiamante.setVisible(true);
            }
        });

        btnAnnulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                frameChiamante.setVisible(true);
            }
        });
    }

}
