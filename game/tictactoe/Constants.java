package game.tictactoe;

public class Constants {
	public final static int INITIAL_MARK = 8 ;
	public final static int MATRIX_SIZE = 3 ;

	public static final int PLAYER_AI = 1;
	public static final int PLAYER_HUMAN = 2;

	public final static int PLAYER_AI_MARK = 1 ;
	public final static int PLAYER_HUMAN_MARK = 2 ;
	
	public final static int PLAYER_AI_WINNIG_SUM = 3 ;
	public final static int PLAYER_HUMAN_WINNING_SUM = 6 ;
	
	
    public final static int NEXT_PLAYER_AI_WINNIG_SUM = Constants.PLAYER_AI_MARK * 2 + INITIAL_MARK ;
    public final static int NEXT_PLAYER_HUMAN_WINNING_SUM = Constants.PLAYER_HUMAN_MARK * 2 + INITIAL_MARK ;
	
    public final static int PLAYER_AI_OPEN_POSITION_SUM = Constants.PLAYER_AI_MARK + INITIAL_MARK*2 ;
    public final static int PLAYER_HUMAN_OPEN_POSITION_SUM = Constants.PLAYER_HUMAN_MARK  + INITIAL_MARK*2 ;
	
    
    enum PLAYER{
    	 
    	AI(1), HUMAN(2);

    	private int id ;
    	PLAYER(int i) {
			id=i ;
		} 
    	
    }
    
}
