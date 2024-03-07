import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Movie {
    public static final String API_KEY = "a65d25bc";
    int ImdbVotes;
    ArrayList<String> actorsList;
    String rating;

    public Movie(ArrayList<String> actorsList, String rating, int ImdbVotes) {
        //Done --> (Write a proper constructor using the get_from_api functions)
//        Nothing to be initialized!
        this.actorsList = actorsList;
        this.rating = rating;
        this.ImdbVotes = ImdbVotes;
    }

    @SuppressWarnings("deprecation")
    /**
     * Retrieves data for the specified movie.
     *
     * @param title the name of the title for which MovieData should be retrieved
     * @return a string representation of the MovieData, or null if an error occurred
     */

    public String getMovieData(String title) throws IOException {
        URL url = new URL("https://www.omdbapi.com/?t=" + title + "&apikey=" + API_KEY);
        URLConnection Url = url.openConnection();
        Url.setRequestProperty("Authorization", "Key" + API_KEY);
        BufferedReader reader = new BufferedReader(new InputStreamReader(Url.getInputStream()));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        //handle an error if the chosen movie is not found
        return stringBuilder.toString();
    }

    public int getImdbVotesViaApi(String moviesInfoJson) {
        //Done --> (This function must change and return the "ImdbVotes" as an Integer)
        // NOTICE :: you are not permitted to convert this function to return a String instead of an int !!!
        int ImdbVotes = 0;
        JSONObject jo = new JSONObject(moviesInfoJson);
        ImdbVotes = Integer.parseInt(jo.getString("imdbVotes").replace(",", ""));
        return ImdbVotes;
    }

    public String getRatingViaApi(String moviesInfoJson) {
        //Done --> (This function must return the rating in the "Ratings" part
        // where the source is "Internet Movie Database")  -->
        String rating = "";
        JSONObject jo = new JSONObject(moviesInfoJson);
        JSONArray ja = jo.getJSONArray("Ratings");
        for (int i = 0; i < ja.length(); ++i) {
            JSONObject jo_nested = ja.getJSONObject(i);
            rating = rating + "Sᴏᴜʀᴄᴇ: " + jo_nested.getString("Source") + " Rᴀᴛᴇ: " + jo_nested.getString("Value") + "\n";
        }
        return rating;
    }

    public void getActorListViaApi(String movieInfoJson) {
        //Done --> (This function must return the "Actors" in actorsList)
        JSONObject jo = new JSONObject(movieInfoJson);
        System.out.println("Aᴄᴛᴏʀs:");
        System.out.println(jo.getString("Actors"));
    }
}