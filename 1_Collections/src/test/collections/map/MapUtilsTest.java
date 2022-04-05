package test.collections.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import main.collections.map.MapUtils;

class MapUtilsTest {

	@Test
	void getBiggestValueTest(){
		final var stringToIntegerMap = Map.of("b", 1, "c", 2, "a", 3);
		final var expectedResult = 3;

		final var actualResult = MapUtils.getBiggestValue(stringToIntegerMap);

		assertEquals(expectedResult,actualResult);

	}

	@Test
	void sortKeysOfMapDescendingTest() {
		// Arrange
		final var stringToIntegerMap = Map.of("b", 1, "c", 2, "a", 3);
		final var expectedResult = List.of("c", "b", "a");

		// Act
		final var actualResult = MapUtils.sortKeysOfMapDescending(stringToIntegerMap);

		// Assert
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void mergeKeysAndValuesTest(){
		final var intMap = Map.of(1,1, 2,2, 3,3 ,4,4 ,5,5);
		final var expectedResult = Set.of(2,4,6,8,10);

		final var actualResult = MapUtils.mergeKeysAndValues(intMap);

		assertEquals(expectedResult,actualResult);
	}
}
