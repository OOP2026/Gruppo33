package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

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
    private JTextField txtData;
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

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public AgendaMedico(Controller controller, JFrame frameChiamante) {
        frame = new JFrame("Agenda");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(frameChiamante);
        frame.setVisible(true);

        if (cercaButton == null) throw new IllegalStateException("cercaButton non inizializzato");


        ButtonGroup group = new ButtonGroup();
        group.add(radioGiornalieraRadio);
        group.add(radioSettimanale);
        radioGiornalieraRadio.setSelected(true);

        tabellaAgenda.setModel(new DefaultTableModel(new Object[][]{}, new String[]{
                "Ora inizio", "Ora fine", "Tipo", "Paziente", "Esito"}
        ));

        DefaultTableModel model= (DefaultTableModel) tabellaAgenda.getModel();

        cercaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LocalDateTime data;
                try {

                    data = LocalDateTime.parse(txtData.getText().trim(), FORMATTER);

                } catch (DateTimeParseException e1) {
                    JOptionPane.showMessageDialog(frame, "Formato data non valido", "Errore", JOptionPane.ERROR_MESSAGE);

                    return;

                }
                model.setRowCount(0);
                List<String[]> Prestazioni;


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

                txtData.setText(LocalDateTime.now().format(FORMATTER));


            }
        });
    }

}
