import org.json.JSONArray;
import org.json.JSONObject;

import javax.print.attribute.standard.JobSheets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

public class Actors {
    public static final String API_KEY = "SJnsnffK6dwjYItrGqwW1w==MN2rJdFLbodbeAZI";   // Done --> add your api key about Actors here
    String netWorth;
    Boolean isAlive;

    public Actors(String netWorth, boolean isAlive){
//        Done --> (Write a proper constructor using the get_from_api functions)
//        Nothing to be initialized!
        this.netWorth = netWorth;
        this.isAlive = isAlive;
    }

    @SuppressWarnings({"deprecation"})
    /**
     * Retrieves data for the specified actor.
     * @param name for which Actor should be retrieved
     * @return a string representation of the Actors info or null if an error occurred
     */
    public String getActorData(String name) {
        try {
            URL url = new URL("https://api.api-ninjas.com/v1/celebrity?name=" +
                    name.replace(" ", "+") + "&apikey=" + API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Api-Key", API_KEY);
//            System.out.println(connection);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                return response.toString();
            } else {
                return "Error: " + connection.getResponseCode() + " " + connection.getResponseMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public double getNetWorthViaApi(String actorsInfoJson) {
        //Done --> (This function must return the "NetWorth")
        double result = 0.0;
        JSONArray ja = new JSONArray(actorsInfoJson);
        JSONObject jo = ja.getJSONObject(0);
        result = jo.getDouble("net_worth");
        return result;
    }

    public boolean isAlive(String actorsInfoJson) {
        //Done --> (If your chosen actor is alive it must return true otherwise it must return false)
        boolean status = false;
        JSONArray ja = new JSONArray(actorsInfoJson);
        JSONObject jo = ja.getJSONObject(0);
        status = jo.getBoolean("is_alive");
        return status;
    }

    public String getDateOfDeathViaApi(String actorsInfoJson) {
        //Done --> (If your chosen actor is deceased it must return the date of death)  -->
        String date = "";
        JSONArray ja = new JSONArray(actorsInfoJson);
        JSONObject jo = ja.getJSONObject(0);
        date = jo.getString("death");
        return date;
    }

}