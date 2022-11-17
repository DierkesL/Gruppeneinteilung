import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDAO {
    public DataSource createDataSource() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl("jdbc:postgresql://db-grpeinteilung-3751.7tc.cockroachlabs.cloud/");
        ds.setPortNumber(26257);
        ds.setDatabaseName("db-grpeinteilung-3751.Gruppeneinteilung");
        ds.setUser("testuser");
        ds.setPassword("_jnQ_7PPPz7j_LJ5PSCbCg");
        ds.setSslMode("require");

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
        DataSource ds = createDataSource();

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
