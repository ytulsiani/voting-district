/**
 * This class represents a canidate that voters can vote for
 * @author Yash Tulsiani
 * @version 1.0
 */
public class Canidate {
    private int votes;
    private String canidateName;
    /**
     * @param canidateName which is the name of the canidate that is running
     * @param votes is the number of votes he/she already has
     */
    public Canidate(String canidateName, int votes) {
        this.votes = votes;
        this.canidateName = canidateName;
    }
    /**
     * @return the name of the canidate
     */
    public String getName() {
        return canidateName;
    }
    /**
     * @return the number of votes a canidate has
     */
    public int getVotes() {
        return votes;
    }
    /**
     * @param numOfVotes is the number of votes you want to add to this canidate
     */
    public void addVotes(int numOfVotes) {
        votes = votes + numOfVotes;
    }
    /**
     * @param o which is object to compare with
     * @return if statement is true
     */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof Canidate) {
            if ((((Canidate) o).getName()).equals(this.getName())) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }
    /**
     * @return the hashcode of this Canidate
     */
    public int hashCode() {
        return votes;
    }
    /**
     * @return gives the string representation of this class
     */
    public String toString() {
        return canidateName;
    }
}