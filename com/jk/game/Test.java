package com.jk.game;

import com.jk.game.ds.DiagonalSum;
import com.jk.game.ds.Position;

public class Test {

	

	//supposed to called after updating pseudo state, to test whether that will result in WIN in NEXT INN.
	public static boolean willAIWonInNext(Game game, Position position) {

		final DiagonalSum diagonalSum = game.getDiagonalSum();

		final boolean winningCondition1 = game.getRowSum(position.getX()) == Constants.NEXT_PLAYER_AI_WINNIG_SUM; // false 
		final boolean winningCondition2 = game.getColSum(position.getY()) == Constants.NEXT_PLAYER_AI_WINNIG_SUM; // false
		final boolean winningCondition3 = diagonalSum.getSumOfD1() == Constants.NEXT_PLAYER_AI_WINNIG_SUM;      // true
		final boolean winningCondition4 = diagonalSum.getSumOfD2() == Constants.NEXT_PLAYER_AI_WINNIG_SUM;	   // false

		
		final boolean winningCondtions[] = {
				winningCondition1, 
				winningCondition2,
				winningCondition3,
				winningCondition4
		} ;
		

		int numberOfWinningConditions = 0 ;
		
	     for (boolean wc : winningCondtions) {
	    	 if (wc) numberOfWinningConditions++ ;
	     }
		
		return numberOfWinningConditions >= 2;
	}

	//supposed to called after updating pseudo or real state, to test whether playerID WON
	public static boolean won(Game game, int playerID, Position p) {

		final int rowSum = game.getRowSum(p.getX())  ;
		final int colSum =  game.getColSum(p.getY()) ;
		final DiagonalSum diagonalSum = game.getDiagonalSum();
		
		if (playerID == 1) {
			return (rowSum == Constants.PLAYER_AI_WINNIG_SUM // TODO:replace with constants.Winn
					|| colSum == Constants.PLAYER_AI_WINNIG_SUM
					|| diagonalSum.getSumOfD1() == Constants.PLAYER_AI_WINNIG_SUM
					|| diagonalSum.getSumOfD2() == Constants.PLAYER_AI_WINNIG_SUM);
		} else {
			return (rowSum == Constants.PLAYER_HUMAN_WINNING_SUM
					|| colSum == Constants.PLAYER_HUMAN_WINNING_SUM
					|| diagonalSum.getSumOfD1() == Constants.PLAYER_HUMAN_WINNING_SUM
					|| diagonalSum.getSumOfD2() == Constants.PLAYER_HUMAN_WINNING_SUM);
		}

	}

}
