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
                        db.testQuery();

                        try {
                            System.out.println(new ApplicationConfig().getConfigJSON(getClass().getResource("config.json").toURI(), "default").toString());
                        } catch (URISyntaxException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
    }
}