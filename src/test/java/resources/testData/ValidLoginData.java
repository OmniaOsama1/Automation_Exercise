package resources.testData;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import java.io.FileReader;
import java.io.IOException;

public class ValidLoginData {
    private WebDriver driver;
    public ValidLoginData(WebDriver driver){
        this.driver=driver;
    }
    private String emailAddress;
    private String password;
    public void validLoginDataProvider() throws IOException, ParseException {
        JSONParser jsonParser=new JSONParser();
        FileReader reader=new FileReader("src/test/java/resources/testData/validLoginData.json");
        JSONObject jsonObject= (JSONObject) jsonParser.parse(reader);
        emailAddress= (String) jsonObject.get("emailAddress");
        password= (String) jsonObject.get("password");
    }
    public String getValidLoginEmailAddress(){
        return emailAddress;
    }
    public String getValidLoginPassword(){
        return password;
    }
}
