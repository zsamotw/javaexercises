package pl.edu.agh.java.exercises.arrays;

import java.util.HashMap;
import java.util.Set;

/**
 * Consider the leftmost and righmost appearances of some value in an array.
 * We'll say that the "span" is the number of elements between the two
 * inclusive. A single value has a span of 1. Returns the largest span found in
 * the given array. (Efficiency is not a priority.)
 * 
 * Przyjmijmy, że odstęp "span" danej wartości w tablicy to liczba elementów
 * pomiędzy pierwszym i ostatniem jej wystąpieniem, włączając je same. Jeśli
 * wartość pojawia się w tablicy tylko raz, jej "span" jest równe 1. Metoda
 * maxSpan powinna zwracać największy istniejący odstęp w zadanej tablicy.
 * 
 * @see http://codingbat.com/prob/p189576
 */
public class ArraySpan {
	public static int maxSpan(int[] array) {
		HashMap<Integer, Integer> map = arrayToMap(array);
		Set<Integer> setOfLengths = map.keySet();
		int max = 0;
		for(int el : setOfLengths) {
			if(el > max) max = el;
		}
		return map.get(max);
		
	}
	
	private static HashMap<Integer, Integer> arrayToMap(int[] array) {
		HashMap<Integer, Integer> res = new HashMap<Integer, Integer>();
		for(int i = 0; i < array.length; i++) {
			int elem = array[i];
			int len = getSpan(array, elem);
			res.put(len, elem);
		}
		return res;
	}
	
	private static int getSpan(int[] array, int elem) {
		int step = 1;
		int res = 0;
		for(int e : array) {
			step += 1;
			if(elem == e && res == 0) {
				step = 0;
				res = 1;
			}
			if(elem == e) {
				res = res + step;
				step = 0;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] in = {1,2,3,2,1};
		System.out.println(getSpan(in,1));
		
	}
}
















