package resources.testData;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PaymentData {
    private WebDriver driver;
    private String nameOnCard;
    private String cardNumber;
    private String CVV;
    private String expirationMonth;
    private String expirationYear;

    public PaymentData(WebDriver driver){
        this.driver=driver;
    }
    public void paymentDataProvider() throws IOException, ParseException {
        JSONParser jsonparser=new JSONParser();
        FileReader reader=new FileReader("src/test/java/resources/testData/paymentData.json");
        JSONObject jsonObject = (JSONObject) jsonparser.parse(reader);
        nameOnCard= (String) jsonObject.get("Name on card");
        cardNumber= (String) jsonObject.get("Card Number");
        CVV = (String) jsonObject.get("CVV");
        expirationMonth= (String) jsonObject.get("Expiration month");
        expirationYear=(String) jsonObject.get("Expiration Year");
    }
    public String getNameOnCard(){
        return nameOnCard;
    }
    public String getCardNumber(){
        return cardNumber;
    }
    public String getCVV(){
        return CVV;
    }
    public String getExpirationMonth(){
        return expirationMonth;
    }
    public String getExpirationYear(){
        return expirationYear;
    }
}
