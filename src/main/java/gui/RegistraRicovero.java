package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistraRicovero {
    private JPanel panel1;
    private JTextField txtdataDimissioniPrevista;
    private JTextField txtDataInizio;
    private JTextField txtdataDimissioniEffettuata;
    private JButton btnOK;
    private JButton btnAnnulla;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    public RegistraRicovero() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
