package bankeralgo;

import javax.swing.*;
import java.awt.*;

public class AdminView {
    public static void display() {
        JFrame frame = new JFrame("Administrator View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;

        FileOperations fileOperations = new FileOperations();

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setPreferredSize(new Dimension(600, 300)); 
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;

        JButton spellCheckButton = new JButton("Spell Check Paragraph");
        spellCheckButton.setFont(new Font("Arial", Font.PLAIN, 16));
        spellCheckButton.addActionListener(e -> {
            String paragraph = textArea.getText().trim();
            if (paragraph.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a paragraph to spell check.");
            } else {
                
                new Thread(() -> {
                    String incorrectWords = fileOperations.spellCheck(paragraph);
                    SwingUtilities.invokeLater(() -> {
                        if (incorrectWords.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "All words are correctly spelled.");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Incorrectly spelled words: " + incorrectWords);
                        }
                    });
                }).start();
            }
        });
        frame.add(spellCheckButton, gbc);

        gbc.gridx++;

        JButton decryptButton = new JButton("Decrypt Baudot String");
        decryptButton.setFont(new Font("Arial", Font.PLAIN, 16));
        decryptButton.addActionListener(e -> {
            String numericalString = JOptionPane.showInputDialog(frame, "Enter Baudot encoded string (e.g., +---- +--+-):");
            if (numericalString != null && !numericalString.trim().isEmpty()) {
                try {
                    String result = fileOperations.decryptAndCheck(numericalString.trim());
                    JOptionPane.showMessageDialog(frame, result);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid Baudot encoded string format.");
                }
            }
        });
        frame.add(decryptButton, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        JButton insertWordButton = new JButton("Insert Word");
        insertWordButton.setFont(new Font("Arial", Font.PLAIN, 16));
        insertWordButton.addActionListener(e -> {
            String wordToInsert = JOptionPane.showInputDialog(frame, "Enter word to insert:");
            if (wordToInsert != null && !wordToInsert.trim().isEmpty()) {
                fileOperations.insertWord(wordToInsert.trim().toLowerCase());
                JOptionPane.showMessageDialog(frame, "Word inserted.");
            }
        });
        frame.add(insertWordButton, gbc);

        gbc.gridx++;

        JButton deleteWordButton = new JButton("Delete Word");
        deleteWordButton.setFont(new Font("Arial", Font.PLAIN, 16));
        deleteWordButton.addActionListener(e -> {
            String wordToDelete = JOptionPane.showInputDialog(frame, "Enter word to delete:");
            if (wordToDelete != null && !wordToDelete.trim().isEmpty()) {
                fileOperations.deleteWord(wordToDelete.trim().toLowerCase());
                JOptionPane.showMessageDialog(frame, "Word deleted.");
            }
        });
        frame.add(deleteWordButton, gbc);

        frame.setVisible(true);
    }
}