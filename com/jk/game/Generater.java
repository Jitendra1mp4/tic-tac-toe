package com.jk.game;

public class Generater {
	
	
	public static void generate(Game game) {
		for(int i = 0 ; i < Constants.MATRIX_SIZE ;i++) {
			for (int j = 0 ; j < Constants.MATRIX_SIZE ; j++ ) {
				
				if (game.matrix[i][j] != Game.INIT_VAL) {
					game.set(i, j, Constants.MY_MARK);
				}
			}
		}
	}
	
}
