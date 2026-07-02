package gui;

import controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Pazienti scadenza.
 */
public class PazientiScadenza {
    private JTable table1;
    private JButton btnIndietro;
    private JPanel panel1;
    /**
     * The constant frame.
     */
    public static JFrame frame;

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

        if (btnIndietro == null) throw new IllegalStateException("btnIndietro non inizializzato");
        btnIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
    }

}
