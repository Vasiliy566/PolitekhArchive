package com.test_ex.politekh;
import java.util.ArrayList;
import java.util.List;

public class Task1 {

	static void addSortedElementToList(int num, List<Integer> numbers) {
		int i = 0;
		for (; i < numbers.size() && num > numbers.get(i); i++) {
		}
	    numbers.add(i, num);
	}

	public static void main(String[] args) {
		System.out.println("input string: " + args[0]);
		List<Integer> numbers = new ArrayList<Integer>();
		int tmp = 0;
		System.out.print("exist numbers: ");
		for (int i = 0; i < args[0].length(); i++) {
			if (Character.isDigit(args[0].charAt(i))) {
				while (i < args[0].length() && (int) args[0].charAt(i) <= 57 && (int) args[0].charAt(i) >= 48) {
					tmp = tmp * 10 + Character.getNumericValue(args[0].charAt(i));
					i++;
				}
				System.out.print(tmp + " ");
				addSortedElementToList(tmp, numbers);
				tmp = 0;
			}
		}
		System.out.println();
		System.out.print("sorted numbers: ");
		for(int i = 0; i < numbers.size(); i ++) {System.out.print(numbers.get(i) + " ");}
	}

}
