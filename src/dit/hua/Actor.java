package dit.hua;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Actor {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String website;
    private String id;
    private List<Show> titles;

    public Actor(String firstName, String lastName, String dateOfBirth, String website) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.website = website;
        this.titles = new ArrayList<>();
        this.id = generateUniqueId();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getWebsite() {
        return website;
    }

    public String getId() {
        return id;
    }

    public List<Show> getTitles() {
        return titles;
    }

    public void addTitle(Show show) {
        titles.add(show);
    }

    private String generateUniqueId() {
        // Generate a unique identification number for the actor
        // Implementation specific to your requirements
        // You can use a combination of the actor's name and other attributes to generate the ID
        // For example, you can concatenate the first few characters of the first name and last name
        // with a randomly generated number or a timestamp
        return "ACT" + UUID.randomUUID().toString();
    }
}


