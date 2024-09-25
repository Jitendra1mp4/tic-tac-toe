package com.jk.tictactoe.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import com.jk.tictactoe.game.Constants.PLAYER;
import com.jk.tictactoe.game.Constants.PRIORITY;
import com.jk.tictactoe.pojo.Position;

public class Generator {

private static final int NUMBER_OF_CELLS = 9;

	
	private PriorityQueue<Position> positionQueue ;

	public Generator() {
		positionQueue = new PriorityQueue<>() ;
	}

	public Position searchAndGetPosition(Game game) {

		   if(Test.handleStartWithSideCenter(game)) {
		    	System.out.println("returned by handleStartWithSideCenter");
		    	return new Position(5) ;
		    }

		assignPrioritiesToPositions(generatePositionList(game), game.state) ; 
	
		return getBestPosition(game) ;
	}

	private List<Position> generatePositionList(Game game) {

		Position position;

		List<Position> positionList = new LinkedList<>();

		for (int cell = 1; cell <= NUMBER_OF_CELLS; cell++) {
			position = new Position(cell);

			if (game.state[position.getX()][position.getY()] == Game.INIT_VAL) {
				positionList.add(position);
			}
		}
		return positionList;
	}

	public void assignPrioritiesToPositions(List<Position> positionList, final int[][] currentState) {

		// make a new game
		Game game = new Game();

		for (Iterator<Position> iterator = positionList.iterator(); iterator.hasNext();) {

			// set the state of game to current state
			game.state = getDeepCopy(currentState);

			Position position = iterator.next();

			// if by applying that position AI wins it is winning position
			game.updateState(position, Constants.PLAYER_AI_MARK);
			if (Test.won(game, Constants.PLAYER.AI, position)) {
				position.setPriority(PRIORITY.AI_WON);
				positionQueue.add(position) ;
				continue;
			}

			// if human can win by marking same position it would be preventivePosition
			game.updateStateForced(position, Constants.PLAYER_HUMAN_MARK);
			if (Test.won(game, Constants.PLAYER.HUMAN, position)) {
				position.setPriority(PRIORITY.PREVENT_HUMAN_TO_WIN);
				positionQueue.add(position) ;
				
				System.out.println("set: "+position);
				continue;
			}

			// if by applying position AI's win is confirm in next inn it is better
			// position
			game.updateStateForced(position, Constants.PLAYER_AI_MARK);
			if (Test.willIWonInNextState(PLAYER.AI,game, position)) {
				position.setPriority(PRIORITY.AI_WIN_IN_NEXT_STATE);
				positionQueue.add(position) ;
				
				System.out.println("set: "+position);
				continue;
			} 
			
			game.updateStateForced(position, Constants.PLAYER_HUMAN_MARK);
			if (Test.willIWonInNextState(PLAYER.HUMAN,game, position)) {
				position.setPriority(PRIORITY.HUMAN_WIN_IN_NEXT_STATE);
				positionQueue.add(position) ;
				
				System.out.println("set: "+position);
				continue ;
			}
					
			
			else {
				game.updateStateForced(position, Constants.PLAYER_AI_MARK);
				int priority = (-1)*Test.NumberOfOpenPostions(PLAYER.AI, game, position);
				
				position.setPriority(PRIORITY.fromNumber(priority));
				positionQueue.add(position) ;

				System.out.println("set: "+position);
			}
		}
	
	}

	
	
	
	private Position getBestPosition(Game game) {
//		
		if(Test.diagonalDangerForAi(game)) {
			System.out.println("diagonalDangerForAi, returning handleDoublePreventiveCase()");
			return handleDiagonalDangerForAi();
		}
		
		if (!positionQueue.isEmpty()) {
			
			Position position = positionQueue.poll();
			System.out.println("returning.. "+position);
			return position ;
		}

		System.out.println("NO position place left!");
		System.out.println("Game Over!");
		System.exit(1) ;	
		return null ;
	}

	private Position handleDiagonalDangerForAi() {
		System.out.println("Generator.handleDoublePreventiveCase()");
		return new Position(4) ;
	}
		
	private static int[][] getDeepCopy(int[][] matrix) {
		int[][] copy = new int[3][];

		for (int i = 0; i < matrix.length; i++) { // *state.length returns number of rows
			copy[i] = matrix[i].clone();
		}

		return copy;
	}


}
