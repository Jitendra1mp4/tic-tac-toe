package com.jk.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.jk.game.ds.Position;

public class Generator {




	private static final int NUMBER_OF_CELLS = 9;

	private Stack<Position> winningPositionList ;
	private Stack<Position> preventivePositionList ;
	private Stack<Position> betterPositionList ;
	private Stack<Position> somethingElse ;


	public Generator() {
		winningPositionList = new Stack<Position>() ;
		preventivePositionList = new Stack<Position>();
		betterPositionList = new Stack<Position>() ;
		somethingElse = new Stack<Position>();
	}
	
	
	
	public Position generate(Game game) {
		
		System.out.println("Generator.generate()");
		
		categorizePositionsList(generatePositionList(game) , game.state);

		return getBestPosition(game) ;
	}




	private List<Position> generatePositionList(Game game) {
		System.out.println("Generator.generatePositionList()");

		Position position ;

		List<Position> positionList = new LinkedList<>() ;

		for(int cell = 1 ; cell <= NUMBER_OF_CELLS ;cell++) {
			position = new Position(cell) ;

			if (game.state[position.getX()][position.getY()] == Game.INIT_VAL) {
				positionList.add(position);
			}
		}
		return positionList;
	}




	public void categorizePositionsList(List<Position> positionList, final int currentState[][]) {
		System.out.println("Generator.categorizePositionsList()");

		// make a new game
		Game game = new Game() ;

		for (Iterator<Position> iterator = positionList.iterator(); iterator.hasNext();) {

			// set the state of game to current state
			game.state = getDeepCopy(currentState); 

			Position position = iterator.next();

			// if by applying that position AI wins it is winning position
			game.updateState(position, Constants.PLAYER_AI_MARK);
			if(Test.won(game, Constants.PLAYER_AI, position)) {
				winningPositionList.push(position) ; continue ;
			}

			
			// if human can win by marking same position it would be  preventivePosition
			game.updateStateForced(position, Constants.PLAYER_HUMAN_MARK);			
			if (Test.won(game, Constants.PLAYER_HUMAN, position)) {
				preventivePositionList.push(position) ; continue ;
			}

			// if by applying that position AI's win is confirm in next inn it is better position
			game.updateStateForced(position, Constants.PLAYER_AI_MARK);
			if (Test.willAIWonInNext(game,position)) {
				betterPositionList.push(position) ; continue ;
			}
			else{
				somethingElse.push(position) ;
			}
		}
	}
	
	
	private Position getBestPosition(Game game) 
	{
		System.out.println("Generator.getBestPosition()");
		
		
		System.out.println("winningPositionList:"+winningPositionList);
		System.out.println("preventivePositionList:"+preventivePositionList);
		System.out.println("betterPositionList:"+betterPositionList);
		System.out.println("somethingElse:"+somethingElse);
		
		if (!winningPositionList.isEmpty()) return winningPositionList.pop() ;
		else if (!preventivePositionList.isEmpty()) return preventivePositionList.pop();
		else if (!betterPositionList.isEmpty()) return betterPositionList.pop() ;
		
		else if (!somethingElse.isEmpty()) {
			 Position p = handleSomethingElse(game) ;
			 return p != null ? p : somethingElse.pop() ;
		}

		System.out.println("all stack are empty returning null");
		return null ;
	}

	private static int[][] getDeepCopy(int matrix[][]) {
		int copy[][] = new int[3][] ;

		for(int i = 0 ; i < matrix.length ; i++ ) {  // *state.length returns number of rows
			copy[i] = matrix[i].clone() ;
		}

		return copy ;
	}
	
	
   private Position handleSomethingElse(Game game){
	   
	   // TODO: opponent at corner wisely
	   
	   if (game.numberOfCellUpdated == 0) return new Position(9) ;
	   if (game.numberOfCellUpdated == 2 && game.state[0][1]==Constants.PLAYER_HUMAN_MARK) return new Position(1) ;
//	   if (game.numberOfCellUpdated == 2 && game.state[1][1]==Constants.PLAYER_HUMAN_MARK) return new Position(6) ;
	   System.out.println("returning null, handler accordingly");
	   return null ;
   }
	
}
