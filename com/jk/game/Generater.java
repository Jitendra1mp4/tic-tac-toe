package com.jk.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Generater {




	private static final int NUMBER_OF_CELLS = 9;

	private Stack<Position> winningPositionList ;
	private Stack<Position> preventivePositionList ;
	private Stack<Position> betterPositionList ;
	private Stack<Position> somethingElse ;


	public Generater() {
		winningPositionList = new Stack<Position>() ;
		preventivePositionList = new Stack<Position>();
		betterPositionList = new Stack<Position>() ;
		somethingElse = new Stack<Position>();
	}
	
	
	
	public Position generate(Game game) {
		
		System.out.println("Generater.generate()");
		
		categorizePositionsList(generatePositionList(game) , game.state);

		return getBestPosition() ;
	}


//	public static Position generate(Game game) {
//		System.out.println("generating position...");
//		Position position ;
//
//		for(int cell = 1 ; cell <= NUMBER_OF_CELLS ;cell++) {
//			position = new Position(cell) ;
//
//			if (game.state[position.getX()][position.getY()] == Game.INIT_VAL) {
//				return position;
//			}
//		}
//		System.out.println("No empty position found setting position=null..");
//		position = null ;
//		return position;
//	}

	private List<Position> generatePositionList(Game game) {
		System.out.println("Generater.generatePositionList()");

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
		System.out.println("Generater.categorizePositionsList()");

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
				/// TODO: handle future winning position for now it is something else
			}
			else{
				somethingElse.push(position) ;
			}
		}
	}
	
	
	
	public Position getBestPosition() 
	{
		System.out.println("Generater.getBestPosition()");
		
		
		System.out.println("winningPositionList:"+winningPositionList);
		System.out.println("preventivePositionList:"+preventivePositionList);
		System.out.println("betterPositionList:"+betterPositionList);
		System.out.println("somethingElse:"+somethingElse);
		
		if (!winningPositionList.isEmpty()) return winningPositionList.pop() ;
		else if (!preventivePositionList.isEmpty()) return preventivePositionList.pop();
		else if (!betterPositionList.isEmpty()) return betterPositionList.pop() ;
		else if (!somethingElse.isEmpty()) return somethingElse.pop();

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
	
	
}
