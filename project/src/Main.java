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
    }
}