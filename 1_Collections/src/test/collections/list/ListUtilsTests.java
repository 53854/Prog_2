package test.collections.list;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import main.collections.list.ListUtils;

class ListUtilsTests {

	@Test
	void getFifthElementTest(){
		final var integerList = List.of(1,2,3,4,5,6);
		final var expectedRestult = 5;

		final var actualResult = ListUtils.getFifthElement(integerList);

		assertEquals(expectedRestult, actualResult);
	}

	@Test
	void sortAscendingTest() {
		// Arrange
		final var integerList = List.of(3, 1, 2, 2);
		final var expectedResult = List.of(1, 2, 2, 3);

		// Act
		final var actualResult = ListUtils.sortAscending(integerList);

		// Assert
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void getMostFrequentElementTest(){
		final var integerList =  List.of(1,2,3,5,1,5,6,1,7,8,9);
		final var expectedResult = 1;

		final var actualResult = ListUtils.getMostFrequentElement(integerList);

		assertEquals(expectedResult,actualResult);
	}

}
