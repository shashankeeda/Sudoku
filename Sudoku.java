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
        {0,0,0,0,0,0,0,0,0}};

    public void print(int[][] board){
        for(int i=0;i<9;i++){
            if((i)%3==0&&1!=0) {
                System.out.print("_____________________");
                System.out.println();
            }
            for(int j=0;j<9;j++){
                System.out.print(board[i][j]+" ");
                if((j+1)%3==0)
                    System.out.print("|");
            }
            System.out.println();
        }
    }

    public boolean check(int row,int col,int value){
        int x=row,y=col;
        for(int i=0;i<9;i++){
            if(board[row][i]==value && i!=col)
                return false;
        }
        for(int i=0;i<9;i++){
            if(board[i][col]==value && i!=row)
                return false;
        }
        if(x<3)
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

        for(int i=x;i<x+3;i++){
            for(int j=y;j<y+3;j++){
                if(board[i][j]==value && 1!=row && j!=col){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean solveSudoku(int [][] board, int row, int col) {
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                if (board[i][j] == 0) {
                    for (int value = 1; value < 10; value++) {
                        if (check(i, j, value)) {
                            board[i][j] = value;

                            if (solveSudoku(board,i,j)) {
                                return true;
                            } else {
                                board[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        char c;
        int number;
        String[] numbers;
        Sudoku sudoku=new Sudoku();
        Scanner input = new Scanner(System.in);
        for(int i=0;i<9;i++){
            System.out.println("Enter 9 digits from 0-9 in row "+(i+1)+" seperated by a space");
            String in=input.nextLine();
            String[] stringnums = in.split(" ");
            for(int j=0;j<9;j++){
                if(!Character.isDigit(stringnums[j].charAt(0)))
                    stringnums[j]="0";
                number=Integer.parseInt(stringnums[j]);
                if(number>9||number<0)
                    number=0;
                sudoku.board[i][j]=number;
            }
        }
        if(sudoku.solveSudoku(board,0,0))
            sudoku.print(board);
        else{
            System.out.println("No Solution");
        }
    }

}
