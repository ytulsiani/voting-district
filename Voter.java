/**
 * This class is designed to show a Voter
 * @author Yash Tulsiani
 * @version  1.0
 */
public class Voter {
    private String voterName;
    private Canidate[] canidateChoices = new Canidate[3];
    /**
     * @param voterName is the name of the voter
     * @param canidateOneName is the name of the voter's first choice
     * @param canidateTwoName is the name of the voter's second choice
     * @param canidateThreeName is the name of the voter's third choice
     */
    public Voter(String voterName, String canidateOneName, String
        canidateTwoName, String canidateThreeName) {
        this.voterName = voterName;
        Canidate canidateOne = new Canidate(canidateOneName, 3);
        Canidate canidateTwo = new Canidate(canidateTwoName, 2);
        Canidate canidateThree = new Canidate(canidateThreeName, 1);
        canidateChoices[0] = canidateOne;
        canidateChoices[1] = canidateTwo;
        canidateChoices[2] = canidateThree;
    }
    /**
     * @return the name of the canidate's first choice
     */
    public Canidate getFirst() {
        return canidateChoices[0];
    }
    /**
     * @return the name of the canidate's second choice
     */
    public Canidate getSecond() {
        return canidateChoices[1];

    }
    /**
     * @return the name of the canidate's third choice
     */
    public Canidate getThird() {
        return canidateChoices[2];

    }
    /**
     * @return the name of the voter
     */
    public String getName() {
        return voterName;
    }
    /**
     * @return a string representation of this class
     */
    public String toString() {
        return voterName;
    }
}