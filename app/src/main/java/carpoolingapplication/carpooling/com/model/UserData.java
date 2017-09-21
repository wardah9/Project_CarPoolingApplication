package carpoolingapplication.carpooling.com.model;

/**
 * Created by wardah on 9/19/17.
 */

public class UserData {

    private static final UserData ourInstance = new UserData();

    public static UserData getInstance() {
        return ourInstance;
    }

    private UserData() {
    }
    public static UserData getOurInstance() {
        return ourInstance;
    }

    private String userName;
    private String userEmail;
    private String userPhone;
    private String userNationality;
    private String userGender;
    private String userAge;
    private String LicenceID;
    private String userFirebaseID;


    public void setUserData(String userName, String userEmail, String userPhone, String userNationality, String userGender, String userAge, String licenceID, String userFirebaseID) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userNationality = userNationality;
        this.userGender = userGender;
        this.userAge = userAge;
        LicenceID = licenceID;
        this.userFirebaseID = userFirebaseID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserNationality() {
        return userNationality;
    }

    public String getUserGender() {
        return userGender;
    }

    public String getUserAge() {
        return userAge;
    }

    public String getLicenceID() {
        return LicenceID;
    }

    public String getUserFirebaseID() {
        return userFirebaseID;
    }
}
