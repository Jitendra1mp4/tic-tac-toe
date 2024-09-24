package com.jk.tictactoe.game;

import com.jk.tictactoe.pojo.DiagonalSum;
import com.jk.tictactoe.pojo.Position;

class Game {

	public static final int INIT_VAL = Constants.INITIAL_MARK;
	
	private int numberOfCellUpdated  ;
	private boolean startedByHuman ;
	
	
	Game() {
		setNumberOfCellUpdated(0); 
	}
	
	
	int[][] state = new int[][] {
			{ INIT_VAL, INIT_VAL, INIT_VAL },
			{ INIT_VAL, INIT_VAL, INIT_VAL },
			{ INIT_VAL, INIT_VAL, INIT_VAL }
	};

	boolean updateState(int i, int j, int val) {
		int temp = state[i][j];

		if (temp == INIT_VAL) {
			state[i][j] = val;
			setNumberOfCellUpdated(getNumberOfCellUpdated() + 1);
			return true;
		}

		System.out.println("Invalid position,try again!");
		return false;

	}

	public boolean updateState(Position p, int symbol) {
			return updateState(p.getX(), p.getY(), symbol);
	}

	public void updateStateForced(Position p, int symbol) {
		state[p.getX()][p.getY()] = symbol;
	}

	int getRowSum(final int row) {
		int sum = 0;
		for (int i = 0; i < 3; i++)
			sum += state[row][i];
		return sum;
	}

	int getColSum(final int col) {
		int sum = 0;
		for (int i = 0; i < 3; i++)
			sum += state[i][col];
		return sum;
	}

	DiagonalSum getDiagonalSum() {
		DiagonalSum digSum = new DiagonalSum();

		final int sumOfD1 = state[0][0] + state[1][1] + state[2][2];
		final int sumOfD2 = state[0][2] + state[1][1] + state[2][0];

		digSum.setSumOfD1(sumOfD1);
		digSum.setSumOfD2(sumOfD2);

		return digSum;
	}

	@Override
	public String toString() {
		StringBuilder stateAsString = new StringBuilder("\n");
		
		String playerAiSymbol = "";
		String playerHumanSymbol = "" ;
		
		if (startedByHuman){
			playerHumanSymbol = Constants.SYMBOL_X ;
			playerAiSymbol = Constants.SYMBOL_O ;
		}
		else {
			playerAiSymbol = Constants.SYMBOL_X ;
			playerHumanSymbol = Constants.SYMBOL_O ;
		}
		
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				
				if (state[i][j] == Constants.INITIAL_MARK) {
					stateAsString.append(Constants.INITIAL_SYMBOL);
					
				} else if (state[i][j] == Constants.PLAYER_AI_MARK)
					stateAsString.append(playerAiSymbol);
				
				else if (state[i][j] == Constants.PLAYER_HUMAN_MARK)
					stateAsString.append(playerHumanSymbol);
			}
			stateAsString.append("\n\n");
		}
		return stateAsString.toString(); 
	}


	public int getNumberOfCellUpdated() {
		return numberOfCellUpdated;
	}

	public void setNumberOfCellUpdated(int numberOfCellUpdated) {
		this.numberOfCellUpdated = numberOfCellUpdated;
	}

	public boolean isStartedByHuman() {
		return startedByHuman;
	}

	public void setStartedByHuman(boolean startedByHuman) {
		this.startedByHuman = startedByHuman;
	}
}
