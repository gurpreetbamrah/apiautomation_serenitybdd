package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import model.GetRequestModel;
import model.JsonHandler;
import net.thucydides.core.annotations.Steps;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.Properties;
import static junit.framework.TestCase.assertTrue;


public class DaftAPISteps {

    static Logger logger = Logger.getLogger(DaftAPISteps.class.getName());


    @Steps
    GetRequestModel getRequestModel;

   @Steps
    JsonHandler  jsonHandler;

    private Properties prop = new Properties();

    private static String LOCATORS_LOCATION = "/properties/";

    public DaftAPISteps() throws IOException {
        prop = new Properties();
        prop.load(DaftAPISteps.class.getResourceAsStream(LOCATORS_LOCATION + "locators.properties"));
    }


    @Given("I send http query request using ([^\\\"]*) ([^\\\"]*) and ([^\\\"]*) as ([^\\\"]*)$")
    public void whenISendHTTPRequest(String url,String resource,String param,String  value) throws Exception {
 getRequestModel.sendHttpRequest( prop.getProperty(url),resource,param,value);

    }


    @Then("I validate ([^\\\"]*) values as ([^\\\"]*) in response of webservice")
    public void thenIVerifyFieldValuesInResponse(String fields, String values) throws Exception {
        String[] fieldArray = fields.split("__");
        String[] valArray = values.split("__");
        if (valArray.length == fieldArray.length) {
                 logger.debug("Length:" +fieldArray.length +","+ valArray.length);
            for (int i = 0; i < valArray.length; i++) {
                          logger.info("value:" +fieldArray[i] +","+ valArray[i]);
               assertTrue(JsonHandler.isFieldValuePresentInJsonArray(GetRequestModel.httpResponse, fieldArray[i], valArray[i]));
            }
        }

    }

    @Then("I count ([^\\\"]*) occurrence in response of webservice as  (\\d+)$")
    public void thenICountOccurranceOfFieldInResponse(String fieldToCheck, int expValue) throws Exception{
        JsonHandler.countOccurranceOfFieldInResponse(GetRequestModel.httpResponse, fieldToCheck, expValue);

    }
}
