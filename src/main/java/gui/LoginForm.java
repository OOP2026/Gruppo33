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
    private JFrame frame;

    public LoginForm() {
        this.controller = new Controller();

        btnAccedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login - Sistema Ospedaliero");
        LoginForm loginForm = new LoginForm();
        loginForm.frame = frame;
        frame.setContentPane(loginForm.panelLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}