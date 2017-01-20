package common;

import java.rmi.RemoteException;
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
				pion.setPlayer(players.get(0));
				if (i == 1) {
					board[3][3] = pion;
				} else {
					board[4][4] = pion;
				}

			} else {
				pion.setPlayer(players.get(1));
				if (i == 3) {
					board[4][3] = pion;
				} else {
					board[3][4] = pion;
				}
			}
		}
	}

	public int getNumberPion(CouleurPion color) throws RemoteException {
		int count = 0;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (board[x][y].getPlayer().getColor() == color) {
					count++;
				}
			}
		}

		return count;
	}

	public CouleurPion[][] GetBoardState() throws RemoteException {

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

	public boolean IsPositionFree(int x, int y) {
		if (board[x][y] != null) {
			return false;
		}
		return true;
	}

	// retourne false si le placement demand� n'est pas possible et modifie la
	// couleur des pions dans le cas contraire
	public boolean IsPositionAuthorised(int x, int y, CouleurPion couleur) throws RemoteException {

		// gauche
		Pion left = board[x - 1][y];
		// si pion pr�sent et de couleur diff�rente, on continue
		if (left != null && left.getPlayer().getColor() != couleur) {

			// si apr�s le premier pion, un autre pion de la couleur du joueur
			// est trouv�, on arr�te, on sais que la position est valide
			for (int i = 0; i < 8; i++) {
				if (board[x - 1 - i][y] != null && board[x - 1 - i][y].getPlayer().getColor() == couleur) {
					for (int j = 0; j < i; j++) {
						board[x - 1 - j][y].getPlayer().setColor(couleur);
					}
					break;
				}
			}
		}

		// gauche/haut
		Pion leftUp = board[x - 1][y - 1];
		// si pion pr�sent et de couleur diff�rente, on continue
		if (leftUp != null && leftUp.getPlayer().getColor() != couleur) {

			// si apr�s le premier pion, un autre pion de la couleur du joueur
			// est trouv�, on arr�te, on sais que la position est valide
			for (int i = 0; i < 8; i++) {
				if (board[x - 1 - i][y - 1 - i] != null
						&& board[x - 1 - i][y - 1 - i].getPlayer().getColor() == couleur) {
					for (int j = 0; j < i; j++) {
						board[x - 1 - j][y - 1 - j].getPlayer().setColor(couleur);
					}
					break;
				}
			}
		}

		// haut
		Pion up = board[x][y - 1];
		// si pion pr�sent et de couleur diff�rente, on continue
		if (up != null && up.getPlayer().getColor() != couleur) {

			// si apr�s le premier pion, un autre pion de la couleur du joueur
			// est trouv�, on arr�te, on sais que la position est valide
			for (int i = 0; i < 8; i++) {
				if (board[x][y - 1 - i] != null && board[x][y - 1 - i].getPlayer().getColor() == couleur) {
					for (int j = 0; j < i; j++) {
						board[x][y - 1 - j].getPlayer().setColor(couleur);
					}
					break;
				}
			}
		}

		// droite/haut
		Pion rightUp = board[x + 1][y - 1];
		// si pion pr�sent et de couleur diff�rente, on continue
		if (rightUp != null && rightUp.getPlayer().getColor() != couleur) {

			// si apr�s le premier pion, un autre pion de la couleur du joueur
			// est trouv�, on arr�te, on sais que la position est valide
			for (int i = 0; i < 8; i++) {
				if (board[x + 1 + i][y - 1 - i] != null
						&& board[x + 1 + i][y - 1 - i].getPlayer().getColor() == couleur) {
					for (int j = 0; j < i; j++) {
						board[x + 1 + j][y - 1 - j].getPlayer().setColor(couleur);
					}
					break;
				}
			}
		}

		// droite
		Pion right = board[x + 1][y];
		// si pion pr�sent et de couleur diff�rente, on continue
		if (right != null && right.getPlayer().getColor() != couleur) {

			// si apr�s le premier pion, un autre pion de la couleur du joueur
			// est trouv�, on arr�te, on sais que la position est valide
			for (int i = 0; i < 8; i++) {
				if (board[x + 1 + i][y] != null && board[x + 1 + i][y].getPlayer().getColor() == couleur) {
					for (int j = 0; j < i; j++) {
						board[x + 1 + j][y].getPlayer().setColor(couleur);
					}
					break;
				}
			}
		}

		// droite/bas
		Pion rightDown = board[x + 1][y + 1];
		// si pion pr�sent et de couleur diff�rente, on continue
		if (rightDown != null && rightDown.getPlayer().getColor() != couleur) {

			// si apr�s le premier pion, un autre pion de la couleur du joueur
			// est trouv�, on arr�te, on sais que la position est valide
			for (int i = 0; i < 8; i++) {
				if (board[x + 1 + i][y + 1 + i] != null
						&& board[x + 1 + i][y + 1 + i].getPlayer().getColor() == couleur) {
					for (int j = 0; j < i; j++) {
						board[x + 1 + j][y + 1 + j].getPlayer().setColor(couleur);
					}
					break;
				}
			}
		}

		// bas
		Pion down = board[x][y + 1];
		// si pion pr�sent et de couleur diff�rente, on continue
		if (down != null && down.getPlayer().getColor() != couleur) {

			// si apr�s le premier pion, un autre pion de la couleur du joueur
			// est trouv�, on arr�te, on sais que la position est valide
			for (int i = 0; i < 8; i++) {
				if (board[x][y + 1 + i] != null && board[x][y + 1 + i].getPlayer().getColor() == couleur) {
					for (int j = 0; j < i; j++) {
						board[x][y + 1 + j].getPlayer().setColor(couleur);
					}
					break;
				}
			}
		}

		// gauche/bas
		Pion leftDown = board[x - 1][y + 1];
		// si pion pr�sent et de couleur diff�rente, on continue
		if (leftDown != null && leftDown.getPlayer().getColor() != couleur) {

			// si apr�s le premier pion, un autre pion de la couleur du joueur
			// est trouv�, on arr�te, on sais que la position est valide
			for (int i = 0; i < 8; i++) {
				if (board[x - 1 - i][y + 1 + i] != null
						&& board[x - 1 - i][y + 1 + i].getPlayer().getColor() == couleur) {
					for (int j = 0; j < i; j++) {
						board[x - 1 - j][y + 1 + j].getPlayer().setColor(couleur);
					}
					break;
				}
			}
		}

		// si toutes les positions autour sont vides
		if (left == null && leftUp == null && up == null && rightUp == null && right == null && rightDown == null
				&& down == null && leftDown == null) {
			System.out.println("position entour�e de vide");
			return false;
		}

		// si toutes les positions autour sont de la m�me couleur que le
		// joueur
		if (left != null && left.getPlayer().getColor() == couleur && leftUp != null
				&& leftUp.getPlayer().getColor() == couleur && up != null && up.getPlayer().getColor() == couleur
				&& rightUp != null && rightUp.getPlayer().getColor() == couleur && right != null
				&& right.getPlayer().getColor() == couleur && rightDown != null
				&& rightDown.getPlayer().getColor() == couleur && down != null && down.getPlayer().getColor() == couleur
				&& leftDown != null && leftDown.getPlayer().getColor() == couleur) {
			System.out.println("position entour�e de votre couleur");
			return false;
		}

		return true;
	}

}
