package com.example.tictactoegame;

public class TTTBoard{
    public static char[] grid={' ',' ',' ',' ',' ',' ',' ',' ',' '};
    public static boolean humanTurn;


    public TTTBoard(){
        humanTurn=true;
    }

    public static boolean checkWin(char plr){
        boolean win=false;
        for (int i=1,j=3;j<6; i+=3, j+=1){
            if(grid[i]==plr && grid[i-1]==plr && grid[i+1]==plr){
                win=true;
            }
            if(grid[j]==plr && grid[j-3]==plr && grid[j+3]==plr){
                win=true;
            }
        }
        if (grid[0]==plr && grid[4]==plr && grid[8]==plr){
            win=true;
        }
        else if (grid[2]==plr && grid[4]==plr && grid[6]==plr){
            win=true;
        }
        return win;
    }

    public String toString(){
        String outStr="Tic Tac Toe: \n";
        for (int i=0;i<grid.length;i++){
            if ((i+1)%3==0){
                outStr+=grid[i]+"\n";
            }
            else if (grid[i]==' '){
                outStr+=" ";
            }
            else{
                outStr+=grid[i];
            }
        }
        return outStr;
    }
}