package dit.hua;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String email;
    private List<Actor> favoriteActors;
    private List<Director> favoriteDirectors;
    private boolean loggedIn;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.favoriteActors = new ArrayList<>();
        this.favoriteDirectors = new ArrayList<>();
        this.loggedIn = false;
    }

    public User() {
        // Default constructor implementation
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void login(String username, String password) {
        if (username.equals(this.username) && password.equals(this.password)) {
            loggedIn = true;
            System.out.println("Login successful. Welcome, " + username + "!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    public void logout() {
        loggedIn = false;
        System.out.println("Logout successful. Goodbye, " + username + "!");
    }

    public void addFavoriteActor(Actor actor) {
        favoriteActors.add(actor);
        System.out.println(actor.getFullName() + " added to favorites.");
    }

    public void removeFavoriteActor(Actor actor) {
        favoriteActors.remove(actor);
        System.out.println(actor.getFullName() + " removed from favorites.");
    }

    public void addFavoriteDirector(Director director) {
        favoriteDirectors.add(director);
        System.out.println(director.getFullName() + " added to favorites.");
    }

    public void removeFavoriteDirector(Director director) {
        favoriteDirectors.remove(director);
        System.out.println(director.getFullName() + " removed from favorites.");
    }
}
