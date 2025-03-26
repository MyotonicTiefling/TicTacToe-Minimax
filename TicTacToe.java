package com.example.tictactoegame;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.event.*;
import java.io.IOException;

public class TicTacToe extends Application{
    int gridSlot=4;
    boolean win=false;
    Font pieces=new Font(60), font=new Font(40);
    Button smart, place, countUp, countDown,reset;
    Label[] gPos =new Label[9];

    @Override
    public void start(Stage myStage) throws IOException {
        myStage.setTitle("Tic Tac Toe!");

        smart=new Button("AI SMART: "+ TTTMinimax.smart);
        smart.setOnAction(this::smartEvent);
        smart.setFont(font);

        place=new Button("Place here!");
        place.setOnAction(this::placeEvent);
        place.setFont(font);

        countUp=new Button("→");
        countUp.setOnAction(this::countEvent);
        countUp.setFont(font);

        countDown=new Button("←");
        countDown.setOnAction(this::countEvent);
        countDown.setFont(font);

        reset=new Button("Reset");
        reset.setOnAction(this::resetEvent);
        reset.setFont(font);

        for (int i=0;i<9;i++){
            gPos[i]=new Label(" ");
            gPos[i].setFont(pieces);
        }

        GridPane myPane=new GridPane();
        myPane.add(smart,2,0);
        myPane.add(gPos[0],1,1);
        myPane.add(gPos[1],2,1);
        myPane.add(gPos[2],3,1);
        myPane.add(gPos[3],1,2);
        myPane.add(gPos[4],2,2);
        myPane.add(gPos[5],3,2);
        myPane.add(gPos[6],1,3);
        myPane.add(gPos[7],2,3);
        myPane.add(gPos[8],3,3);
        myPane.add(countDown,1,4);
        myPane.add(place,2,4);
        myPane.add(countUp,3,4);
        myPane.add(reset,2,5);

        myPane.setAlignment(Pos.CENTER);
        myPane.setHgap(20);
        myPane.setVgap(20);

        for (int i=0;i<9;i++){
            GridPane.setHalignment(gPos[i], Pos.CENTER.getHpos());
        }
        GridPane.setHalignment(countDown, Pos.CENTER.getHpos());
        GridPane.setHalignment(countUp, Pos.CENTER.getHpos());
        GridPane.setHalignment(place, Pos.CENTER.getHpos());
        GridPane.setHalignment(smart, Pos.CENTER.getHpos());
        GridPane.setHalignment(reset, Pos.CENTER.getHpos());


        if (TTTMinimax.smart && !win){
            TTTMinimax.move();
            win=TTTBoard.checkWin('o');
        }
        else if (!win){
            TTTMinimax.moveRand();
            win=TTTBoard.checkWin('o');
        }
        for (int i=0; i<9;i++){
            gPos[i].setText(""+TTTBoard.grid[i]);
        }

        Scene myScene = new Scene(myPane,625,625);
        myStage.setScene(myScene);
        myStage.setMaximized(true);
        gPos[4].setText("⌖");
        myStage.show();
    }

    public void smartEvent(ActionEvent ev){
        TTTMinimax.smart=!TTTMinimax.smart;
        smart.setText("AI SMART: "+TTTMinimax.smart);
    }

    public void placeEvent(ActionEvent ev){
        if (!win && TTTBoard.grid[gridSlot]==' '){
            TTTBoard.grid[gridSlot]='x';
            win=TTTBoard.checkWin('x');
            TTTBoard.humanTurn=false;
            if (TTTMinimax.smart && !win){
                TTTMinimax.move();
                win=TTTBoard.checkWin('o');
            }
            else if (!win){
                TTTMinimax.moveRand();
                win=TTTBoard.checkWin('o');
            }
        }
        for (int i=0; i<9;i++){
            gPos[i].setText(""+TTTBoard.grid[i]);
        }
        gPos[gridSlot].setText("⌖");
    }

    public void countEvent(ActionEvent ev) {
        for (int i=0; i<9;i++){
            gPos[i].setText(""+TTTBoard.grid[i]);
        }
        if(ev.getSource()==countUp){
            if ((gridSlot + 1 )< 9) {
                gridSlot += 1;
            }
            gPos[gridSlot].setText("⌖");

        }
        else{
            if (gridSlot-1>-1) {
                gridSlot-= 1;
            }
            gPos[gridSlot].setText("⌖");
        }
    }
    public void resetEvent(ActionEvent ev){
        for(int i=0; i<9; i++){
            TTTBoard.grid[i]=' ';
        }
        TTTMinimax.smart=true;
        if (TTTMinimax.smart){
            TTTMinimax.move();
        }
        else{
            TTTMinimax.moveRand();

        }
        win=false;
        gridSlot=4;
        for (int i=0; i<9;i++){
            gPos[i].setText(""+TTTBoard.grid[i]);
        }
        gPos[gridSlot].setText("⌖");
        TTTBoard.humanTurn=true;
    }
}