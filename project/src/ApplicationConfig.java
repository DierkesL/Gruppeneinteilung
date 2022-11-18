import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

public class ApplicationConfig {

    public JSONObject getConfigJSON(URI configPath, String configContext) {
        try {
            File configFile = new File(configPath);
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

            JSONObject configJSON = new JSONObject(configFileContent);

            if(configContext != "default") {
                return new JSONObject(configJSON.get(configContext).toString());
            } else {
                return new JSONObject(configFileContent);
            }

        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }
}
