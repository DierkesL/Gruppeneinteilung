import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    JFrame frameLogIn = new JFrame();

    public static void main(String[] args) {
        EventQueue.invokeLater(
                new Runnable() {
                    public void run() {
                        Main form = new Main();
                        form.frameLogIn.setVisible(true);
                    }
                }
        );
    }

    public Main() {
        showLogIn();
    }

    // UI Stuff

    private void showLogIn() {
        //JFrame frameLogIn = new JFrame();

        frameLogIn.setSize(500, 200);
        frameLogIn.setResizable(false);
        frameLogIn.setTitle("Login");
        frameLogIn.setLocationRelativeTo(null);
        frameLogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogIn.setLayout(new GridBagLayout());

        JLabel formTitle = new JLabel("Gruppeneinteilung Login", SwingConstants.CENTER);
        JLabel usernameLabel = new JLabel("Benutzername:", SwingConstants.CENTER);
        JTextField usernameInput = new JTextField();
        JLabel passwordLabel = new JLabel("Password:", SwingConstants.CENTER);
        JPasswordField passwordInput = new JPasswordField();
        JButton submitButton = new JButton("Login");
        JButton resetButton = new JButton("Zurücksetzen");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkCredentials(usernameInput.getText(), passwordInput);
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
        frameLogIn.add(formTitle, frameGridConstraints);

        frameGridConstraints.gridwidth = 1;
        frameGridConstraints.anchor = GridBagConstraints.CENTER;
        frameGridConstraints.weightx = 0.5;
        frameGridConstraints.weighty = 1;
        frameGridConstraints.gridx = 0;
        frameGridConstraints.gridy = 2;
        frameLogIn.add(usernameLabel, frameGridConstraints);

        frameGridConstraints.anchor = GridBagConstraints.CENTER;
        frameGridConstraints.weightx = 2;
        frameGridConstraints.weighty = 1;
        frameGridConstraints.gridx = 1;
        frameGridConstraints.gridy = 2;
        frameLogIn.add(usernameInput, frameGridConstraints);

        frameGridConstraints.anchor = GridBagConstraints.CENTER;
        frameGridConstraints.weightx = 0.5;
        frameGridConstraints.weighty = 1;
        frameGridConstraints.gridx = 0;
        frameGridConstraints.gridy = 3;
        frameLogIn.add(passwordLabel, frameGridConstraints);

        frameGridConstraints.anchor = GridBagConstraints.CENTER;
        frameGridConstraints.weightx = 2;
        frameGridConstraints.weighty = 1;
        frameGridConstraints.gridx = 1;
        frameGridConstraints.gridy = 3;
        frameLogIn.add(passwordInput, frameGridConstraints);

        frameGridConstraints.fill = GridBagConstraints.HORIZONTAL;
        frameGridConstraints.ipady = 10;
        frameGridConstraints.gridx = 0;
        frameGridConstraints.gridy = 4;
        frameLogIn.add(submitButton, frameGridConstraints);

        frameGridConstraints.fill = GridBagConstraints.HORIZONTAL;
        frameGridConstraints.ipady = 10;
        frameGridConstraints.gridx = 1;
        frameGridConstraints.gridy = 4;
        frameLogIn.add(resetButton, frameGridConstraints);

        frameLogIn.setVisible(true);
    }

    private void showMainMenu() {
        JFrame frame = new JFrame();

        frame.setSize(800, 600);
        frame.setResizable(false);
        frameLogIn.setTitle("Gruppeneinteilung");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

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
        frame.add(formTitle, frameGridConstraints);

        frameGridConstraints.weighty = 0.1;
        frameGridConstraints.gridx = 0;
        frameGridConstraints.gridy = 1;
        buttonLeft.setPreferredSize(new Dimension(200, 50));
        frame.add(buttonLeft, frameGridConstraints);

        frameGridConstraints.weighty = 0.1;
        frameGridConstraints.weightx = 0;
        frameGridConstraints.gridx = 1;
        frameGridConstraints.gridy = 1;
        buttonCenter.setPreferredSize(new Dimension(200, 50));
        frame.add(buttonCenter, frameGridConstraints);

        frameGridConstraints.weightx = 0;
        frameGridConstraints.gridx = 2;
        frameGridConstraints.gridy = 1;
        buttonRight.setPreferredSize(new Dimension(200, 50));
        frame.add(buttonRight, frameGridConstraints);

        frame.setVisible(true);
    }

    private void checkCredentials(String username, JPasswordField password) {
        String passwordFull = "";

        for(char passwordChar : password.getPassword()) {
            passwordFull = passwordFull + passwordChar;
        }

        // Password check via database still needs to be implemented
        if(username.equals("Test") && passwordFull.equals("1234")) {
            showMainMenu();
            frameLogIn.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Falsche Login-Daten!" + passwordFull ,"Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showGruppeneinteilung() {
        // Code from Lucas here when ready

    }
}