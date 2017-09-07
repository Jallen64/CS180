package edu.purdue.allen408.cs180;

/**
 * Created by jaall on 4/20/2017.
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class TwentyFortyEight {
    private int[][] board;
    private final Random rand = new Random();
    private final int boardWidth;
    private int score;

    int [][]currentBoard;

    public Stack<Integer[][]> undoBoard;
    public Stack<Integer[][]> redoBoard;

    public int[][] undo(){

        if(undoBoard == null || undoBoard.isEmpty()){
            return null;
        }
        redoBoard.push(toObject(board));
        int[][]temp;
        temp= toInt(undoBoard.pop());
        return temp;
    }

    public int[][] redo(){
        if(redoBoard.empty())
            return null;
        undoBoard.push(toObject(board));
        int [][] redo = toInt(redoBoard.pop());

        return redo;
    }



    public TwentyFortyEight(int boardWidth){
        undoBoard = new Stack<>();
        redoBoard = new Stack<>();
        this.boardWidth = boardWidth;
        reset();
    }

    public void reset() {
        board = new int[boardWidth][boardWidth];
        placeRandom();
        score = 0;
    }

    public int numBlanks() {
        int moveCount = 0;
        for(int x = 0; x < boardWidth; x++) {
            for(int y = 0; y < boardWidth; y++) {
                if(board[x][y] == 0) {
                    moveCount++;
                }
            }
        }

        return moveCount;
    }

    /**
     * Not a required method. Just a helper method to get the new score. Used in all move methods.....
     * moveUp, moveDown, etc.
     */
    public void makeScore() {
        int maxScore = 0;
        for(int i = 0; i < boardWidth; i++) {
            for(int j = 0; j < boardWidth; j++) {
                if(board[i][j] > maxScore) {
                    maxScore = board[i][j];
                }
            }
        }
        score = maxScore;
    }

    public void placeRandom(){
        int blanks = numBlanks();
        if(blanks == 0) {
            return;
        }

        int nextItem = rand.nextInt(blanks);
        for(int x = 0; x < boardWidth; x++) {
            for(int y = 0; y < boardWidth; y++) {
                if(board[x][y] != 0) {
                    continue;
                }
                else {
                    if(nextItem == 0) {
                        board[x][y] = 2;
                        return;
                    }
                    nextItem--;
                }
            }
        }
    }

    public boolean moveTo(int fromRow, int fromCol, int toRow, int toCol) {
        // check the bounds
        if(     fromRow > boardWidth - 1 || fromCol > boardWidth - 1 ||
                toRow > boardWidth - 1 || toCol > boardWidth - 1 ||
                fromCol < 0 || fromRow < 0 ||
                toRow < 0 || toCol < 0) {
            return false;
        }

        if(fromRow == toRow + 1 && fromCol == toCol) {
            // doNothing
        }

        else if(fromRow == toRow - 1 && fromCol == toCol) {
            // doNothing
        }

        else if(fromCol == toCol + 1 && fromRow == toRow) {
            // doNothing
        }

        else if(fromCol == toCol - 1 && fromRow == toRow) {
            // doNothing
        }
        else
            return false;


        final int from = board[fromRow][fromCol];
        final int to   = board[toRow][toCol];

        if(from == 0)
            return false;

        if(to == 0 || to == from) {
            board[toRow][toCol] = from + to;
            board[fromRow][fromCol] = 0;
//            if(to == from)
//                score += from + to;
            return true;
        }

        return false;
    }


    public boolean moveUp(){
        undoBoard.push(toObject(board));
        boolean madeMove = false;
        for(int x = 0; x < boardWidth; x++) {
            for(int y = 0; y < boardWidth; y++) {
                madeMove = moveTo(x, y, x-1,y) || madeMove;
            }
        }
        makeScore();

        if(!madeMove)
            undoBoard.pop();

        return madeMove;
    }

    public boolean moveDown() {
        undoBoard.push(toObject(board));
        boolean madeMove = false;
        for(int x = boardWidth - 1; x >= 0; x--) {
            for(int y = 0; y < boardWidth; y++) {
                madeMove = moveTo(x, y, x+1,y) || madeMove;
            }
        }
        makeScore();

        if(!madeMove)
            undoBoard.pop();

        return madeMove;
    }

    public boolean moveRight() {
        undoBoard.push(toObject(board));
        boolean madeMove = false;
        for(int x = 0; x < boardWidth; x++) {
            for(int y = boardWidth - 1; y >= 0; y--) {
                madeMove = moveTo(x, y, x,y+1) || madeMove;
            }
        }
        makeScore();

        if(!madeMove)
            undoBoard.pop();

        return madeMove;
    }

    public boolean moveLeft() {
        undoBoard.push(toObject(board));
        boolean madeMove = false;

        for(int x = 0; x < boardWidth; x++) {
            for(int y = 0; y < boardWidth; y++) {
                madeMove = moveTo(x, y, x,y-1) || madeMove;
            }
        }
        makeScore();
        if(!madeMove)
            undoBoard.pop();



        return madeMove;

    }



    public Integer[][] toObject( int[][] past){
        Integer[][] temp;
        temp= new Integer[4][4];
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4 ; j++) {
                temp[i][j] = (Integer) past[i][j];
            }
        }
        return temp;
    }

    public int[][] toInt(Integer[][] temp){
        int[][] past;
        past= new int[4][4];
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                past[i][j] = (int)temp[i][j];
            }
        }
        return past;
    }

    ////////////////////////////////////////////////////////////////////////
    // Do not edit the methods below, they are for testing and running the
    // program.
    ////////////////////////////////////////////////////////////////////////
    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] newBoard) {
        board = newBoard;
    }


    /**
     * The main will allow you to play the game.
     *
     * @param args
     */
    public static void main(String args[]) {
        TwentyFortyEight tfe = new TwentyFortyEight(4);


        Scanner s = new Scanner(System.in);
        int bestScore = 0;
        boolean resetFlag = false;

        while(true) {
            System.out.println("Current score: " + tfe.getScore() + " | Best score: " + bestScore);
            tfe.showBoard();

            System.out.println("Enter up, down, left or right to move in that direction.");
            System.out.println("Enter reset to reset the game or quit to exit.");
            String line = s.nextLine();

            switch(line){
                case "up":
                    while(tfe.moveUp()){
                        // do nothing
                    }
                    break;
                case "down":
                    while(tfe.moveDown()){
                        // do nothing
                    }
                    break;
                case "left":
                    while(tfe.moveLeft()){
                        // do nothing
                    }
                    break;
                case "right":
                    while(tfe.moveRight()){
                        // do nothing
                    }
                    break;
                case "reset":
                    tfe.reset();
                    resetFlag = true;
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Invalid move, Please try again!!!!\n");
                    continue;

            }

            bestScore = Math.max(bestScore, tfe.getScore());
            if(!resetFlag) {
                tfe.placeRandom();
                resetFlag = false;
            }
        }
    }


    public void showBoard() {
        for(int x = 0; x < boardWidth * 6 + 1; x++){
            System.out.print("-");
        }
        System.out.println();

        for(int x = 0; x < boardWidth; x++) {
            for(int y = 0; y < boardWidth; y++) {
                String square = (board[x][y] == 0)? "":board[x][y] + "";
                System.out.printf("|%5s", square);
            }
            System.out.println("|");

            for(int y = 0; y < boardWidth * 6 + 1; y++){
                System.out.print("-");
            }
            System.out.println();
        }
    }

    public int getScore() {
        return score;
    }

}



