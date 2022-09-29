import javax.swing.*;
import java.awt.*;

public class Main {

    JFrame frame = new JFrame();

    public static void main(String[] args) {
        EventQueue.invokeLater(
                new Runnable() {
                    public void run() {
                        Main form = new Main();
                        form.frame.setVisible(true);
                    }
                }
        );
    }

    public Main() {
        initialize();
    }

    // Initialize UI
    private void initialize() {
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        JButton buttonLeft = new JButton("Links");
        JButton buttonCenter = new JButton("Mitte");
        JButton buttonRight = new JButton("Rechts");

        GridBagConstraints frameGridConstraints = new GridBagConstraints();

        frameGridConstraints.weightx = 0.5;
        frameGridConstraints.gridx = 0;
        frameGridConstraints.gridy = 0;
        frame.add(buttonLeft, frameGridConstraints);

        frameGridConstraints.weightx = 0.5;
        frameGridConstraints.gridx = 1;
        frameGridConstraints.gridy = 0;
        frame.add(buttonCenter, frameGridConstraints);

        frameGridConstraints.weightx = 0.5;
        frameGridConstraints.gridx = 2;
        frameGridConstraints.gridy = 0;
        frame.add(buttonRight, frameGridConstraints);
    }
}