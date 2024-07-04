package resources.testData;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import java.io.FileReader;
import java.io.IOException;

public class ContactUsData {
    private WebDriver driver;
    private String name;
    private String emailAddress;
    private String subject;
    private String message;

    public ContactUsData(WebDriver driver){
        this.driver=driver;
    }
    public void contactUsDataProvider() throws IOException, ParseException {
        JSONParser jsonParser=new JSONParser();
        FileReader reader=new FileReader("src/test/java/resources/testData/contactUsData.json");
        JSONObject jsonObject= (JSONObject) jsonParser.parse(reader);
        name=(String) jsonObject.get("name");
        emailAddress=(String) jsonObject.get("emailAddress");
        subject=(String) jsonObject.get("subject");
        message=(String) jsonObject.get("message");
    }
    public String getName(){
        return name;
    }
    public String getEmailAddress(){
        return emailAddress;
    }
    public String getSubject(){
        return subject;
    }
    public String getMessage(){
        return message;
    }
    public void print(){
        String name=getName();
        System.out.println(name);
    }
}
