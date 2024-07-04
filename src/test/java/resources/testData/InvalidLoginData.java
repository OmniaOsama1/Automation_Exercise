package resources.testData;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

import java.io.FileReader;
import java.io.IOException;

public class InvalidLoginData {
    private WebDriver driver;
    public InvalidLoginData(WebDriver driver){
        this.driver=driver;
    }
    private String emailAddress;
    private String password;
    public void invalidLoginDataProvider() throws IOException, ParseException {
        JSONParser jsonParser=new JSONParser();
        FileReader reader=new FileReader("src/test/java/resources/testData/invalidLoginData.json");
        JSONObject jsonObject= (JSONObject) jsonParser.parse(reader);
        emailAddress= (String) jsonObject.get("emailAddress");
        password= (String) jsonObject.get("password");
    }
    public String getInvalidLoginEmailAddress(){
        return emailAddress;
    }
    public String getInvalidLoginPassword(){
        return password;
    }
}
