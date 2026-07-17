package gui;

import controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * The type Registra paziente.
 */
public class RegistraPaziente {

    private JTextField txtNome;
    private JTextField txtCognome;
    private JTextField txtCodiceFiscale;
    private JButton btnOK;
    private JButton btnAnnulla;
    private JPanel panel1;
    /**
     * The Frame.
     */
    public JFrame frame;
    private Controller controller;

    /**
     * Instantiates a new Registra paziente.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     */
    public RegistraPaziente(Controller controller, JFrame frameChiamante) {
        this.controller = controller;

        frame = new JFrame("Registra Paziente");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(frameChiamante);

        if (btnOK == null) throw new IllegalStateException("btnOK non inizializzato");
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

               try {
                   controller.registraPaziente(nome, cognome, cf);

                   JOptionPane.showMessageDialog(frame, "Paziente registrato con successo.", "Successo", JOptionPane.INFORMATION_MESSAGE);
                   frame.dispose();
                   frameChiamante.setVisible(true);

               } catch (Exception ex) {

                   JOptionPane.showMessageDialog(frame, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
               }


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
