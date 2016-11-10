import javafx.application.Application;
import java.util.ArrayList;
import java.util.Random;

public class Main implements Constants{

    private static Space[][] board;
    private static int rows;
    private static int cols;
    private static int points;

    public static void main(String[] args){
        Application.launch(Gui.class, args);

        // Debug stuff
        dispBoard();
        //for(;;){}
    }

    // Only called when user fills launcher form
    public static void init(){
        // Initializing game board and variables
        board = new Space[rows][cols];
        points = 0;

        // Initializing spaces on board
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                board[row][col] = new Space(0);
            }
        }
        generateNumber();
    }

    private static void generateNumber(){
        ArrayList<Integer[]>unfilled = new ArrayList<>();
        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                if(board[r][c].getValue() == 0){
                    unfilled.add(new Integer[]{r,c});
                }
            }
        }
        if(unfilled.size() == 0){lose();}
        Random r = new Random();
        int randomIndex = r.nextInt(unfilled.size());
        int randomTwoFour = r.nextInt(1);

        // If randomTwoFour == 0, set random unfilled space equal to 2, else set it equal to 4
        board[unfilled.get(randomIndex)[0]][unfilled.get(randomIndex)[1]].setValue((randomTwoFour == 0)?2:4);
    }

    private static void lose(){
        System.exit(0);
    }

    static void dispBoard(){
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                System.out.print(board[row][col]+" ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    static void swipe(int direction){
        switch(direction){
            case LEFT:
                shift(LEFT);
                for(int r=0; r<rows; r++){
                    for(int c=0; c<cols-1; c++){
                        if(board[r][c].equals(board[r][c+1])){
                            board[r][c].add(board[r][c+1]);
                            shift(LEFT);
                        }
                    }
                }
                break;
            case RIGHT:
                shift(RIGHT);
                for(int r=0; r<rows; r++){
                    for(int c=cols-1; c>0; c--){
                        if(board[r][c].equals(board[r][c-1])){
                            board[r][c].add(board[r][c-1]);
                            shift(RIGHT);
                        }
                    }
                }
                break;
            case UP:
                shift(UP);
                for(int c=0; c<cols; c++){
                    for(int r=0; r<rows-1; r++){
                        if(board[r][c].equals(board[r+1][c])){
                            board[r][c].add(board[r+1][c]);
                            shift(UP);
                        }
                    }
                }
                break;
            case DOWN:
                shift(DOWN);
                for(int c=0; c<cols; c++){
                    for(int r=rows-1; r>0; r--){
                        if(board[r][c].equals(board[r-1][c])){
                            board[r][c].add(board[r-1][c]);
                            shift(DOWN);
                        }
                    }
                }
                break;
            default:
                // Throw an error
                break;
        }
        generateNumber();
    }

    private static void shift(int direction){
        switch(direction){
            case LEFT:
                // For every row on the board...
                for(int r=0; r<rows; r++){
                    // Keep a record of indices of filled (non-zero value) spaces
                    ArrayList<Integer>filled = new ArrayList<>();
                    for(int c=0; c<cols; c++){
                        Space currentSpace = board[r][c];
                        // If the current space isn't filled, save its column index
                        if(currentSpace.getValue() != 0) filled.add(c);
                    }
                    // Go through every space in the current row (left to right)...
                    for(int i=0; i<cols; i++){
                        // And put the filled spaces first
                        if(i<filled.size()) board[r][i].setValue(board[r][filled.get(i)].getValue());
                        // When filled spaces run out, make the rest in the row zero
                        else board[r][i].setValue(0);
                    }
                }
                break;
            case RIGHT:
                for(int r=0; r<rows; r++){
                    ArrayList<Integer>filled = new ArrayList<>();
                    for(int c=0; c<cols; c++){
                        if(board[r][c].getValue() != 0){
                            filled.add(c);
                        }
                    }
                    int count = 0;
                    for(int i=cols-1; i>=0; i--){
                        if(count<filled.size()){
                            board[r][i].setValue(board[r][filled.get(filled.size()-1-count)].getValue());
                            count++;
                        }
                        else {
                            board[r][i].setValue(0);
                        }
                    }
                }
                break;
            case UP:
                for(int c=0; c<cols; c++){
                    ArrayList<Integer>filled = new ArrayList<>();
                    for(int r=0; r<rows; r++){
                        if(board[r][c].getValue() != 0) filled.add(r);
                    }
                    for(int i=0; i<rows; i++){
                        if(i<filled.size()) board[i][c].setValue(board[filled.get(i)][c].getValue());
                        else board[i][c].setValue(0);
                    }
                }
                break;
            case DOWN:
                for(int c=0; c<cols; c++){
                    ArrayList<Integer>filled = new ArrayList<>();
                    for(int r=0; r<rows; r++){
                        if(board[r][c].getValue() != 0) filled.add(r);
                    }
                    int count = 0;
                    for(int i=rows-1; i>=0; i--){
                        if(count<filled.size()){
                            board[i][c].setValue(board[filled.get(filled.size()-1-count)][c].getValue());
                            count++;
                        }
                        else board[i][c].setValue(0);
                    }
                }
                break;
            default:
                // Throw an error
                break;
        }
    }

    public static void setRows(int r){
        rows = r;
    }

    public static int getRows(){return rows;}

    public static void setCols(int c){
        cols = c;
    }

    public static int getCols(){return cols;}

    public static Space getSpace(int r, int c){return board[r][c];}
}
