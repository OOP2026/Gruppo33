package gui;

import controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PazientiScadenza {
    private JTable table1;
    private JButton btnIndietro;
    private JPanel panel1;
    public static JFrame frame;

    public PazientiScadenza(Controller controller, JFrame frameChiamante) {
        frame = new JFrame("Pazienti in scadenza");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(panel1);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);


   ;     btnIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            frameChiamante.setVisible(true);
            frame.setVisible(false);
            }
        });
    }
}
