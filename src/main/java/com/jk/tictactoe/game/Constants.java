package com.jk.tictactoe.game;

public class Constants {
	public static final int INITIAL_MARK = 8 ;
	public static final int MATRIX_SIZE = 3 ;
	

	public static final int PLAYER_AI = 1;
	public static final int PLAYER_HUMAN = 2;

	public static final int PLAYER_AI_MARK = 1 ;
	public static final int PLAYER_HUMAN_MARK = 2 ;
	
	public static final int PLAYER_AI_WINNING_SUM = 3 ;
	public static final int PLAYER_HUMAN_WINNING_SUM = 6 ;
	
    public static final int NEXT_PLAYER_AI_WINNING_SUM = Constants.PLAYER_AI_MARK * 2 + INITIAL_MARK ;
    public static final int NEXT_PLAYER_HUMAN_WINNING_SUM = Constants.PLAYER_HUMAN_MARK * 2 + INITIAL_MARK ;
	
    public static final int PLAYER_AI_OPEN_POSITION_SUM = Constants.PLAYER_AI_MARK + INITIAL_MARK*2 ;
    public static final int PLAYER_HUMAN_OPEN_POSITION_SUM = Constants.PLAYER_HUMAN_MARK  + INITIAL_MARK*2 ;
	
    public static final int DOUBLE_DANGER_SUM_FOR_AI =  PLAYER_HUMAN_MARK * 2 + PLAYER_AI_MARK ;
    
    public enum PLAYER{
   	 
    	AI(1), HUMAN(2);

    	private int id ;
    	PLAYER(int i) {
			id=i ;
		}
    	
		public int getId() {
			return id;
		} 
    	
    }
    
    public enum PRIORITY{

    	HIGHEST(-10),
        AI_WON(-9),
        PREVENT_HUMAN_TO_WIN(-8),
        AI_WIN_IN_NEXT_STATE(-7),
        HUMAN_WIN_IN_NEXT_STATE(-6) ,

    	NORM_PRIO_5(-5),
    	NORM_PRIO_4(-4),
    	NORM_PRIO_3(-3),
    	NORM_PRIO_2(-2),
    	NORM_PRIO_1(-1),
    	
    	LOWEST(0); 
    	
    	private int priority ;
		PRIORITY(int priority) {
			this.priority = priority;
		}
		
		
		
		public int getPriority() {
			return priority;
		}

		public static PRIORITY fromNumber(int number) {

			if (number >= -10 && number <= 0) {
				
				for (PRIORITY priority : PRIORITY.values()) {
					if (priority.priority == number) 
							return  priority ;
				}
			}
			
			System.out.println("invalid number provided to get priority!");
			return null ;
		}


    	
    }
    
}
