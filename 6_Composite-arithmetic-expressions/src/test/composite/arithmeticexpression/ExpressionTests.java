package test.composite.arithmeticexpression;

import main.composite.arithmeticexpression.Addition;
import main.composite.arithmeticexpression.Constant;
import main.composite.arithmeticexpression.Division;
import main.composite.arithmeticexpression.Expression;
import main.composite.arithmeticexpression.Multiplication;
import main.composite.arithmeticexpression.Subtraction;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ExpressionTests {

	private static final Logger LOGGER = Logger.getLogger(ExpressionTests.class.getName());

	@Test
	void testAddition(){
		Expression a = new Constant(5);
		Expression b = new Constant(5);
		Expression c = new Constant(8);
		Expression d = new Addition(b,c);
		Expression addiTest = new Addition(a,d);

		long expectedResult = 10;
		long actualResult = addiTest.evaluate();

		assertEquals(expectedResult,actualResult);
	}
	
	@Test
	void testSubtraction() {
		//Arrange
		Expression a = new Constant(10);
		Expression b = new Constant(5);
		Expression c = new Constant(2);
		Expression d = new Subtraction(b,c);
		Expression subTest = new Subtraction(a,d);
		
		final var expectedResult = 7;
		
		//Act
		
		final var actualResult = subTest.evaluate();
		
		//Assert
		assertEquals(expectedResult, actualResult);
		
	}
	
	@Test
	void testMultiplication() {
		//Arrange
		Expression a = new Constant(6);
		Expression b = new Constant(2);
		Expression c = new Constant(2);
		Expression d = new Multiplication(a,b);
		Expression multiTest = new Multiplication(c,d);
		
		final var expectedResult = 24;
		
		//Act
		final var actualResult = multiTest.evaluate();
		
		//Assert
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	void testDivision() {
		//Arrange
		Expression a = new Constant(60);
		Expression b = new Constant(10);
		Expression c = new Constant(2);
		Expression d = new Division(a,b);
		Expression diviTest = new Division(d,c);
		
		final var expectedResult = 3;
		
		//Act
		final var actualResult = diviTest.evaluate();
		
		//Assert
		assertEquals(expectedResult, actualResult);
	}


	/*@Test
	void test() {
		fail("Nothing implemented yet");
	}*/
}
