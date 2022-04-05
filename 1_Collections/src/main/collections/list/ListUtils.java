package main.collections.list;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListUtils {

	/**
	 * Get the 5th element of the given List
	 */
	public static Integer getFifthElement(final List<Integer> integerList) {
		return integerList.get(4);
	}

	/**
	 * Sort the given List in ascending order.
	 */
	public static List<Integer> sortAscending(final List<Integer> integerList) {
		return integerList
				.stream()
				.sorted()
				.toList();
	}

	/**
	 * Get the element from the given List, that is in it most frequently.
	 */
	public static Integer getMostFrequentElement(final List<Integer> integerList) {
		int maxValue = 0;
		int maxCount = 0;

		int i,j;

		for(i = 0; i < integerList.size(); i++){
			int count = 0;
			for (j = 0; j < integerList.size(); j++){
				if(integerList.get(j).equals(integerList.get(i))){
					++count;
				}

				if(count > maxCount){
					maxCount = count;
					maxValue = integerList.get(i);
				}
			}
		}

		return maxValue;
	}

}
