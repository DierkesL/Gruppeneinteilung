import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrameHandling {

    JFrame frameLogIn = new JFrame();
    SessionController sessionController = new SessionController();
    BaseDAO dao = null;

    public void startApplication(JFrame targetFrame, BaseDAO _dao) {
        dao = _dao;

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
                showGruppeneinteilung(targetFrame);
                if(SessionController.validateSession() == true) {
                    //showGruppeneinteilung(targetFrame);
                }else{
                    //JOptionPane.showMessageDialog(null, "Ungültige Sitzung, bitte erneut anmelden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    //showLogIn(targetFrame);
                }
            }
        });

        buttonRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showManagement(targetFrame);
                if(SessionController.validateSession() == true) {
                    //showGruppeneinteilung(targetFrame);
                }else{
                    //JOptionPane.showMessageDialog(null, "Ungültige Sitzung, bitte erneut anmelden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    //showLogIn(targetFrame);
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
            if(sessionController.initializeSession(dao.SessionDataSource, username, passwordFull)) {
                showMainMenu(targetFrame);
            } else {
                JOptionPane.showMessageDialog(null, "Ungültiger Benutzername oder ungültiges Passwort.", "Fehler", JOptionPane.ERROR_MESSAGE);
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

    private void showGruppeneinteilung(JFrame targetFrame) {
        final long serialVersionUID = 1L;
        JPanel contentPane;
        JMenuBar menuBar;

        JComboBox<String> klasseDropDown;
        JRadioButton zufaelligRadioButton;
        JRadioButton leistungRadioButton;
        JRadioButton eigeneWuenscheRadioButton;
        JRadioButton homogenRadioButton;
        JRadioButton heterogenRadioButton;

        targetFrame.setLocationRelativeTo(null);

        targetFrame.setTitle("Aufteilung");
        targetFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        targetFrame.setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(0, 10));
        targetFrame.setContentPane(contentPane);

        // Menubar
        menuBar = new JMenuBar();
        JMenu menuItem = new JMenu("Menü");
        JMenu aufteilungItem = new JMenu("Aufteilung");
        JMenu verwaltungItem = new JMenu("Verwaltung");
        JMenu hilfeItem = new JMenu("Hilfe");

        menuBar.add(menuItem);
        menuBar.add(aufteilungItem);
        menuBar.add(verwaltungItem);
        menuBar.add(hilfeItem);
        targetFrame.setJMenuBar(menuBar);

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(0, 30));
        JLabel headerLabel = new JLabel("Aufteilung", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));

        headerPanel.add(headerLabel, BorderLayout.CENTER);
        contentPane.add(headerPanel, BorderLayout.PAGE_START);

        JPanel dropdownPanel = new JPanel(new BorderLayout());

        JLabel klasseLabel = new JLabel("Klasse:");
        klasseLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        dropdownPanel.add(klasseLabel, BorderLayout.WEST);
        klasseDropDown = new JComboBox<String>();
        klasseDropDown.addItem("Klasse 1");
        klasseDropDown.addItem("Klasse 2");
        klasseDropDown.addItem("Klasse 3");
        klasseDropDown.addItem("Klasse 4");
        //klasseDropDown.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        //dropdownPanel.add(klasseDropDown, BorderLayout.CENTER);
        contentPane.add(klasseDropDown, BorderLayout.CENTER);

        // Radio Button Panel
        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setBorder(BorderFactory.createTitledBorder("Aufteilen nach..."));
        radioButtonPanel.setLayout(new GridLayout(5, 1, 0, 5));
        zufaelligRadioButton = new JRadioButton("Zufällig");
        leistungRadioButton = new JRadioButton("Leistung");
        eigeneWuenscheRadioButton = new JRadioButton("Eigene Wünsche");
        homogenRadioButton = new JRadioButton("Homogen");
        heterogenRadioButton = new JRadioButton("Heterogen");

        ButtonGroup radioButtonGroup = new ButtonGroup();

        radioButtonGroup.add(zufaelligRadioButton);
        radioButtonGroup.add(leistungRadioButton);
        radioButtonGroup.add(eigeneWuenscheRadioButton);
        radioButtonGroup.add(homogenRadioButton);
        radioButtonGroup.add(heterogenRadioButton);

        radioButtonPanel.add(zufaelligRadioButton);
        radioButtonPanel.add(leistungRadioButton);
        radioButtonPanel.add(eigeneWuenscheRadioButton);
        radioButtonPanel.add(homogenRadioButton);
        radioButtonPanel.add(heterogenRadioButton);

        contentPane.add(radioButtonPanel, BorderLayout.CENTER);

        // Separator
        contentPane.add(new JSeparator(), BorderLayout.SOUTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 5, 0));
        buttonPanel.setPreferredSize(new Dimension(0, 40));
        JButton backButton = new JButton("Abbrechen");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                targetFrame.setJMenuBar(null);
                showMainMenu(targetFrame);
            }
        });

        buttonPanel.add(backButton);
        JButton nextButton = new JButton("Weiter >");
        //nextButton.addActionListener(this);
        buttonPanel.add(nextButton);
        contentPane.add(buttonPanel, BorderLayout.PAGE_END);
    }

    private void showEditSchueler(JFrame targetFrame) {
        targetFrame.setTitle("Gruppenaufteilung - Editieren Schüler");
        targetFrame.setSize(400, 150);
        // Erstelle die Eingabefelder
        JTextField vornameField = new JTextField(20);
        JTextField nachnameField = new JTextField(20);

        // Erstelle die Auswahl für Klasse
        String[] klassen = {"5a", "5b", "6a", "6b", "7a", "7b", "8a", "8b", "9a", "9b", "10a", "10b"};
        JComboBox<String> klasseComboBox = new JComboBox<String>(klassen);

        // Erstelle die Buttons
        JButton speichernButton = new JButton("Speichern");
        JButton zurueckButton = new JButton("Zurück");

        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showManagement(targetFrame);
            }
        });

        // Füge die Komponenten zum Layout hinzu
        JPanel contentPane = new JPanel(new BorderLayout());

        JPanel centerPane = new JPanel(new GridLayout(3, 1));
        centerPane.add(new JLabel("Vorname"));
        centerPane.add(vornameField);
        centerPane.add(new JLabel("Nachname"));
        centerPane.add(nachnameField);
        centerPane.add(new JLabel("Klasse"));
        centerPane.add(klasseComboBox);
        contentPane.add(centerPane, BorderLayout.CENTER);

        JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPane.add(zurueckButton);
        buttonPane.add(speichernButton);
        contentPane.add(buttonPane, BorderLayout.SOUTH);

        targetFrame.setContentPane(contentPane);

        // Setze die Größe des Fensters und mache es sichtbar
        targetFrame.setLocationRelativeTo(null);
        targetFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        targetFrame.setVisible(true);
    }

    private void showEditKlasse(JFrame targetFrame) {
        targetFrame.setTitle("Gruppenaufteilung - Editieren Klasse");
        targetFrame.setSize(new Dimension(300, 100));
        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelBezeichnung = new JLabel("Bezeichnung:");
        JTextField textFieldBezeichnung = new JTextField(20);
        inputPanel.add(labelBezeichnung);
        inputPanel.add(textFieldBezeichnung);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton buttonSpeichern = new JButton("Speichern");
        JButton buttonZurueck = new JButton("Zurück");

        buttonZurueck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showManagement(targetFrame);
            }
        });

        buttonPanel.add(buttonZurueck);
        buttonPanel.add(buttonSpeichern);

        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        targetFrame.setContentPane(panel);
        targetFrame.setLocationRelativeTo(null); // Fenster zentrieren
        targetFrame.setVisible(true);
    }

    private void showEditLehrer(JFrame targetFrame) {
        targetFrame.setTitle("Gruppenaufteilung - Editieren Lehrer");
        targetFrame.setSize(400, 175);
        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.add(new JLabel("Vorname:"));
        JTextField vornameField = new JTextField();
        inputPanel.add(vornameField);
        inputPanel.add(new JLabel("Nachname:"));
        JTextField nachnameField = new JTextField();
        inputPanel.add(nachnameField);
        inputPanel.add(new JLabel("Klasse:"));
        JComboBox<String> klasseCombo = new JComboBox<>(new String[]{"Klasse A", "Klasse B", "Klasse C"});
        inputPanel.add(klasseCombo);
        inputPanel.add(new JLabel("Passwort:"));
        JPasswordField passwortField = new JPasswordField();
        inputPanel.add(passwortField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton speichernButton = new JButton("Speichern");
        JButton zurueckButton = new JButton("Zurück");

        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showManagement(targetFrame);
            }
        });

        buttonPanel.add(zurueckButton);
        buttonPanel.add(speichernButton);
        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        targetFrame.setContentPane(panel);
        targetFrame.setLocationRelativeTo(null); // Fenster zentrieren
        targetFrame.setVisible(true);
    }

    private void showManagement(JFrame targetFrame) {
        JMenuBar menuBar;

        targetFrame.getContentPane().removeAll();

        targetFrame.setSize(800, 600);
        targetFrame.setResizable(false);
        targetFrame.setIconImage(new ImageIcon(getClass().getResource("Gruppeneinteilung_Icon.png")).getImage());
        targetFrame.setTitle("Gruppeneinteilung - Verwaltung");
        targetFrame.setLocationRelativeTo(null);
        targetFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        targetFrame.setLayout(new GridBagLayout());

        // Menubar
        menuBar = new JMenuBar();
        JMenu menuItem = new JMenu("Menü");
        JMenu aufteilungItem = new JMenu("Aufteilung");
        JMenu verwaltungItem = new JMenu("Verwaltung");
        JMenu hilfeItem = new JMenu("Hilfe");
        menuBar.add(menuItem);
        menuBar.add(aufteilungItem);
        menuBar.add(verwaltungItem);
        menuBar.add(hilfeItem);
        targetFrame.setJMenuBar(menuBar);
        JLabel formTitle = new JLabel("Verwaltung");
        JButton buttonLeft = new JButton("Editieren Schüler");
        JButton buttonCenter = new JButton("Editieren Lehrer");
        JButton buttonRight = new JButton("Editieren Klasse");

        buttonLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEditSchueler(targetFrame);
                if(SessionController.validateSession() == true) {
                    //showGruppeneinteilung(targetFrame);
                }else{
                    //JOptionPane.showMessageDialog(null, "Ungültige Sitzung, bitte erneut anmelden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    //showLogIn(targetFrame);
                }
            }
        });

        buttonRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEditKlasse(targetFrame);
                if(SessionController.validateSession() == true) {
                    //showGruppeneinteilung(targetFrame);
                }else{
                    //JOptionPane.showMessageDialog(null, "Ungültige Sitzung, bitte erneut anmelden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    //showLogIn(targetFrame);
                }
            }
        });

        buttonCenter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEditLehrer(targetFrame);
                if(SessionController.validateSession() == true) {
                    //showGruppeneinteilung(targetFrame);
                }else{
                    //JOptionPane.showMessageDialog(null, "Ungültige Sitzung, bitte erneut anmelden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    //showLogIn(targetFrame);
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
}