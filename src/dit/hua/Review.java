package dit.hua;

public class Review {
    private String reviewerName;
    private int rating;
    private String comment;

    public Review(String reviewerName, int rating, String comment) {
        this.reviewerName = reviewerName;
        this.rating = rating;
        this.comment = comment;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
