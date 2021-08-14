package accounts;

public class Review {

    private final String from;
    private final String to;
    private final int points;
    private final int jobid;

    public Review(String from, String to, int points, int jobid) {
        this.from = from;
        this.to = to;
        this.points = points;
        this.jobid = jobid;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getPoints() {
        return points;
    }

    public int getJobid() {
        return jobid;
    }

    public boolean equals(Review rev) {
        return this.from.equals(rev.getFrom()) && this.to.equals(rev.getTo()) && this.points == rev.points && this.jobid == rev.jobid;
    }
}
