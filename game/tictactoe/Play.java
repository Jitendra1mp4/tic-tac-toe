package game.tictactoe;
import java.util.Scanner;

import game.pojo.Position;
import game.tictactoe.Constants.PLAYER;


public class Play {
	

    public Play() {

        Scanner sc = new Scanner(System.in);

        Game game = new Game();

        System.out.println("AI's Symbol : " + Game.PLAYER_AI_SYMBOL);
        System.out.println("YOUR Symbol : " + Game.PLAYER_HUMAN_SYMBOL);

        System.out.println("initial game state:");
        System.out.println(game) ;


        System.out.println("To be first player press 1 otherwise 0: ");
        int i = sc.nextInt() ;

        do {

        	boolean stateUpdated = false ;
            if (i % 2 == 0) { 
                System.out.println("AI's turn...");
          
                Generator generator = new Generator();
                Position p = generator.searchAndGetPosition(game);
                
                stateUpdated = game.updateState(p,  Constants.PLAYER_AI_MARK);
                
                System.out.println("position set, now game is : ");
                System.out.println(game) ;
                
                if(Test.won(game,PLAYER.AI, p)) {
                    System.out.println("AI WON!");
                    break ;
                }
            }

            else { // for player 2
                System.out.println("Your turn");
                System.out.print("Enter position : ");
                
                
                Position p = new Position(sc.nextInt());
                stateUpdated = game.updateState(p, Constants.PLAYER_HUMAN_MARK);
                
                
                System.out.println(game) ;
                
                if(Test.won(game,PLAYER.HUMAN, p)) {
                    System.out.println("YOU WON!");
                    break ;
                }
            }

            if (stateUpdated) i++ ;
        } while (game.numberOfCellUpdated<9);

        sc.nextLine();
        System.out.println("Game Over!");
        sc.close();
    }

}
