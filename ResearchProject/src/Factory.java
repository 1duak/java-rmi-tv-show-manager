
public class Factory {
    public static TVShow createShow(String genre) {
        if (genre.equalsIgnoreCase("Thriller")) {
            return new ThrillerShow();
        } else {
            return new FantasyShow();
        }
    }
}
