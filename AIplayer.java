
/**
 * Implments a simple AI player to 
 * automatically contol the tiger moves
 *
 * @author Professor Ajmal Mian 
 * @dated Sep 2021
 * @version 1.0 
 */
//import java.util.Random;
import java.util.*;

public class AIplayer
{
    private Random rn; // for random tiger or location selection
    private GameRules rul; // an instance of GameRules to check for legal moves
    private int[] tigerLocs; // location of tigers for convenience 
    private int ntigers; // number of tigers placed
    
    /**
     * Constructor for objects of class AIplayer.
     * Initializes instance variables.
     */
    public AIplayer()
    {
        // TODO 14
        this.ntigers = 0;
        rn = new Random();
        rul = new GameRules();
        this.tigerLocs = new int[24];
        
    }

    /**
     * Place tiger in a random vacant location on the Board
     * Update the board, the tiger count and tigerLocs.
     */
    public void placeTiger(Board bd)
    {
        //TODO 15
        int i = 0;

        while (i != 3) {
            System.out.println("Tigers: " + ntigers);
            int selectedLocation = rn.nextInt(24);
            System.out.println(selectedLocation);
            if (ntigers == 3) {
                break;
            }
            if (bd.isVacant(selectedLocation)) {
                ntigers++;
                bd.setTiger(selectedLocation);
                tigerLocs[i] = selectedLocation;
                i++;
            }
        }
    }
    
    /**
     * If possible to eat a goat, must eat and return 1
     * If cannot eat any goat, make a move and return 0
     * If cannot make any move (all Tigers blocked), return -1
     */
    public int makeAmove(Board bd)
    {
        if (eatGoat(bd))  return 1; // did eat a goat
        else if (simpleMove(bd)) return 0; // made a simple move
        else return -1; // could not move
    }
    
    /**
     * Randomly choose a tiger, move it to a legal destination and return true
     * if none of the tigers can move, return false.
     * Update the board and the tiger location (tigerLocs)
     */
    private boolean simpleMove(Board bd)
    {
        //TODO 21

        System.out.println("Tiger 1 is at location " + tigerLocs[0]);
        System.out.println("Tiger 2 is at location " + tigerLocs[1]);
        System.out.println("Tiger 3 is at location " + tigerLocs[2]);

        for (int i = 0; i < 3; i++) {
            int randomLocation = tigerLocs[i];
            System.out.println("Checking tiger " + i + " simple moves");
            for (int j = 0; j < 24; j++) {
                System.out.println(rul.isLegalMove(randomLocation, j));
                if (rul.isLegalMove(randomLocation, j)) {
                    if (bd.isVacant(j)) {
                        System.out.println("Tiger " + i + " is moving");
                        System.out.println("Moving from " + randomLocation + " To " + j);
                        bd.swap(randomLocation, j);
                        tigerLocs[0] = j;
                        return true;
                    } else {
                        System.out.println("Found a location but not vacant");
                    }
                }
            }
        }

        System.out.println("We are checking simple move for tiger");

        return false; 
    }
    
    /**
     * If possible, eat a goat and return true otherwise return false.
     * Update the board and the tiger location (tigerLocs)
     * 
     * Hint: use the canEatGoat method of GameRules
     */
    private boolean eatGoat(Board bd)
    {
        //TODO 22

        // Cycle through where the goats are
        // Check each tiger can't eat a goat

        int[] scapegoat = new int[2]; // Holds locations of all goats currently on board

        /*
        for (int i = 0; i < 24; i++) { // Cycles through all locations and puts 1 where goats are
            if (bd.isGoat(i)) {
                scapegoat[i] = 1;
                System.out.println("Goat at: " + i);
            } else {
                scapegoat[i] = 0;
            }
        }
        */

        for (int i = 0; i < 3; i++) { // Checks each tiger
            int randomLoc = tigerLocs[i]; // randomLoc refers to tiger (i) location
            for (int j = 0; j < 24; j++) {
                System.out.println("Checking if we can eat a goat atm...");
                if(rul.canEatGoat(randomLoc, bd, scapegoat)) {
                    System.out.println("Can eat a goat here");
                    System.out.println("Goat eaten at loc: " + scapegoat[0] + " tiger jumped to loc: " + scapegoat[1]);
                    // Set tiger to new location
                    // Set goat to vacant location
                    // Minus a goat from the count
                    // Update tigerLocs[]
                }


            }
        }

        return false;
    }
   
}
