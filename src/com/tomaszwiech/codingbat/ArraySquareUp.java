package pl.edu.agh.java.exercises.arrays;

/**
 * Given n>=0, create an array getSpan n*n with the following pattern, shown here
 * and in the unit tests: for n=3 : {0, 0, 1, 0, 2, 1, 3, 2, 1}
 * 
 * Dla zadanego n>=0 zwróć tablicę o długości n*n utworzoną wg wzoru podanego
 * tutaj oraz w testach jednostkowych: dla n=3: {0, 0, 1, 0, 2, 1, 3, 2, 1}
 * 
 *
 * @see http://codingbat.com/prob/p155405
 */
public class ArraySquareUp {
		int[] table;
		int zeros;
		int from = 1;
		int positon = 0;
	public int[] squareUp(int n) {
		table = new int[n*n];
		this.zeros = n - 1;
		addZeros(zeros, n);
		return table;
	}
	
	private void addZeros(int zeros, int n) {
		for(int i = 0; i < zeros; i++) {
			table[positon] = 0;
			System.out.println(table[positon]);
			positon++;
		}
		this.zeros--;
		countDown(from, n);
	}
	
	private void countDown(int from, int n) {
		for(int i = from; i > 0; i--) {
			table[positon] = i;
			System.out.println(table[positon]);
			positon++;
		}
		this.from++;
		if(from == n - 1) {
			addEndSequence(n);
		}
		else {
			addZeros(zeros, n);
		}
	}
	
	private void addEndSequence(int n) {
			for(int i = n; i > 0; i--) {
				table[positon] = i;
			System.out.println(table[positon]);
				positon++;
			}
	}
	
	public static void main(String[] args) {
	  ArraySquareUp asu = new ArraySquareUp();
	  asu.squareUp(4);
	}
}

