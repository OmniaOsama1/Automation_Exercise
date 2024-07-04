package resources.testData;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

import java.io.FileReader;
import java.io.IOException;

public class ProductsCategoryAndSubcategoryTestData {
    private final WebDriver driver;
    public ProductsCategoryAndSubcategoryTestData(WebDriver driver){
        this.driver=driver;
    }
    private String womenCategory;
    private String menCategory;
    private String womenSubCategory;
    private String menSubCategory;
    private String womanCategoryPageTitle;
    private String navigatedManCategoryPage;
    public void getProductCategoryAndSubcategoryDataProvider() throws IOException, ParseException {
        JSONParser jsonParser=new JSONParser();
        FileReader reader=new FileReader("src/test/java/resources/testData/productsCategoryAndSubcategoryTestData.json");
        JSONObject object =(JSONObject)jsonParser.parse(reader);
        JSONObject  productCategory= (JSONObject) object.get("Category");
        womenCategory= (String) productCategory.get("WomenCategory");
        menCategory=(String) productCategory.get("MenCategory");
        JSONObject subCategories= (JSONObject) object.get("SubCategories");
        womenSubCategory= (String) subCategories.get("WomenSubCategory");
        menSubCategory=(String) subCategories.get("MenSubCategory");
        womanCategoryPageTitle= (String) object.get("WomanCategoryPageTitle");
        navigatedManCategoryPage= (String) object.get("NavigatedManCategoryPage");
    }
    public String getWomenCategory(){
        return womenCategory;
    }
    public String getMenCategory(){
        return menCategory;
    }
    public String getWomenSubCategory(){
        return womenSubCategory;
    }
    public String getMenSubCategory(){
        return menSubCategory;
    }
    public String getWomanCategoryPageTitle(){
        return womanCategoryPageTitle;
    }
    public String getNavigatedManCategoryPage(){
        return navigatedManCategoryPage;
    }
}
