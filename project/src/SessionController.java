import javax.management.RuntimeErrorException;
import javax.sql.DataSource;
public class SessionController {
    public DataSource SessionDataSource = null;

    public boolean initializeSession(String username, String password) {
        username = username.trim();

        try {
            DataSource ds = new BaseDAO().createDataSource(username, password, new ApplicationConfig().getConfigJSON(getClass().getResource("config.json").toURI(), "database"));

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

    public void destroySession() {
        SessionDataSource = null;
    }

}
