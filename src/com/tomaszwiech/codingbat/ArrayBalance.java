package pl.edu.agh.java.exercises.arrays;

/**
 * Given an array, return true if there is a place to split the array so that
 * the sum of the numbers on one side is equal to the sum of the numbers on the
 * other side.
 * 
 * Dla zadanej tablicy zwróć true, jeśli da się tak rozmieścić jej elementy, że
 * suma jej elementów w lewej i w prawej połowie będzie równa.
 * 
 * @see http://codingbat.com/eprob/p158767
 */
public class ArrayBalance {
	public boolean canBalance(int[] array) {
		final int leftBound = 0;
		final int rightBound = array.length - 1;
		for(int i = 1; i < array.length - 1; i++) {
			int sumLeft = sum(array, leftBound, i);
			int sumRight = sum(array, i, rightBound);
			if(sumLeft == sumRight) return true;
		}
		return false;
	}
	
	private int sum(int[] array, int from, int to) {
		int sumAll = 0;
		for(int i = from; i < to; i++) {
			sumAll += array[i];
		}
		return sumAll;
	}
}

