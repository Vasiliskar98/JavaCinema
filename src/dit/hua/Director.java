package dit.hua;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Director {
    private String fullName;
    private String dateOfBirth;
    private String website;
    private List<Show> titles;
    private String id;

    public Director(String fullName, String dateOfBirth, String website) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.website = website;
        this.titles = new ArrayList<>();
        this.id = generateUniqueId();
    }

    public String getFullName() {
        return fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getWebsite() {
        return website;
    }

    public List<Show> getTitles() {
        return titles;
    }

    public void addTitle(Show show) {
        titles.add(show);
    }

    public String getId() {
        return id;
    }

    private String generateUniqueId() {
        // Generate a unique identification number for the director
        // Implementation specific to your requirements
        // You can use a combination of the director's name and other attributes to generate the ID
        // For example, you can concatenate the first few characters of the full name with a randomly generated number or a timestamp
        return "DIR" + UUID.randomUUID().toString();
    }
}
