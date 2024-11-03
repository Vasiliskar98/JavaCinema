package dit.hua;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Show {
    private String id; // New field for show ID
    private String title;
    private int year;
    private String[] genres;
    private String country;
    private Director director;
    private List<Actor> actors;
    private ShowType type;
    private int seasons;
    private int episodesPerSeason;
    private String lastAired;
    private List<Review> reviews;
    private int duration;
    private double rating;

    public Show(String title, int year, String[] genres, String country, Director director, List<Actor> actors) {
        this.id = generateUniqueId(); // Generate a unique ID for the show
        this.title = title;
        this.year = year;
        this.genres = genres;
        this.country = country;
        this.director = director;
        this.actors = actors;
        this.type = ShowType.SINGLE;
        this.seasons = 1;
        this.episodesPerSeason = 1;
        this.lastAired = "-";
        this.reviews = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String[] getGenres() {
        return genres;
    }

    public String getCountry() {
        return country;
    }

    public Director getDirector() {
        return director;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public ShowType getType() {
        return type;
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
        if (seasons > 1) {
            this.type = ShowType.SERIES;
        } else {
            this.type = ShowType.SINGLE;
        }
    }

    public int getEpisodesPerSeason() {
        return episodesPerSeason;
    }

    public void setEpisodesPerSeason(int episodesPerSeason) {
        this.episodesPerSeason = episodesPerSeason;
    }

    public String getLastAired() {
        return lastAired;
    }

    public void setLastAired(String lastAired) {
        this.lastAired = lastAired;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public double getAverageRating() {
        if (reviews.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (Review review : reviews) {
            sum += review.getRating();
        }

        return sum / reviews.size();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    private String generateUniqueId() {
        return "SHOW-" + UUID.randomUUID().toString(); // Generate a unique ID for the show
    }
}
