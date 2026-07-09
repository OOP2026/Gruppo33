package gui;

import controller.Controller;
import model.Paziente;
import model.Ricovero;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.time.format.DateTimeFormatter;

/**
 * The type Pazienti scadenza.
 */
public class PazientiScadenza {
    private JTable tablePazienti;
    private JButton btnIndietro;
    private JPanel panel1;
    private JButton btnOggi;
    private JButton btnCerca;
    private JTextField txtData;
    /**
     * The constant frame.
     */
    public static JFrame frame;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    /**
     * Instantiates a new Pazienti scadenza.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     */
    public PazientiScadenza(Controller controller, JFrame frameChiamante) {
        frame = new JFrame("Pazienti in scadenza");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(panel1);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);


        if (tablePazienti == null) throw new IllegalStateException("tablePazienti non inizializzato");
       tablePazienti.setModel(new DefaultTableModel(new Object[][]{}, new String[]{
               "Nome", "Cognome", "Codice Fiscale", "Dimissione prevista"}
       ));

       DefaultTableModel model= (DefaultTableModel) tablePazienti.getModel();




        if (btnIndietro == null) throw new IllegalStateException("btnIndietro non inizializzato");
        btnIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });

        btnCerca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LocalDate data;

                try {
                    data = LocalDate.parse(txtData.getText().trim(), FORMATTER);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Formato data non valido.\nUsare: dd/MM/yyyy",
                            "Errore", JOptionPane.ERROR_MESSAGE);
                }

                model.setRowCount(0);
                List<Ricovero> ricoveriInScadenza = controller.getRicoveriInScadenza(LocalDate.parse(txtData.getText().trim(), FORMATTER));
                if (ricoveriInScadenza.isEmpty()){
                    JOptionPane.showMessageDialog(frame, "Nessun paziente in scadenza in questa data.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                for (Ricovero r : ricoveriInScadenza) {
                    Paziente p = r.getPaziente();
                    model.addRow(new Object[]{
                            p.getNome(),
                            p.getCognome(),
                            p.getCodiceFiscale(),
                            r.getDataDimissioniPrevista().format(
                                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                    });
                }


            }
        });

        btnOggi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                txtData.setText(LocalDateTime.now().format(FORMATTER));

            }
        });
    }

}
