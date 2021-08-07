package accounts;

public class EmployeePersonalData implements PersonalData{

    private String firstname;
    private String lastname;
    private String livingPlace;
    private String profileHeading;
    private String profileDescription;


    public EmployeePersonalData(String firstname, String lastname, String livingPlace,
                                String profileHeading, String profileDescription) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.livingPlace = livingPlace;
        this.profileHeading = profileHeading;
        this.profileDescription = profileDescription;
    }

    @Override
    public String getFirstName() {
        return firstname;
    }

    @Override
    public String getLastName() {
        return lastname;
    }

    @Override
    public void setLivingPlace(String country) {
        livingPlace=country;
    }

    @Override
    public String getLivingPlace() {
        return livingPlace;
    }

    @Override
    public void setProfileHeading(String heading) {
        profileHeading=heading;
    }

    @Override
    public String getProfileHeading() {
        return profileHeading;
    }

    @Override
    public void setProfileDescription(String description) {
        profileDescription=description;
    }

    @Override
    public String getProfileDescription() {
        return profileDescription;
    }
}
