/**
 * abstract class that is a district where people vote
 * @author Yash Tulsiani
 * @version 1.0
 */
public abstract class District {
    private String districtName;
    /**
     * @param districtName which is the name of the district
     */
    public District(String districtName) {
        this.districtName = districtName;
    }
    /**
     * @return the name of the district
     */
    public String getName() {
        return districtName;
    }
    /**
     * @return the string representation of this class
     */
    public String toString() {
        return districtName;
    }
    /**
     * @return gives the Canidate that is the winner of this election
     */
    abstract Canidate getWinner();
    /**
     * @return gets the total number of votes in the election
     */
    abstract int getWeight();
    //boolean compareTo(Object o);
}