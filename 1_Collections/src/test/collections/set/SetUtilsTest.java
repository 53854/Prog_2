package test.collections.set;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import main.collections.set.SetUtils;

public class SetUtilsTest {

	@Test
	void getBiggestIntegerTest(){
		final var integerSet = Set.of(1,2,3,9,5,6,7);
		final var expectedResult = 9;

		final var actualRestult = SetUtils.getBiggestInteger(integerSet);

		assertEquals(expectedResult,actualRestult);
	}

	@Test
	void intersectionTest() {
		// Arrange
		final var set1 = Set.of(1, 2, 3);
		final var set2 = Set.of(2, 1);
		final var expectedResult = Set.of(1,2);

		// Act
		final var actualResult = SetUtils.intersection(set1, set2);

		// Assert
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void cartesianProductTest(){
		final var set1 = Set.of(1, 2, 3);
		final var set2 = Set.of(2, 1);
		final var expectedResult = Set.of(List.of(1, 2), List.of(1, 1), List.of(2, 2), List.of(2, 1), List.of(3, 2),
				List.of(3, 1));

		final var actualResult = SetUtils.cartesianProduct(set1, set2);

		assertEquals(expectedResult, actualResult);
	}
}
