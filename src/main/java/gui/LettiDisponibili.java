package gui;

import controller.Controller;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Reparto;
import model.Letto;
import java.util.List;

/**
 * The type Letti disponibili.
 */
public class LettiDisponibili {
    private JTable tableLetti;
    private JPanel panel1;
    private JButton btnIndietro;
    private JComboBox comboBoxR;
    private JButton cercaButton;
    /**
     * The constant frame.
     */
    public static JFrame frame;

    /**
     * Instantiates a new Letti disponibili.
     *
     * @param controller     the controller
     * @param frameChiamante the frame chiamante
     */
    public LettiDisponibili(Controller controller, JFrame frameChiamante) {
        frame = new JFrame("Letti disponibili");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        if (comboBoxR == null) throw new IllegalStateException("btncomboBoxR non inizializzato");
        List<Reparto> reparti = controller.getReparti();
        for (Reparto r : reparti) {
            comboBoxR.addItem(r.getNome() + " " + "(" + r.getIdReparto() + ")");

            // per la tabella
            tableLetti.setModel(new DefaultTableModel(new Object[][]{}, new String[]{
                    "Codice Letto", "Stato"}) {

            });
        }

        // Colora la riga in rosso se il letto è occupato
        tableLetti.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String stato = (String) table.getValueAt(row, 1);
                if ("OCCUPATO".equals(stato)) {
                    c.setForeground(Color.RED);
                } else {
                    c.setForeground(Color.BLACK);
                }
                return c;
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

        cercaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) tableLetti.getModel();
                List<Letto> letti = controller.getLetti();
                if (letti != null)
                    for (Letto l : letti)
                        model.addRow(new Object[]{l.getCodiceUnivoco()}); // {l.getCodiceUnivoco(), l.getStato()}); dà errore

            }
        });
    }

}
