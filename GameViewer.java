
/**
 * Controls the drawing of the board and game play.
 * Allows the human player to make goat moves. 
 * Calls AIplayer to make tiger moves. 
 *
 * @Student 1 Name:
 * @Student 1 Number:
 *
 * @Student 2 Name:
 * @Student 2 Number: 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;

public class GameViewer implements MouseListener
{
    // instance variables
    private int bkSize; // block size - all other measurements to be derived from bkSize
    private int brdSize; // board size
    private SimpleCanvas sc; // an object of SimpleCanvas to draw
    private GameRules rules; // an object of GameRules
    private Board bd; // an object of Board
    private AIplayer ai; //an object of AIplayer

    // 2D coordinates of valid locations on the board in steps of block size
    public static final int[][] locs = {{1,1},                  {4,1},                  {7,1},

                                                {2,2},          {4,2},          {6,2},

                                                        {3,3},  {4,3},  {5,3},

                                        {1,4},  {2,4},  {3,4},          {5,4},  {6,4},  {7,4},

                                                        {3,5},  {4,5},  {5,5},

                                                {2,6},          {4,6},          {6,6},

                                        {1,7},                  {4,7},                  {7,7} };

    // source and destination for the goat moves
    private int[] mov = {-1,-1}; //-1 means no selection

    /**
     * Constructor for objects of class GameViewer
     * Initializes instance variables and adds mouse listener.
     * Draws the board.
     */
    public GameViewer(int bkSize)
    {
        this.bkSize = bkSize;
        brdSize = bkSize*8;
        sc = new SimpleCanvas("Tigers and Goats", brdSize, brdSize, Color.BLUE);
        sc.addMouseListener(this);
        rules = new GameRules();
        bd = new Board();
        ai = new AIplayer();
        drawBoard();
    }

    /**
     * Constructor with default block size
     */
    public GameViewer( )
    {
        this(80);
    }

    /**
     * Draws the boad lines and the pieces as per their locations.
     * Drawing of lines is provided, students to implement drawing
     * of pieces and number of goats.
     */
    private void drawBoard()
    {
        sc.drawRectangle(0,0,brdSize,brdSize,Color.BLUE); //wipe the canvas

        //draw shadows of Goats and Tigers - not compulsory, for beauty only /////////////

        //////////////////////////////////////////////////////
        // Draw the lines
        for(int i=1; i<9; i++)
        {
            //draw the red lines
            if(i<4)
                sc.drawLine(locs[i-1][0]*bkSize, locs[i-1][1]*bkSize,
                        locs[i+5][0]*bkSize, locs[i+5][1]*bkSize, Color.red);
            else if(i==4)
                sc.drawLine(locs[i+5][0]*bkSize, locs[i+5][1]*bkSize,
                        locs[i+7][0]*bkSize, locs[i+7][1]*bkSize, Color.red);
            else if(i==5)
                sc.drawLine(locs[i+7][0]*bkSize, locs[i+7][1]*bkSize,
                        locs[i+9][0]*bkSize, locs[i+9][1]*bkSize, Color.red);
            else
                sc.drawLine(locs[i+9][0]*bkSize, locs[i+9][1]*bkSize,
                        locs[i+15][0]*bkSize, locs[i+15][1]*bkSize, Color.red);

            if(i==4 || i==8) continue; //no more to draw at i=4,8
            // vertical white lines
            sc.drawLine(i*bkSize, i*bkSize,
                        i*bkSize, brdSize-i*bkSize,Color.white);
            // horizontal white lines
            sc.drawLine(i*bkSize,         i*bkSize,
                        brdSize-i*bkSize, i*bkSize, Color.white);

        }

        // TODO 10
        // Draw the goats and tigers. (Drawing the shadows is not compulsory)
        // Display the number of goats
        for (int i = 0; i < 24; i++) {
            if (bd.isGoat(i)) { // Draw Goats
                //System.out.println("Goat at: " + (locs[i][0]) * bkSize + " " + (locs[i][1]) * bkSize);
                sc.drawDisc((locs[i][0]) * bkSize, (locs[i][1]) * bkSize, 25, Color.GREEN);
            } else if (bd.isTiger(i)) {
                sc.drawDisc((locs[i][0]) * bkSize, (locs[i][1]) * bkSize, 25, Color.RED);
            }
        }
        sc.drawString("Goats: " + rules.getNumGoats(), 300, 300, Color.white);

    }

    /**
     * If vacant, place a goat at the user clicked location on board.
     * Update goat count in rules and draw the updated board
     */
    public void placeGoat(int loc)
    {
        //
        //TODO 2
        //If vacant location then place else error

        if (bd.isVacant(loc)) {
            bd.setGoat(loc);
            rules.addGoat(1);
            System.out.println("Goats placed: " + rules.getNumGoats());
            drawBoard();
            if (rules.getNumGoats() == 12) {
                System.out.println("Tigers to be placed now");
            }
        } else {
            System.out.println("Already occupied by goat");
        }

    }

    /**
     * Calls the placeTiger method of AIplayer to place a tiger on the board.
     * Increments tiger count in rules.
     * Draws the updated board.
     */
    public void placeTiger()
    {
        //TODO 13
        ai.placeTiger(bd);
        rules.incrTigers();
        drawBoard();

    }

    /**
     * Toggles goat selection - changes the colour of selected goat.
     * Resets selection and changes the colour back when the same goat is clicked again.
     * Selects destination (if vacant) to move and calls moveGoat to make the move.
     */
    public void selectGoatMove(int loc)
    {
        //TODO 16

        sc.drawDisc((locs[loc][0]) * bkSize, (locs[loc][1]) * bkSize, 25, Color.WHITE);

        mov[1] = loc;

        System.out.println("mov0: " + mov[0] + "  mov1: " + mov[1]);


        moveGoat();

    }

    /**
     * Make the user selected goat move only if legal otherwise set the destination to -1 (invalid).
     * If did make a goat move, then update board, draw the updated board, reset mov to -1,-1.
     * and call tigersMove() since after every goat move, there is a tiger move.
     */
    public void moveGoat()
    {
        //TODO 18

        System.out.println("Is legal move? " + rules.isLegalMove(mov[0], mov[1]));
        if(rules.isLegalMove(mov[0], mov[1])) {
            if (bd.isVacant(mov[1])) {
                bd.setGoat(mov[1]);
                bd.setVacant(mov[0]);
                drawBoard();
                mov[0] = -1;
                mov[1] = -1;
                tigersMove();
            } else {
                mov[1] = -1;
            }
        } else {
            mov[1] = -1;
        }

    }

    /**
     * Call AIplayer to make its move. Update and draw the board after the move.
     * If Tigers cannot move, display "Goats Win!".
     * If goats are less than 6, display "Tigers Win!".
     * No need to terminate the game.
     */
    public void tigersMove()
    {
        //TODO 20

        if (rules.getNumGoats() < 6) {
            // TIGERS WIN
            System.out.println("TIGERS WIN");
        }

        int didMove = ai.makeAmove(bd);

        if(didMove == 1) {
            // Ate a goat
            drawBoard();
        } else if (didMove == 0) {
            // Simple move to vacant spot
            drawBoard();
        } else {
            // GOATS WIN
            System.out.println("GOATS WIN");
        }

    }

    /**
     * Respond to a mouse click on the board. DONE
     * Get a valid location nearest to the click (from GameRules). DONE
     * If nearest location is still far, do nothing. DONE
     * Otherwise, call placeGoat to place a goat at the location.
     * Call this.placeTiger when it is the tigers turn to place.
     * When the game changes to move stage, call selectGoatMove to move
     * the user selected goat to the user selected destination.
     */
    public void mousePressed(MouseEvent e)
    {
        //TODO 1
        // Check where we clicked nearest location
        int x = e.getX();
        int y = e.getY();
        int loc = rules.nearestLoc(x, y, this.bkSize);

        if (loc != -1) { // Check valid location of click

            if (rules.isMoveStage()) { // Check is we are in move stage of game

                if(rules.isGoatsTurn()) { // Check if it is our turn

                    if (mov[0] == -1) { // Haven't selected an initial goat to move

                        if (bd.isGoat(loc)) { // Check we clicked on a goat

                            mov[0] = loc;
                        }
                    } else if (mov[1] == -1) {

                        if (bd.isVacant(loc)) {

                            selectGoatMove(loc);
                        }
                    }
                } else {
                    this.placeTiger(); // NOT our turn - place tiger
                }
            } else {
                placeGoat(loc); // NOT move stage
            }
        }

        //System.out.println("X: " + x + " Y: " + y);

    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
