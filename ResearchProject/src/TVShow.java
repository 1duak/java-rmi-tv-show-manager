import java.io.Serializable;

public abstract class TVShow implements Serializable {
    private String title;
    private String genre;
    private int releaseYear;
    private int seasons;

    //getters and setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getSeasons() {
        return seasons;
    }
    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public String toString() {
        return "Title: " + title + ", Genre: " + genre + ", Year: " + releaseYear + ", Seasons: " + seasons;
    }
    public abstract String showType();

}
