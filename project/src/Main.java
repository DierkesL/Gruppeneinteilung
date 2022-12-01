import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;

public class Main {

    static FrameHandling frameHandler = new FrameHandling();
    static BaseDAO db = new BaseDAO();
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