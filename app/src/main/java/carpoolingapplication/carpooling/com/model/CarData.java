package carpoolingapplication.carpooling.com.model;

/**
 * Created by wardah on 9/20/17.
 */

public class CarData {

    private static final CarData ourInstance = new CarData();

    public static CarData getInstance() {
        return ourInstance;
    }

    private CarData() {
    }
    public static CarData getOurInstance() {
        return ourInstance;
    }

    private String carBranch;
    private String carType;
    private String passengersNo;
    private String gender;
    private String userGender;
    private String userAge;
    private String LicenceID;
    private String userFirebaseID;



}
