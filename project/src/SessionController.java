import javax.management.RuntimeErrorException;
import javax.sql.DataSource;
import java.sql.SQLException;

public class SessionController {
    public static DataSource SessionDataSource = null;

    public boolean initializeSession(String username, String password) {
        username = username.trim();

        try {
            DataSource ds = new BaseDAO().dbConnector(username, password);

            if(ds != null)
            {
                SessionDataSource = ds;
                return true;
            }

        } catch(Exception ex) {
            throw new RuntimeErrorException(new Error(), ex.getMessage());
        }

        return false;
    }

    public static boolean validateSession() {
        try {
            if(SessionDataSource != null) {
                if(SessionDataSource.getConnection() != null) {
                    return true;
                }
            }
        }catch(Exception ex) {
            return false;
        }

        return false;
    }

    public static void destroySession() {
        SessionDataSource = null;
    }

}
