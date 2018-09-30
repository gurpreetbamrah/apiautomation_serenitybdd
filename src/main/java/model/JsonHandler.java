package model;


import net.thucydides.core.annotations.Step;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by gurpreetsingh on 10/21/2016.
 */
public class JsonHandler {

    static Logger logger = Logger.getLogger(JsonHandler.class.getName());

    @Step
    public static boolean isFieldValuePresentInJsonArray(String json, String field, String value) {

        // Create an objectmapper
        ObjectMapper mapper = new ObjectMapper();
        boolean found = false;

        try {
            JsonNode rootNode = mapper.readTree(json);
            List<String> foundValues = rootNode.findValuesAsText(field);
            for(String listValue : foundValues) {
                if(listValue.contains(value)) {
                    found = true;
                }
            }

        } catch (JsonGenerationException e) {
            logger.warn(e.getMessage());
        } catch (JsonMappingException e) {
            logger.warn(e.getMessage());
        } catch (IOException e) {
           logger.warn(e.getMessage());
        }

        // Return whether the value was found or not
        return found;
    }

    @Step
    public static void countOccurranceOfFieldInResponse(String json, String field, int expValue) {
        int len = json.split(field).length - 1;
       assertTrue(len == expValue);

    }


}
