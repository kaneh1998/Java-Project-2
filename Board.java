
/**
 * Maintains and updates the status of the board
 * i.e. the locations of goats and tigers
 *
 * @Student 1 Name: 
 * @Student 1 Number: 
 * 
 * @Student 2 Name: 
 * @Student 2 Number:
 */
public class Board
{
    // An enumated type for the three possibilities
    private enum Piece {GOAT, TIGER, VACANT};
    Piece[] board;

    /**
     * Constructor for objects of class Board.
     * Initializes the board VACANT.
     */
    public Board()
    {
        // TODO 3
        //20 Locations on the board all initialised to VACANT
        this.board = new Piece[24];
        for (int i = 0; i < 24; i++) {
            this.board[i] = Piece.VACANT;
        }
        
    }

            
    /**
     * Checks if the location a is VACANT on the board
     */
    public boolean isVacant(int a)
    {
        //TODO 4
        if (this.board[a] == Piece.VACANT) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Sets the location a on the board to VACANT
     */
    public void setVacant(int a)
    {
        //TODO 5
        this.board[a] = Piece.VACANT;
        
    }
    
    /**
     * Checks if the location a on the board is a GOAT
     */
    public boolean isGoat(int a)
    {
        //TODO 6
        if (this.board[a] == Piece.GOAT) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Sets the location a on the board to GOAT
     */
    public void setGoat(int a)
    {
        //TODO 7
        this.board[a] = Piece.GOAT;
        
    }
    
    /**
     * Sets the location a on the board to TIGER
     */
    public void setTiger(int a)
    {
        //TODO 8
        this.board[a] = Piece.TIGER;
        
    }

    public boolean isTiger(int a) // Custom method
    {
        if (this.board[a] == Piece.TIGER) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Moves a piece by swapping the contents of a and b
     */
    public void swap(int a, int b)
    {
        //TODO 9
        //tiger in a & goat in b? swap a and b?


        
    }
}
