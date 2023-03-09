import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;

public class Main {

    public static FrameHandling frameHandler = new FrameHandling();
    public static BaseDAO db = new BaseDAO();
    public static JFrame mainFrame = new JFrame();

    public static void main(String[] args) {
        EventQueue.invokeLater(
                new Runnable() {
                    public void run() {
                        if(db.initializeDatabaseConnection()) {
                            frameHandler.startApplication(mainFrame, db);
                        }else{
                            JOptionPane.showMessageDialog(null, "Es konnte keine Verbindung zur Datenbank aufgebaut werden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        );
    }
}