public class Movie {
    private String title;
    private String overview;
    private String releaseDate;

    public Movie(String title, String overview, String releaseDate){
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public String getTitle(){
        return title;
    }

    public String getOverview(){
        return overview;
    }

    public String getReleaseDate(){
        return releaseDate;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setOverview(String overview){
        this.overview = overview;
    }

    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString(){
        return "Title: " + title + "\nOverview: " + overview + "\nRelease Date: " + releaseDate;
    }
}
