package com.jk.game;

import com.jk.game.ds.DiagonalSum;
import com.jk.game.ds.Position;

class Game {

	public static final int INIT_VAL = Constants.INITIAL_MARK;    
	public int numberOfCellUpdated = 0 ;

	int state[][] = new int[][] {
		{ INIT_VAL, INIT_VAL, INIT_VAL },
		{ INIT_VAL, INIT_VAL, INIT_VAL },
		{ INIT_VAL, INIT_VAL, INIT_VAL }
	};



	void updateState(int i, int j, int val) {
		int temp  = state[i][j] ;

		if (temp == INIT_VAL) {
			state[i][j] = val;
			numberOfCellUpdated++ ;
			return ;
		}

		System.out.println("invalid position, already filled!");
		return  ;

	}


	public void updateState(Position p, int symbol) {
		updateState(p.getX(),p.getY(),symbol);
		return ;

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

		digSum.sumOfD1 = state[0][0] + state[1][1] + state[2][2];
		digSum.sumOfD2 = state[0][2] + state[1][1] + state[2][0];

		return digSum;
	}

	@Override
	public String toString() {
		String res = "" ;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				res+=state[i][j] + " ";
			}
			res += "\n" ;
		}
		return res ;
	}


	public boolean equals(Game game) {
		for (int i = 0  ; i < 3 ; i++) {
			for(int j = 0 ; j < 3 ; j++) {
				if(this.state[i][j]!=game.state[i][j])
					return false ;
			}
		}
		return true ;
	}
}
