package resources.testData;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

import java.io.FileReader;
import java.io.IOException;

public class RegistrationData {
    private WebDriver driver;
    private String name;
    private String emailAddress;
    private String gender;
    private String password;
    private String firstName;
    private String lastName;
    private String company;
    private String address;
    private String address2;
    private String country;
    private String state;
    private String city;
    private String zipCode;
    private String mobileNumber;

    public RegistrationData(WebDriver driver){
        this.driver=driver;
    }

    public void registerDataProvider() throws IOException, ParseException {
        JSONParser jsonParser=new JSONParser();
        FileReader reader =new FileReader("src/test/java/resources/testData/registerationData.json");
        Object obj =jsonParser.parse(reader);
        JSONObject jsonObject= (JSONObject) obj;
        name= (String) jsonObject.get("name");
        emailAddress= (String) jsonObject.get("emailAddress");
        gender= jsonObject.get("gender").toString();
        password= (String) jsonObject.get("password");
        firstName= (String) jsonObject.get("firstName");
        lastName= (String) jsonObject.get("lastName");
        company= (String) jsonObject.get("company");
        address= (String) jsonObject.get("address");
        address2= (String) jsonObject.get("address2");
        country=(String) jsonObject.get("Country");
        state= (String) jsonObject.get("state");
        city= (String) jsonObject.get("city");
        zipCode= (String) jsonObject.get("zipcode");
        mobileNumber= (String) jsonObject.get("mobileNumber");
    }
    public String getName(){
        return name;
    }
    public String getEmailAddress(){
        return emailAddress;
    }
    public String getPassword(){
        return password;
    }
    public String getGender(){return gender;}
    public String getFirstName(){
        return firstName;
    }public String getLastName(){
        return lastName;
    }
    public String getCompany(){
        return company;
    }
    public String getAddress(){
        return address;
    }
    public String getAddress2(){
        return address2;
    }
    public String getState(){
        return state;
    }
    public String getCountry(){
        return country;
    }
    public String getCity(){
        return city;
    }
    public String getZipCode(){
        return zipCode;
    }
    public String getMobileNumber(){
        return mobileNumber;
    }
}
