import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

class tmdbCLI {
    public static void main(String[] args){
        if(args[0].equals("--type")){
            String type = args[1];
            MovieFactory.getMovies(type);

        }else {
            throw new IllegalArgumentException("Invalid argument: " + args[1]);
        }
        }
        
}