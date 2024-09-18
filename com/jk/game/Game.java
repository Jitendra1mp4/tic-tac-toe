package com.jk.game;

class Game {

    public static final int INIT_VAL = 8;
	public final static int WINNING_SUM_PLAYER_1 = 3;
    public final static int WINNING_SUM_PLAYER_2 = 6;

    int matrix[][] = new int[][] {
            { INIT_VAL, INIT_VAL, INIT_VAL },
            { INIT_VAL, INIT_VAL, INIT_VAL },
            { INIT_VAL, INIT_VAL, INIT_VAL }
    };
    
   

	void set(int i, int j, int val) {
        matrix[i][j] = val;
    }
	
	
	  public void set(Position p, int symbol) {
	    	int temp  = matrix[p.getX()][p.getY()] ;
	    	
	    	if (temp == INIT_VAL) {
	    		matrix[p.getX()][p.getY()] = symbol;
	    		return ;
	    	}
	    	
	    	System.out.println("invalid position, already filled!");
	    	return  ;
	    	
	    }


    int getRowSum(final int row) {
        int sum = 0;
        for (int i = 0; i < 3; i++)
            sum += matrix[row][i];
        return sum;
    }

    int getColSum(final int col) {
        int sum = 0;
        for (int i = 0; i < 3; i++)
            sum += matrix[i][col];
        return sum;
    }

    DiagonalSum getDiagonalSum() {
        DiagonalSum digSum = new DiagonalSum();

        digSum.sumOfD1 = matrix[0][0] + matrix[1][1] + matrix[2][2];
        digSum.sumOfD2 = matrix[0][2] + matrix[1][1] + matrix[2][0];

        return digSum;
    }

  
    public String toString() {
    	String res = "" ;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res+=matrix[i][j] + " ";
            }
           res += "\n" ;
        }
        return res ;
    }


}
