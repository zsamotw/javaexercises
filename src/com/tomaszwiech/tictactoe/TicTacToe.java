package com.tomaszwiech.tictactoe;

/**
 * Program nie ma obslugi wyjatkow.
 */

import java.util.Scanner;

public class TicTacToe {
			
    private char[][] board = new char[4][4];
	private Player player1;
	private Player player2;
	
	TicTacToe(String name1, String name2) {
		player1 = new Player(name1, 'X');
		player2 = new Player(name2, 'O');
		for(int i = 0; i <= 3; i++) {
			for(int j = 0; j <= 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	private void printBoard() {
		for(int i = 1; i <= 3; i++) {
			for(int j = 1; j <= 3; j++) {
				System.out.print(board[i][j]);
				if(j == 3) System.out.println();
			}
		}
	}
	
	
	private boolean checkMove(Move move) {
		return board[move.x][move.y] != 'X' && 
			    board[move.x][move.y] != 'O';
	}
	
	private void addMoveToBoard(Move move, Player player) {
		board[move.x][move.y] = player.symbol;
	}
	
	private boolean checkHorizontal(int x, Player player) {
		for(int i = 1; i <= 3; i++) {
			if(board[x][i] != player.symbol) {
				return false;
			}
		}
		return true;
	}

	private boolean checkVertical(int x, Player player) {
		for(int i = 1; i <= 3; i++) {
			if(board[i][x] != player.symbol) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Można napisac algorytm bardziej zalezny od wybranego pola,ale wydaje mi sie ze przy tak malej 
	 * planszy i sprawdzaniu dwoch przekatnych nie ma to wiekszego sensu. 
	 */
	
	private boolean checkDiagonal(Player player) {
		if((board[1][1] == player.symbol
		   && board[2][2] == player.symbol
		   && board[3][3] == player.symbol) ||
		   (board[1][3] == player.symbol
		   && board[2][2] == player.symbol
		   && board[3][1] == player.symbol)) 
		{
			return true;
		}
		return false;
	}
	
	
	private String checkWhoWon(Move move, Player player) {
		if(checkHorizontal(move.x, player) || checkVertical(move.y, player) || checkDiagonal(player)) {
			return player.name;
		}
		else {
			return null;
		}
	}
	
	private String sequence(Player player) {
		printBoard();
		System.out.println("Ruch nalezy do: " + player.symbol);
		Move move = player.setMove();
		while(!checkMove(move)) {
			System.out.println("Pole zajete. Podaj jeszcze raz wspolrzedne");
			move = player.setMove();
		} 
		addMoveToBoard(move, player);
		return checkWhoWon(move, player);
	}
	
	void Game() {
		String winner = null;
		while(true) {
			winner = sequence(player1);
			if(winner != null) {
				break;
			}
			winner = sequence(player2);
			if(winner != null) {
				break;
			}
		}
		System.out.println("");
		System.out.println("THE WINNER IS " + winner + "!!!");
		System.out.println("Dlaczego. Sam zobacz:");
		printBoard();

	}
}

class Move {
	int x;
	int y;
	
	Move(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Player {
	private Scanner scanner = new Scanner(System.in);
	String name;
	char symbol;
	

	Player(String name, char symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	Move setMove() {
		int x = 0;
		int y = 0;
		while(!checkMove(x,y)) {
		System.out.println("Podaj wspolrzedna y: ");
		x = scanner.nextInt();
		System.out.println("Podaj wspolrzedna x: ");
		y = scanner.nextInt();
		if(!checkMove(x,y)) {
			System.out.println("\n!!!Ruch poza polem gry. jeszcze raz podaj dane!!!\n");
		}
		}
		return new Move(x,y);
	}
	
	boolean checkMove(int x, int y) {
		return x >= 1 &&
				x <= 3 &&
				y >= 1 &&
				y <= 3;
	}

}

class GameMe{
	public static void main(String[] args) {
		TicTacToe ttt = new TicTacToe("Player1", "Player2");
		ttt.Game();
 	}
}