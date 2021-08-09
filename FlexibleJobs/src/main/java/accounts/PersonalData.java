package accounts;

public class PersonalData {

    private String username;
    private String firstname;
    private String lastname;
    private String livingPlace;
    private String profileHeading;
    private String profileDescription;

    public PersonalData(String username, String firstname, String lastname, String livingPlace, String profileHeading, String profileDescription) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.livingPlace = livingPlace;
        this.profileHeading = profileHeading;
        this.profileDescription = profileDescription;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLivingPlace(String country) {
        this.livingPlace = country;
    }

    public String getLivingPlace() {
        return livingPlace;
    }

    public void setProfileHeading(String heading) {
        this.profileHeading = heading;
    }

    public String getProfileHeading() {
        return profileHeading;
    }

    public void setProfileDescription(String description) {
        this.profileDescription = description;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

}
