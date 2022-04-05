package main.collections.map;

import java.util.*;

public class MapUtils {

	/**
	 * Get the biggest value of the given Map.
	 */
	public static Integer getBiggestValue(final Map<String, Integer> stringToIntegerMap) {
		return Collections.max(stringToIntegerMap.values());
	}

	/**
	 * Return the keys of the given Map sorted in descending order.
	 */
	public static List<String> sortKeysOfMapDescending(final Map<String, Integer> stringToIntegerMap) {
		return stringToIntegerMap.
				keySet().
				stream().
				sorted(Comparator.reverseOrder()).
				toList();
	}

	/**
	 * Convert the keys and values of the given Map into one Set.
	 */
	public static Set<Integer> mergeKeysAndValues(final Map<Integer, Integer> integerMap) {
		HashSet<Integer> result = new HashSet<Integer>();

		for (Map.Entry<Integer, Integer> entry : integerMap.entrySet()) {
			result.add(entry.getValue() + entry.getKey());
		}

		return result;
	}
}
