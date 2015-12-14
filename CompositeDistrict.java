import java.util.ArrayList;
/**
 * This is a district that represents smaller districts put together
 * @author Yash Tulsiani
 * @version 1.0
 */
public class CompositeDistrict extends District {
    private ArrayList<District> districtList = new ArrayList<>();
    private ArrayList<Canidate> canidateList = new ArrayList<>();
    /**
     * @param name which is the name of this district
     * @param districtList which is the list of subdistricts in this district
     */
    public CompositeDistrict(String name, ArrayList<District> districtList) {
        super(name);
        this.districtList = districtList;
    }
    /**
     * @return canidate that won this district's election
     */
    public Canidate getWinner() {
        for (District i : districtList) {
            if (canidateList.contains(i.getWinner())) {
                int index = canidateList.indexOf(i.getWinner());
                canidateList.get(index).addVotes(3);
            } else {
                canidateList.add(i.getWinner());
            }
        }
        int highest = 0;
        Canidate highestCanidate = null;
        for (Canidate i : canidateList) {
            if (i.getVotes() > highest) {
                highest = i.getVotes();
                highestCanidate = i;
            }
        }
        return highestCanidate;
    }
    /**
     * @return returns the total number of voters in this district
     */
    public int getWeight() {
        int weight = 0;
        for (District i : districtList) {
            weight += i.getWeight();
        }
        return weight;
    }
}