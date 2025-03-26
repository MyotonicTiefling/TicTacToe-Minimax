package com.example.tictactoegame;

public class TTTMinimax{

    public static char plr='o'; //AI's piece
    public static char opponent='x'; //Human's piece.
    public static boolean smart=true;

    public static void move(){ //Finds the best spot in this iteration to move and updates the board
        int high=-2,posBest=0;
        char[] possibleGrid=TTTBoard.grid;
        for (int i=0;i<9;i++){
            int score=0;
            if(possibleGrid[i]==' '){ //if the space is free

                possibleGrid[i]=plr;
                score=getScore(false,possibleGrid); //Calls a recursive method to find the max
                possibleGrid[i]=' ';
                if (score>high){ //If our chances are better here, the best move is here
                    high=score;
                    posBest=i;
                }
            }
        }
        if(possibleGrid[posBest]==' '){ //If the space is free
            possibleGrid[posBest]=plr;
        }
        TTTBoard.humanTurn=true; //switches turn in com.example.tictactoegame.TTTBoard
    }
    public static void moveRand(){
        boolean placed=false;
        int spot=0;
        while (!placed){
            spot=(int)Math.floor((Math.random()*8)+1);
            if(TTTBoard.grid[spot]==' '){
                TTTBoard.grid[spot]=plr;
                placed=true;
            }
        }
        TTTBoard.humanTurn=true;
    }
    public static int getScore(boolean max, char[] inGrid){ //determines the score for a layer
        int score=0,best=0,count=0; //current score, best move currently, count of filled spaces
        for (int i=0;i<inGrid.length;i++){
            if(inGrid[i]=='x' || inGrid[i]=='o'){ //if filled
                count++;
            }
        }
        if(TTTBoard.checkWin(plr)){//If AI wins with this set of decisions +1 to score
            return 1;
        }
        else if(TTTBoard.checkWin(opponent)){ //If Human wins with this set of decisions -1 to score
            return -1;
        }
        else if (count==9 && !TTTBoard.checkWin(opponent) && !TTTBoard.checkWin(plr)){ //If all spaces are filled && is a draw
            return 0;
        }

        else {
            score=0;//score for this iteration
            if(max){best=-2;}else{best=+2;}
            for (int i=0;i<9;i++){
                if(inGrid[i]==' '){
                    inGrid[i]=(max)?plr:opponent; //if maximizing then plr, else minimizing opponent
                    score=getScore(!max,inGrid); //recursive statement until end of branch is reached
                    inGrid[i]=' ';
                    if (max && score>best){
                        best=score;
                    }
                    if (!max && score<best){
                        best=score;
                    }
                }
            }
            return best;
        }
    }
}
