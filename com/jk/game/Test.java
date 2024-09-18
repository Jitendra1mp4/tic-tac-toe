package com.jk.game;

public class Test {
	

	
	
	
	
	
	
	
	
	
  public static boolean checkResult(Game game ,int playerID, Position p) {

      if (playerID != 1 && playerID != 2) {
          System.out.println("invalid player id");
          return false;
      }

      DiagonalSum d = game.getDiagonalSum();

      if (playerID == 1) {
          return (
                     game.getRowSum(p.getX()) == Game.WINNING_SUM_PLAYER_1  // TODO:replace with constants.Winn
                  || game.getColSum(p.getY()) == Game.WINNING_SUM_PLAYER_1
                  || d.sumOfD1 == Game.WINNING_SUM_PLAYER_1
                  || d.sumOfD2 == Game.WINNING_SUM_PLAYER_1
                 );
      } else {
          return (
                     game.getRowSum(p.getX()) == Game.WINNING_SUM_PLAYER_2
                  || game.getColSum(p.getY()) == Game.WINNING_SUM_PLAYER_2
                  || d.sumOfD1 == Game.WINNING_SUM_PLAYER_2
                  || d.sumOfD2 == Game.WINNING_SUM_PLAYER_2
                  );
      }

  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//   public static boolean checkResult(int playerID, Position p) {
//
//       if (playerID != 1 && playerID != 2) {
//           System.out.println("invalid player id");
//           return false;
//       }
//
//       DiagonalSum d = getDiagonalSum();
//
//       if (playerID == 1) {
//           return (
//                      getRowSum(p.getX()) == Game.WINNING_SUM_PLAYER_1
//                   || getColSum(p.getY()) == Game.WINNING_SUM_PLAYER_1
//                   || d.sumOfD1 == WINNING_SUM_PLAYER_1
//                   || d.sumOfD2 == WINNING_SUM_PLAYER_1
//                  );
//       } else {
//           return (
//                      getRowSum(p.getX()) == Game.WINNING_SUM_PLAYER_2
//                   || getColSum(p.getY()) == Game.WINNING_SUM_PLAYER_2
//                   || d.sumOfD1 == WINNING_SUM_PLAYER_2
//                   || d.sumOfD2 == WINNING_SUM_PLAYER_2
//                   );
//       }
//
//   }
}
