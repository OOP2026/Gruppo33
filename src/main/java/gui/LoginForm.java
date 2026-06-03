package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {

    private JPanel panelLogin;
    private JTextField txtLogin;
    private JPasswordField txtPassword;
    private JButton btnAccedi;
    private JLabel lblErrore;

    private Controller controller;
    private static JFrame frame;

    public LoginForm() {
        this.controller = new Controller();

        btnAccedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login    = txtLogin.getText();
                String password = new String(txtPassword.getPassword());

                String ruolo = controller.login(login, password);

                if (ruolo == null) {
                    lblErrore.setText("Credenziali non valide. Riprovare.");
                    lblErrore.setVisible(true);
                } else {
                    lblErrore.setVisible(false);

                    Home home = new Home(controller, frame, ruolo);
                    home.getFrame().setVisible(true);
                    frame.setVisible(false);
                }
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Login - Sistema Ospedaliero");
        LoginForm loginForm = new LoginForm();
        frame.setContentPane(loginForm.panelLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}