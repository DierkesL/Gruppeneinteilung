import javax.swing.*;
import java.awt.*;

public class Main {

    static FrameHandling frameHandler = new FrameHandling();
    static JFrame mainFrame = new JFrame();

    public static void main(String[] args) {
        EventQueue.invokeLater(
                new Runnable() {
                    public void run() {
                        frameHandler.startApplication(mainFrame);
                    }
                }
        );
    }
}