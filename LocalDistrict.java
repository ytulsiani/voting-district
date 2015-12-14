import java.util.ArrayList;
/**
 * This is a district that represents the smallest form of a district
 * @author Yash Tulsiani
 * @version 1.0
 */
public class LocalDistrict extends District {
    private ArrayList<Voter> voterList = new ArrayList<>();
    private ArrayList<Canidate> canidateList = new ArrayList<>();
    /**
     * @param name which is the name of the district
     * @param voterList which is a list of voters in this district
     */
    public LocalDistrict(String name, ArrayList<Voter> voterList) {
        super(name);
        this.voterList = voterList;
    }
    /**
     * gets the canidateList
     * @return list of canidates
     */
    public ArrayList<Canidate> getCanidateList() {
        return canidateList;
    }
    /**
     * gets the voterList
     * @return list of voters
     */
    public ArrayList<Voter> getVoterList() {
        return voterList;
    }

    /**
     * @return the winner of this election
     */
    public Canidate getWinner() {
        for (Voter i : voterList) {
            if (canidateList.contains(i.getFirst())) {
                int index = canidateList.indexOf(i.getFirst());
                canidateList.get(index).addVotes(3);
            } else {
                canidateList.add(i.getFirst());
            }
            if (canidateList.contains(i.getSecond())) {
                int index = canidateList.indexOf(i.getSecond());
                canidateList.get(index).addVotes(2);
            } else {
                canidateList.add(i.getSecond());
            }
            if (canidateList.contains(i.getThird())) {
                int index = canidateList.indexOf(i.getThird());
                canidateList.get(index).addVotes(1);
            } else {
                canidateList.add(i.getThird());
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
     * @return the total number of voters
     */
    public int getWeight() {
        return voterList.size();
    }
}