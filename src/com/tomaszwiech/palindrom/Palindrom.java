package com.tomaszwiech.palindrom;

public class Palindrom {

	
	static boolean check(String text) {
		String res = text.replaceAll(" ", "").toLowerCase();
		int len = res.length();
		for(int i = 0; i <= (len / 2); i++) {
			if(!(res.charAt(i) == res.charAt((len -1) - i))) {
				return false;
			}
		}
		return true;
	}
}

class App {
	public static void main(String[] args) {
		System.out.println(Palindrom.check("I car komedia i demokraci"));
	}
}
