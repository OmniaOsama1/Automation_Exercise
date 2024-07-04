package resources.testData;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

import java.io.FileReader;
import java.io.IOException;

public class AddToCartTestData {
    private WebDriver driver;
    public AddToCartTestData(WebDriver driver){
        this.driver=driver;
    }
    private String firstProductName;
    private String firstProductPrice;
    private String firstProductQuantity;
    private String firstProductTotalPrice;
    private String secondProductName;
    private String secondProductPrice;
    private String secondProductQuantity;
    private String secondProductTotalPrice;

    public void addToCartDataProvider() throws IOException, ParseException {
        JSONParser jsonParser=new JSONParser();
        FileReader reader=new FileReader("src/test/java/resources/testData/addToCartTestData.json");
        JSONObject obj = (JSONObject)jsonParser.parse(reader);
        JSONObject firstProduct= (JSONObject) obj.get("firstProduct");
        firstProductName= (String)firstProduct.get("productName");
        firstProductPrice=(String)firstProduct.get("productPrice");
        firstProductQuantity=(String)firstProduct.get("productQuantity");
        firstProductTotalPrice=(String)firstProduct.get("totalPrice");
        JSONObject secondProduct = (JSONObject) obj.get("secondProduct");
        secondProductName= (String)secondProduct.get("productName");
        secondProductPrice=(String)secondProduct.get("productPrice");
        secondProductQuantity=(String)secondProduct.get("productQuantity");
        secondProductTotalPrice= (String) secondProduct.get("totalPrice");
    }
    public String getFirstProductName(){
        return firstProductName;
    }
    public String getFirstProductQuantity(){
        return firstProductQuantity;
    }
    public String getFirstProductPrice(){
        return firstProductPrice;
    }
    public String getFirstProductTotalPrice(){
        return firstProductTotalPrice;
    }
    public String getSecondProductName(){
        return secondProductName;
    }
    public String getSecondProductQuantity(){
        return secondProductQuantity;
    }
    public String getSecondProductPrice(){
        return secondProductPrice;
    }
    public String geSecondProductTotalPrice(){
        return secondProductTotalPrice;
    }
}
