package gui;

import controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LettiDisponibili {
    private JTable table1;
    private JPanel panel1;
    private JButton btnIndietro;
    public static JFrame frame;

    public LettiDisponibili(Controller controller, JFrame frameChiamante) {
        frame = new JFrame("Letti disponibili");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

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
