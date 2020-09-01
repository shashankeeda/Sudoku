//Shashank Eeda
//Kate Walker
import java.util.*;
public class Sudoku {
    static int[][] board={{0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}};//making a 2D array for the board and setting all spaces to empty
    public void print(int[][] board){//function to print the board
        for(int i=0;i<9;i++){
            if((i)%3==0&&1!=0) {//prints a horizontal line at every third row to section off the 9 3X3 squares of the board
                System.out.print("_________________");
                System.out.println();
            }
            for(int j=0;j<9;j++){//loop to go through the array and print each value and then a space
                System.out.print(board[i][j]+" ");
                if((j+1)%3==0)// puts vertical line at every third column to section off the 9 3X3 squares of the board
                    System.out.print("|");
            }
            System.out.println();
        }
    }
    public boolean check(int row,int col,int value){//function to check what numbers can go in each spot on the board
        int x=row,y=col;
        for(int i=0;i<9;i++){//loop to go through each row of the board
            if(board[row][i]==value && i!=col)//if the value at the row and i is equal to the value and the column is not equal to i return false
                return false;
        }
        for(int i=0;i<9;i++){//loop to go through each column of the board
            if(board[i][col]==value && i!=row)// if the value at i and column is equal to the value and the row is not equal to i return false
                return false;
        }
        if(x<3)//moving x and y to get the value of the left corner of the 3X3 square
            x=0;
        else if(x<6)
            x=3;
        else
            x=6;
        if(y<3)
            y=0;
        else if(y<6)
            y=3;
        else
            y=6;
        for(int i=x;i<x+3;i++){//loop to go through each row of the 3X3 square
            for(int j=y;j<y+3;j++){//nested loop to go through each column of the 3X3 square
                if(board[i][j]==value && 1!=row && j!=col){//if the value is found in the 3X3 square return false
                    return false;
                }
            }
        }
        return true;//if the value isn't in the row , the column or the 3X3 square return true
    }
    public boolean solveSudoku(int [][] board, int row, int col) {//function to check if the puzzle has been solved
        for (int i=0; i<9; i++) {//loop to go through each row
            for (int j=0; j<9; j++) {// nested loop to go through each column
                if (board[i][j] == 0) {//check if the spot on the board is empty
                    for (int value = 1; value < 10; value++) {//loop through possible values to go in the space
                        if (check(i, j, value)) {//calls check function
                            board[i][j] = value;//places value on the board
                            if (solveSudoku(board,i,j)) {//recursive call to check if value can go in that spot if it can return true
                                return true;
                            } else {
                                board[i][j] = 0;//if it can't reset the spot to empty
                            }
                        }
                    }
                    return false;//return false if puzzle isn't solved
                }
            }
        }
        return true;//return true if puzzle has been solved
    }
    public static void main(String[] args){
        char c;
        int number;
        String[] numbers;//defining an array of strings to store the numbers the user inputs
        Sudoku sudoku=new Sudoku();//making an object of the Sudoku class
        Scanner input = new Scanner(System.in);
        for(int i=0;i<9;i++){//loop to allow user to input values for all 9 rows
            System.out.println("Enter 9 digits from 0-9 in row (0 to represent a empty space) "+(i+1)+" seperated by a space");
            String in=input.nextLine();
            String[] stringnums = in.split(" ");//splitting the string at the space so that each number can be retrieved
            for(int j=0;j<9;j++){//looping through each of the 9 values inputed by the user
                if(!Character.isDigit(stringnums[j].charAt(0)))
                    stringnums[j]="0";
                number=Integer.parseInt(stringnums[j]);//getting the number and turning it in to an int
                if(number>9||number<0)//if the number is greater than 9 or 0 set the spot to 0 to represent an empty square
                    number=0;
                sudoku.board[i][j]=number;//place each number in the correct spot
            }
        }
        System.out.println("\nInputted Board:");
        sudoku.print(board);//print the board before sloving it
        System.out.println("\n");
        if(sudoku.solveSudoku(board,0,0)){
            System.out.println("Solution:");
            sudoku.print(board);//print the solved board
        }
        else{
            System.out.println("No Solution");//if board can't be solved print no solution
        }
    }
}
