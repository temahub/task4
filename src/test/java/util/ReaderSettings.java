package util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReaderSettings{
    private static final String BROWSER = "browser";
    private static final String URL = "url";

    public static String browserName;
    public static String urlName;

    public static void readSettingsFromJSONFile(String fileName) throws IOException, JSONException {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader
                (new FileInputStream(fileName), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);

        JSONObject jObject = new JSONObject(responseStrBuilder.toString());
        browserName = jObject.getString(BROWSER);
        urlName = jObject.getString(URL);
    }
}
