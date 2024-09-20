package com.jk.game;
import java.util.Scanner;

import com.jk.game.ds.Position;

public class TicTacToe {

	
    public static void main(String args[]) {

//    	boolean wantToPlay = true;
//    	while(wantToPlay) {
    		System.out.println("here is new game :)");
    		new Play();
//    	}
    }
}




class Play {
	
	private final int player1ID = Constants.PLAYER_AI ;
	private final int player2ID = Constants.PLAYER_HUMAN ;
	final int symbolP1 = Constants.PLAYER_AI_MARK;
	final int symbolP2 = Constants.PLAYER_HUMAN_MARK;

    public Play() {

        Scanner sc = new Scanner(System.in);


        Game game = new Game();

        System.out.println("AI's Symbol : " + Game.PLAYER_AI_SYMBOL);
        System.out.println("YOUR Symbol : " + Game.PLAYER_HUMAN_SYMBOL);

        System.out.println("initial game state:");
        System.out.println(game) ;

        System.out.println("AI's turn...");
        game.updateState(new Position(5), symbolP1);
                
        System.out.println("position set, now game is : ");
        System.out.println(game) ;

        int i = 1;
        do {

            if (i % 2 == 0) { 
                System.out.println("AI's turn...");
          
                Generator generator = new Generator();
                Position p = generator.generate(game);
                
                game.updateState(p, symbolP1);
                
                System.out.println("position set, now game is : ");
                System.out.println(game) ;
                
                if(Test.won(game,player1ID, p)) {
                    System.out.println("AI WON!");
                    break ;
                }
            }

            else { // for player 2
                System.out.println("Your turn");
                System.out.print("Enter position : ");
                
                
                Position p = new Position(sc.nextInt());
                game.updateState(p, symbolP2);
                
                
                System.out.println(game) ;
                
                if(Test.won(game,player2ID, p)) {
                    System.out.println("YOU WON!");
                    break ;
                }
            }

            i++;
        } while (game.numberOfCellUpdated<9);

        sc.nextLine();
        System.out.println("Game Over!");
        sc.close();
    }

}
