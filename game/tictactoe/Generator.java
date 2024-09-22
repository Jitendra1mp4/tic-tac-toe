package game.tictactoe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import game.pojo.Position;
import game.tictactoe.Constants.PLAYER;

public class Generator {

	private static final int NUMBER_OF_CELLS = 9;

	private Stack<Position> winningPositionList;
	private Stack<Position> preventivePositionList;
	private Stack<Position> aiBetterPositionList;

	private List<Position> humansBetterPosition ;
	private PriorityQueue<Position> somethingElse;


	public Generator() {
		winningPositionList = new Stack<>();
		preventivePositionList = new Stack<>();
		aiBetterPositionList = new Stack<>();
		
		somethingElse = new PriorityQueue<Position>();
		humansBetterPosition = new ArrayList<Position>() ;
	}

	public Position searchAndGetPosition(Game game) {

		// System.out.println("Generator.generate()");

		categorizePositions(generatePositionList(game), game.state) ; 
	
		return getBestPosition(game) ;
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

	public void categorizePositions(List<Position> positionList, final int currentState[][]) {
		// System.out.println("Generator.categorizePositionsList()");

		// make a new game
		Game game = new Game();

		for (Iterator<Position> iterator = positionList.iterator(); iterator.hasNext();) {

			// set the state of game to current state
			game.state = getDeepCopy(currentState);

			Position position = iterator.next();

			// if by applying that position AI wins it is winning position
			game.updateState(position, Constants.PLAYER_AI_MARK);
			if (Test.won(game, Constants.PLAYER.AI, position)) {
//				return position ;
				winningPositionList.push(position);
				continue;
			}

			// if human can win by marking same position it would be preventivePosition
			game.updateStateForced(position, Constants.PLAYER_HUMAN_MARK);
			if (Test.won(game, Constants.PLAYER.HUMAN, position)) {
//				return position ;
				preventivePositionList.push(position);
				continue;
			}

			// if by applying position AI's win is confirm in next inn it is better
			// position
			game.updateStateForced(position, Constants.PLAYER_AI_MARK);
			
			if (Test.willIWonInNextState(PLAYER.AI,game, position)) {
				aiBetterPositionList.push(position);
				continue;
//				return position ;
			} 
			
			game.updateStateForced(position, Constants.PLAYER_HUMAN_MARK);
			if (Test.willIWonInNextState(PLAYER.HUMAN,game, position)) {
				humansBetterPosition.add(position) ;
				continue ;
			}
			
			
			else {
				game.updateStateForced(position, Constants.PLAYER_AI_MARK);
				
				int priority = (-1)*Test.NumberOfOpenPostions(PLAYER.AI, game, position);
				
				System.out.println(position+" : "+priority);
				
				position.setPriority(priority);
				
				somethingElse.add(position);
			}
		}
		
		
//		if(!humansBetterPosition.isEmpty()) {
//			System.out.println("Generator.getBestPosition()");
//			Position hbf = humansBetterPosition.get(0);
//			System.out.println("hbf:"+hbf);
//			if ( hbf != null) {return hbf ;}
//		}

//		if (!somethingElse.isEmpty()) {
////			Position position = handleSomethingElse(game);
////			return position != null ? position : somethingElse.poll();
//			return somethingElse.poll() ;  
//		}
//
//		System.out.println("Game Over!");
//		System.out.println("NO position place left!");
//		System.exit(1) ;	
//		return null ;
	}

	
	
	
	private Position getBestPosition(Game game) {
		// System.out.println("Generator.getBestPosition()");

		// System.out.println("winningPositionList:"+winningPositionList);
		// System.out.println("preventivePositionList:"+preventivePositionList);
		// System.out.println("betterPositionList:"+betterPositionList);
		// System.out.println("somethingElse:"+somethingElse);

		if (!winningPositionList.isEmpty())
			return winningPositionList.pop();
		
		else if (!preventivePositionList.isEmpty())
			return preventivePositionList.pop();
		
		else if (!aiBetterPositionList.isEmpty())
			return aiBetterPositionList.pop();
		
		else if (!humansBetterPosition.isEmpty()) 
			return humansBetterPosition.get(0) ;
		

		else if (!somethingElse.isEmpty()) {
			Position p = handleSomethingElse(game);
			return p != null ? p : somethingElse.poll();
		}

		System.out.println("NO position place left!");
		System.out.println("Game Over!");
		System.exit(1) ;	
		return null ;
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
