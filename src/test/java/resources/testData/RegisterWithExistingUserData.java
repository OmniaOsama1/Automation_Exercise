package resources.testData;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import java.io.FileReader;
import java.io.IOException;

public class RegisterWithExistingUserData {
    private WebDriver driver;
    private String name;
    private String emailAddress;
    public RegisterWithExistingUserData (WebDriver driver){
        this.driver=driver;
    }
    public void registerWithExistingUserDataProvider() throws IOException, ParseException {
        JSONParser jsonParser=new JSONParser();
        FileReader reader =new FileReader("src/test/java/resources/testData/registerWithExistingData.json");
        Object obj =jsonParser.parse(reader);
        JSONObject jsonObject= (JSONObject) obj;
        name= (String) jsonObject.get("name");
        emailAddress= (String) jsonObject.get("emailAddress");
    }
    public String getName(){
        return name;
    }
    public String getEmailAddress(){
        return emailAddress;
    }
}
