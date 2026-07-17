package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import controller.Controller;


/**
 * The type Gestione prestazioni.
 */
public class GestionePrestazioni {

    private JComboBox comboBoxPaziente;
    private JPanel panel1;
    private JComboBox comboBoxTipo; // si riferisce agli enum visita o intervento chirurgico
    private JTextField txtOraInizio;
    private JTextField txtOraFine;
    private JButton btnRegistra;
    private JButton btnIndietro;
    /**
     * The constant frame.
     */
    public static JFrame frame;
    private Controller controller;

    /**
     * Instantiates a new Gestione prestazioni.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     */

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Instantiates a new Gestione prestazioni.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     */
    public GestionePrestazioni(Controller controller, JFrame frameChiamante) {
        frame = new JFrame("Gestici le prestazioni");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(frameChiamante);


       List<String> pazienti = controller.getNomiPazientiRicoverati();

       if (comboBoxPaziente == null) throw new IllegalStateException("comboBoxPaziente non inizializzato");

       for (String s : pazienti) comboBoxPaziente.addItem(s);

       List<String> tipi = controller.getTipiPrestazione();
       for (String s : tipi) comboBoxTipo.addItem(s);


       if (btnRegistra == null) throw new IllegalStateException("btnRegistra non inizializzato");

       btnRegistra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (comboBoxPaziente.getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(frame,
                            "Selezionare un paziente.",
                            "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                LocalDateTime oraInizio;
                LocalDateTime oraFine;
                try {
                    oraInizio = LocalDateTime.parse(
                            txtOraInizio.getText().trim(), FORMATTER);
                    oraFine = LocalDateTime.parse(
                            txtOraFine.getText().trim(), FORMATTER);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Formato data non valido.\nUsare: dd/MM/yyyy HH:mm",
                            "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!oraInizio.isBefore(oraFine)) {
                    JOptionPane.showMessageDialog(frame,
                            "L'ora di inizio deve essere precedente all'ora di fine.",
                            "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(comboBoxTipo == null) throw new IllegalStateException("comboBoxTipo non inizializzato");

                try {
                    controller.registraPrestazione(
                            comboBoxPaziente.getSelectedIndex(),
                            comboBoxTipo.getSelectedIndex(),
                            oraInizio,
                            oraFine);
                    JOptionPane.showMessageDialog(frame,
                            "Prestazione registrata con successo.",
                            "Successo", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    frameChiamante.setVisible(true);
                } catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(frame,
                            ex.getMessage(),
                            "Errore", JOptionPane.ERROR_MESSAGE);

                } catch (Exception ex2){
                    JOptionPane.showMessageDialog(frame, ex2.getMessage(),
                            "Errore di database", JOptionPane.ERROR_MESSAGE);
                }

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
