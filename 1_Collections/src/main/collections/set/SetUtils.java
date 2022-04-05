package main.collections.set;

import java.util.*;

public class SetUtils {

	/**
	 * Get the biggest element of the given Set.
	 */
	public static Integer getBiggestInteger(final Set<Integer> integerSet) {
		return Collections.max(integerSet);
	}

	/**
	 * Compute the intersection of the given Sets.
	 */
	public static Set<Integer> intersection(final Set<Integer> set1, final Set<Integer> set2) {
		Set<Integer> intersect = new HashSet<Integer>(set1);
		intersect.retainAll(set2);
		return intersect;
	}

	/**
	 * Compute the cartesian product of the given Sets.
	 */
	public static Set<List<Integer>> cartesianProduct(final Set<Integer> set1, final Set<Integer> set2) {

		List<List<Integer>> cp = new ArrayList<>();

		for (int i:set1) {
			for (int j: set2) {
				cp.add(List.of(i,j));
			}
		}

		return new HashSet<>(cp);
	}

}
