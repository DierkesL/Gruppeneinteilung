import org.json.JSONObject;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.*;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDAO {

    public JSONObject readConfigJSON() {
        try {
            File configFile = new File(getClass().getResource("config.json").toURI());
            BufferedReader buffer = new BufferedReader(new FileReader(configFile));
            String configFileContent = "";
            String bufferString = "";

            while((bufferString = buffer.readLine()) != null) {
                if(configFileContent != "") {
                    configFileContent += "\n" + bufferString;
                }else{
                    configFileContent += bufferString;
                }
            }

            return new JSONObject(configFileContent);
        } catch(IOException | URISyntaxException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    public JSONObject getConfigDatabaseCredentials(JSONObject configJSON) {
        return new JSONObject(configJSON.get("database").toString());
    }

    public DataSource createDataSource(JSONObject configDatabase) {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl(configDatabase.get("db_url").toString());
        ds.setPortNumber((int)configDatabase.get("db_port"));
        ds.setDatabaseName(configDatabase.get("db_name").toString());
        ds.setUser(configDatabase.get("db_user").toString());
        ds.setPassword(configDatabase.get("db_password").toString());
        ds.setSslMode(configDatabase.get("db_sslmode").toString());

        try{
            if(ds.getConnection() != null) {
                System.out.println();
                return ds;
            } else {
                System.out.println("Error: DB Connection failed.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void testQuery() {
        DataSource ds = createDataSource(getConfigDatabaseCredentials(readConfigJSON()));

        try {
            Connection conn = ds.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM schueler");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                System.out.println(rs.getString("vorname"));
            }
        } catch(SQLException ex) {
            System.out.println(ex);
        }
    }
}
