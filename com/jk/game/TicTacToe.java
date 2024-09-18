package com.jk.game;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String args[]) {
        new Play();
    }
}




class Play {
	
	private final int player1ID = Constants.player1 ;
	private final int player2ID = Constants.player2 ;
	final int symbolP1 = Constants.MY_MARK;
	final int symbolP2 = Constants.OPPONENT_MARK;

    public Play() {

        Scanner sc = new Scanner(System.in);


        Game game = new Game();

        System.out.println("Welcome:\nSymbol for player1 : " + symbolP1);
        System.out.println("Symbol for player1 : " + symbolP2);

        System.out.println("initial game state:");
        System.out.println(game) ;

        int i = 0;

        do {

            if (i % 2 == 0) { 
                System.out.println("Player 1's turn");
//                System.out.print("enter position : ");
                
                Position p = Generater.generate(game);
                
                System.out.println("position set now game is : ");
                
                System.out.println(game) ;
                
                if(Test.checkResult(game,player1ID, p)) {
                    System.out.println("Player 1 WON");
                    break ;
                }
            }

            else { // for player 2
                System.out.println("Player 2's turn");
                System.out.print("enter position : ");
                
                
                Position p = new Position(sc.nextInt());
                game.set(p, symbolP2);
                
                
                System.out.println(game) ;
                
                if(Test.checkResult(game,player2ID, p)) {
                    System.out.println("Player 2 WON");
                    break ;
                }
            }

            i++;
        } while (i < 9);

        System.out.println("Game Over!");
        sc.close();
    }

}
