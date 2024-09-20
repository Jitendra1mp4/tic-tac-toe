package com.jk.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.jk.game.ds.Position;

public class Generator {

	private static final int NUMBER_OF_CELLS = 9;

	private Stack<Position> somethingElse;

	public Generator() {
		somethingElse = new Stack<Position>();
	}

	public Position generate(Game game) {

		// System.out.println("Generator.generate()");

		return getBestPosition(generatePositionList(game), game.state);
	}

	private List<Position> generatePositionList(Game game) {
		// System.out.println("Generator.generatePositionList()");

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

	public Position getBestPosition(List<Position> positionList, final int currentState[][]) {
		// System.out.println("Generator.categorizePositionsList()");

		// make a new game
		Game game = new Game();

		for (Iterator<Position> iterator = positionList.iterator(); iterator.hasNext();) {

			// set the state of game to current state
			game.state = getDeepCopy(currentState);

			Position position = iterator.next();

			// if by applying that position AI wins it is winning position
			game.updateState(position, Constants.PLAYER_AI_MARK);
			if (Test.won(game, Constants.PLAYER_AI, position)) {
				return position ;
			}

			// if human can win by marking same position it would be preventivePosition
			game.updateStateForced(position, Constants.PLAYER_HUMAN_MARK);
			if (Test.won(game, Constants.PLAYER_HUMAN, position)) {
				return position ;
			}

			// if by applying position AI's win is confirm in next inn it is better
			// position
			game.updateStateForced(position, Constants.PLAYER_AI_MARK);
			
			if (Test.willAIWonInNext(game, position)) {

				return position ;
			} 
			
			else {
				somethingElse.push(position);
			}
		}
		
		if (!somethingElse.isEmpty()) {
			Position position = handleSomethingElse(game);
			return position != null ? position : somethingElse.pop();
		}

		System.out.println("All stack are empty returning null");
		return null;
		
	}

	
	
	private Position handleSomethingElse(Game game) {

		if (game.numberOfCellUpdated == 2) {
			
			if ((game.state[2][2] == Constants.PLAYER_HUMAN_MARK))	
				return new Position(1); 
			
			
			if ((game.state[2][0] == Constants.PLAYER_HUMAN_MARK))
				return new Position(3); 
			
			
			if ((game.state[0][0] == Constants.PLAYER_HUMAN_MARK))
				return new Position(7); 
		}

		return null;
	}

	
	
	
	private static int[][] getDeepCopy(int matrix[][]) {
		int copy[][] = new int[3][];

		for (int i = 0; i < matrix.length; i++) { // *state.length returns number of rows
			copy[i] = matrix[i].clone();
		}

		return copy;
	}


}
