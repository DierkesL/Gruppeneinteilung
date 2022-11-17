import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameHandling {

    public void startApplication(JFrame targetFrame) {
        Main main = new Main();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        targetFrame.setVisible(true);
        showLogIn(targetFrame);
    }

    private void showLogIn(JFrame targetFrame) {
        targetFrame.getContentPane().removeAll();

        targetFrame.setSize(500, 250);
        targetFrame.setResizable(false);
        targetFrame.setIconImage(new ImageIcon(getClass().getResource("Gruppeneinteilung_Icon.png")).getImage());
        targetFrame.setTitle("Gruppeneinteilung - Login");
        targetFrame.setLocationRelativeTo(null);
        targetFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        targetFrame.setLayout(new GridBagLayout());

        JLabel formTitle = new JLabel("Gruppeneinteilung Login", SwingConstants.CENTER);
        JLabel usernameLabel = new JLabel("Benutzername:", SwingConstants.CENTER);
        JTextField usernameInput = new JTextField();
        JLabel passwordLabel = new JLabel("Passwort:", SwingConstants.CENTER);
        JPasswordField passwordInput = new JPasswordField();
        JButton submitButton = new JButton("Anmelden");
        JButton resetButton = new JButton("Zurücksetzen");

        usernameInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkCredentials(targetFrame, usernameInput.getText(), passwordInput);
            }
        });
        passwordInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkCredentials(targetFrame, usernameInput.getText(), passwordInput);
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkCredentials(targetFrame, usernameInput.getText(), passwordInput);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetCredentials(targetFrame);
            }
        });

        GridBagConstraints frameGridConstraints = new GridBagConstraints();

        formTitle.setHorizontalAlignment(JLabel.CENTER);
        formTitle.setFont(new Font("Arial", Font.PLAIN, 26));
        frameGridConstraints.gridwidth = 3;
        frameGridConstraints.anchor = GridBagConstraints.BASELINE;
        frameGridConstraints.weighty = 1;
        frameGridConstraints.weightx = 0;
        frameGridConstraints.fill = GridBagConstraints.HORIZONTAL;
        frameGridConstraints.gridx = 0;
        frameGridConstraints.gridy = 0;
        frameGridConstraints.insets.set(10, 0, 15, 0);
        targetFrame.add(formTitle, frameGridConstraints);

        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        frameGridConstraints.gridwidth = 1;
        //frameGridConstraints.anchor = GridBagConstraints.WEST;
        frameGridConstraints.weightx = 1;
        frameGridConstraints.weighty = 1;
        frameGridConstraints.gridx = 0;
        frameGridConstraints.gridy = 2;
        targetFrame.add(usernameLabel, frameGridConstraints);

        usernameInput.setFont(new Font("Arial", Font.PLAIN, 14));
        frameGridConstraints.anchor = GridBagConstraints.CENTER;
        frameGridConstraints.weightx = 2;
        frameGridConstraints.weighty = 1;
        frameGridConstraints.gridx = 1;
        frameGridConstraints.gridy = 2;
        frameGridConstraints.insets.set(0, 0, 0, 10);
        targetFrame.add(usernameInput, frameGridConstraints);

        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        frameGridConstraints.gridwidth = 1;
        //frameGridConstraints.anchor = GridBagConstraints.WEST;
        frameGridConstraints.weightx = 1;
        frameGridConstraints.weighty = 1;
        frameGridConstraints.gridx = 0;
        frameGridConstraints.gridy = 3;
        targetFrame.add(passwordLabel, frameGridConstraints);

        passwordInput.setFont(new Font("Arial", Font.PLAIN, 14));
        frameGridConstraints.anchor = GridBagConstraints.CENTER;
        frameGridConstraints.weightx = 2;
        frameGridConstraints.weighty = 1;
        frameGridConstraints.gridx = 1;
        frameGridConstraints.gridy = 3;
        frameGridConstraints.insets.set(0, 0, 0, 10);
        targetFrame.add(passwordInput, frameGridConstraints);

        frameGridConstraints.fill = GridBagConstraints.HORIZONTAL;
        frameGridConstraints.ipady = 5;
        frameGridConstraints.gridx = 0;
        frameGridConstraints.gridy = 4;
        frameGridConstraints.insets.set(25, 10, 10, 10);
        targetFrame.add(resetButton, frameGridConstraints);

        frameGridConstraints.fill = GridBagConstraints.HORIZONTAL;
        frameGridConstraints.ipady = 5;
        frameGridConstraints.gridx = 1;
        frameGridConstraints.gridy = 4;
        frameGridConstraints.insets.set(25, 10, 10, 10);
        targetFrame.add(submitButton, frameGridConstraints);

        targetFrame.setVisible(true);
    }

    private void showMainMenu(JFrame targetFrame) {
        targetFrame.getContentPane().removeAll();

        targetFrame.setSize(800, 600);
        targetFrame.setResizable(false);
        targetFrame.setIconImage(new ImageIcon(getClass().getResource("Gruppeneinteilung_Icon.png")).getImage());
        targetFrame.setTitle("Gruppeneinteilung - Übersicht");
        targetFrame.setLocationRelativeTo(null);
        targetFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        targetFrame.setLayout(new GridBagLayout());

        JLabel formTitle = new JLabel("Gruppeneinteilung Pro");
        JButton buttonLeft = new JButton("Gruppenaufteilung");
        JButton buttonCenter = new JButton("Gruppe laden");
        JButton buttonRight = new JButton("Verwaltung");

        buttonLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null,e.getActionCommand().toString() ,"LOL", JOptionPane.INFORMATION_MESSAGE);
                //showGruppeneinteilung();
                showGruppeneinteilung();
            }
        });

        GridBagConstraints frameGridConstraints = new GridBagConstraints();

        frameGridConstraints.weighty = 0.1;
        frameGridConstraints.gridx = 1;
        frameGridConstraints.gridy = 0;
        formTitle.setFont(new Font("Arial", Font.PLAIN, 36));
        targetFrame.add(formTitle, frameGridConstraints);

        frameGridConstraints.weighty = 0.1;
        frameGridConstraints.gridx = 0;
        frameGridConstraints.gridy = 1;
        buttonLeft.setPreferredSize(new Dimension(200, 50));
        targetFrame.add(buttonLeft, frameGridConstraints);

        frameGridConstraints.weighty = 0.1;
        frameGridConstraints.weightx = 0;
        frameGridConstraints.gridx = 1;
        frameGridConstraints.gridy = 1;
        buttonCenter.setPreferredSize(new Dimension(200, 50));
        targetFrame.add(buttonCenter, frameGridConstraints);

        frameGridConstraints.weightx = 0;
        frameGridConstraints.gridx = 2;
        frameGridConstraints.gridy = 1;
        buttonRight.setPreferredSize(new Dimension(200, 50));
        targetFrame.add(buttonRight, frameGridConstraints);

        targetFrame.setVisible(true);
    }

    private void checkCredentials(JFrame targetFrame, String username, JPasswordField password) {
        String passwordFull = "";

        for(char passwordChar : password.getPassword()) {
            passwordFull = passwordFull + passwordChar;
        }

        if(username.trim().equals("") || passwordFull.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Bitte Login-Daten vollständig ausfüllen.","Fehler", JOptionPane.ERROR_MESSAGE);
        }else{
            // Password check via database still needs to be implemented
            if(username.equals("Test") && passwordFull.equals("1234")) {
                showMainMenu(targetFrame);
            }else{
                JOptionPane.showMessageDialog(null, "Falscher Benutzername oder falsches Passwort.","Fehler", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void resetCredentials(JFrame targetFrame)
    {
        String username = JOptionPane.showInputDialog(null, "Bitte geben Sie den Benutzernamen des betreffenden Accounts an:", "Passwort zurücksetzen", JOptionPane.INFORMATION_MESSAGE);

        if(username.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Sie müssen einen Benutzernamen angeben.","Fehler", JOptionPane.ERROR_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Dem betreffenden Account wird eine Mail mit einem neuen Passwort gesendet, sofern dieser existiert.","Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showGruppeneinteilung() {
        // Code from Lucas here when ready
    }
}
