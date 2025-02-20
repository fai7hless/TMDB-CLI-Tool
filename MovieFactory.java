import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class MovieFactory {
    private static List<Movie> movies = new ArrayList<Movie>();
    
    
    
        public  JsonArray getMovies(String type){
    
            String uri;
            JsonArray arr = null;
    
            switch (type) {
                case "playing":
                    uri = "https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1";
                    break;
    
                case "popular":
                    uri = "https://api.themoviedb.org/3/movie/popular?language=en-US&page=1";
                    break;
                
                case "top":
                     uri = "https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1";
                    break;
    
                case "upcoming":
                    uri = "https://api.themoviedb.org/3/movie/upcoming?language=en-US&page=1";
                    break;
            
                default:
                    throw new IllegalArgumentException("Invalid type: " + type);
            }
            
            String apiKey = getAPIKey();

            try{
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .header("accept", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    arr = JsonParser.parseString(response.body()).getAsJsonObject().getAsJsonArray("results");
                    return arr;
                }else{
                    System.out.println("Error: " + response.statusCode());
                    return null;
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }

            return arr;
            
        }
    
        public static String getAPIKey(){
            Properties properties = new Properties();
            String apiKey = "";
            try (FileInputStream input = new FileInputStream("config.properties")) {
                properties.load(input);
                apiKey = properties.getProperty("api.key");
            } catch (IOException e) {
                System.err.println("Error reading configuration file: " + e.getMessage());
            }
            return apiKey;
        }
    
        public void createMovies(JsonArray arr){
            for (int i = 0; i < 5; i++) {
                String title = arr.get(i).getAsJsonObject().get("title").getAsString();
                String overview = arr.get(i).getAsJsonObject().get("overview").getAsString();
                String releaseDate = arr.get(i).getAsJsonObject().get("release_date").getAsString();
                Movie movie = new Movie(title, overview, releaseDate);
                movies.add(movie);
            }
        }
    
    public void displayMovies(){
        for(Movie movie : movies){
            System.out.println(movie);
            System.out.println();
        }
    }
}
