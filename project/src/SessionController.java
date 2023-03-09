import javax.management.RuntimeErrorException;
import javax.sql.DataSource;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionController {
    public static DataSource SessionDataSource = null;

    public boolean initializeSession(DataSource ds, String username, String password) {
        if(!SessionController.validateSession()) {
            try {
                String password_hashed = sha512(password, "ABS_2023_69");
                System.out.println("Password hashed: " + password_hashed);

                if(password_hashed != "") {
                    password = password_hashed;

                    Connection conn = ds.getConnection();
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM lehrer WHERE lehrer_benutzername = ? AND lehrer_password_encrypted  = ?");

                    stmt.setString(1, username);
                    stmt.setString(2, password);

                    ResultSet rs = stmt.executeQuery();

                    int i = 0;
                    while(rs.next()) {
                        i++;
                    }

                    // i > 0 then user is found with provided credentials
                    if(i > 0) {
                        return true;
                    }
                }
            } catch(Exception ex) {
                System.out.println(ex);
            }
        }

        return false;
    }

    public static boolean validateSession() {
        // Validate logged in user here
        return false;
    }

    public static String sha512(String _string, String _salt) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(_salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(_string.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();

            for(int j = 0; j < bytes.length; j++){
                sb.append(Integer.toString((bytes[j] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        }catch(Exception e){
            return "";
        }
    }

    public static void destroySession() {
        SessionDataSource = null;
    }

}
