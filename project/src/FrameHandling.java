import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

import static java.awt.Component.CENTER_ALIGNMENT;

public class FrameHandling {

    JFrame frameLogIn = new JFrame();
    SessionController sessionController = new SessionController();

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
        usernameInput.setText("testuser");
        passwordInput.setText("_jnQ_7PPPz7j_LJ5PSCbCg");
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
                if(SessionController.validateSession() == true) {
                    showDivision(targetFrame);
                }else{
                    JOptionPane.showMessageDialog(null, "Ungültige Sitzung, bitte erneut anmelden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    showLogIn(targetFrame);
                }
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

        for (char passwordChar : password.getPassword()) {
            passwordFull = passwordFull + passwordChar;
        }

        try {
            if(sessionController.initializeSession(username, passwordFull)) {
                showMainMenu(targetFrame);
            } else {
                JOptionPane.showMessageDialog(null, "Session konnte nicht erstellt werden.", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        } catch(Exception ex) {
            if(ex.getMessage().contains("ERROR: password authentication failed for user")) {
                JOptionPane.showMessageDialog(null, "Falscher Benutzername oder falsches Passwort.","Fehler", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Es ist ein unbekannter Fehler aufgetreten.","Fehler", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex.getMessage());
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
        // Add code for form "Gruppeneinteilung" here
    }

    private void showDivision(JFrame targetFrame) {
        targetFrame.getContentPane().removeAll();

        targetFrame.setSize(800, 600);
        targetFrame.setResizable(false);
        targetFrame.setIconImage(new ImageIcon(getClass().getResource("Gruppeneinteilung_Icon.png")).getImage());
        targetFrame.setTitle("Gruppeneinteilung - Aufteilung");
        targetFrame.setLocationRelativeTo(null);
        targetFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        targetFrame.setLayout(new BorderLayout());

        var container = targetFrame.getContentPane();

        var headerLabel = new JLabel("Aufteilung");
        headerLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        var classLabel = new JLabel("Klasse", SwingConstants.CENTER);
        var comboBox = new JComboBox();
        comboBox.setAlignmentY(0);
        var splitByLabel = new JLabel("Aufteilen nach...", SwingConstants.CENTER);

        var radioBtnGroup = new ButtonGroup();
        var randomRadioBtn = new JRadioButton("Zufällig");
        var performanceRadioBtn = new JRadioButton("Leistung");
        var ownWishesRadioBtn = new JRadioButton("Eigene Wünsche");
        var homogeneousRadioBtn = new JRadioButton("Homogen");
        var heterogeneousRadioBtn = new JRadioButton("Heterogen");
        radioBtnGroup.add(randomRadioBtn);
        radioBtnGroup.add(performanceRadioBtn);
        radioBtnGroup.add(ownWishesRadioBtn);
        radioBtnGroup.add(homogeneousRadioBtn);
        radioBtnGroup.add(heterogeneousRadioBtn);

        var backBtn = new JButton("< Zurück");
        backBtn.setPreferredSize(new Dimension(100, 35));
        var nextBtn = new JButton("> Weiter");
        nextBtn.setPreferredSize(new Dimension(100, 35));

        var panelTop = new JPanel();
        var panelBottom = new JPanel();
        var panelCenter = new JPanel();
        var blackline = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY);
        panelBottom.setBorder(blackline);
        var panelCenterSplitPart = new JPanel();
        panelCenterSplitPart.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        var panel3 = new JPanel();
        var panel4 = new JPanel();
        var panel5 = new JPanel();

        panelTop.setLayout(new FlowLayout());
        panelBottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelCenter.setLayout(new GridLayout(2, 2));
        panelCenterSplitPart.setLayout(new FlowLayout(FlowLayout.CENTER));

        panelTop.add(headerLabel);

        panelBottom.add(backBtn);
        panelBottom.add(nextBtn);

        panelCenter.add(classLabel);
        panelCenter.add(comboBox);
        panelCenter.add(splitByLabel);
        panelCenter.add(panelCenterSplitPart);

        panelCenterSplitPart.add(randomRadioBtn);
        panelCenterSplitPart.add(performanceRadioBtn);
        panelCenterSplitPart.add(ownWishesRadioBtn);
        panelCenterSplitPart.add(homogeneousRadioBtn);
        panelCenterSplitPart.add(heterogeneousRadioBtn);

        container.add(panelTop, BorderLayout.PAGE_START);
        container.add(panelCenter, BorderLayout.CENTER);
        container.add(panelBottom, BorderLayout.PAGE_END);

        targetFrame.setVisible(true);
    }
}