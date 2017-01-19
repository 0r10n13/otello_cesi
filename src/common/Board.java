package common;

import java.util.List;

public class Board {

	private Pion[][] board;

	public Board() {
		board = new Pion[8][8];
	}

	public void InitStartBoard(List<IPlayer> players) {

		for (int i = 0; i < 4; i++) {
			Pion pion = new Pion();
			if (i < 2) {
				pion.setPlayer((PlayerImpl) players.get(0));
				if (i == 1) {
					board[3][3] = pion;
				} else {
					board[4][4] = pion;
				}

			} else {
				pion.setPlayer((PlayerImpl) players.get(1));
				if (i == 3) {
					board[4][3] = pion;
				} else {
					board[3][4] = pion;
				}
			}
		}
	}

	public CouleurPion[][] GetBoardState() {

		CouleurPion[][] result = new CouleurPion[8][8];

		for (int x = 0; x < 8; x++) {

			for (int y = 0; y < 8; y++) {
				if (board[x][y] == null) {
					result[x][y] = null;
				} else {
					result[x][y] = board[x][y].getPlayer().getColor();
				}
			}
		}

		return result;
	}
}
