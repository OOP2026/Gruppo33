package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.Controller;

/**
 * The type Agenda medico.
 */
public class AgendaMedico {
    private JButton btnIndietro;
    private JButton cercaButton;
    private JTable tabellaAgenda;
    private JPanel panel1;
    private JRadioButton radioGiornalieraRadio;
    private JRadioButton radioSettimanale;
    private JButton modificaEsitoButton;
    private JButton oggiButton;
    /**
     * The constant frame.
     */
    public static JFrame frame;
    private Controller controller;

    /**
     * Instantiates a new Agenda medico.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     */
    public AgendaMedico(Controller controller, JFrame frameChiamante) {
        frame = new JFrame("Agenda");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(frameChiamante);
        frame.setVisible(true);

        if (cercaButton == null) throw new IllegalStateException("cercaButton non inizializzato");
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
        modificaEsitoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        oggiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
