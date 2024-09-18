package com.jk.game;

public class Generater {
	
	
	

	private static final int NUMBER_OF_CELLS = 9;

	public static Position generate(Game game) {
		System.out.println("generating position...");
		Position position ;
		for(int cell = 1 ; cell <= NUMBER_OF_CELLS ;cell++) {
			position = new Position(cell) ;
			
				if (game.matrix[position.getX()][position.getY()] == Game.INIT_VAL) {
					game.set(position, Constants.MY_MARK);
					return position;
				}
		}
		System.out.println("No empty position found setting position=null..");
		position = null ;
		return position;
	}
	
}
