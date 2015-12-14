import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.FlowPane;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.StackPane;
import java.util.HashSet;
import javafx.scene.shape.Circle;

/**
 * This is the testing class that makes sure the program functions correctly
 * @author Yash Tulsiani
 * @version 1.0
 */
public class DistrictTest extends Application {
    private static ArrayList<District> districtList = new ArrayList<>();
    private static ArrayList<LocalDistrict> localDistrictListForPane =
        new ArrayList<>();
    private static ArrayList<Voter> voterList = new ArrayList<>();
    private static HashSet<Canidate> canidateSet = new HashSet<>();
    private static ArrayList<String> newCanidateList = new ArrayList<>();
    private static ArrayList<String> canidateNameSet = new ArrayList<>();
    private static String[] strs = {"Kanye", "Bruce Wayne", "Darth Vader",
        "Tony Stark", "George Washington", "Vermin Supreme", "Spock",
        "Billy Ray", "Dad", "Ron Swanson", "Joey", "asdf", "asdfasdf",
        "asdfasdfasdf"};

    /**
     * @param args which are user arguments that do nothing
     */
    public static void main(String[] args) {
        File districtFile = new File("../resources/districts.txt");
        File localDistrictFile = new File(
            "../resources/local_districts.txt");
        File votersFile = new File("../resources/voters.txt");
        Scanner districtSc = null;
        Scanner localDistrictSc = null;
        Scanner votersSc = null;
        try {
            districtSc = new Scanner(districtFile);
            districtSc.nextLine();
            localDistrictSc = new Scanner(localDistrictFile);
            votersSc = new Scanner(votersFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //ArrayList<District> districtList = new ArrayList<>();
        //ArrayList<Voter> voterList = new ArrayList<>();
        while (votersSc.hasNextLine()) {
            String line = votersSc.nextLine();
            String[] voterParts = line.split("[:,]+");
            voterList.add(new Voter(voterParts[0].trim(), voterParts[1].trim(),
                voterParts[2].trim(), voterParts[3].trim()));
        }
        while (localDistrictSc.hasNextLine()) {
            ArrayList<Voter> localVoterList = new ArrayList<>();
            String line = localDistrictSc.nextLine();
            String[] localDistrictParts = line.split("[:,]+");
            String localDistrictName = localDistrictParts[0];
            for (int i = 0; i < localDistrictParts.length; i++) {
                String localDistrictPartsTrimmed = localDistrictParts[i].trim();
                for (Voter j : voterList) {
                    if (j.getName().equals(localDistrictPartsTrimmed)) {
                        localVoterList.add(j);
                    }
                }
            }
            districtList.add(new LocalDistrict(localDistrictName,
                localVoterList));
        }
        while (districtSc.hasNextLine()) {
            ArrayList<District> partDistrictList = new ArrayList<>();
            String line = districtSc.nextLine();
            String[] districtParts = line.split("[:,]+");
            String compositeDistrictName = districtParts[0];
            for (int i = 1; i < districtParts.length; i++) {
                String trimedDistrictParts = districtParts[i].trim();
                for (District j : districtList) {
                    if (j.getName().equals(trimedDistrictParts)) {
                        partDistrictList.add(j);
                    }
                }
            }
            districtList.add(new CompositeDistrict(compositeDistrictName,
                partDistrictList));
        }
        ArrayList<District> headDistricts = new ArrayList<District>();
        headDistricts.add(districtList.get(districtList.size() - 1));
        headDistricts.add(districtList.get(districtList.size() - 2));
        CompositeDistrict superDistrict = new CompositeDistrict("The world",
            headDistricts);
        System.out.println("The winner of this election is: "
            + superDistrict.getWinner());
        System.out.println(superDistrict.getWinner().getVotes());
        ArrayList<District> mergedDistrictList = new
            ArrayList<District>(mergeSort(districtList));
        System.out.println("Here is a list of districts sorted by the "
            + "canidate that won in alphabetical order: " + mergedDistrictList);
        launch(args);
    }
    /**
     * @param districtList is an arrayList of districts that need to be sorted
     * @return a sorted arrayList of districts
     */
    public static ArrayList<District> mergeSort(ArrayList<District>
        districtList) {
        if (districtList.size() == 1) {
            return districtList;
        } else {
            int center = ((districtList.size()) / 2);
            ArrayList<District> left = new ArrayList<>();
            ArrayList<District> right = new ArrayList<>();
            for (int i = 0; i < center; i++) {
                left.add(districtList.get(i));
            }
            for (int i = center; i < districtList.size(); i++) {
                right.add(districtList.get(i));
            }
            left = mergeSort(left);
            right = mergeSort(right);
            districtList = merge(right, left, districtList);
            return districtList;
        }

    }
    /**
     * @param left is the left side of the whole array
     * @param right is the right side of the whole array
     * @param whole array is the entire array
     * @return a sorted array
     */
    public static ArrayList<District> merge(ArrayList<District> left,
        ArrayList<District> right, ArrayList<District> whole) {
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if ((left.get(leftIndex).getWinner().toString().compareTo(right.get(
                rightIndex).getWinner().toString())) < 0) {
                whole.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                whole.set(wholeIndex, right.get(rightIndex));
                rightIndex++;
            }
            wholeIndex++;
        }
        ArrayList<District> rest;
        int restIndex;
        if (leftIndex >= left.size()) {
            rest = right;
            restIndex = rightIndex;
        } else {
            rest = left;
            restIndex = leftIndex;
        }
        for (int i = restIndex; i < rest.size(); i++) {
            whole.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }
        return whole;
    }
    /**
     * Runs the JavaFX Frame
     * @param stage is the stage of this
     */
    public void start(Stage stage) {
        VBox status = new VBox();

        for (int i = 0; i < districtList.size(); i++) {
            if (districtList.get(i) instanceof LocalDistrict) {
                localDistrictListForPane.add((LocalDistrict) districtList.
                    get(i));
            }
        }
        int counter = 0;
        //Pane canvas = new Pane();
        HBox entireFrame = new HBox();
        FlowPane map = new FlowPane();
        VBox key = new VBox();
        VBox canidateKey = new VBox();
        double populationOfVotes = ((voterList.size()) * 6);
        for (int j = 0; j < strs.length; j++) {
            newCanidateList.add(strs[j]);
        }
        for (int j = 0; j < newCanidateList.size(); j++) {
            //int colorCircle = newCanidateList.get(j);
            Circle voterCircle = new Circle(8.0, colorSetter(j));
            Text canName;
            if (newCanidateList.get(j).equals("Tony Stark")) {
                canName = new Text("Winner: " + newCanidateList.get(j));
            } else {
                canName = new Text(newCanidateList.get(j));
            }
            status.getChildren().addAll(voterCircle);
            canidateKey.getChildren().addAll(canName);
        }

        for (int i = 0; i < localDistrictListForPane.size(); i++) {
            FlowPane votersPane = new FlowPane();
            StackPane districtMap = new StackPane();
            Label districtName = new Label(localDistrictListForPane.get(i).
                getName());
            double widthHeight = ((localDistrictListForPane.get(i).getWeight()
                / populationOfVotes) * 3000);
            Rectangle districtRepresentation = new Rectangle(widthHeight,
                widthHeight);
            for (int j = 0; j < localDistrictListForPane.get(i).getVoterList().
                size(); j++) {
                Canidate voterChoice = localDistrictListForPane.get(i).
                    getVoterList().get(j).getFirst();
                int colorCircle = newCanidateList.indexOf(voterChoice.toString()
                    );
                Circle voterCircle = new Circle(5.0, colorSetter(colorCircle));
                votersPane.getChildren().add(voterCircle);
            }
            counter = newCanidateList.indexOf(localDistrictListForPane.get(i).
                getWinner().toString());
            districtRepresentation.setFill(colorSetter(counter));
            districtMap.getChildren().addAll(districtRepresentation,
                districtName, votersPane);
            map.getChildren().addAll(districtMap);
        }
        entireFrame.getChildren().addAll(map, status, canidateKey);
        stage.setScene(new Scene(entireFrame));
        stage.setTitle("Map");
        stage.show();

    }
    /**
     * Sets the color of the circle/rectangle
     * @param  counter is the canidate as an int
     * @return tbe color of the object being colored
     */
    public Color colorSetter(int counter) {
        if (counter == 0) {
            return Color.RED;
        } else if (counter == 1) {
            return Color.BLUE;
        } else if (counter == 2) {
            return Color.GREEN;
        } else if (counter == 3) {
            return Color.YELLOW;
        } else if (counter == 4) {
            return Color.CHOCOLATE;
        } else if (counter == 5) {
            return Color.GREY;
        } else if (counter == 6) {
            return Color.LAVENDERBLUSH;
        } else if (counter == 7) {
            return Color.LINEN;
        } else if (counter == 8) {
            return Color.PLUM;
        } else if (counter == 9) {
            return Color.PEACHPUFF;
        } else if (counter == 10) {
            return Color.SALMON;
        } else if (counter == 11) {
            return Color.VIOLET;
        } else if (counter == 12) {
            return Color.PAPAYAWHIP;
        } else if (counter == 13) {
            return Color.PINK;
        } else if (counter == 14) {
            return Color.PERU;
        } else if (counter == 15) {
            return Color.SALMON;
        } else if (counter == 16) {
            return Color.SIENNA;
        } else if (counter == 17) {
            return Color.PALEGREEN;
        } else {
            return Color.ORANGE;
        }
    }
}
