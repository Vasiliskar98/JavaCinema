package dit.hua;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaEntertainmentApp {
    private List<Show> shows;
            private List<Actor> actors;
            private List<Director> directors;
            private User user;
            private Scanner scanner;

    public JavaEntertainmentApp() {
                shows = new ArrayList<>();
                actors = new ArrayList<>();
                directors = new ArrayList<>();
                user = new User();
                scanner = new Scanner(System.in);
            }

    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Welcome to JavaEntertainmentApp!");
            System.out.println("1. Show Entry");
            System.out.println("2. Show Renewal");
            System.out.println("3. Information Search");
            System.out.println("4. Favorite Actor/Director");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    showEntry();
                    break;
                case 2:
                    showRenewal();
                    break;
                case 3:
                    informationSearch();
                    break;
                case 4:
                    favoriteActorDirector();
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private void showEntry() {
        System.out.println("===== Show Entry =====");
        System.out.print("Enter the title of the show: ");
        String title = scanner.nextLine();
        System.out.print("Enter the year of projection: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter the genre(s) of the show (comma-separated): ");
        String[] genres = scanner.nextLine().split(",");
        System.out.print("Enter the country of production: ");
        String country = scanner.nextLine();
        System.out.print("Enter the name of the director: ");
        String directorName = scanner.nextLine();

        String[] directorNames = directorName.split(" ");
        String directorFirstName = directorNames[0];
        String directorLastName = directorNames[1];
        Director director = findDirector(directorFirstName, directorLastName);
        if (director == null) {
            System.out.print("Enter the date of birth of the director: ");
            String directorDob = scanner.nextLine();
            System.out.print("Enter the website of the director: ");
            String directorWebsite = scanner.nextLine();
            director = new Director(directorName, directorDob, directorWebsite);
            directors.add(director);
        }

        System.out.print("Enter the number of actors: ");
        int numActors = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        List<Actor> actorList = new ArrayList<>();
        for (int i = 0; i < numActors; i++) {
            System.out.println("Enter details for Actor " + (i + 1));
            System.out.print("Enter the first name of the actor: ");
            String actorFirstName = scanner.nextLine();
            System.out.print("Enter the last name of the actor: ");
            String actorLastName = scanner.nextLine();

            Actor actor = findActor(actorFirstName, actorLastName);
            if (actor == null) {
                System.out.print("Enter the date of birth of the actor: ");
                String actorDob = scanner.nextLine();
                System.out.print("Enter the website of the actor: ");
                String actorWebsite = scanner.nextLine();
                actor = new Actor(actorFirstName, actorLastName, actorDob, actorWebsite);
                actors.add(actor);
            }

            actorList.add(actor);
        }

        Show show = new Show(title, year, genres, country, director, actorList);

        System.out.print("Enter the duration of the show (in minutes): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        show.setDuration(duration);

        System.out.print("Enter the rating of the show (out of 10): ");
        double rating = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character
        show.setRating(rating);

        shows.add(show);

        System.out.println("Show entry successful!");
        System.out.println("Show details:");
        System.out.println(show);
        System.out.println("======================");
    }

    private void showRenewal() {
        System.out.println("===== Show Renewal =====");
        System.out.print("Enter the show ID or title: ");
        String searchInput = scanner.nextLine();

        Show show = findShow(searchInput);
        if (show == null) {
            System.out.println("Show not found.");
            return;
        }

        System.out.print("Enter the number of seasons: ");
        int seasons = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter the number of episodes per season: ");
        int episodesPerSeason = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter the year last aired (- if still playing): ");
        String lastAired = scanner.nextLine();

        show.setSeasons(seasons);
        show.setEpisodesPerSeason(episodesPerSeason);
        show.setLastAired(lastAired);

        System.out.println("Show renewal successful!");
    }

    private void informationSearch() {
        System.out.println("===== Information Search =====");
        System.out.print("Enter the show title or year of projection: ");
        String searchInput = scanner.nextLine();

        List<Show> searchResults = findShows(searchInput);

        if (searchResults.isEmpty()) {
            System.out.println("No matching shows found.");
            return;
        }

        for (Show show : searchResults) {
            System.out.println("Show ID: " + show.getId());
            System.out.println("Title: " + show.getTitle());
            System.out.println("Year of Projection: " + show.getYear());
            System.out.println("Director: " + show.getDirector().getFullName());
            System.out.println("Average User Rating: " + show.getAverageRating());
            if (show.getType() == ShowType.SERIES || show.getType() == ShowType.MINI_SERIES) {
                System.out.println("Seasons: " + show.getSeasons());
                System.out.println("Last Aired: " + show.getLastAired());
            }
            System.out.println();
        }
    }

    private void favoriteActorDirector() {
        System.out.println("===== Favorite Actor/Director =====");
        System.out.print("Enter the first name of the actor/director: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter the last name of the actor/director: ");
        String lastName = scanner.nextLine();

        Actor actor = findActor(firstName, lastName);
        Director director = findDirector(firstName, lastName);

        if (actor == null && director == null) {
            System.out.println("Actor/Director not found.");
            return;
        }

        if (actor != null) {
            System.out.println("Actor Found!");
            System.out.println("ID: " + actor.getId());
            System.out.println("Name: " + actor.getFullName());
            System.out.println("Titles: ");
            for (Show show : actor.getTitles()) {
                System.out.println("Title: " + show.getTitle() + ", Average Rating: " + show.getAverageRating());
            }
            System.out.println("Show with highest average rating: ");
            Show highestRatedShow = getHighestRatedShow(actor.getTitles());
            if (highestRatedShow != null) {
                System.out.println("Title: " + highestRatedShow.getTitle() + ", Average Rating: " + highestRatedShow.getAverageRating());
            }
            System.out.println("Show with lowest average rating: ");
            Show lowestRatedShow = getLowestRatedShow(actor.getTitles());
            if (lowestRatedShow != null) {
                System.out.println("Title: " + lowestRatedShow.getTitle() + ", Average Rating: " + lowestRatedShow.getAverageRating());
            }
        }
        if (director != null) {
            System.out.println("Director Found!");
            System.out.println("ID: " + director.getId());
            System.out.println("Name: " + director.getFullName());
            System.out.println("Titles: ");
            for (Show show : director.getTitles()) {
                System.out.println("Title: " + show.getTitle() + ", Average Rating: " + show.getAverageRating());
            }
            System.out.println("Show with highest average rating: ");
            Show highestRatedShow = getHighestRatedShow(director.getTitles());
            if (highestRatedShow != null) {
                System.out.println("Title: " + highestRatedShow.getTitle() + ", Average Rating: " + highestRatedShow.getAverageRating());
            }
            System.out.println("Show with lowest average rating: ");
            Show lowestRatedShow = getLowestRatedShow(director.getTitles());
            if (lowestRatedShow != null) {
                System.out.println("Title: " + lowestRatedShow.getTitle() + ", Average Rating: " + lowestRatedShow.getAverageRating());
            }
        }

        System.out.print("Do you want to add this actor/director to your favorites? (Y/N): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            if (user.isLoggedIn()) {
                if (actor != null) {
                    user.addFavoriteActor(actor);
                }
                if (director != null) {
                    user.addFavoriteDirector(director);
                }
                System.out.println("Added to favorites successfully!");
            } else {
                System.out.println("You need to be logged in to add favorites.");
            }
        }
    }

// Helper methods to find shows, actors, directors

    private Show findShow(String title) {
        for (Show show : shows) {
            if (show.getTitle().equalsIgnoreCase(title)) {
                return show;
            }
        }
        return null;
    }

    private List<Show> findShows(String searchInput) {
        List<Show> searchResults = new ArrayList<>();
        for (Show show : shows) {
            if (show.getTitle().equalsIgnoreCase(searchInput) || String.valueOf(show.getYear()).equals(searchInput)) {
                searchResults.add(show);
            }
        }
        return searchResults;
    }

//    private Actor findActor(String firstName, String lastName) {
//        for (Actor actor : actors) {
//            if (actor.getFirstName().equals(firstName) && actor.getLastName().equals(lastName)) {
//                return actor;
//            }
//        }
//        return null;
//    }

    private Actor findActor(String firstName, String lastName) {
        for (Actor actor : actors) {
            if (actor.getFirstName().equalsIgnoreCase(firstName) && actor.getLastName().equalsIgnoreCase(lastName)) {
                return actor;
            }
        }
        return null;
    }


    private Director findDirector(String firstName, String lastName) {
        for (Director director : directors) {
            if (director.getFullName().equalsIgnoreCase(firstName + " " + lastName)) {
                return director;
            }
        }
        return null;
    }

    private Show getHighestRatedShow(List<Show> shows) {
        Show highestRatedShow = null;
        double highestRating = 0;
        for (Show show : shows) {
            if (show.getAverageRating() > highestRating) {
                highestRating = show.getAverageRating();
                highestRatedShow = show;
            }
        }
        return highestRatedShow;
    }

    private Show getLowestRatedShow(List<Show> shows) {
        Show lowestRatedShow = null;
        double lowestRating = 10;
        for (Show show : shows) {
            if (show.getAverageRating() < lowestRating) {
                lowestRating = show.getAverageRating();
                lowestRatedShow = show;
            }
        }
        return lowestRatedShow;
    }

}
