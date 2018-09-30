package model;

import net.thucydides.core.annotations.Step;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by gurpreetsingh on 10/21/2016.
 */
public class GetRequestModel {

    private Properties prop = new Properties();

    private static String LOCATORS_LOCATION = "/properties/";

    public static String httpResponse = "";

    public GetRequestModel() throws IOException {
        prop = new Properties();
        prop.load(GetRequestModel.class.getResourceAsStream(LOCATORS_LOCATION + "locators.properties"));
    }


    @Step
    public String sendHttpRequest(String Requesturl,String resource, String param, String value) throws JSONException, IOException {
        String separator = "?";
        String lineSeparater= "/";
        String URLF=Requesturl +lineSeparater+resource+ separator + param + "=" + value;
        URL url = new URL(URLF.trim());
        System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
      //  conn.setRequestProperty("Accept", "application/json");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Error code:" + conn.getResponseCode());
        }
        Scanner scan = new Scanner(url.openStream());
        httpResponse = new String();
        while (scan.hasNext())
            httpResponse += scan.nextLine();
        scan.close();
        conn.disconnect();
       // System.out.println("*******"+httpResponse);
        return httpResponse;

    }


}