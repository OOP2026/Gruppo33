package gui;

import controller.Controller;
import model.Paziente;
import model.Reparto;
import model.Letto;
import model.Ricovero;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
        frame.setVisible(true);
        if (comboBoxP == null) {
            throw new IllegalStateException("btncomboBoxP non inizializzato");
        }
        List<Paziente> pazienti = controller.getPazienti();
        for (Paziente p : pazienti)
            comboBoxP.addItem(p.getNome() + " " + p.getCognome() + " " + "["+p.getCodiceFiscale()+"]");


        List<Reparto> reparti = controller.getReparti();
        for (Reparto r : reparti) comboBoxR.addItem(r.getNome() + " " + "("+r.getIdReparto()+")");


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
                          "Formato data non valido. Usare: dd/MM/yyyy HH:mm", "Errore", JOptionPane.ERROR_MESSAGE);
                  return;
              }

                if (!dataInizio.isAfter(dataDimissioniPrevista)) {
                    JOptionPane.showMessageDialog(frame, "La data di inizio deve essere precedente alla dimissione prevista.",
                            "Errore", JOptionPane.ERROR_MESSAGE);
                }

                Paziente paziente = pazienti.get(comboBoxP.getSelectedIndex());
                int idxReparto =  comboBoxR.getSelectedIndex();
                Reparto rSelezionato = reparti.get(idxReparto);
                List<Letto> letti = controller.getLettiDisp(rSelezionato);
                Letto letto = letti.get(comboBoxL.getSelectedIndex());

                try{
                    controller.registraRicovero(paziente, letto, dataInizio, dataDimissioniPrevista);
                    JOptionPane.showMessageDialog(frame, "Ricovero registrato correttamente.", "Successo", JOptionPane.INFORMATION_MESSAGE);


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
                Reparto rSelezionato = reparti.get(idx);
                List<Letto> letti = controller.getLettiDisp(rSelezionato);
                for (Letto l : letti) {
                    comboBoxL.addItem("Letto"+l.getCodiceUnivoco());
                }


            }
        });
    }
}
