package com.tomaszwiech.sudokuchecker;

/**
 * Zadanie zrobione z uzyciem pomcocniczej tablicy.
 * Program pobiera dane z pliku Excel i zapisuje do int[].
 * Weryfikacja nastepuje juz na danych w tablicy. 
 */



import java.io.File; 
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class SudokuBoardChecker {
	Workbook workbook;

	SudokuBoardChecker(Workbook workbook) {
		this.workbook = workbook;
	}

	/*
	 * Metoda sprawdza poprawnosc wypelnienia danych w pliku.
	 */
	
	public boolean verifyBoardStructure(int sheetIndex) {
		boolean isOk = true;

		for(Row row : workbook.getSheetAt(sheetIndex)) {
			if(row == null) {
				isOk = false;
			}
			for(Cell cell : row) {
				if(cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
					if(cell.getNumericCellValue() < 1 || cell.getNumericCellValue() > 9) {
						isOk = false;
					}
				}
				if(!cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
					isOk = false;
				}
			}
		}
		return isOk;
	}
	
	/*
	 * Metoda pobiera dane z pliku Excel i zwraca tablice wypelnina danymi
	 */

	private int[][] getArrayFromSheet(int sheetIndex) {
		Sheet sheet = workbook.getSheetAt(sheetIndex);
		int[][] array = new int[9][9];
		for(int i = 0; i < array.length; i++) {
			Row row = sheet.getRow(i);
			for(int j = 0; j < array.length; j++) {
				Cell cell = row.getCell(j);
				CellType celltype = cell.getCellTypeEnum();
				if(celltype.equals(CellType.NUMERIC)) {
					int cellValue = (int)cell.getNumericCellValue();
					array[i][j] = cellValue;
				}
			}

		}
		return array;
	}

	private boolean checkRows(int[][] array) {
		for(int r = 0; r < array.length; r++) {
			Set<Integer> tempSet = new HashSet<>();
			for(int c = 0; c < array.length; c++) {
				if(!tempSet.add(array[r][c])) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean checkColumns(int[][] array) {
		for(int c = 0; c < array.length; c++) {
			Set<Integer> tempSet = new HashSet<>();
			for(int r = 0; r < array.length; r++) {
				if(!tempSet.add(array[r][c])) {
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * Pomocnicza metoda do sprawdznia poprawnosci kwadratu na podstawie wspolrzednych pierwszej komorki
	 */
	
	private boolean checkSquere(int [][] array, int fromRow, int fromColumn) {
		Set<Integer> tempSet = new HashSet<>();
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				if(!tempSet.add(array[r + fromRow][c + fromColumn])) {
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * Metoda sprawdza kwadraty wyznaczone przez dane z tablicy helper
	 */
	
	private boolean checkSqueres(int[][] array) {
		int[] helper = {0,3,6};
		for(int i : helper) {
			for(int j : helper) {
				if(!checkSquere(array, i,j)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean verifyBoard(int sheetIndex) {
		int[][] boardArray = getArrayFromSheet(sheetIndex);
		return (checkRows(boardArray) && checkColumns(boardArray) && checkSqueres(boardArray));
	}
}

class App2 {
	public static void main(String[] arg) {
		try {
			Workbook wb = WorkbookFactory.create(new File("sudoku.xlsx"));
			SudokuBoardChecker sbc = new SudokuBoardChecker(wb);
			for(int i = 0; i <= 6; i++) {
				if(!sbc.verifyBoardStructure(i)) {
					System.out.println("Sheet no: " + (i + 1) + " not completed");																																																																																			
				} else if(!sbc.verifyBoard(i)) {
					System.out.println("Sheet no: " + (i + 1) + " incorrect");
				} else {
					System.out.println("Sheet no: " + (i + 1) + " correct");
				}
			}
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}

	}
}

