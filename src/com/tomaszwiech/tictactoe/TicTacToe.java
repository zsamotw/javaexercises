package com.tomaszwiech.tictactoe;

/**
 * Program nie ma obslugi wyjatkow.
 */

import java.util.Scanner;

public class TicTacToe {
			
    private char[][] board = new char[3][3];
	private Player player1;
	private Player player2;
	private static final char x = 'X';
	private static final char o = 'O';
	
	TicTacToe(String name1, String name2) {
		player1 = new Player(name1, x);
		player2 = new Player(name2, o);
		for(int i = 0; i <= 2; i++) {
			for(int j = 0; j <= 2; j++) {
				board[i][j] = '-';
			}
		}
	}

	private void printBoard() {
		for(int i = 0; i <= 2; i++) {
			for(int j = 0; j <= 2; j++) {
				System.out.print(board[i][j]);
				if(j == 2) System.out.println();
			}
		}
	}
	
	
	private boolean checkMove(Move move) {
		return board[move.x][move.y] != x &&
			    board[move.x][move.y] != o;
	}
	
	private void addMoveToBoard(Move move, Player player) {
		board[move.x][move.y] = player.symbol;
	}
	
	private boolean checkHorizontal(int x, Player player) {
		for(int i = 0; i <= 2; i++) {
			if(board[x][i] != player.symbol) {
				return false;
			}
		}
		return true;
	}

	private boolean checkVertical(int x, Player player) {
		for(int i = 0; i <= 2; i++) {
			if(board[i][x] != player.symbol) {
				return false;
			}
		}
		return true;
	}
	
	
	private boolean checkDiagonal(Player player) {
		if((board[0][0] == player.symbol
		   && board[1][1] == player.symbol
		   && board[2][2] == player.symbol) ||
		   (board[0][2] == player.symbol
		   && board[1][1] == player.symbol
		   && board[2][0] == player.symbol)) 
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
	
	void startGame() {
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

	boolean isCorrect() {
		return x >= 0 &&
				x <= 2 &&
				y >= 0 &&
				y <= 2;
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
		Move move = null;
		do {
		System.out.println("Podaj wspolrzedna y: ");
		x = scanner.nextInt() - 1;
		System.out.println("Podaj wspolrzedna x: ");
		y = scanner.nextInt() - 1;
		move = new Move(x,y);
		if(!move.isCorrect()) {
			System.out.println("\n!!!Ruch poza polem gry. jeszcze raz podaj dane!!!\n");
		}
		} while(move.isCorrect());
		return move;
	}

	public static void main(String[] args) {
		TicTacToe ttt = new TicTacToe("Player1", "Player2");
		ttt.startGame();
	}
}
