package resources.testData;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

import java.io.FileReader;
import java.io.IOException;

public class AddReviewTestData {
    private WebDriver driver;
    public AddReviewTestData(WebDriver driver){
        this.driver=driver;
    }
    private String productName;
    private String name;
    private String emailAddress;
    private String reviewComment;
    private String reviewSuccessMessage;

    public void getAddReviewDataProvider() throws IOException, ParseException {
        JSONParser jsonParser=new JSONParser();
        FileReader reader=new FileReader("src/test/java/resources/testData/addReviewTestData.json");
        JSONObject obj= (JSONObject) jsonParser.parse(reader);
        productName=(String) obj.get("ProductName");
        name= (String) obj.get("Name");
        emailAddress = (String) obj.get("EmailAddress");
        reviewComment = (String) obj.get("ReviewComment");
        reviewSuccessMessage = (String) obj.get("ReviewMessage");
    }

    public String getProductName() {
        return productName;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public String getReviewSuccessMessage() {
        return reviewSuccessMessage;
    }
}
