package bankeralgo;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame loginFrame = new JFrame("Login");
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginFrame.setSize(400, 300);
            loginFrame.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel welcomeLabel = new JLabel("Welcome! Please log in.", SwingConstants.CENTER);
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
            loginFrame.add(welcomeLabel, gbc);

            gbc.gridy++;
            gbc.gridwidth = 1;

            JButton adminLoginButton = new JButton("Administrator Login");
            adminLoginButton.setFont(new Font("Arial", Font.PLAIN, 14));
            adminLoginButton.addActionListener(e -> showLoginDialog());
            loginFrame.add(adminLoginButton, gbc);

            loginFrame.setVisible(true);
        });
    }

    private static void showLoginDialog() {
        JFrame loginDialog = new JFrame("Login");
        loginDialog.setSize(400, 200);
        loginDialog.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        loginDialog.add(usernameLabel, gbc);

        gbc.gridx++;
        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setPreferredSize(new Dimension(200, 30));
        loginDialog.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        loginDialog.add(passwordLabel, gbc);

        gbc.gridx++;
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setPreferredSize(new Dimension(200, 30)); 
        loginDialog.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 14));
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("DSA_try") && password.equals("1234")) {
                AdminView.display();
                loginDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(loginDialog, "Invalid credentials. Please try again.");
            }
        });
        loginDialog.add(loginButton, gbc);

        loginDialog.setVisible(true);
    }
}