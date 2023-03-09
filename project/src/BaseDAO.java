import org.json.JSONObject;
import org.postgresql.ds.PGSimpleDataSource;

import javax.management.RuntimeErrorException;
import javax.sql.DataSource;
import java.io.*;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDAO {

    public static DataSource SessionDataSource = null;

    public DataSource createDataSource(JSONObject configDatabase) {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl(configDatabase.get("db_url").toString());
        ds.setPortNumber((int)configDatabase.get("db_port"));
        ds.setDatabaseName(configDatabase.get("db_name").toString());
        ds.setUser(configDatabase.get("db_username").toString());
        ds.setPassword(configDatabase.get("db_password").toString());
        ds.setSslMode(configDatabase.get("db_sslmode").toString());

        try{
            if(ds.getConnection() != null) {
                System.out.println();
                return ds;
            } else {
                System.out.println("Error: DB Connection failed");
            }
        } catch (Exception ex) {
            throw new RuntimeErrorException(new Error(), ex.getMessage());
        }

        return null;
    }

    public boolean initializeDatabaseConnection() {
        try {
            DataSource ds = new BaseDAO().createDataSource(new ApplicationConfig().getConfigJSON(getClass().getResource("config.json").toURI(), "database"));

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

    public static boolean validateDatabaseConnection() {
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

    public void testQuery() {
        /*
        try {
           DataSource ds = createDataSource(new ApplicationConfig().getConfigJSON(getClass().getResource("config.json").toURI(), "database"));

            Connection conn = ds.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM schueler");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                System.out.println(rs.getString("vorname"));
            }

        } catch(SQLException | URISyntaxException ex) {
            System.out.println(ex);
        }
        */
    }
}
