import org.json.JSONObject;
import org.postgresql.ds.PGSimpleDataSource;

import javax.management.RuntimeErrorException;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.io.*;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;

public class BaseDAO {
    public static DataSource dbConnector(String username, String password){
        try {
            DataSource ds = createDataSource(username, password, new ApplicationConfig().getConfigJSON(BaseDAO.class.getResource("config.json").toURI(), "database"));
            return ds;
        }
        catch (Exception e){
            throw new RuntimeErrorException(new Error(), e.getMessage());
        }
    }
    private static DataSource createDataSource(String user, String password, JSONObject configDatabase) {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl(configDatabase.get("db_url").toString());
        ds.setPortNumber((int)configDatabase.get("db_port"));
        ds.setDatabaseName(configDatabase.get("db_name").toString());
        ds.setUser(user);
        ds.setPassword(password);
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


    public static ArrayList<String> getUsers(String username, String password) throws SQLException {
        try {
            ArrayList<String> users = new ArrayList<>();
            DataSource db = dbConnector(username, password);
            Connection con = db.getConnection();
            PreparedStatement sql = con.prepareStatement("select lehrer_id, password_encrypted from lehrer");
            ResultSet rs = sql.executeQuery();

            while (rs.next()){
                users.add(rs.getString(0)+rs.getString(1));
            }
            return users;
        }
        catch (Exception ex){
            throw new SQLException(ex.getMessage());
        }

    }
    public ArrayList<String> getCoursesFromTeacher(String username,String password ){
        try {
            ArrayList<String> courses = new ArrayList<>();
            DataSource db = dbConnector(username, password);
            Connection con = db.getConnection();
            PreparedStatement sql = con.prepareStatement("Select lernfeld_id from lernfeld_lehrer_klasse where lehrer_id = ?");
            sql.setString(1, username);

            ResultSet rs = sql.executeQuery();
            while (rs.next()){
                courses.add(rs.getString("lernfeld_id"));
            }
            return courses;
        }
        catch (Exception e){
            throw new RuntimeErrorException(new Error(), e.getMessage());
        }
    }

    public ArrayList<String> getStudentsFromCourses(String course, String username, String password){
        try {
            ArrayList<String> students = new ArrayList<>();
            DataSource db = dbConnector(username, password);
            Connection con = db.getConnection();
            PreparedStatement sql = con.prepareStatement("select schueler_id from lernfeld_schueler where lernfeld_id = ?");
            sql.setString(1, course);

            ResultSet rs = sql.executeQuery();
            while (rs.next()){
                students.add(rs.getString("schueler_id"));
            }
            return students;
        }
        catch (Exception e){
            throw new RuntimeErrorException(new Error(), e.getMessage());
        }


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
