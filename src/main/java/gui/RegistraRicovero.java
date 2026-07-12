package gui;

import controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;


/**
 * The type Registra ricovero.
 */
public class RegistraRicovero {
    /**
     * The constant frame.
     */
    public static JFrame frame;
    private JPanel panel1;
    private JTextField txtdataDimissioniPrevista;
    private JTextField txtDataInizio;
    private JTextField txtdataDimissioniEffettuata;
    private JButton btnOK;
    private JButton btnAnnulla;
    private JComboBox comboBoxL;
    private JComboBox comboBoxR;
    private JComboBox comboBoxP;
    private JButton aggiornaButton;


    /**
     * Instantiates a new Registra ricovero.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     */
    public RegistraRicovero(Controller controller, JFrame frameChiamante) {
        frame = new JFrame("Registra Ricovero");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(frameChiamante);
        frame.setVisible(true);


        if (comboBoxP == null) {
            throw new IllegalStateException("btncomboBoxP non inizializzato");
        }
        List<String> nomiPazienti = controller.getNomiPazienti();
        for (String s : nomiPazienti)
            comboBoxP.addItem(s);


        List<String> nomiReparti = controller.getNomiReparti();
        for (String s : nomiReparti) comboBoxR.addItem(s);


        btnAnnulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBoxP.getSelectedIndex() < 0
                        || comboBoxL.getSelectedIndex() < 0) {
                    JOptionPane.showMessageDialog(frame, "Selezionare paziente e/o letto.", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                LocalDateTime dataInizio;
                LocalDateTime dataDimissioniPrevista;
                LocalDateTime dataDimissioniEffettuata = null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

              try {
                  dataInizio = LocalDateTime.parse(txtDataInizio.getText(), formatter);
                  dataDimissioniPrevista = LocalDateTime.parse(txtdataDimissioniPrevista.getText(), formatter);

              }
              catch (DateTimeParseException e1){
                  JOptionPane.showMessageDialog(frame,
                          "Formato data non valido. Usare: dd/MM/yyyy HH:mm\n(Verifica se hai lasciato uno spazio dopo l'ora)", "Errore", JOptionPane.ERROR_MESSAGE);

                  return;
              }

                if (!dataInizio.isAfter(dataDimissioniPrevista)) {
                    JOptionPane.showMessageDialog(frame, "La data di inizio deve essere precedente alla dimissione prevista.",
                            "Errore", JOptionPane.ERROR_MESSAGE);
                  return;
                }

                try{
                    controller.registraRicovero(comboBoxP.getSelectedIndex(),
                            comboBoxR.getSelectedIndex(),
                            comboBoxL.getSelectedIndex(),
                            dataInizio,
                            dataDimissioniPrevista);
                    JOptionPane.showMessageDialog(frame, "Ricovero registrato correttamente.", "Successo", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    frameChiamante.setVisible(true);

                } catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                }


                }

        });

        comboBoxR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxL.removeAllItems();
                int idx = comboBoxR.getSelectedIndex();
                if (idx < 0) return;
                List<String> letti = controller.getCodiciLettiDisponibili(idx);
                for (String s : letti) comboBoxL.addItem(s);


            }
        });
    }
}
