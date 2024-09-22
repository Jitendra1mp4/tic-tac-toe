package game.tictactoe;

import game.pojo.DiagonalSum;
import game.pojo.Position;
import game.tictactoe.Constants.PLAYER;

public class Test {

	
	//supposed to called after updating pseudo state, to test whether that will result in WIN in NEXT INN.
	public static boolean willIWonInNextState(Constants.PLAYER player,Game game, Position position) {

		final DiagonalSum diagonalSum = game.getDiagonalSum();

		boolean winningCondition1 = false;
		boolean winningCondition2 = false;
		boolean winningCondition3 = false;
		boolean winningCondition4 = false;
		
		
		if (player == PLAYER.AI) {
			winningCondition1 = game.getRowSum(position.getX()) == Constants.NEXT_PLAYER_AI_WINNIG_SUM; // false 
			winningCondition2 = game.getColSum(position.getY()) == Constants.NEXT_PLAYER_AI_WINNIG_SUM; // false
			winningCondition3 = diagonalSum.getSumOfD1() == Constants.NEXT_PLAYER_AI_WINNIG_SUM;      // true
			winningCondition4 = diagonalSum.getSumOfD2() == Constants.NEXT_PLAYER_AI_WINNIG_SUM;	   // false
			
			
			
		}else if(player == PLAYER.HUMAN) {
			winningCondition1 = game.getRowSum(position.getX()) == Constants.NEXT_PLAYER_HUMAN_WINNING_SUM;  
			winningCondition2 = game.getColSum(position.getY()) == Constants.NEXT_PLAYER_HUMAN_WINNING_SUM;
			winningCondition3 = diagonalSum.getSumOfD1() == Constants.NEXT_PLAYER_HUMAN_WINNING_SUM;
			winningCondition4 = diagonalSum.getSumOfD2() == Constants.NEXT_PLAYER_HUMAN_WINNING_SUM;
		}

		
		final boolean winningConditions[] = {
				winningCondition1, 
				winningCondition2,
				winningCondition3,
				winningCondition4
		} ;
		

		int numberOfWinningConditions = 0 ;
		
	     for (boolean wc : winningConditions) {
	    	 if (wc) numberOfWinningConditions++ ;
	     }
		
		return numberOfWinningConditions >= 2;
	}

	//supposed to called after updating pseudo or real state, to test whether playerID WON
	public static boolean won(Game game, Constants.PLAYER player, Position p) {

		final int rowSum = game.getRowSum(p.getX())  ;
		final int colSum =  game.getColSum(p.getY()) ;
		final DiagonalSum diagonalSum = game.getDiagonalSum();
		
		if (player == PLAYER.AI) {
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

	
	
public static int NumberOfOpenPostions(Constants.PLAYER player,Game game, Position position) {

		final DiagonalSum diagonalSum = game.getDiagonalSum();

		boolean rowIsOpen = false;
		boolean columIsOpen = false;
		boolean diagonal1isOpen = false;
		boolean diagonal2isOpen = false;
		
		
		if (player == PLAYER.AI) {
			rowIsOpen = game.getRowSum(position.getX()) == Constants.PLAYER_AI_OPEN_POSITION_SUM;  
			columIsOpen = game.getColSum(position.getY()) == Constants.PLAYER_AI_OPEN_POSITION_SUM; 
			diagonal1isOpen = diagonalSum.getSumOfD1() == Constants.PLAYER_AI_OPEN_POSITION_SUM;      
			diagonal2isOpen = diagonalSum.getSumOfD2() == Constants.PLAYER_AI_OPEN_POSITION_SUM;	   
			
			
			
		}else if(player == PLAYER.HUMAN) {
			rowIsOpen = game.getRowSum(position.getX()) == Constants.PLAYER_HUMAN_OPEN_POSITION_SUM;  
			columIsOpen = game.getColSum(position.getY()) == Constants.PLAYER_HUMAN_OPEN_POSITION_SUM;
			diagonal1isOpen = diagonalSum.getSumOfD1() == Constants.PLAYER_HUMAN_OPEN_POSITION_SUM;
			diagonal2isOpen = diagonalSum.getSumOfD2() == Constants.PLAYER_HUMAN_OPEN_POSITION_SUM;
		}

		
		final boolean openGroups[] = {
				rowIsOpen, 
				columIsOpen,
				diagonal1isOpen,
				diagonal2isOpen
		} ;
		

		int numberOfOpenGroups = 0 ;
		
	     for (boolean og : openGroups) {
	    	 if (og) numberOfOpenGroups++ ;
	     }
		
		return numberOfOpenGroups ;
	}


public static boolean diagonalDangerForAi(Game game) {
	final DiagonalSum diagonalSum = game.getDiagonalSum();
	return  game.state[1][1] == Constants.PLAYER_AI_MARK && game.numberOfCellUpdated ==3 && (diagonalSum.getSumOfD1() == Constants.DOUBLE_DANGER_SUM_FOR_AI ||   
			diagonalSum.getSumOfD2() == Constants.DOUBLE_DANGER_SUM_FOR_AI ) ;	
	
}
}
