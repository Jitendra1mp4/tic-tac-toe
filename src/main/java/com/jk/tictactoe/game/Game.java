package com.jk.tictactoe.game;

import com.jk.tictactoe.pojo.DiagonalSum;
import com.jk.tictactoe.pojo.Position;

class Game {

	public static final String PLAYER_HUMAN_SYMBOL = "O ";
	public static final String PLAYER_AI_SYMBOL = "X ";
	public static final String INITIAL_SYMBOL = "- ";
	public static final int INIT_VAL = Constants.INITIAL_MARK;
	
	public int numberOfCellUpdated = 0;

	int state[][] = new int[][] {
			{ INIT_VAL, INIT_VAL, INIT_VAL },
			{ INIT_VAL, INIT_VAL, INIT_VAL },
			{ INIT_VAL, INIT_VAL, INIT_VAL }
	};

	boolean updateState(int i, int j, int val) {
		int temp = state[i][j];

		if (temp == INIT_VAL) {
			state[i][j] = val;
			numberOfCellUpdated++;
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
		String stateAsString = "";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				
				if (state[i][j] == Constants.INITIAL_MARK) {
					stateAsString += INITIAL_SYMBOL;
					
				} else if (state[i][j] == Constants.PLAYER_AI_MARK)
					stateAsString += PLAYER_AI_SYMBOL;
				
				else if (state[i][j] == Constants.PLAYER_HUMAN_MARK)
					stateAsString += PLAYER_HUMAN_SYMBOL;
			}
			stateAsString += "\n";
		}
		return stateAsString;
	}

	public boolean equals(Game game) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.state[i][j] != game.state[i][j])
					return false;
			}
		}
		return true;
	}
}
