package com.jk.tictactoe.game;
import java.util.Scanner;

import com.jk.tictactoe.game.Constants.PLAYER;
import com.jk.tictactoe.pojo.Position;


public class Play {
	

    public Play() {

        Scanner sc = new Scanner(System.in);

        Game game = new Game();

        System.out.println("press 1 or any ODD Number to start first") ;
        System.out.println("press 0 or any Even Number to let AI start first : ");

        int i = sc.nextInt() ;

        if (i%2==0) {
        	game.setStartedByHuman(false);
        	System.out.println("AI's Symbol : " + Constants.SYMBOL_X);
        	System.out.println("YOUR Symbol : " + Constants.SYMBOL_O);
        }else {
        	game.setStartedByHuman(true);
        	System.out.println("AI's Symbol : " + Constants.SYMBOL_O);
        	System.out.println("YOUR Symbol : " + Constants.SYMBOL_X);

        }
        
        

        System.out.println("initial game state:");
        System.out.println(game) ;



        do {
        	
        	Position position ;

        	boolean stateUpdated = false ;
            if (i % 2 == 0) { 
                System.out.println("AI's turn...");
                Generator generator = new Generator();
                position = generator.searchAndGetPosition(game);
                stateUpdated = game.updateState(position,  Constants.PLAYER_AI_MARK);
            }

            else { // for player human
                System.out.println("Your turn");
                System.out.print("Enter position : ");
                position = new Position(sc.nextInt());
                stateUpdated = game.updateState(position, Constants.PLAYER_HUMAN_MARK);
            }
            
            
            System.out.println("position set, now game is : ");
            System.out.println(game) ;
            
            if(Test.won(game,PLAYER.AI, position)) {
            	System.out.println("AI WON!");
            	break ;
            }
            
            if(Test.won(game,PLAYER.HUMAN, position)) {
                System.out.println("YOU WON!");
                break ;
            }

            if (stateUpdated) i++ ;
        } while (game.getNumberOfCellUpdated()<9);

        sc.nextLine();
        System.out.println("Game Over!");
        sc.close();
    }

}
